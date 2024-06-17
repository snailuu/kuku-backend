package io.geekidea.boot.common.compoent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SendEmail {
    @Resource
    private JavaMailSenderImpl javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmail(String email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("帅哥认证官方");
            message.setText("你好，你是官方认证的大帅哥！");
            message.setTo(email);
            message.setFrom(sender);
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
