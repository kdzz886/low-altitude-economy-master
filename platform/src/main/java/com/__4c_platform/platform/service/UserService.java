// service/UserService.java
package com.__4c_platform.platform.service;

import com.__4c_platform.platform.entity.User;
import com.__4c_platform.platform.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public boolean register(User user) {
        // 检查用户名是否已存在
        User existingUser = userMapper.selectByUsername(user.getUsername());
        if (existingUser != null) {
            return false; // 用户名已存在
        }
        // 插入新用户
        userMapper.insert(user);
        return true;
    }

    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            return user; // 登录成功
        }
        return null; // 用户名或密码错误
    }
}