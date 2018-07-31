package com.rabbitmq.rabbitmqdemo.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BaseResponseDTO {
    @JSONField(name = "code")
    private int code;

    @JSONField(name = "error_msg")
    private String errorMsg;
    
    @JSONField(name = "detail")
    private String detail;

    @JSONField(name = "request_id")
    private String requestId;

    @JSONField(name = "data")
    private Object data;

    public BaseResponseDTO(){

    }

    public BaseResponseDTO(int code, String errorMsg, String detail, String requestId, Object data) {
        super();
        this.code = code;
        this.errorMsg = errorMsg;
        this.detail = detail;
        this.requestId = requestId;
        this.data = data;
    }

    public BaseResponseDTO(int code, String errorMsg, String detail, Object data) {
        super();
        this.code = code;
        this.errorMsg = errorMsg;
        this.detail = detail;
        this.requestId = "";
        this.data = data;
    }

    public BaseResponseDTO(Object data) {
        super();
        this.code = 200;
        this.errorMsg = "";
        this.detail = "";
        this.requestId = "";
        this.data = data;
    }

    public BaseResponseDTO(String errorMsg) {
        super();
        this.code = 500;
        this.errorMsg = errorMsg;
        this.detail = errorMsg;
        this.requestId = "";
    }
    
    public BaseResponseDTO(int code, String errorMsg) {
        super();
        this.code = code;
        this.errorMsg = errorMsg;
        this.detail = errorMsg;
        this.requestId = "";
    }

    public BaseResponseDTO(int code, String errorMsg, String detail) {
        super();
        this.code = code;
        this.errorMsg = errorMsg;
        this.detail = detail;
        this.requestId = "";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
