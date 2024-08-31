package org.bupt.minisemester.service;

import org.bupt.minisemester.dao.entity.*;
import org.bupt.minisemester.dao.mapper.NovelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class NovelServiceGlobal {

    @Autowired
    private NovelMapper novelmapper;

    public void addBookUploaded(String title, String description, String author, String noveltype) {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("标题不能为空");
        }

        Novel novel = new Novel();
        novel.setTitle(title);
        novel.setDescription(description);
        novel.setAuthor(author);
        novel.setNoveltype(noveltype);
        this.novelmapper.insertBookUploaded(title, description, author, noveltype);
    }
}
