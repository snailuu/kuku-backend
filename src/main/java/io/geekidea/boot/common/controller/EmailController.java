package io.geekidea.boot.common.controller;

import io.geekidea.boot.common.compoent.SendEmail;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class EmailController {
    @Resource
    private SendEmail sendEmail;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendEmail/{email}")
    public String sendEmail(@PathVariable("email") String email) {
//        rabbitTemplate.convertAndSend("emailExchange","email",email);
        sendEmail.sendEmail(email);
        return "ok";
    }



}

