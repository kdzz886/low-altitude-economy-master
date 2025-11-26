package com.__4c_platform.platform.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Enterprise {
    private Long id;
    private String name;
    private String description;
    private String tags;
    private String location;
    private String contactPerson;
    private String contactPhone;
    private Date createTime;
    private Date updateTime;
}