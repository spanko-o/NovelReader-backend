package org.bupt.minisemester.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface userMapper {
    @Select("SELECT password FROM User WHERE username=#{username} LIMIT 1")
    String getPassword(@Param("username") String username);

    @Insert("INSERT INTO User (user_id, username, password) VALUES (#{user_id}, #{username}, #{password})")
    boolean insert(@Param("user_id")String userId, @Param("username") String username, @Param("password") String password);
}
