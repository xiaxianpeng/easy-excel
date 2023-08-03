package com.example.easyexcel.model;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {
    @ExcelProperty("ID")
    private Integer id;
    @ExcelProperty("NAME")
    private String name;
    @ExcelProperty("SEX")
    private Integer sex;
    @ExcelProperty("BIRTHDAY")
    private Date birthday;
}
