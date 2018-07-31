package com.rabbitmq.rabbitmqdemo.controller;

import com.rabbitmq.rabbitmqdemo.message.handler.MessageNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ASUS
 */
@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);


    @Autowired
    private MessageNotifier messageNotifier;

    @RequestMapping(value = "/send",method = RequestMethod.POST)
    @ResponseBody
    public String sendMessage(@RequestBody String payload){
        logger.info("controller层发送消息");
        messageNotifier.sendMessage();
        return "123";
    }

}
