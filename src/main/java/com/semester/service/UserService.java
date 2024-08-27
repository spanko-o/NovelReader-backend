package com.semester.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.semester.mapper.usernameMapper;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private usernameMapper usernamemapper;

    public String getUsernameById(String id){
        String username = usernamemapper.getUsername(id);
        if(username == null){
            System.out.println("查找失败");
            return null;
        }
        return username;
    }

    public String getPassword(String username){
        String password = usernamemapper.getPassword(username);
        if(password == null){
            return null;
        }
        return password;
    }

    public boolean insert(String username, String password, String name) {
        String userId = UUID.randomUUID().toString(); // 生成唯一的 userId
        int rowsAffected = usernamemapper.insert(userId, username, password, name);
        return rowsAffected > 0;
    }
}
