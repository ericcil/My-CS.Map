package com.atomec.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.InvocationTargetException;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.atomec.conveyentity.ExcelConstant;
import com.atomec.conveyentity.ExcelEntityMap;
import com.atomec.service.DetailReader;
import com.atomec.service.WorkBookReader;

public class WorkBookReaderImpl implements WorkBookReader {

	
	public Workbook workBookPrivider(InputStream inputStream) throws IOException{
		if (!inputStream.markSupported()) {  
			inputStream = new PushbackInputStream(inputStream,8);  
        }  
        if (POIFSFileSystem.hasPOIFSHeader(inputStream)) {//2003  
        	return new HSSFWorkbook(inputStream);
        }
        if (POIXMLDocument.hasOOXMLHeader(inputStream)) {//2007
        	return new XSSFWorkbook(inputStream);
        }
		return null;
	}
	
	public void readExcelWorkBook(ExcelEntityMap excelEntityMap) throws IOException,
		NoSuchFieldException,SecurityException, InstantiationException,IllegalAccessException,
		NoSuchMethodException, IllegalArgumentException,InvocationTargetException
	{
		Workbook wookBook =  this.workBookPrivider(excelEntityMap.getExcelInputStream());
		if(wookBook==null){
			excelEntityMap.setErrorMsg(ExcelConstant.ERROE_MSG_ILLEGALFILE);
			return;
		}
		DetailReader detailReader = new DetailReaderImpl();
		detailReader.readExcel(wookBook, excelEntityMap);
	}

}
