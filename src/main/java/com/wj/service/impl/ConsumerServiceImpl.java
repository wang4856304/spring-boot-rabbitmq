package com.wj.service.impl;

import com.wj.service.ConsumerService;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author jun.wang
 * @title: ConsumerServiceImpl
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/30 16:20
 */

@Service
public class ConsumerServiceImpl implements ConsumerService {

    private static Logger logger = LoggerFactory.getLogger(ConsumerServiceImpl.class);

    @RabbitListener(queues = {"bind", "test"})
    @Override
    public void consumer(Message message) {
        //手动处理应答，避免消费端失败无限ack循环
        try {
            String msg = new String(message.getBody());
            int i = Integer.valueOf(msg);
            System.out.println(msg);
        }
        catch (Exception e) {
            logger.error("error message", e);
        }
    }

    public static void main(String args[]) {
        System.out.println(String.format("queue name=%s, message=%s", "a", "b"));
    }
}
