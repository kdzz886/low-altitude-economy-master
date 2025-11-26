package com.__4c_platform.platform.entity;

import lombok.Data; // 导入注解
import java.util.Date;

@Data // 添加这个注解
public class User {
    private Long id;
    private String username;
    private String password;
    private String companyName;
    private String email;
    private String phone;
    private Date createTime;
    private Date updateTime;
    // 删掉手写的getter和setter
}