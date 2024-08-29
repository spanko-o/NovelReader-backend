package org.bupt.minisemester.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "book_uploaded")
@TableName("book_uploaded")
public class BookUploaded implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId("book_id")
    private int Bookid;

    @NotEmpty(message = "标题不能为空")
    @TableField("title")
    private String title;

    @TableField("description")
    private String description;

    @TableField("author")
    private String author;

    @TableField("noveltype")
    private String noveltype;

    @OneToMany(mappedBy = "book_id")
    private List<ChapterUploaded> chapterlist;

    // Getters and Setters
    public int getBookid() {
        return Bookid;
    }

    public void setBookid(int bookid) {
        Bookid = bookid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNoveltype() {
        return noveltype;
    }

    public void setNoveltype(String noveltype) {
        this.noveltype = noveltype;
    }
}
