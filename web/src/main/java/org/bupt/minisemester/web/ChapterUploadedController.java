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

    @GetMapping("/novel")
    public List<ChapterUploaded> getAllChapters() {
        return chapterUploadedService.getAllChapters();
    }

    @GetMapping("/novel/{id}")
    public List<ChapterUploaded> getChapterByNovelId(@PathVariable int id) {
        return chapterUploadedService.getChapterByNid(id);
    }
    @PostMapping
    public void saveChapter(@RequestBody ChapterUploaded chapter) {
        chapterUploadedService.saveChapter(chapter);
    }

    @PutMapping
    public void updateChapter(@RequestBody ChapterUploaded chapter) {
        chapterUploadedService.updateChapter(chapter);
    }

    @DeleteMapping("/{id}")
    public void deleteChapter(@PathVariable Integer id) {
        chapterUploadedService.deleteChapter(id);
    }
}
