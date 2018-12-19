package com.godzilla.cn.godzilla.mapper;

import com.godzilla.cn.godzilla.bean.Article;
import com.godzilla.cn.godzilla.bean.JiraTask;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface JiraTaskMapper {
    @Insert("INSERT INTO jiratask (issuekey,description,reaction,summary,reporter,status,issuetype,title,state,createtime) values (#{issuekey},#{description},#{reaction},#{summary},#{reporter},#{status},#{issueType},#{title},#{state},#{createTime})")
    int add(JiraTask jiraTask) throws Exception;

    @Delete("UPDATE jiratask SET state = '1' WHERE id = #{id}")
    int delete(long id);

    @Select("SELECT * FROM jiratask WHERE id = #{id}")
    JiraTask findJiraTaskById(@Param("id") long id);

    @Select("SELECT * FROM jiratask WHERE state = '0'")
    List<JiraTask> findAllList();

}
