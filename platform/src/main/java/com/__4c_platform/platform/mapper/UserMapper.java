// mapper/UserMapper.java
package com.__4c_platform.platform.mapper;

import com.__4c_platform.platform.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user_table(username, password, company_name, email, phone) VALUES(#{username}, #{password}, #{companyName}, #{email}, #{phone})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Select("SELECT * FROM user_table WHERE username = #{username}")
    User selectByUsername(String username);

    @Select("SELECT * FROM user_table WHERE id = #{id}")
    User selectById(Long id);
}