
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class LuceneIndexSuperQuery {
    public void highLightQuery() throws IOException, ParseException, InvalidTokenOffsetsException {
        //创建目录对象
        Directory directory = FSDirectory.open(new File("indexDir"));

        //通过目录读取对象，动态的读取目录对象
        IndexReader indexReader = DirectoryReader.open(directory);

        //这才是真正的查询对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        //执行查询两个参数，query为查询条件对象，第二个参数为选取命中结果的前n条
        QueryParser queryParser = new QueryParser("title", new IKAnalyzer());

        Query query = queryParser.parse("跳槽");

        //查询返回前N条文档的一个集合
        TopDocs topDocs = indexSearcher.search(query, 5);

        //真实命中的数量
        int totalHits = topDocs.totalHits;

        System.out.println("总共命中了 " + totalHits + " 条");

        //得分文档数组
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;


        //准备高亮工具

        Formatter formatter = new SimpleHTMLFormatter("<em>", "</em>");

        Scorer scorer = new QueryScorer(query);

        Highlighter highlighter = new Highlighter(formatter, scorer);

        if (null != scoreDocs && 0 != scoreDocs.length) {
            System.out.println("真实查询到的：" + scoreDocs.length);


            for (ScoreDoc scoreDoc : scoreDocs) {
                System.out.println("=======================================");
                //文档的得分
                float score = scoreDoc.score;
                //文档编号
                int docID = scoreDoc.doc;
                //获取得分文档
                Document doc = indexSearcher.doc(docID);

                String id = doc.get("id");
                String titleValue = doc.get("title");

                String highLightTitle = highlighter.getBestFragment(new IKAnalyzer(), "title", titleValue);

                System.out.println("得分：" + score + " id: " + doc.get("id") + " title:" + highLightTitle);
            }

        }
    }


    public void sortQuery() throws IOException, ParseException, InvalidTokenOffsetsException {
        //创建目录对象
        Directory directory = FSDirectory.open(new File("indexDir"));

        //通过目录读取对象，动态的读取目录对象
        IndexReader indexReader = DirectoryReader.open(directory);

        //这才是真正的查询对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        //执行查询两个参数，query为查询条件对象，第二个参数为选取命中结果的前n条
        QueryParser queryParser = new QueryParser("title", new IKAnalyzer());

        Query query = queryParser.parse("谷歌");

        //创建排序添加对象
        Sort sort = new Sort(new SortField("id", SortField.Type.LONG, false));

        //查询返回前N条文档的一个集合
        TopDocs topDocs = indexSearcher.search(query, 5, sort);

        //真实命中的数量
        int totalHits = topDocs.totalHits;

        System.out.println("总共命中了 " + totalHits + " 条");

        //得分文档数组
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        if (null != scoreDocs && 0 != scoreDocs.length) {
            System.out.println("真实查询到的：" + scoreDocs.length);


            for (ScoreDoc scoreDoc : scoreDocs) {
                System.out.println("=======================================");
                //文档的得分
                float score = scoreDoc.score;
                //文档编号
                int docID = scoreDoc.doc;
                //获取得分文档
                Document doc = indexSearcher.doc(docID);

                String id = doc.get("id");
                String titleValue = doc.get("title");

                System.out.println("得分：" + score + " id: " + id + " title:" + titleValue);
            }

        }
    }

    public void pageQuery(int currentPage, int page_size) throws IOException, ParseException, InvalidTokenOffsetsException {
        int start = (currentPage - 1)* page_size;
        int end = currentPage * page_size;


        //创建目录对象
        Directory directory = FSDirectory.open(new File("indexDir"));

        //通过目录读取对象，动态的读取目录对象
        IndexReader indexReader = DirectoryReader.open(directory);

        //这才是真正的查询对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        //执行查询两个参数，query为查询条件对象，第二个参数为选取命中结果的前n条
        QueryParser queryParser = new QueryParser("title", new IKAnalyzer());

        Query query = queryParser.parse("谷歌");

        //查询返回前N条文档的一个集合
        TopDocs topDocs = indexSearcher.search(query, 5);

        //真实命中的数量
        int totalHits = topDocs.totalHits;

        System.out.println("总共命中了 " + totalHits + " 条");

        //得分文档数组
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;



        if (null != scoreDocs && 0 != scoreDocs.length) {
            System.out.println("真实查询到的：" + scoreDocs.length);

            int total = scoreDocs.length;

            int totalPages = total% page_size ==0?total/ page_size :total/ page_size +1;

            if (totalPages<currentPage){
                System.out.println("太大了没那么多页，总共："+totalPages);
                return;
            }else if (totalPages==currentPage){
                end = total;
            }

            for (int i = start; i < end; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                System.out.println("=======================================");
                //文档的得分
                float score = scoreDoc.score;
                //文档编号
                int docID = scoreDoc.doc;
                //获取得分文档
                Document doc = indexSearcher.doc(docID);

                String id = doc.get("id");
                String titleValue = doc.get("title");

                System.out.println("得分：" + score + " id: " + id + " title:" + titleValue);
            }

        }
    }
}
