package com.vcredit.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常代码枚举
 * @Author chenyubin
 * @Date 2018/7/24
 */
@AllArgsConstructor
public enum ExceptionCodeEnum {

    DEFAULT_CODE("E0000","系统异常")
    ,BAD_REQUEST_FORMAT("E0001","请求参数格式异常")
    ,PARAM_VALID("B0001","参数校验未通过");


    @Getter
    private String errorCode;
    @Getter
    private String errorMsg;


}
