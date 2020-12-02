package com.jxlt.udic.riskcontrol.website.exception;

import com.jxlt.udic.riskcontrol.website.enums.ResponseResultCodeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {
    private int code = ResponseResultCodeEnum.ERROR.getCode();

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ResponseResultCodeEnum enumObj) {
        super(enumObj.getMsg());
        this.code = enumObj.getCode();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
