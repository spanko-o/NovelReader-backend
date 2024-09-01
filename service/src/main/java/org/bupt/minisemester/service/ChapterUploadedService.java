package org.bupt.minisemester.service;

import org.bupt.minisemester.dao.entity.ChapterUploaded;
import org.bupt.minisemester.dao.mapper.ChapterUploadedMapper;
import org.bupt.minisemester.service.ChapterUploadedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ChapterUploadedService {
    ChapterUploaded getChapterById(int id);
    List<ChapterUploaded> getAllChapters();
    void saveChapter(ChapterUploaded chapter);
    void updateChapter(ChapterUploaded chapter);
    void deleteChapter(int id);
}

