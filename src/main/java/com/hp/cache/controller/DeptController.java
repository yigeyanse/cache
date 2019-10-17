package com.hp.cache.controller;

import com.hp.cache.bean.Department;
import com.hp.cache.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaodong
 * @title
 * @date 2019/10/17 10:23
 * @desc
 */
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id){
        Department dept = deptService.getDeptById(id);
        return dept;
    }
}
