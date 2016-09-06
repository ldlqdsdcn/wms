package com.delmar.core.api.def;

import lombok.Getter;

/**
 * Created by admin on 2016/9/6.
 * 错误状态码
 */
@Getter
public enum  ErrorCodes {
    BUSINESS_EXCEPTION(1,"业务逻辑异常");
    private int    code;
    private String message;

    ErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
