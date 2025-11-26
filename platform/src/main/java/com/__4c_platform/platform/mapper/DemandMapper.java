package com.__4c_platform.platform.mapper;

import com.__4c_platform.platform.entity.Demand;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DemandMapper {

    @Select("SELECT * FROM demand_table")
    List<Demand> selectAll();

    @Select("SELECT * FROM demand_table WHERE publisher_id = #{publisherId}")
    List<Demand> selectByPublisherId(Long publisherId);

    @Insert("INSERT INTO demand_table (title, description, publisher_id, status) " +
            "VALUES (#{title}, #{description}, #{publisherId}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Demand demand);
}