package com.godzilla.cn.godzilla.service.serviceImp;

import com.godzilla.cn.godzilla.bean.Article;
import com.godzilla.cn.godzilla.mapper.ArticleMapper;
import com.godzilla.cn.godzilla.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArticleServiceImp implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public int add(String name, String detail,String nodes) {
        return articleMapper.add(name, detail,nodes);
    }
    public int update(String name, String detail,String nodes, Long id) {
        return articleMapper.update(name, detail,nodes, id);
    }
    public int delete(Long id) {
        return articleMapper.delete(id);
    }
    public Article findArticleById(Long id) {
        return articleMapper.findArticleById(id);
    }
    public List<Article> findArticleList() {
        return articleMapper.findArticleList();
    }

    public List<Article> findArticleTitleList(){
        return  articleMapper.findArticleTitleList();
    }

}


