package com.vcredit.controller.interception.base;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
@Slf4j
public class ExceptionAdvice /*extends ResponseEntityExceptionHandler*/ {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    private static final String ERROR_CODE_PARAM_VALID = "E0001";
    private static final String ERROR_CODE_BUSINESS = "E0002";


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultBody> handleCustomException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        String code = null;
        log.error("request error,info:",ex);
        //TODO 业务异常处理,异常体系待补充

        if(ex instanceof MethodArgumentNotValidException){
            return new ResponseEntity( paramValidExceptionHandle(ex) ,HttpStatus.OK);
        }

        ResultBody body = new ResultBody().error("E0002",ex.getMessage());
        return new ResponseEntity(body,HttpStatus.OK);
    }


    /**
     * 处理Hibernate Validator抛出的异常
     * @param ex
     * @return
     */
    private ResultBody paramValidExceptionHandle(Exception ex){
        MethodArgumentNotValidException validEx = (MethodArgumentNotValidException) ex;
        BindingResult exResult = validEx.getBindingResult();
        String msg = "参数校验异常";
        if( exResult.getErrorCount() > 0 ){
            ObjectError objError = exResult.getAllErrors().get(0);
            if( !StringUtils.isEmpty(objError.getDefaultMessage()) ){
                msg = objError.getDefaultMessage();
            }
        }
        return new ResultBody().error(ERROR_CODE_PARAM_VALID,msg);
    }
}
