package org.bupt.minisemester.web;

import org.bupt.minisemester.common.jwt.JwtToken;
import org.bupt.minisemester.service.BookUploadedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookUploadedController {

    @Autowired
    private BookUploadedService bookUploadedService;

    @PostMapping("/add")
    public String addBookUploaded(@RequestParam String title, @RequestParam String desc, @RequestParam String author, @RequestParam String noveltype) {
        bookUploadedService.addBookUploaded(title, desc, author, noveltype);
        return "Book uploaded successfully!";
    }
}
