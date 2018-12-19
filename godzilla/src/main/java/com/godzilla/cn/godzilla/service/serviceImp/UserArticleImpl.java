package com.godzilla.cn.godzilla.service.serviceImp;

import com.godzilla.cn.godzilla.bean.Article;
import com.godzilla.cn.godzilla.bean.UserArticle;
import com.godzilla.cn.godzilla.mapper.UserArticleMapper;
import com.godzilla.cn.godzilla.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserArticleImpl implements UserArticleService {
    @Autowired
    private UserArticleMapper userArticleMapper;

    @Override
    public int add(long userId, long articleId, String read, String state) {

        return userArticleMapper.add(userId,articleId,read,state);
    }

    @Override
    public int update(long userId, long articleId, String read, String state, long id) {
        return userArticleMapper.update(userId,articleId,read,state,id);
    }

    @Override
    public int delete(long id) {
        return userArticleMapper.delete(id);
    }

    @Override
    public UserArticle findAllById(long id) {

        return userArticleMapper.findAllById(id);
    }

    @Override
    public List<UserArticle> findAllByuserId(long userid) {
        return userArticleMapper.findAllByuserId(userid);
    }

    @Override
    public int modifyRead(long id) {
        return userArticleMapper.modifyRead(id);
    }
}
