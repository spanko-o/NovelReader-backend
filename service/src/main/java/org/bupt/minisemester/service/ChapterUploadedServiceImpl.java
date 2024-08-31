package org.bupt.minisemester.service;

import org.bupt.minisemester.dao.entity.ChapterUploaded;
import org.bupt.minisemester.dao.mapper.ChapterUploadedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterUploadedServiceImpl implements ChapterUploadedService {

    @Autowired
    private ChapterUploadedMapper chapterUploadedMapper;

    @Override
    public ChapterUploaded getChapterById(int id) {
        return chapterUploadedMapper.selectById(id);
    }

    @Override
    public List<ChapterUploaded> getAllChapters() {
        return chapterUploadedMapper.selectList(null);
    }

    @Override
    public void saveChapter(ChapterUploaded chapter) {
        chapterUploadedMapper.insert(chapter);
    }

    @Override
    public void updateChapter(ChapterUploaded chapter) {
        chapterUploadedMapper.updateById(chapter);
    }

    @Override
    public void deleteChapter(int cid) {
        chapterUploadedMapper.deleteById(cid);
    }

    @Override
    public void checkBookById(int cid) {
        try{
            int index = chapterUploadedMapper.countByBookId(cid);
            if (index == 0) {
                throw new noSuchBook("No book found");
            }
        }
        catch(Exception e){}
    }
}
