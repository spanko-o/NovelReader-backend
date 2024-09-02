package org.bupt.minisemester.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Entity
@Table(name = "chapter_uploaded")
@TableName("chapter_uploaded")
public class ChapterUploaded implements Serializable {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId("cid")
    private int cid;

    @Getter
    @Setter
    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    @ManyToOne
    @JoinColumn(name="novel_id")
    private Novel novelId;
}
