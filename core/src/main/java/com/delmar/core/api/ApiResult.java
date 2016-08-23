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
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean           success;
    private int               code;
    private String            message;
    private String            requestId;

    private T                 data;

    private ApiResult() {
        this.requestId = UUID.randomUUID().toString().replaceAll("\\-", "");
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.success = true;
        apiResult.code = StatusCode.SUCCESS.getCode();
        apiResult.message = StatusCode.SUCCESS.getMessage();
        apiResult.data = data;
        return apiResult;
    }

    public static <T> ApiResult<T> fail(int code, String message) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.success = false;
        apiResult.code = code;
        apiResult.message = message;
        return apiResult;
    }
}
