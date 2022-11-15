package com.example.learn.data.from;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank(message = "名字不能为空")
    private String name;

    @ApiModelProperty("年龄")
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("是否禁用 0否 1是")
    private Boolean disable;
}
