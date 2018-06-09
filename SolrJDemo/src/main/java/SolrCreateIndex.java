import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import pojo.Item;

import java.io.IOException;

/**
 * Created by Abyss on 2018/6/8.
 * description:
 */
public class SolrCreateIndex {

    public void createIndex() throws IOException, SolrServerException {
        HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/core1");
        SolrInputDocument solrInputFields = new SolrInputDocument();
        solrInputFields.addField("id", "500");
        solrInputFields.addField("title", "500块都不给me");
        solrInputFields.addField("price", "50000");
        solrServer.add(solrInputFields);
        solrServer.commit();
    }

    public void createIndexBean() throws IOException, SolrServerException {
        HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/core1");
        Item item = new Item();
        item.setId("501");
        item.setPrice(50100);
        item.setTitle("给你501块");
        solrServer.addBean(item);
        solrServer.commit();
    }

    public void createIndexForCloudSolr() throws IOException, SolrServerException {
        CloudSolrServer solrServer = new CloudSolrServer("192.168.72.101:2181,192.168.72.102:2181,192.168.72.103:2181");
        solrServer.setDefaultCollection("Collection1");
        Item item = new Item();
        item.setId("501");
        item.setPrice(50100);
        item.setTitle("给你501块");
        solrServer.addBean(item);
        solrServer.commit();
    }
}
