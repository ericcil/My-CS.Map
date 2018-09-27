package com.atomec.conveyentity;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

public class ExcelEntityMap {
	
	private FileInputStream excelInputStream;//excel输入流
	private Properties mappingProperties;//excel列和对象属性的映射
	private Class<?> resultClass;//目标转换类型
	private boolean resultFlag; //返回结果标示，true为解析成功，false为解析失败
	private String errorMsg;	//错误信息
	private List<Object> results;	//解析结果 
	
	public ExcelEntityMap(){};
	
	
	public Properties getMappingProperties() {
		return mappingProperties;
	}

	public void setMappingProperties(Properties mappingProperties) {
		this.mappingProperties = mappingProperties;
	}

	public void setResultClass(Class<?> resultClass) {
		this.resultClass = resultClass;
	}

	public FileInputStream getExcelInputStream() {
		return excelInputStream;
	}

	public void setExcelInputStream(FileInputStream excelInputStream) {
		this.excelInputStream = excelInputStream;
	}

	public Class<?> getResultClass() {
		return resultClass;
	}


	public boolean isResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(boolean resultFlag) {
		this.resultFlag = resultFlag;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List<Object> getResults() {
		return results;
	}

	public void setResults(List<Object> results) {
		this.results = results;
	}
	
	
}
