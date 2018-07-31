package com.rabbitmq.rabbitmqdemo.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component("amqpAsynFailedMessageHandler")
public class AmqpAsynFailedMessageHandler {

    private static Logger logger = LoggerFactory.getLogger(AmqpAsynFailedMessageHandler.class);

    @ServiceActivator
    public Message<?> handleFailedMessage(Exception e) {
        logger.error("异步消息消费异常", e);
        throw new AmqpRejectAndDontRequeueException(e.getMessage(), e.getCause());
    }
}
