package io.geekidea.boot.common.controller;

import cn.hutool.core.util.RandomUtil;
import io.geekidea.boot.common.service.impl.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private SendEmailService emailService;

    @GetMapping("/sendSimpleEmail")
    public String sendSimpleEmail() throws Exception{
        String receiver = "67217277@qq.com";
        String subject = "Springbooto 邮箱测试";
        String lockCode = RandomUtil.randomNumbers(5);
        String Content = "asdasdasdasdasas\n"+lockCode;
        emailService.sendEmail(receiver,subject,Content);
        return "发送成功:"+receiver;
    }
}
