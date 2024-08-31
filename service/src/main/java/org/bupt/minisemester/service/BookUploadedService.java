package org.bupt.minisemester.service;

import org.bupt.minisemester.dao.entity.*;
import org.bupt.minisemester.dao.mapper.BookUploadedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BookUploadedService {

    @Autowired
    private BookUploadedMapper bookUploadedMapper;

    public void addBookUploaded(String title, String description, String author, String noveltype) {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("标题不能为空");
        }

        Novel novel = new Novel();
        novel.setTitle(title);
        novel.setDescription(description);
        novel.setAuthor(author);
        novel.setNoveltype(noveltype);
        bookUploadedMapper.insertBookUploaded(title, description, author, noveltype);
    }
}
