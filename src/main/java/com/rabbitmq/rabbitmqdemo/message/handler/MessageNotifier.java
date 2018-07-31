package com.rabbitmq.rabbitmqdemo.message.handler;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author ASUS
 */
@Component
public class MessageNotifier {
    private static final Logger logger = LoggerFactory.getLogger(MessageNotifier.class);

    @Autowired
    @Qualifier("message_send_channel")
    private MessageChannel messageSendChannel;

    public void sendMessage(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","wyz");
        logger.info("发送信息，message = {}", jsonObject);
        Message<?> message = MessageBuilder.withPayload(jsonObject.toString()).build();
        messageSendChannel.send(message);
    }

}
