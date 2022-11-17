package com.example.learn.test.controller;

import com.alibaba.excel.EasyExcel;
import com.example.learn.controller.BaseController;
import com.example.learn.test.converter.ExcelListener;
import com.example.learn.test.data.AccountExport;
import com.example.learn.util.excel.CommonCellStyleStrategy;
import com.example.learn.util.excel.CustomCellWriteHandler;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TestExportController
 * @Description 演示导入导出，无实际用处
 * @Date 2022/11/17 09:55
 * @Author pluto
 */
@RestController
public class TestExportController extends BaseController {

    /**
     * 导出
     */
    @GetMapping("test/export")
    public void testExport(HttpServletResponse response) throws IOException {
        List<AccountExport> list = Lists.newArrayList();
        AccountExport export1 = new AccountExport();
        export1.setId(1);
        export1.setGender(1);
        export1.setBirthday(new Date());
        export1.setUsername("张三");
        list.add(export1);
        AccountExport export2 = new AccountExport();
        export2.setId(2);
        export2.setGender(1);
        export2.setBirthday(new Date());
        export2.setUsername("李四");
        list.add(export2);
        AccountExport export3 = new AccountExport();
        export3.setId(3);
        export3.setGender(0);
        export3.setBirthday(new Date());
        export3.setUsername("王五");
        list.add(export3);
        EasyExcel.write(writeWorkbookToFile(response, "测试excel").getOutputStream(), AccountExport.class)
                .sheet("成员列表")
                // 注册通用格式策略
                .registerWriteHandler(CommonCellStyleStrategy.getHorizontalCellStyleStrategy())
                // 设置自定义格式策略
                .registerWriteHandler(new CustomCellWriteHandler())
                .doWrite(list);

    }

    /**
     * 从Excel导入会员列表
     */
    @PostMapping("/test/import")
    @ResponseBody
    public void importMemberList(@RequestPart("file") MultipartFile file) throws IOException {
        // 方式一：同步读取，将解析结果返回，比如返回List<Member>，业务再进行相应的数据集中处理
        List<AccountExport> list = EasyExcel.read(file.getInputStream())
                .head(AccountExport.class)
                .sheet()
                .doReadSync();
        for (AccountExport accountExport : list) {
            System.out.println(accountExport);
        }

        // 方式二：对照doReadSync()方法的是最后调用doRead()方法，不进行结果返回，而是在ExcelListener中进行一条条数据的处理；
        EasyExcel.read(file.getInputStream(), AccountExport.class, new ExcelListener()).sheet().doRead();
    }
}
