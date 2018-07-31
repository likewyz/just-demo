package com.rabbitmq.rabbitmqdemo.common.exception;

import com.rabbitmq.rabbitmqdemo.common.constants.BaseCode;
import com.rabbitmq.rabbitmqdemo.common.dto.BaseResponseDTO;
import com.rabbitmq.rabbitmqdemo.common.util.FastJsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * @author xujun
 */
@Component
public class FailedMessageHandler {
    private static final Logger logger = LoggerFactory.getLogger(FailedMessageHandler.class);

    @ServiceActivator
    public Message<?> handleFailedMessage(Exception e) {
        BaseException baseException = getFirstBaseException(e);
        if (Objects.isNull(baseException)) {
            logger.error("未知异常", e);
            baseException = new BaseException(BaseCode.UNKNOWN);
        }
        return MessageBuilder.withPayload(buildRespMap(baseException)).build();
    }

    private BaseException getFirstBaseException(Throwable ex) {
        while (Objects.nonNull(ex)) {
            if (ex instanceof BaseException) {
                break;
            }
            ex = ex.getCause();
        }
        return Objects.nonNull(ex) ? (BaseException) ex : null;
    }

    private String buildRespMap(BaseException baseException) {
        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        baseResponseDTO.setCode(baseException.getError().getCode());
        baseResponseDTO.setErrorMsg(
                Optional.ofNullable(baseException.getLocalizedMessage())
                        .filter(StringUtils::isNotBlank)
                        .orElse(baseException.getError().getMessage()));
        baseResponseDTO.setDetail(baseException.getLocalizedMessage());

        return FastJsonUtils.toJSONString(baseResponseDTO);
    }

}
