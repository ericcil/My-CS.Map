package com.vcredit.config.exception;

import lombok.Getter;

/**
 * @Author chenyubin
 * @Date 2018/7/24
 */
public class BusinessException extends RuntimeException{

    @Getter
    private String errorCode;
    @Getter
    private String errorMsg;


    public BusinessException(ExceptionCodeEnum exEnum){
        if(exEnum == null){
            new BusinessException(ExceptionCodeEnum.DEFAULT_CODE);
            return;
        }
        new BusinessException(exEnum.getErrorCode(),exEnum.getErrorMsg());
    }

    public BusinessException(String errorCode,String msg){
        this.errorCode = errorCode;
        this.errorMsg = msg;
    }

}
