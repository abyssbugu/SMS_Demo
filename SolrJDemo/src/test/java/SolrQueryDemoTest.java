import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.Before;
import org.junit.Test;
import pojo.Item;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Abyss on 2018/6/8.
 * description:
 */
public class SolrQueryDemoTest {

    private SolrQueryDemo solr;

    @Before
    public void setUp() throws Exception {
        solr = new SolrQueryDemo();
    }

    @Test
    public void query() throws IOException, SolrServerException {
        solr.query(new SolrQuery("title:手机"));
    }

    @Test
    public void queryBean() throws IOException, SolrServerException {
        solr.queryBean(new SolrQuery("title:手机"));
    }

    /**
     * 范围查询
     */
    @Test
    public void queryRange() throws IOException, SolrServerException {
        solr.queryBean(new SolrQuery("price:[399900 TO 499900]"));
    }

    /**
     * 相似度查询
     */
    @Test
    public void querySimilar() throws IOException, SolrServerException {
        solr.query(new SolrQuery("title:iphnoe~2"));
    }

    /**
     * 排序查询
     */
    @Test
    public void querySort() throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery("title:手机");
        solrQuery.setSort("price", SolrQuery.ORDER.desc);
        solr.queryBean(solrQuery);
    }

    /**
     * 分页查询
     */
    @Test
    public void testPagedQuery() throws Exception {
        // 准备分页参数：
        int currentPage = 2;// 当前页
        final int PAGE_SIZE = 5;// 每页显示条数
        int start = (currentPage - 1) * PAGE_SIZE;// 当前页的起始条数

        // 连接Solr服务器
        HttpSolrServer server = new HttpSolrServer("http://localhost:8080/solr/core2");
        // 创建查询对象：SolrQuery
        SolrQuery query = new SolrQuery("title:手机");
        // 设置分页信息到查询对象中
        query.setStart(start);// 设置起始条数
        query.setRows(5);// 设置每页条数

        // 执行查询,获取响应
        QueryResponse response = server.query(query);
        // 获取查询结果,指定实体类的类型，返回实体类的集合
        List<Item> list = response.getBeans(Item.class);
        // 打印搜索结果信息
        System.out.println("当前第" + currentPage + "页，本页共" + list.size() + "条数据。");
        // 遍历集合
        for (Item item : list) {
            System.out.println(item);
        }
    }

    // 查询索引并且高亮：
    @Test
    public void testHighlightingQuery() throws Exception {
        // 连接Solr服务器,需要指定地址：我们可以直接从浏览器复制地址。要删除#
        HttpSolrServer server = new HttpSolrServer("http://localhost:8080/solr/core2");
        // 创建查询对象
        SolrQuery query = new SolrQuery("title:手机");
        // 设置高亮的标签
        query.setHighlightSimplePre("<em>");
        query.setHighlightSimplePost("</em>");
        // 设置高亮字段
        query.addHighlightField("title");

        // 查询
        QueryResponse response = server.query(query);
        // 解析高亮响应结果,是一个Map
        // 外层的Map：它的key是文档的id,value是一个Map，存放的是这个文档的其它高亮字段
        // 内存的Map：是其它高亮字段，key是其它字段的名称，value是这个字段的值，这个值是一个List
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        // 获取非高亮结果
        List<Item> list = response.getBeans(Item.class);
        for (Item item : list) {
            String id = item.getId();
            System.out.println("id: " + id);
            System.out.println("title: " + highlighting.get(id).get("title").get(0));
            System.out.println("price: " + item.getPrice());
        }
    }

}