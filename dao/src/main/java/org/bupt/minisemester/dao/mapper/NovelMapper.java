package org.bupt.minisemester.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface NovelMapper {

    @Select("SELECT title, `desc`, author, noveltype, picture FROM novel WHERE id = #{id}")
    Map<String, String> selectNovelById(@Param("id") Integer id);
    // 书籍详情页

    @Select("SELECT id,title, picture FROM novel ORDER BY RAND() LIMIT 12")
    List<Map<String, String>> selectNovelForMain();
    // 用于主页随机显示书籍

    // 用于建立索引
    @Select("SELECT * FROM novel")
    List<Map<String, String>> selectNovel();
}
