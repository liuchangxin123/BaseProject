package com.example.learn.test.data;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.example.learn.test.converter.GenderConverter;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName AccountExport
 * @Description 账号导出
 * @Date 2022/11/17 09:53
 * @Author pluto
 */

/**
 * @ExcelIgnore：忽略掉该字段；
 * @ExcelProperty("用户名")：设置该列的名称为”用户名“；
 * @ColumnWidth(20)：设置表格列的宽度为20；
 * @DateTimeFormat("yyyy-MM-dd")：按照指定的格式对日期进行格式化；
 * @ExcelProperty(value = "性别", converter = GenderConverter.class)：自定义内容转换器，类似枚举的实现，将“男”、“女”转换成“0”、“1”的数值。
 */
@Data
public class AccountExport {

    /**
     * EasyExcel使用：导出时忽略该字段
     */
    @ExcelIgnore
    private Integer id;

    @ExcelProperty("用户名")
    @ColumnWidth(20)
    private String username;

    /**
     * EasyExcel使用：日期的格式化
     */
    @ColumnWidth(20)
    @ExcelProperty("出生日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date birthday;

    /**
     * EasyExcel使用：自定义转换器
     */
    @ColumnWidth(10)
    @ExcelProperty(value = "性别", converter = GenderConverter.class)
    private Integer gender;
}
