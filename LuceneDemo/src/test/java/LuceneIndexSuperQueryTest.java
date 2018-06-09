import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Abyss on 2018/6/3.
 * description:
 */
public class LuceneIndexSuperQueryTest {

    private LuceneIndexSuperQuery luceneIndexSuperQuery;

    @Before
    public void setUp() throws Exception {
        luceneIndexSuperQuery = new LuceneIndexSuperQuery();
    }

    @Test
    public void highLightQuery() throws ParseException, InvalidTokenOffsetsException, IOException {
        luceneIndexSuperQuery.highLightQuery();
    }

    @Test
    public void sortQuery() throws ParseException, InvalidTokenOffsetsException, IOException {
        luceneIndexSuperQuery.sortQuery();
    }

    @Test
    public void pageQuery() throws ParseException, InvalidTokenOffsetsException, IOException {
        luceneIndexSuperQuery.pageQuery(3, 2);
    }
}