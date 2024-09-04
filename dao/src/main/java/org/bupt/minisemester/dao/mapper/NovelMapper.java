package org.bupt.minisemester.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.bupt.minisemester.dao.DTO.NovelDTO;
import org.bupt.minisemester.dao.entity.Novel;
import org.bupt.minisemester.dao.entity.User;

import java.util.List;
import java.util.Map;

@Mapper
public interface NovelMapper extends BaseMapper<Novel> {

    @Select("SELECT title, `desc`, author, noveltype, picture FROM novel WHERE id = #{id}")
    Map<String, String> selectNovelById(@Param("id") Integer id);
    // 书籍详情页

    @Select("SELECT id,title, picture FROM novel ORDER BY RAND() LIMIT 12")
    List<Map<String, String>> selectNovelForMain();
    // 用于主页随机显示书籍

    // 用于建立索引
    @Select("SELECT * FROM novel")
    List<Map<String, String>> selectNovel();

    @Select("SELECT author,description,noveltype,picture,title from novel where id = #{nid}")
    List<Map<String, String>> selectNovelDetails(@Param("nid") int nid);

    @Insert("INSERT INTO novel (title, description, author, noveltype, status, user_id) " +
            "VALUES (#{title}, #{description}, #{author}, #{noveltype}, #{status}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "param1.id")
    void insertBookUploaded(@Param("novel") Novel novel,
                            @Param("title") String title,
                            @Param("description") String description,
                            @Param("author") String author,
                            @Param("noveltype") String noveltype,
                            @Param("status") boolean status,
                            @Param("userId") String userId);

    @Select("SELECT * FROM novel WHERE id = #{id}")
    Novel findById(@Param("id") Integer id);

    @Select("select id,title,picture,status from novel where user_id is NOT NULL")
    List<NovelDTO> findAll();

    @Update("UPDATE novel SET title = #{title}, description = #{description}, author = #{author}, noveltype = #{noveltype} WHERE id = #{id}")
    void updateBookinfo(Novel novel);
}
