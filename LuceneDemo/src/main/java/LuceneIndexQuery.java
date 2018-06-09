import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

/**
 * Created by Abyss on 2018/6/3.
 * description:
 */
public class LuceneIndexQuery {

    public void search(Query query) throws IOException {

        FSDirectory indexDir = FSDirectory.open(new File("indexDir"));
        DirectoryReader reader = DirectoryReader.open(indexDir);

        //真正的查询对象
        IndexSearcher indexSearcher = new IndexSearcher(reader);

        //返回前N个文档的集合
        TopDocs topDocs = indexSearcher.search(query, 5);

        //真实命中数量
        int totalHits = topDocs.totalHits;
        System.out.println("总共命中了" + totalHits + "条");

        //得分文档数组
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        if (scoreDocs != null && scoreDocs.length > 0) {
            for (ScoreDoc scoreDoc : scoreDocs) {
                System.out.println("=======================================");
                //文档的得分
                float score = scoreDoc.score;
                //文档编号
                int docID = scoreDoc.doc;
                //获取得分文档
                Document doc = indexSearcher.doc(docID);

                System.out.println("得分：" + score + " id: " + doc.get("id") + " title:" + doc.get("title"));
            }
        }

    }
}
