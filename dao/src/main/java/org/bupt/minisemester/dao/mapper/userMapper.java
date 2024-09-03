package org.bupt.minisemester.dao.mapper;

import org.apache.ibatis.annotations.*;
import org.bupt.minisemester.dao.DTO.NovelDTO;
import org.bupt.minisemester.dao.entity.User;

import java.util.List;

@Mapper
public interface userMapper {
    @Select("SELECT password FROM User WHERE username=#{username} LIMIT 1")
    String getPassword(@Param("username") String username);

    @Insert("INSERT INTO User (user_id, username, password) VALUES (#{user_id}, #{username}, #{password})")
    boolean insert(@Param("user_id")String userId, @Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM user WHERE user_id = #{uid}")
    User findByUid(@Param("uid") String uid);

    @Select("SELECT user_id FROM user WHERE username = #{username}")
    String findUidByUsername(String username);

    @Update("INSERT INTO user_star_novels (user_id, novel_id) VALUES (#{userId}, #{novelId})")
    void addStarNovel(@Param("userId") String userId, @Param("novelId") Integer novelId);

    @Select("select id,title,picture,status from user_star_novels u join novel n on u.novel_id = n.id where u.user_id = #{uid}")
    List<NovelDTO> findNovelByUid(@Param("uid") String uid);

    @Select("select id,title,picture,status from novel where user_id = #{uid}")
    List<NovelDTO> findUploadedNovel(@Param("uid") String uid);
}
