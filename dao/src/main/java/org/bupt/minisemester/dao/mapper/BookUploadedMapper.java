package org.bupt.minisemester.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bupt.minisemester.dao.entity.BookUploaded;

@Mapper
public interface BookUploadedMapper extends BaseMapper<BookUploaded> {

    @Insert("INSERT INTO novel (title, description, author, noveltype) VALUES (#{title}, #{description}, #{author}, #{noveltype})")
    void insertBookUploaded(@Param("title") String title, @Param("description") String description, @Param("author") String author, @Param("noveltype") String noveltype);
}
