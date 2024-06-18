package io.snailuu.boot.common.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendEmailService {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Value("19986452145@163.com")
    private String sender;

    @Async
    public void sendEmail(String receiver, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            System.out.println("邮件发送成功");
        }catch (MailException e) {
            System.out.printf("邮件发送失败");
            log.info(e.toString());
        }
    }
}
