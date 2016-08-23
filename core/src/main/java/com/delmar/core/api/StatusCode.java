package com.delmar.core.api;

import lombok.Getter;

/**
 * Created by admin on 2016/8/23.
 */
public enum StatusCode {
    SUCCESS(0, "成功"),

    WEB_EXCEPTION(10000, "web异常"),
    WEB_EXCEPTION_INPUTS_INVALID(10001, "web入参不合法"),

    BUSINESS_EXCEPTION(20000, "业务异常"),
    BUSINESS_EXCEPTION_INVALID_PARAMETERS(20001, "参数不合法"),
    BUSINESS_EXCEPTION_ENTITY_NOT_FOUND(20002, "未找到主体对象"),
    BUSINESS_EXCEPTION_ILLEGAL_STATUS(20003, "状态不合法"),
    BUSINESS_EXCEPTION_OPERATION_NOT_ALLOWED(20004, "操作不允许"),
    BUSINESS_EXCEPTION_COLLECTION_IS_EMPTY(20005, "数据集合为空"),
    BUSINESS_EXCEPTION_OPERATION_FAILED(20006, "操作失败"),
    BUSINESS_EXCEPTION_OPERATION_NOT_SUPPORTTED(20007, "操作不支持"),
    BUSINESS_EXCEPTION_CONDITION_NOT_SUPPORTTED(20008, "条件不支持"),
    BUSINESS_EXCEPTION_VERIFIED_FAILURE(20009, "验证失败"),
    API_EXCEPTION(30000, "api异常"),

    PERSISTENCE_EXCEPTION(40000, "持久化异常"),

    CONFIGURATION_EXCEPTION(50000, "配置异常"),

    NATIVE_EXCEPTION(60000, "java异常"),
    NATIVE_EXCEPTION_ENTITY_COPY_ERROR(60001, "对象拷贝异常");
    @Getter
    private int code;
    @Getter
    private String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
