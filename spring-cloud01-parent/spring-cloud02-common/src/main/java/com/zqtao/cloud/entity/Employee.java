package com.zqtao.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zqtao
 * @description: 测试环境：Employee 实体
 * @date: 2020/7/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    /**
     * 雇员 id
     */
    private Integer empId;
    /**
     * 雇员名称
     */
    private String empName;
    /**
     * 雇员薪资
     */
    private Double empSalary;
}

