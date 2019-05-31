package com.wj.service.impl;

import com.wj.service.ConsumerService;
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

    @RabbitListener(queues = {"bind", "test"})
    @Override
    public void consumer(String message) {
        System.out.println(message);
    }
}
