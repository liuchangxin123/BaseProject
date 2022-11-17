package com.example.learn.test.controller;

import com.alibaba.excel.EasyExcel;
import com.example.learn.config.JsonResult;
import com.example.learn.config.RabbitTopicConfig;
import com.example.learn.controller.BaseController;
import com.example.learn.data.pojo.Login;
import com.example.learn.test.converter.ExcelListener;
import com.example.learn.test.data.AccountExport;
import com.example.learn.util.TokenUtil;
import com.example.learn.util.excel.CommonCellStyleStrategy;
import com.example.learn.util.excel.CustomCellWriteHandler;
import com.google.common.collect.Lists;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName TestController
 * @Description 测试所用，无实际用处
 * @Date 2022/11/17 09:55
 * @Author pluto
 */
@RestController
public class TestController extends BaseController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

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

    @GetMapping("/test/sendMessage")
    public String sendTopicMessage(){
        String messageId = UUID.randomUUID().toString();
        String messageData = "test message,hello!";
        String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Map<String,Object> map = new HashMap<>();
        map.put("messageId",messageId);
        map.put("data",messageData);
        map.put("current",current);
        rabbitTemplate.convertAndSend(RabbitTopicConfig.EXCHANGE_TOPICS_INFORM, "inform.email.a", map, new CorrelationData(UUID.randomUUID().toString()));
        return "ok";
    }

    @RabbitListener(queues = RabbitTopicConfig.QUEUE_INFORM_EMAIL)
    @RabbitHandler
    public void process(Map map , Channel channel, Message message) throws IOException {
        System.out.println("消费者接收到的消息是"+map.toString());
        //由于配置设置了手动应答，所以这里要进行一个手动应答。注意：如果设置了自动应答，这里又进行手动应答，会出现double ack，那么程序会报错。
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @PostMapping("/test/login")
    public JsonResult login(@RequestParam("username") String username,@RequestParam("password") String password) {
        if (username.equals("pluto") && password.equals("1234qwer")) {
            Login login = new Login();
            login.setUsername("pluto");
            login.setPassword("1234qwer");
            String sign = TokenUtil.sign(login);
            return JsonResult.ok(sign);
        }
        return JsonResult.error("用户名密码不正确");
    }

    @GetMapping("/test/find")
    public JsonResult find() {
        return JsonResult.ok("执行查询方法");
    }
}
