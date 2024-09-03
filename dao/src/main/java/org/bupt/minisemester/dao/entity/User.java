package org.bupt.minisemester.dao.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity // JPA 注解
@Table(name = "user") // 指定数据库中的表名为user
@TableName("User") // MyBatis-Plus 注解
public class User {
    @Id
    @TableId(value = "user_id", type = IdType.ASSIGN_UUID)
    private String userId = UUID.randomUUID().toString();

    @NotEmpty(message = "用户名不能为空")
    @TableField("username")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @TableField("password")
    private String password;

    @ElementCollection
    @CollectionTable(name = "user_star_novels", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "novel_id")
    private List<Integer> star_novels = new ArrayList<>();

    public void addStarNovel(Novel novel) {
        if(this.star_novels == null) {
            this.star_novels = new ArrayList<>();
        }
        this.star_novels.add(novel.getId());
    }
}