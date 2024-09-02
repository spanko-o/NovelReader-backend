package org.bupt.minisemester.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.query.UnknownNamedQueryException;

import java.util.List;

@Data
@Entity // JPA 注解
@Table(name = "novel") // 指定数据库中的表名为novel
@TableName("novel") // MyBatis-Plus 注解，表名为小写
public class Novel {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// MyBatis-Plus 注解，设置 id 为自增主键
    private Integer id;

    @Setter
    @NotEmpty(message = "标题不能为空")
    @TableField("title") // 对应数据库中的字段 title
    private String title;

    @Setter
    @TableField("description") // 对应数据库中的字段 description
    private String description;

    @Getter
    @Setter
    @TableField("author") // 对应数据库中的字段 author
    private String author;

    @Setter
    @TableField("noveltype") // 对应数据库中的字段 noveltype
    private String noveltype;

    @TableField("picture") // 对应数据库中的字段 picture
    private String picture;

    @OneToMany(mappedBy = "novelId")
    private List<ChapterUploaded> chapterList;

    @TableField("status") // 说明是官方上传的还是用户上传的：1是官方上传的，0为用户上传的
    private boolean status;

    @ManyToOne
    @JoinColumn(name="userid")
    private User user;

    public Novel(){}
    public Novel(String title, String description, String author, String noveltype, String picture) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.noveltype = noveltype;
        this.picture = picture;
    }
}
