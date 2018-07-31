package com.rabbitmq.rabbitmqdemo.message.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author wyz
 */
@Component
public class MessageActivator {
    private static final Logger logger = LoggerFactory.getLogger(MessageActivator.class);

    public void dealMessage(String message){
        logger.info("开始处理消息，message={}", message);
        JSONObject jsonObject = JSON.parseObject(message);
        logger.info("消息值:{}", jsonObject.getString("name"));
    }


}
