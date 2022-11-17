package com.example.learn.test.converter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.learn.test.data.AccountExport;

/**
 * @ClassName ExcelListener
 * @Description 导入excel是可以导入一条数据对一条数据进行解析操作
 * @Date 2022/11/17 10:55
 * @Author pluto
 */
public class ExcelListener extends AnalysisEventListener<AccountExport> {

    @Override
    public void invoke(AccountExport accountExport, AnalysisContext analysisContext) {
        // do something
        System.out.println("读取account=" + accountExport);
        // do something
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // do something
        System.out.println("读取Excel完毕");
        // do something
    }
}
