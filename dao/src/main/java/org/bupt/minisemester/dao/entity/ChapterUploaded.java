package org.bupt.minisemester.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "chapter_uploaded")
@TableName("chapter_uploaded")
public class ChapterUploaded implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId("id")
    private int id;

    @TableField("title")
    private String title;

    @TableField("ctime")
    private Timestamp ctime;

    @TableField("mtime")
    private Timestamp mtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookUploaded bookUploaded;

    @TableField("content")
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

    public Timestamp getMtime() {
        return mtime;
    }

    public void setMtime(Timestamp mtime) {
        this.mtime = mtime;
    }

    public BookUploaded getBookUploaded() {
        return bookUploaded;
    }

    public void setBookUploaded(BookUploaded bookUploaded) {
        this.bookUploaded = bookUploaded;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
