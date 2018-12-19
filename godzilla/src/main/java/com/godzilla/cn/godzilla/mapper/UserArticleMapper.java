package com.godzilla.cn.godzilla.mapper;

import com.godzilla.cn.godzilla.bean.Article;
import com.godzilla.cn.godzilla.bean.UserArticle;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserArticleMapper {
    @Insert("INSERT INTO userarticle (userId,articleId,isRead,state) VALUES(#{userId},#{articleId},#{isRead},#{state})")
    int add(@Param("userId") long userId, @Param("articleId") long aticleId,@Param("isRead") String isRead, @Param("state") String state);

    @Update("UPDATE userarticle SET userId = #{userId}, articleId = #{articleId}, isRead = #{isRead},state = #{state} WHERE id = #{id}")
    int update(@Param("userId") long userId, @Param("articleId") long aticleId,@Param("isRead") String isRead, @Param("state") String state, @Param("id") Long id);

    @Delete("UPDATE userarticle SET state = '1' WHERE id = #{id}")
    int delete(long id);

    @Select("SELECT id, userId, articleId, isRead,state FROM userarticle WHERE id = #{id}")
    UserArticle findAllById(@Param("id") long id);

    @Select("SELECT u.id, u.userId, u.articleId,e.title as articleTitle, u.isRead, u.state  FROM userarticle u,article e WHERE u.articleId = e.id and u.userId = #{userId} and u.state = '0'")
    List<UserArticle> findAllByuserId(long userId);


    @Delete("UPDATE userarticle SET isRead = '1' WHERE id = #{id}")
    int modifyRead(long id);
}
