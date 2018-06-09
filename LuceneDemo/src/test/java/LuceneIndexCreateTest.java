import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Abyss on 2018/6/3.
 * description:
 */
public class LuceneIndexCreateTest {

    private LuceneIndexCreate luceneIndexCreate;

    @Before
    public void setUp() throws Exception {
        luceneIndexCreate = new LuceneIndexCreate();
    }

    /**
     * 添加索引
     */
    @Test
    public void testCreateIndex() throws IOException {
        luceneIndexCreate.testCreateIndex();
    }

    /**
     * 批量添加索引
     */
    @Test
    public void testCreate2() throws Exception {
        luceneIndexCreate.testCreate2();
    }

    @Test
    public void createIndexBatchNumeric() throws IOException {
        luceneIndexCreate.createIndexBatchNumeric();
    }
}