package org.bupt.minisemester.web;

import org.bupt.minisemester.dao.entity.ChapterUploaded;
import org.bupt.minisemester.service.ChapterUploadedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapters")
public class ChapterUploadedController {

    @Autowired
    private ChapterUploadedService chapterUploadedService;

    @GetMapping("/{id}")
    public ChapterUploaded getChapterById(@PathVariable int id) {
        return chapterUploadedService.getChapterById(id);
    }

    @GetMapping
    public List<ChapterUploaded> getAllChapters() {
        return chapterUploadedService.getAllChapters();
    }

    @PostMapping
    public void saveChapter(@RequestBody ChapterUploaded chapter) {
        chapterUploadedService.checkBookById(chapter.getBook_uploaded());
        chapterUploadedService.saveChapter(chapter);
    }

    @PutMapping
    public void updateChapter(@RequestBody ChapterUploaded chapter) {
        chapterUploadedService.updateChapter(chapter);
    }

    @DeleteMapping("/{id}")
    public void deleteChapter(@PathVariable int id) {
        chapterUploadedService.deleteChapter(id);
    }
}
