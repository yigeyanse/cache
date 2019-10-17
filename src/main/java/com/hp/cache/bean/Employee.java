package com.hp.cache.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

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
public class Employee implements Serializable {

    private Integer id;
    private String lastName;
    private String gender;
    private String email;
    private Integer dId;
}
