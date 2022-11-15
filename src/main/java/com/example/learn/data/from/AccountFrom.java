package com.example.learn.data.from;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName AccountFrom
 * @Description 新增账号信息
 * @Date 2022/11/15 10:27
 * @Author pluto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiOperation("新增账号信息")
public class AccountFrom {

    @ApiModelProperty("姓名")

    private String name;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("是否禁用 0否 1是")
    private Boolean disable;
}
