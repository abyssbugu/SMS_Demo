import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Abyss on 2018/6/8.
 * description:
 */
public class SolrCreateIndexTest {

    private SolrCreateIndex solr;

    @Before
    public void setUp() throws Exception {
        solr = new SolrCreateIndex();
    }

    @Test
    public void createIndex() throws IOException, SolrServerException {
        solr.createIndex();
    }

    @Test
    public void createIndexBean() throws IOException, SolrServerException {
        solr.createIndexBean();
    }

    @Test
    public void createIndexForCloudSolr() throws IOException, SolrServerException {
        solr.createIndexForCloudSolr();
    }
}