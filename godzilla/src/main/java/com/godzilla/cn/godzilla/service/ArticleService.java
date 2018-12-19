package com.godzilla.cn.godzilla.service;

import com.godzilla.cn.godzilla.bean.Article;

import java.util.List;

public interface ArticleService {
    int add(String name, String detail,String nodes);
    int update(String name, String detail,String nodes, Long id);
    int delete(Long id);
    Article findArticleById(Long id);
    List<Article> findArticleList();
    List<Article> findArticleTitleList();

}
