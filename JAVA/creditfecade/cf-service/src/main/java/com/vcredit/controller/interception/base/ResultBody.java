package com.vcredit.controller.interception.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 默认使用的统一回参包装
 * @Author chenyubin
 * @Date 2018/7/11
 */
@Getter
@Setter
@Accessors(chain = true)
public class ResultBody<T> {

    public static final Integer CODE_SUCCESS = 1;//请求完成
    public static final Integer CODE_ERROR  = 2;//请求异常
    public static final Integer CODE_FAIL  = 3;//请求失败

    private Integer code;
    private String errorCode;
    private T content;
    private String msg;


    /**
     * 成功包装
     * @param content
     * @return
     */
    public ResultBody<T> success(String msg,T content){
        this.code = CODE_SUCCESS;
        this.content = content;
        this.msg = msg;
        return this;
    }

    /**
     * 失败包装，没有异常码
     * @param msg
     * @param content
     * @return
     */
    public ResultBody<T> fail(String msg,T content){
        this.code = CODE_FAIL;
        this.content = content;
        this.msg = msg;
        return this;
    }

    /**
     * 异常包装，没有实体内容
     * @param errorCode
     * @param msg
     * @return
     */
    public ResultBody<T> error(String errorCode,String msg){
        this.code = CODE_ERROR;
        this.errorCode = errorCode;
        this.msg = msg;
        return this;
    }
}
