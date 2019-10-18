package com.wj.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @author jun.wang
 * @title: OtherSender
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/7/24 11:34
 */

@Component
public class OtherSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private final static Logger logger = LoggerFactory.getLogger(OtherSender.class);

    @Autowired
    @Qualifier("confirmRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {

    }

    //消息到达交换机回调
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            System.out.println("消息发送成功:" + correlationData.getId());
        }
        else {
            System.out.println(s);
        }
    }

    public void send(String exchangeName, String routeKey, Object msg) {
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(this);
        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString().replace("-", ""));
        rabbitTemplate.convertAndSend(exchangeName, routeKey, msg, correlationData);
    }

    //exchange到queue成功,则不回调return
    //exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.error("message={}, replyCode={}, replyText={}, exchange={}, routingKey={}",
                new String(message.getBody()), replyCode, replyText, exchange, routingKey);
    }
}
