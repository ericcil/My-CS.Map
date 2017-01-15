package com.cyb.log.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 函数调用日志 记录模型
 * 
 * @author yubin chen
 * @date 2017年1月15日
 */
public class LogParamModel implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 类名 
	 */
	private String clazzName;
	/**
	 * 方法名
	 */
	private String methodName;
	/**
	 * 参数
	 */
	private String param;
	/**
	 * 方法返回值
	 */
	private String result;
	/**
	 * 被调用时间（假设等于开始执行时间）
	 */
	private Date callTime;
	/**
	 * 执行时间
	 */
	private Long runTime;
	
	public LogParamModel(){}
	
	public LogParamModel logClazzName(String clazzName){
		this.clazzName = clazzName;
		return this;
	}
	
	public LogParamModel logMethod(String methodName){
		this.methodName = methodName;
		return this;
	}
	
	public LogParamModel logParam(String param){
		this.param = param;
		return this;
	}
	
	public LogParamModel logResult(String result){
		this.result = result;
		return this;
	}
	
	public LogParamModel logCallTime(Date callTime){
		this.callTime = callTime;
		return this;
	}
	
	public LogParamModel logRunTime(Long runTime){
		this.runTime = runTime;
		return this;
	}
	
	public String getClazzName() {
		return clazzName;
	}
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Date getCallTime() {
		return callTime;
	}
	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}
	public Long getRunTime() {
		return runTime;
	}
	public void setRunTime(Long runTime) {
		this.runTime = runTime;
	}
	
}
