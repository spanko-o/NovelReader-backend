package org.bupt.minisemester.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.bupt.minisemester.dao.entity.ChapterUploaded;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChapterUploadedMapper extends BaseMapper<ChapterUploaded> {

    @Insert("INSERT INTO chapter_uploaded (title, content, novel_id) VALUES (#{title}, #{content}, #{novelId})")
    void insertChapter(@Param("title") String title, @Param("content") String content, @Param("novelId") Integer novelId);

    @Select("SELECT cid,title FROM chapter_uploaded where novel_id = #{nid}")
    List<ChapterUploaded> selectChapter(@Param("nid") Integer nid);
}
