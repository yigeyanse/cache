package com.hp.cache.mapper;

import com.hp.cache.bean.Employee;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @title:
 * @author: xiaodong
 * @date: 2019/10/16 09:18
 * @desc:
 */
@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM employee WHERE id= #{id}")
    public Employee getEmpById(Integer id);

    @Update("UPDATE employee SET last_name=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} WHERE id=#{id}")
    public void updateEmp(Employee employee);

    @Delete("DELETE FROM employee WHERE id=#{id}")
    public void deleteEmpById(Integer id);

    @Insert("INSERT INTO employee(last_name,email,gender,d_id) VALUES(#{lastName},#{email},#{gender},#{dId})")
    public void insertEmployee(Employee employee);
}
