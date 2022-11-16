package com.example.learn.data.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName Good
 * @Description 商品表
 * @Date 2022/11/16 17:44
 * @Author pluto
 */
@Data
@TableName("good")
public class Good {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String sn;

    private String name;

    private String alias;

    private String picture;

    private Integer stock;

    private BigDecimal costPrice;

    private BigDecimal price;

    private Boolean status;

    private String operate;

}
