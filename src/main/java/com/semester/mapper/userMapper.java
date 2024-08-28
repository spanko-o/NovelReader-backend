package com.semester.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface userMapper {
    @Select("SELECT password FROM User WHERE username=#{username} LIMIT 1")
    String getPassword(@Param("username") String username);

    @Insert("INSERT INTO User  username, password, name) VALUES (#{userId}, #{username}, #{password}, #{name})")
    boolean insert(@Param("username") String username, @Param("password") String password, @Param("name") String name);
}
