package com.atomec.service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.apache.poi.ss.usermodel.Workbook;

import com.atomec.conveyentity.ExcelEntityMap;

public interface WorkBookReader {
	
	public Workbook workBookPrivider(InputStream inputStream) throws IOException;
	
	public void readExcelWorkBook(ExcelEntityMap excelEntityMap) throws IOException, 
		NoSuchFieldException, SecurityException, InstantiationException,
		IllegalAccessException, NoSuchMethodException, 
		IllegalArgumentException, InvocationTargetException;
}
