package com.wj.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @author jun.wang
 * @title: Sender
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/7/24 9:42
 */

@Component
public class Sender implements RabbitTemplate.ConfirmCallback {

    @Autowired
    @Qualifier("confirmRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {

    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            System.out.println(correlationData.getId());
        }
        else {
            System.out.println(s);
        }
    }

    public void send(String exchangeName, String routeKey, Object msg) {
        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString().replace("-", ""));
        rabbitTemplate.convertAndSend(exchangeName, routeKey, msg, correlationData);
    }
}
