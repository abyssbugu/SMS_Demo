import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Abyss on 2018/6/3.
 * description:
 */
public class LuceneIndexCreate {
    /**
     * 批量创建数字索引
     */
    public void createIndexBatchNumeric() throws IOException {
        //创建目录对象，使用open方法自动匹配最佳实现
        Directory directory = FSDirectory.open(new File("indexDir"));

        //创建文档
        Document document1 = new Document();
        //给文档添加字段
        document1.add(new LongField("id", 1L, Field.Store.YES));
        document1.add(new TextField("title", "谷歌地图之父跳槽facebook", Field.Store.YES));

        //创建文档
        Document document2 = new Document();
        //给文档添加字段
        document2.add(new LongField("id", 2L, Field.Store.YES));
        document2.add(new TextField("title", "谷歌地图之父加盟FaceBook", Field.Store.YES));

        //创建文档
        Document document3 = new Document();
        //给文档添加字段
        document3.add(new LongField("id", 3L, Field.Store.YES));
        document3.add(new TextField("title", "谷歌地图创始人拉斯离开谷歌加盟Facebook", Field.Store.YES));

        //创建文档
        Document document4 = new Document();
        //给文档添加字段
        document4.add(new LongField("id", 4L, Field.Store.YES));
        document4.add(new TextField("title", "谷歌地图之父跳槽Facebook与Wave项目取消有关", Field.Store.YES));

        //创建文档
        Document document5 = new Document();
        //给文档添加字段
        document5.add(new LongField("id", 5L, Field.Store.YES));
        document5.add(new TextField("title", "谷歌地图之父拉斯加盟社交网站Facebook", Field.Store.YES));

        //创建分词器，中文分词器过时，所以使用标准分词器替代
        Analyzer analyzer = new IKAnalyzer();

        //创建索引写入的配置对象,两个参数，分别为使用的lucene版本，以及分词器对象
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);

        //设置下次打开目录的方式为清除之前所有的数据
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        //创建索引写入的对象，两个参数分别表示写道哪里去，以及分词的配置
        IndexWriter indexWriter = new IndexWriter(directory, config);

        List<Document> documents = new ArrayList<>();
        documents.add(document1);
        documents.add(document2);
        documents.add(document3);
        documents.add(document4);
        documents.add(document5);

        //把文档交给索引写入器
        indexWriter.addDocuments(documents);

        //提交
        indexWriter.commit();
        indexWriter.close();

    }

    /**
     * 批量创建索引
     */
    public void testCreate2() throws Exception {
        // 创建文档的集合
        Collection<Document> docs = new ArrayList<>();
        // 创建文档对象
        Document document1 = new Document();
        document1.add(new StringField("id", "1", Field.Store.YES));
        document1.add(new TextField("title", "谷歌地图之父跳槽facebook", Field.Store.YES));
        docs.add(document1);
        // 创建文档对象
        Document document2 = new Document();
        document2.add(new StringField("id", "2", Field.Store.YES));
        document2.add(new TextField("title", "谷歌地图之父加盟FaceBook", Field.Store.YES));
        docs.add(document2);
        // 创建文档对象
        Document document3 = new Document();
        document3.add(new StringField("id", "3", Field.Store.YES));
        document3.add(new TextField("title", "谷歌地图创始人拉斯离开谷歌加盟Facebook", Field.Store.YES));
        docs.add(document3);
        // 创建文档对象
        Document document4 = new Document();
        document4.add(new StringField("id", "4", Field.Store.YES));
        document4.add(new TextField("title", "谷歌地图之父跳槽Facebook与Wave项目取消有关", Field.Store.YES));
        docs.add(document4);
        // 创建文档对象
        Document document5 = new Document();
        document5.add(new StringField("id", "5", Field.Store.YES));
        document5.add(new TextField("title", "谷歌地图之父拉斯加盟社交网站Facebook", Field.Store.YES));
        docs.add(document5);

        // 索引目录类,指定索引在硬盘中的位置
        Directory directory = FSDirectory.open(new File("indexDir"));
        // 引入IK分词器
        Analyzer analyzer = new IKAnalyzer();
        // 索引写出工具的配置对象
        IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, analyzer);
        // 设置打开方式：OpenMode.APPEND 会在索引库的基础上追加新索引。OpenMode.CREATE会先清空原来数据，再提交新的索引
        conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        // 创建索引的写出工具类。参数：索引的目录和配置信息
        IndexWriter indexWriter = new IndexWriter(directory, conf);
        // 把文档集合交给IndexWriter
        indexWriter.addDocuments(docs);
        // 提交
        indexWriter.commit();
        // 关闭
        indexWriter.close();
    }

    public void testCreateIndex() throws IOException {
        //创建目录对象,自动匹配平台
        Directory directory = FSDirectory.open(new File("indexDir"));

        //创建文档对象
        Document document = new Document();

        //为文档添加对象
        document.add(new StringField("id", "1", Field.Store.YES));
        document.add(new TextField("title", "谷歌地图之父,碉堡了", Field.Store.YES));
        document.add(new StringField("id", "2", Field.Store.YES));
        document.add(new TextField("title", "谷歌妈妈挣了很多钱", Field.Store.YES));
        document.add(new StoredField("url", "www.abyss.com"));

        //索引写入配置,中文分词解析器
        IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST
                , new IKAnalyzer(true));
        conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        //文档交给索引写入器
        IndexWriter indexWriter = new IndexWriter(directory
                , conf);
        indexWriter.addDocument(document);

        //提交
        indexWriter.commit();
        indexWriter.close();

    }

}
