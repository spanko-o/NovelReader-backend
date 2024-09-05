package org.bupt.minisemester.service.MainWebpage;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.index.Term;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class searchService {

    // 定义索引文件存储路径
    private final static String IDX_DIR = "service/src/main/resources/index";

    /**
     * 使用Lucene进行索引搜索
     * @param queryStr 查询的字符串
     * @return 返回搜索结果的列表，每个结果为一个Map对象
     * @throws IOException 如果读取索引文件时发生IO错误
     * @throws ParseException 如果查询解析失败
     */
    public List<Map<String, Object>> searchIndex(String queryStr) throws IOException, ParseException {
        // 存储搜索结果的列表
        List<Map<String, Object>> resultList = new ArrayList<>();

        // 打开存储索引的目录
        Directory directory = FSDirectory.open(Paths.get(IDX_DIR));
        // 创建IndexReader对象以读取索引
        IndexReader indexReader = DirectoryReader.open(directory);
        // 创建IndexSearcher对象以执行搜索
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        // 创建Term对象以表示要搜索的字段和内容
        Term term = new Term("title", queryStr);
        // 使用FuzzyQuery进行模糊搜索，这允许搜索包含输入内容的相似词语
        FuzzyQuery fuzzyQuery = new FuzzyQuery(term);

        // 选择要使用的查询对象，这里选择了模糊查询
        Query query = fuzzyQuery;

        // 执行搜索，返回TopDocs对象，包含匹配的文档
        TopDocs docs = indexSearcher.search(query, 10); // 搜索前10条结果

        // 遍历搜索结果，将每个结果转换为Map并添加到resultList中
        for (ScoreDoc scoreDoc : docs.scoreDocs) {
            if (scoreDoc.doc ==0) {
                continue;
            }
            // 通过docId获取Document对象
            Document doc = indexSearcher.doc(scoreDoc.doc);
            // 创建Map对象存储该文档的相关信息
            Map<String, Object> result = new HashMap<>();
            result.put("id", doc.get("id")); // 存储文档的ID
            result.put("title", doc.get("title")); // 存储文档的标题
            result.put("description", doc.get("description")); // 存储文档的描述
            result.put("author", doc.get("author")); // 存储文档的作者
            result.put("noveltype", doc.get("noveltype")); // 存储文档的小说类型
            result.put("picture", doc.get("picture")); // 存储文档的图片路径
            result.put("score", scoreDoc.score); // 存储搜索得分

            // 将结果添加到结果列表中
            resultList.add(result);
        }
        // 关闭IndexReader以释放资源
        indexReader.close();

        // 对结果进行排序，按得分降序排列
        resultList.sort((r1, r2) -> Float.compare((Float) r2.get("score"), (Float) r1.get("score")));

        // 返回最终的搜索结果列表
        return resultList;
    }
}
