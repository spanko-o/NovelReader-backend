package org.bupt.minisemester.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.UUID;

@Data
@TableName("User")
public class User {
    @TableId()
    private String userId = UUID.randomUUID().toString();

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;//默认用户名



}