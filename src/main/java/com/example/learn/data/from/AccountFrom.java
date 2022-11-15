package com.example.learn.data.from;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName AccountFrom
 * @Description TODO
 * @Date 2022/11/15 10:27
 * @Author pluto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountFrom {

    private String name;

    private Integer age;

    private String phone;

    private String address;

    private Boolean disable;
}
