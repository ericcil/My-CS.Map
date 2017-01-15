package com.cyb.log.annotation;

import java.util.Date;
import java.util.Properties;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cyb.log.JacksonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

@Aspect
@Component
public class LogParamAop {

	private static final String AOPCLAZZ = "@annotation(com.cyb.log.annotation.LogParam)";
	//"execution(String tests(*))"
	
	private static Properties prop;//预备配置参数
	
	private ThreadLocal<Date> beginTime = new ThreadLocal<Date>();
	
	Logger logger = LoggerFactory.getLogger(LogParamAop.class);
	
	@Before(AOPCLAZZ)
	public void beforeExec(JoinPoint joinPoint){
		beginTime.set(new Date());
	}
	
	@AfterReturning(value=AOPCLAZZ,returning="result")
	public void afterExec(JoinPoint joinPoint,Object result){
		long endTime = System.currentTimeMillis();
		Date begin = beginTime.get();
		long runtime = endTime - begin.getTime();
		
		MethodSignature ms=(MethodSignature) joinPoint.getSignature();
        String methodName = ms.getMethod().getName();
        Object[] params = joinPoint.getArgs();
        
        
        
        
        
        String str = "wu";
        if(result instanceof String){
        	str = (String)result;
        }
        logger.info("===={}",str);
        logger.info(">>>{}",methodName);
	}
	
	
	public String write2Json(Object obj){
		try{
			return JacksonUtil.obj2Json(obj);
		}catch(JsonProcessingException e){
			logger.error("write object error",e);
			return "";
		}
	}
}
