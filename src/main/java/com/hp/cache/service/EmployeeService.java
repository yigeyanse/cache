package com.hp.cache.service;

import com.hp.cache.bean.Employee;
import com.hp.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author xiaodong
 * @title
 * @date 2019/10/16 09:49
 * @desc
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    //@Cacheable(cacheNames = "emp",keyGenerator = "myKeyGenerator",condition = "#a0>1 and #root.methodName eq 'getEmp'",unless = "#a0==2")
    @Cacheable(value = "emp",keyGenerator = "myKeyGenerator")
    public Employee getEmp(Integer id){
        System.out.println("查询"+id+"号员工");
        Employee emp= employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * 运行时机：
     * 1.先调用目标方法
     * 2.将目标方法的结果缓存起来
     * @param employee
     * @return
     */
    @CachePut(value = "emp",key = "#result.id")
    public Employee updateEmp(Employee employee){
        System.out.println("updateEmp:"+employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * allEntries 全部清空缓存 标志 默认false
     * beforeInvocation 默认false 是否在方法之前执行
     * 删除
     * @param id
     */
    @CacheEvict(value = "emp",key = "#id",allEntries = false)
    public void deleteEmp(Integer id){
        System.out.println("deleteEmp:"+id);
        //employeeMapper.deleteEmpById(id);
    }
}
