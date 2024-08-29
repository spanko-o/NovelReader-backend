package org.bupt.minisemester.service;

import org.bupt.minisemester.dao.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final userMapper usernamemapper;

    @Autowired
    public UserService(userMapper usernamemapper) {
        this.usernamemapper = usernamemapper;
    }

    public boolean userExists(String username, String password) {
        String storedPassword = usernamemapper.getPassword(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    public String getPassword(String username) {
        return usernamemapper.getPassword(username);
    }

    public boolean insert(String userId,String username, String password) {
        return usernamemapper.insert(userId,username, password);
    }
}
