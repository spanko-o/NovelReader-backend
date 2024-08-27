package com.semester.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.semester.mapper.usernameMapper;

@Service
public class UserService {

    @Autowired
    private usernameMapper usernamemapper;

    public boolean usernameExists(String username) {
        return usernamemapper.getUsername(username) != null;
    }

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

    public boolean insert(String username, String password,String name) {
        return usernamemapper.insert(username, password, name);
    }
}
