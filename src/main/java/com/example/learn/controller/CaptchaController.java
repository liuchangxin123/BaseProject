package com.example.learn.controller;

import cn.hutool.core.map.MapUtil;
import com.example.learn.config.JsonResult;
import com.example.learn.util.RedisUtil;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName CaptchaController
 * @Description 验证码管理
 * @Date 2022/11/15 17:10
 * @Author pluto
 */
@RestController
public class CaptchaController {

    private static final String CAPTCHA_KEY = "captcha";

    @Autowired
    Producer producer;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/captcha")
    public JsonResult Captcha() throws IOException {
        String key = UUID.randomUUID().toString();
        String code = producer.createText();

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);

        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";

        String base64Img = str + encoder.encode(outputStream.toByteArray());

        redisUtil.hset(CAPTCHA_KEY, key, code, 120);

        return JsonResult.ok(
                MapUtil.builder()
                        .put("userKey", key)
                        .put("captcherImg", base64Img)
                        .build()
        );
    }
}
