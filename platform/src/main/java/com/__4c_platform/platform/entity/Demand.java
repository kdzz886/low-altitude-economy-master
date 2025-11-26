package com.__4c_platform.platform.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Demand {
    private Long id;
    private String title;
    private String description;
    private Long publisherId;  // 发布需求的企业ID
    private String status;     // 需求状态: pending, active, completed, cancelled
    private Date createTime;
    private Date updateTime;
}