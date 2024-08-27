package com.semester.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface usernameMapper {

    @Select("SELECT username FROM User WHERE userId=#{userId} LIMIT 1")
    String getUsername(@Param("userId") String userId);

    @Select("SELECT password FROM User WHERE username=#{username} LIMIT 1")
    String getPassword(@Param("username") String username);

    @Insert("INSERT INTO User (userId, username, password, name) VALUES (#{userId}, #{username}, #{password}, #{name})")
    int insert(@Param("userId") String userId, @Param("username") String username, @Param("password") String password, @Param("name") String name);
}
