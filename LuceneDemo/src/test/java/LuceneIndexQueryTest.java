import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Abyss on 2018/6/3.
 * description:
 */
public class LuceneIndexQueryTest {

    private LuceneIndexQuery luceneIndexQuery;

    @Before
    public void setUp() throws Exception {
        luceneIndexQuery = new LuceneIndexQuery();
    }

   

    @Test
    public void testQueryIndex() throws IOException, ParseException {

        //查询解析器，两个参数，分别为要查询的字段以及对应的分词器
        QueryParser queryParser = new QueryParser("title", new IKAnalyzer());

        //用户查询的最终条件封装  parse方法的内容就是用户的查询内容
        Query query = queryParser.parse("谷歌");

        luceneIndexQuery.search(query);
    }


    @Test
    public void testQueryIndexMultiField() throws IOException, ParseException {


        //查询解析器，两个参数，分别为要查询的字段以及对应的分词器
        MultiFieldQueryParser queryParser = new MultiFieldQueryParser(new String[]{"title", "id"}, new IKAnalyzer());

        //用户查询的最终条件封装  parse方法的内容就是用户的查询内容
        Query query = queryParser.parse("1");//1  口口

        luceneIndexQuery.search(query);


    }

    /**
     * 词条查询，本质就是词条，不需要再次传入分词器
     * */
    @Test
    public void testTermQuery() throws IOException, ParseException {

        TermQuery termQuery = new TermQuery(new Term("title","谷歌"));

        luceneIndexQuery.search(termQuery);
    }


    /**
     * 通配符查询
     * 本质上还是词条查询
     * ?表示任意单个字符
     * *表示任意多个字符
     * */
    @Test
    public void testWildcardQuery() throws IOException, ParseException {

        WildcardQuery wildcardQuery = new WildcardQuery(new Term("title","谷歌*"));

        luceneIndexQuery.search(wildcardQuery);
    }

    /**
     * 有个最大编辑距离的概念，取值范围为0-2，默认2
     * */
    @Test
    public void testFuzzyQuery() throws IOException, ParseException {

        FuzzyQuery fuzzyQuery = new FuzzyQuery(new Term("title","facebooc"),1);

        luceneIndexQuery.search(fuzzyQuery);
    }

    /**
     * 数值范围查询
     * 当字段类型为非String则，查询时候需要把查询的条件转成对应的类型才能做查询
     * "1" --->口口
     * */
    @Test
    public void testNumericQuery() throws IOException, ParseException {
        //5个参数分别表示，字段名称，最小，最大，是否包含最小，是否包含最大
        NumericRangeQuery numericRangeQuery = NumericRangeQuery.newLongRange("id",2L,3L,true,true);

        luceneIndexQuery.search(numericRangeQuery);
    }

    /**
     *
     * 组合查询
     * MUST VS MUST
     * SHOULD　VS  SHOULD
     * MUST  VS  MUST_NOT
     */
    @Test
    public void testBooleanQuery() throws IOException, ParseException {
        BooleanQuery booleanQuery = new BooleanQuery();

        NumericRangeQuery query1 = NumericRangeQuery.newLongRange("id",2L,4L,true,true);
        NumericRangeQuery query2 = NumericRangeQuery.newLongRange("id",3L,5L,true,true);

        booleanQuery.add(query1,BooleanClause.Occur.MUST);
        booleanQuery.add(query2,BooleanClause.Occur.MUST_NOT);

        luceneIndexQuery.search(booleanQuery);

    }
}