package com.hp.cache.service;

import com.hp.cache.bean.Department;
import com.hp.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

/**
 * @author xiaodong
 * @title
 * @date 2019/10/17 10:18
 * @desc
 */
@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    RedisCacheManager redisCacheManager;

    /**
     * 注解方式存取缓存
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "dept")
    public Department getDeptById2(Integer id){
        System.out.println("部门查询"+id);
        Department dept = departmentMapper.getDeptById(id);
        return dept;
    }

    /**
     * 使用编码方式存取缓存
     * @param id
     * @return
     */
    public Department getDeptById(Integer id){
        System.out.println("部门查询"+id);
        Department department = departmentMapper.getDeptById(id);
        Cache dept = redisCacheManager.getCache("dept");
        dept.put("dept:2",department);
        return department;
    }
}
