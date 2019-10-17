package com.hp.cache.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xiaodong
 * @title
 * @date 2019/10/16 09:12
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Department {

    private Integer id;
    private String departmentName;
}
