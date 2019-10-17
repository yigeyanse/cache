package com.hp.cache.controller;

import com.hp.cache.bean.Employee;
import com.hp.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaodong
 * @title
 * @date 2019/10/16 09:51
 * @desc
 */
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        Employee employee =employeeService.getEmp(id);
        return employee;
    }

    @GetMapping("/emp")
    public Employee update(Employee employee){
        Employee emp = employeeService.updateEmp(employee);
        return emp;
    }

    @GetMapping("/delemp")
    public String delete(Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }
}
