package com.godzilla.cn.godzilla.service.serviceImp;

import com.godzilla.cn.godzilla.bean.User;
import com.godzilla.cn.godzilla.mapper.UserMapper;
import com.godzilla.cn.godzilla.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int add(String count, String name, String password) {
        return userMapper.add(count,name,password);
    }

    @Override
    public int update(String count, String name, String password, Long id) {
        return userMapper.update(count,name,password,id);
    }

    @Override
    public int delete(Long id) {
        return userMapper.delete(id);
    }

    @Override
    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }

    @Override
    public List<User> findUserList() {
        return userMapper.findUserList();
    }

    @Override
    public User fingUserbyCountAndPassword(String count, String password) {
        return userMapper.fingUserbyCountAndPassword(count,password);
    }
}
