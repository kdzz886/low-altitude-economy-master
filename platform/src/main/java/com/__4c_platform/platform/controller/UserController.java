// controller/UserController.java
package com.__4c_platform.platform.controller;

import com.__4c_platform.platform.entity.User;
import com.__4c_platform.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        boolean success = userService.register(user);
        result.put("success", success);
        result.put("message", success ? "注册成功" : "用户名已存在");
        return result;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");

        Map<String, Object> result = new HashMap<>();
        User user = userService.login(username, password);
        if (user != null) {
            result.put("success", true);
            result.put("message", "登录成功");
            result.put("data", user);
        } else {
            result.put("success", false);
            result.put("message", "用户名或密码错误");
        }
        return result;
    }
}