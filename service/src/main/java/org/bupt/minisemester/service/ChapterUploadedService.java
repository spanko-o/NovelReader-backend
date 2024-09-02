package org.bupt.minisemester.service;

import org.bupt.minisemester.dao.entity.ChapterUploaded;
import org.bupt.minisemester.dao.mapper.ChapterUploadedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterUploadedService {

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
    public List<ChapterUploaded> getChapterByNid(Integer nid){
        return chapterUploadedMapper.selectChapter(nid);
    }
}
