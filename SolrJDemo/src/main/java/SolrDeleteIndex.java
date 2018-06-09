import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import pojo.Item;

import java.io.IOException;

/**
 * Created by Abyss on 2018/6/8.
 * description:
 */
public class SolrDeleteIndex {


    public void deleteIndexById() throws IOException, SolrServerException {
        HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/core1");
        solrServer.deleteById("501");
        solrServer.commit();
    }

    public void deleteIndexByQuery() throws IOException, SolrServerException {
        HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/core1");
        solrServer.deleteByQuery("title:iphone");
        solrServer.commit();
    }
}
