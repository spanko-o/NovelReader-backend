package org.bupt.minisemester.service;

import org.bupt.minisemester.dao.entity.User;
import org.bupt.minisemester.dao.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static userMapper usermapper;

    @Autowired
    public UserService(userMapper usernamemapper) {
        usermapper = usernamemapper;
    }

    public boolean userExists(String username, String password) {
        String storedPassword = usermapper.getPassword(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    public String getPassword(String username) {
        return usermapper.getPassword(username);
    }

    public boolean insert(String userId,String username, String password) {
        return usermapper.insert(userId,username, password);
    }

    public static User getUserByUid(String userId) {return usermapper.findByUid(userId);}

    public static String getUidByUsername(String username) {
        return usermapper.findUidByUsername(username);
    }
    public static boolean isBookStarred(String userId, int book_id) {
        List<Integer> starredNovels = usermapper.getUserStarredNovels(userId);
        return starredNovels !=null && starredNovels.contains(book_id);
    }
}
