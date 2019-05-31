package com.wj;

import com.wj.service.SenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author jun.wang
 * @title: RabbitMQTest
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/30 15:08
 */

@SpringBootTest(classes=RabbitMQApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RabbitMQTest {

    @Autowired
    private SenderService senderService;

    @Test
    public void test() {
        //senderService.send("test", "123");
        senderService.send("exchange-test", "bind.test", "123");
    }
}
