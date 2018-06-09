import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Abyss on 2018/6/8.
 * description:
 */
public class SolrDeleteIndexTest {

    private SolrDeleteIndex solr;

    @Before
    public void setUp() throws Exception {
        solr = new SolrDeleteIndex();
    }

    @Test
    public void deleteIndexById() throws IOException, SolrServerException {
        solr.deleteIndexById();
    }

    @Test
    public void deleteIndexByQuery() throws IOException, SolrServerException {
        solr.deleteIndexByQuery();
    }
}