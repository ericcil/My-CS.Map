package com.vcredit.controller.interception.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常统一处理类
 * 考虑到大多项目不是restful风格的接口，不采用{@link ResponseEntityExceptionHandler} 提供的默认异常处理
 * @Author chenyubin
 * @Date 2018/7/11
 */
@ControllerAdvice
public class ExceptionAdvice /*extends ResponseEntityExceptionHandler*/ {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultBody> handleCustomException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        String code = null;
        logger.error("request error,info:",ex);
        //TODO 业务异常处理,异常体系待补充

        ResultBody body = new ResultBody().error("XXX",ex.getMessage());
        ResponseEntity<ResultBody> entity = new ResponseEntity(body,HttpStatus.OK);
        return entity;
    }
}
