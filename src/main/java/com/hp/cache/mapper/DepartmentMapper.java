package com.hp.cache.mapper;

import com.hp.cache.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @title:
 * @author: xiaodong
 * @date: 2019/10/16 09:18
 * @desc:
 */
@Mapper
public interface DepartmentMapper {

    @Select("SELECT * FROM department where id = #{id}")
    Department getDeptById(Integer id);
}
