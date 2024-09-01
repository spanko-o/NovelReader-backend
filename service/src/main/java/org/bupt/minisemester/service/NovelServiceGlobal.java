package org.bupt.minisemester.service;

import jakarta.transaction.Transactional;
import org.bupt.minisemester.dao.entity.*;
import org.bupt.minisemester.dao.mapper.ChapterUploadedMapper;
import org.bupt.minisemester.dao.mapper.NovelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class NovelServiceGlobal {

    @Autowired
    private NovelMapper novelMapper;

    @Autowired
    private ChapterUploadedMapper chapterUploadedMapper;

    @Transactional
    public void importNovel(String novelTitle, String content) {
        try {
            System.out.println("开始导入小说：" + novelTitle);

            Novel novel = new Novel();
            novel.setTitle(novelTitle);
            novelMapper.insert(novel);

            System.out.println("小说" + novelTitle + "已保存到数据库");

            NovelSplitter splitter = new NovelSplitter(content);
            List<NovelSplitter.Chapter> chapters = splitter.split();
            System.out.println("小说已分割为" + chapters.size() + "个章节");

            for (NovelSplitter.Chapter chapter : chapters) {
                ChapterUploaded chapterEntity = new ChapterUploaded();
                chapterEntity.setTitle(chapter.getTitle());
                chapterEntity.setContent(chapter.getContent());
                chapterEntity.setNovel(novel);
                chapterUploadedMapper.insertChapter(chapter.getTitle(),chapter.getContent(),novel.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void addBookUploaded(String title, String description, String author, String noveltype) {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("标题不能为空");
        }

        Novel novel = new Novel();
        novel.setTitle(title);
        novel.setDescription(description);
        novel.setAuthor(author);
        novel.setNoveltype(noveltype);
        this.novelMapper.insertBookUploaded(title, description, author, noveltype);
    }
}
