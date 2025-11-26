// entity/Demand.java
package com.__4c_platform.platform.entity;

import lombok.Data;
import java.util.Date;

@Data  // 这个注解会自动生成getter/setter
public class Demand {
    private Long id;
    private String title;
    private String description;
    private Long publisherId;  // 确保这个字段存在
    private String status;
    private Date createTime;
    private Date updateTime;
}