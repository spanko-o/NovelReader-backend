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

    public ChapterUploaded getChapterById(int id) {
        return chapterUploadedMapper.selectById(id);
    }

    public List<ChapterUploaded> getAllChapters() {
        return chapterUploadedMapper.selectList(null);
    }

    public void saveChapter(ChapterUploaded chapter) {
        chapterUploadedMapper.insert(chapter);
    }

    public void updateChapter(ChapterUploaded chapter) {
        chapterUploadedMapper.updateById(chapter);
    }

    public void deleteChapter(int cid) {
        chapterUploadedMapper.deleteById(cid);
    }

    public List<ChapterUploaded> getChapterByNid(Integer nid){
        return chapterUploadedMapper.selectChapter(nid);
    }
}