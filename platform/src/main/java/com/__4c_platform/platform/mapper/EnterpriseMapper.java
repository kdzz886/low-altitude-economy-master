package com.__4c_platform.platform.mapper;

import com.__4c_platform.platform.entity.Enterprise;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EnterpriseMapper {

    @Select("SELECT * FROM enterprise_table")
    List<Enterprise> selectAll();

    @Select("SELECT * FROM enterprise_table WHERE id = #{id}")
    Enterprise selectById(Long id);

    @Insert("INSERT INTO enterprise_table (name, description, tags, location, contact_person, contact_phone) " +
            "VALUES (#{name}, #{description}, #{tags}, #{location}, #{contactPerson}, #{contactPhone})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Enterprise enterprise);

    @Update("UPDATE enterprise_table SET name=#{name}, description=#{description}, tags=#{tags}, " +
            "location=#{location}, contact_person=#{contactPerson}, contact_phone=#{contactPhone} " +
            "WHERE id=#{id}")
    int update(Enterprise enterprise);

    @Delete("DELETE FROM enterprise_table WHERE id = #{id}")
    int deleteById(Long id);
}