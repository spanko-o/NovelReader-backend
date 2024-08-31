package org.bupt.minisemester.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity // JPA 注解
@Table(name = "novel") // 指定数据库中的表名为novel
@TableName("novel") // MyBatis-Plus 注解，表名为小写
public class Novel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// MyBatis-Plus 注解，设置 id 为自增主键
    private Integer id;

    @NotEmpty(message = "标题不能为空")
    @TableField("title") // 对应数据库中的字段 title
    private String title;

    @TableField("description") // 对应数据库中的字段 description
    private String description;

    @TableField("author") // 对应数据库中的字段 author
    private String author;

    @TableField("noveltype") // 对应数据库中的字段 noveltype
    private String noveltype;

    @TableField("picture") // 对应数据库中的字段 picture
    private String picture;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
}
