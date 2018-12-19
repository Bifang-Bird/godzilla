package com.godzilla.cn.godzilla.service;

import com.godzilla.cn.godzilla.bean.Article;
import com.godzilla.cn.godzilla.bean.UserArticle;

import java.util.List;

public interface UserArticleService {
    int add(long userId,long articleId,String read, String state);
    int update(long userId,long articleId,String read, String state, long id);
    int delete(long id);
    UserArticle findAllById(long id);
    List<UserArticle> findAllByuserId(long id);
    int modifyRead(long id);

}
