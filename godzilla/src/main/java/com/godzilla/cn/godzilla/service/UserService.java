package com.godzilla.cn.godzilla.service;

import com.godzilla.cn.godzilla.bean.Article;
import com.godzilla.cn.godzilla.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    int add(String count, String name, String password);
    int update(String count, String name, String password, Long id);
    int delete(Long id);
    User findUserById(Long id);
    User fingUserbyCountAndPassword(String count,String password);
    List<User> findUserList();

}
