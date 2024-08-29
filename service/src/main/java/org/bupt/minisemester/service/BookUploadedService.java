package org.bupt.minisemester.service;

import org.bupt.minisemester.dao.entity.BookUploaded;
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

        BookUploaded bookUploaded = new BookUploaded();
        bookUploaded.setTitle(title);
        bookUploaded.setDescription(description);
        bookUploaded.setAuthor(author);
        bookUploaded.setNoveltype(noveltype);
        bookUploadedMapper.insertBookUploaded(title, description, author, noveltype);
    }
}
