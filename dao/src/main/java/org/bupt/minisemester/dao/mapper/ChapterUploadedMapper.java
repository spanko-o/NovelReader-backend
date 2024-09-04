package org.bupt.minisemester.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.bupt.minisemester.dao.DTO.ChapterDTO;
import org.bupt.minisemester.dao.entity.ChapterUploaded;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChapterUploadedMapper extends BaseMapper<ChapterUploaded> {
    @Select("select COUNT(*) from novel where id = #{bid}")
    int countByBookId(int bid);

    @Insert("INSERT INTO chapter_uploaded (title, content, novel_id, relative_id) VALUES (#{title}, #{content}, #{novelId},#{relativeId})")
    void insertChapter(@Param("title") String title, @Param("content") String content, @Param("novelId") Integer novelId, @Param("relativeId") Integer relativeId);


    @Select("SELECT relative_id ,title FROM chapter_uploaded where novel_id = #{nid}")
    List<ChapterUploaded> selectChapter(@Param("nid") Integer nid);

    @Select("SELECT title, content FROM chapter_uploaded WHERE novel_id = #{bookId} AND relative_id = #{relativeId}")
    ChapterDTO findChapterByBookIdAndRelativeID(@Param("bookId") Integer bookId, @Param("relativeId") Integer relativeId);
}
