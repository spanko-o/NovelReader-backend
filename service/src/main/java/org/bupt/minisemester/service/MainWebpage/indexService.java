package org.bupt.minisemester.service.MainWebpage;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.bupt.minisemester.dao.mapper.NovelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Service
public class indexService {

    private final static String IDX_DIR = "service/src/main/resources/index";

    @Autowired
    private NovelMapper novelMapper;

    /**
     * 创建索引
     */
    public void createIndex() {
        try {
            // 打开索引目录
            Directory directory = FSDirectory.open(Paths.get(IDX_DIR));
            // 创建分词器
            SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
            // 配置IndexWriter
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter indexWriter = new IndexWriter(directory, config);

            // 清空现有索引
            indexWriter.deleteAll();

            // 从数据库获取数据
            List<Map<String, String>> novelList = novelMapper.selectNovel();
            for (Map<String, String> novel : novelList) {
                // 创建文档
                Document doc = new Document();
                doc.add(new TextField("id", String.valueOf(novel.getOrDefault("id", "")), Field.Store.YES));
                doc.add(new TextField("title", novel.getOrDefault("title", ""), Field.Store.YES));
                doc.add(new TextField("description", novel.getOrDefault("description", ""), Field.Store.YES));
                doc.add(new TextField("author", novel.getOrDefault("author", ""), Field.Store.YES));
                doc.add(new TextField("noveltype", novel.getOrDefault("noveltype", ""), Field.Store.YES));
                doc.add(new TextField("picture", novel.getOrDefault("picture", ""), Field.Store.YES));

                // 将文档添加到索引
                indexWriter.addDocument(doc);
            }

            // 提交更改并关闭索引写入器
            indexWriter.close();
            System.out.println("索引创建完成！");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
