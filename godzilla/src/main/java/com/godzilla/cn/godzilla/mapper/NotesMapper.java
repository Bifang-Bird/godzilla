package com.godzilla.cn.godzilla.mapper;

import com.godzilla.cn.godzilla.bean.Notes;
import com.godzilla.cn.godzilla.bean.User;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

@Mapper
public interface NotesMapper {
    @Insert("INSERT INTO notes (userId,note,content,date) VALUES(#{userId},#{note},#{content},#{date})")
    int add( @Param("userId") long userId,@Param("note") String note, @Param("content") String content, @Param("date") String date);

    @Update("UPDATE notes SET note = #{note}, content = #{content} WHERE id = #{id}")
    int update(@Param("note") String note, @Param("content") String content, @Param("id") long id);

    @Delete("DELETE FROM notes WHERE id = #{id}")
    int delete(long id);

    @Select("SELECT id,userId, note, content, date  FROM  notes  WHERE id = #{id}")
    Notes findNotesById(@Param("id") long id);

    @Select("SELECT id,userId, note, content, date  FROM  notes  WHERE userId = #{userId} order by date desc")
    List<Notes> findNotesByUserId(@Param("userId") long userId);

    @Select("SELECT id,userId, note, content, date  FROM  notes  WHERE userId = '1' UNION select 0,1111 as userId,t.reaction,t.summary, DATE_FORMAT(t.createtime, '%Y年%m月%d日 %h时%i分%s秒') from jiratask t order by date desc")
    List<Notes> findInfoByUserId(@Param("userId") long userId);
}
