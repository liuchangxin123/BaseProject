package com.example.learn.controller;

import com.example.learn.config.JsonResult;
import com.example.learn.service.dao.GoodService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GoodController
 * @Description TODO
 * @Date 2022/11/16 17:48
 * @Author pluto
 */
@RestController
@Api(tags = "商品管理")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @GetMapping("/abc/{sn}")
    public JsonResult findBySn(@PathVariable String sn) {
        return JsonResult.ok( goodService.findBySn(Lists.newArrayList(sn)));
    }
}
