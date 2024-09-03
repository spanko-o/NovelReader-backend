package org.bupt.minisemester.service;

import jakarta.transaction.Transactional;
import org.bupt.minisemester.dao.DTO.ChapterDTO;
import org.bupt.minisemester.dao.entity.*;
import org.bupt.minisemester.dao.mapper.ChapterUploadedMapper;
import org.bupt.minisemester.dao.mapper.NovelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NovelServiceGlobal {

    @Autowired
    private NovelMapper novelMapper;

    @Autowired
    private ChapterUploadedMapper chapterUploadedMapper;

    @Transactional
    public void importNovel(String novelTitle, String content, User user, Boolean status) {
        try {
            System.out.println("开始导入小说：" + novelTitle);

            Novel novel = new Novel();
            novel.setTitle(novelTitle);
            novel.setUser(user);
            novel.setStatus(status);
            novelMapper.insertBookUploaded(novel, novel.getTitle(), novel.getDescription(), novel.getAuthor(), novel.getNoveltype(), novel.isStatus(), user.getUserId());

            NovelSplitter splitter = new NovelSplitter(content);
            List<NovelSplitter.Chapter> chapters = splitter.split();
            System.out.println("小说已分割为" + chapters.size() + "个章节");

            for (NovelSplitter.Chapter chapter : chapters) {
                ChapterUploaded chapterEntity = new ChapterUploaded();
                chapterEntity.setTitle(chapter.getTitle());
                chapterEntity.setContent(chapter.getContent());
                chapterEntity.setNovelId(novel);
                chapterUploadedMapper.insertChapter(chapter.getTitle(), chapter.getContent(), novel.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public ChapterDTO getChapter(Integer novelId, Integer chapterId) {
        return chapterUploadedMapper.findChapterByBookIdAndChapterID(novelId, chapterId);
    }

    public List<Map<String, String>> getBookUploaded(Integer nid) {
        return this.novelMapper.selectNovelDetails(nid);
    }
}
