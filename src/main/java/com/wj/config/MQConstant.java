package com.wj.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jun.wang
 * @title: MQConstant
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/7/23 16:18
 */

@Configuration
public class MQConstant {

    @Bean("test-args")
    public Queue queue() {
        // 此队列必须设置消息超时时间，因为生产环境上是没有此队列的消费者的！！
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 10000);// 设置队列中消息的过期时间：10s
        return new Queue("args-test", true, false, false, args);
    }
}
