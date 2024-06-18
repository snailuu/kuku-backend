package io.snailuu.boot.generator.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootTest
public class mailTest {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("这是邮件主题");
        message.setText("内容信息");
        message.setTo("67217277@qq.com");
        message.setFrom("19986452145@163.com");
        mailSender.send(message);
    }
}
