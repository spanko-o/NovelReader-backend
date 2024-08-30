package org.bupt.minisemester.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.bupt.minisemester.dao.entity.ChapterUploaded;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChapterUploadedMapper extends BaseMapper<ChapterUploaded> {
    @Select("select COUNT(*) from book_uploaded where bookid = #{bid}")
    int countByBookId(int bid);
}
