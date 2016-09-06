package com.delmar.core.api;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by admin on 2016/8/23.
 */
@Getter
@ToString
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean           success;
    private int               code;
    private String            message;
    private String            requestId;

    private T                 data;

    private Result() {
        this.requestId = UUID.randomUUID().toString().replaceAll("\\-", "");
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.success = true;
        result.code = StatusCode.SUCCESS.getCode();
        result.message = StatusCode.SUCCESS.getMessage();
        result.data = data;
        return result;
    }

    public static <T> Result<T> fail(int code, String message) {
        Result<T> result = new Result<>();
        result.success = false;
        result.code = code;
        result.message = message;
        return result;
    }
}
