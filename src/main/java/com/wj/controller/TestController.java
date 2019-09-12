package com.wj.controller;

import com.wj.service.Sender;
import com.wj.service.impl.OtherSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jun.wang
 * @title: TestController
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/7/24 9:56
 */

@RestController
public class TestController {


    @Autowired
    private Sender sender;

    @Autowired
    private OtherSender otherSender;


    @RequestMapping("/test/confirm")
    public Object testConfirm() {
        //sender.send("exchange-test", "bind.test", "123");
        otherSender.send("exchange-test", "bind.test", "123");
        return "success";
    }
}
