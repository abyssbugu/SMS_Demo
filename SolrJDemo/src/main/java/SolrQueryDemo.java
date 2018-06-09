import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import pojo.Item;

import java.io.IOException;
import java.util.List;

/**
 * Created by Abyss on 2018/6/8.
 * description:
 */
public class SolrQueryDemo {


    public void query(SolrQuery solrQuery) throws IOException, SolrServerException {
        HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/core2");
        QueryResponse response = solrServer.query(solrQuery);
        SolrDocumentList results = response.getResults();
        if (results != null && results.size() > 0) {
            for (SolrDocument result : results) {
                System.out.println("id: " + result.getFieldValue("id"));
                System.out.println("titile: " + result.getFieldValue("title"));
                System.out.println("price: " + result.getFieldValue("price"));
            }
        }
    }

    public void queryBean(SolrQuery solrQuery) throws IOException, SolrServerException {
        HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/core2");
        QueryResponse query = solrServer.query(solrQuery);
        List<Item> beans = query.getBeans(Item.class);
        if (beans != null && beans.size()>0) {
            for (Item bean : beans) {
                System.out.println("id: " + bean.getId());
                System.out.println("titile: " + bean.getTitle());
                System.out.println("price: " + bean.getPrice());
            }
        }
    }
}
