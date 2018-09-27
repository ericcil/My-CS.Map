package com.atomec.service.impl;


import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.atomec.conveyentity.ExcelEntityMap;
import com.atomec.service.DetailReader;

public class DetailReaderImpl implements DetailReader {

	public void readExcel(Workbook workBook,ExcelEntityMap excelEntityMap) throws IOException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		if(null==workBook.getSheetAt(0)){
			excelEntityMap.setResultFlag(false);
			excelEntityMap.setErrorMsg("解析错误，没有找到sheet页");
			return;
		}
		Sheet sheet = workBook.getSheetAt(0);
		if(null==sheet.getRow(0)){
			excelEntityMap.setResultFlag(false);
			excelEntityMap.setErrorMsg("解析错误，没有找到首列");
			return;
		}
		Properties mapping = excelEntityMap.getMappingProperties();
		//获取属性列
		Row columnRow = sheet.getRow(0);
		//获取最后一行行号
		int lastRow = sheet.getLastRowNum();
		System.out.println("lastRow="+lastRow);
		//获取最后一列列号
		int lastColumn = columnRow.getLastCellNum();
		List<Field> fields = new ArrayList<Field>();
		List<Method> methods = new ArrayList<Method>();
		//验证列、字段对应关系，并将字段放入list
		for(Integer column=0;column<lastColumn;column++){
			if(StringUtils.isEmpty(mapping.get(column.toString()).toString())){
				return;//字段为空
			}
			String fieldname = mapping.get(column.toString()).toString();
			Field f = excelEntityMap.
					getResultClass().getDeclaredField(fieldname);
			fields.add(f);
			methods.add(excelEntityMap.getResultClass().
					getMethod("set"+StringUtils.capitalize(f.getName()),f.getType()));
		}
		
		for(int rnum=1;rnum<=lastRow;rnum++){
			Row row = sheet.getRow(rnum);
			
			System.out.println("rnum="+rnum);
			
			Object obj = excelEntityMap.getResultClass().newInstance();
			for(int cnum=0;cnum<lastColumn;cnum++){
				
				Cell cell = row.getCell(cnum);
				int cellType = cell.getCellType();
				Field field = fields.get(cnum);
				Method method = methods.get(cnum);
				switch(cellType){
					case Cell.CELL_TYPE_ERROR:break;
					case Cell.CELL_TYPE_BLANK:break;
					case Cell.CELL_TYPE_BOOLEAN:
						Boolean bValue = cell.getBooleanCellValue();
						if(field.getType().equals(bValue.getClass())){
							method.invoke(obj, bValue);
						}else{
							return;
						}
						break;
					case Cell.CELL_TYPE_NUMERIC:
						Double d = cell.getNumericCellValue();
						String dValue = d.toString();
						if(field.getType().equals(dValue.getClass())){
							method.invoke(obj, dValue);
						}
						break;
					case Cell.CELL_TYPE_STRING:
						String sValue = cell.getStringCellValue();
						if(field.getType().equals(sValue.getClass())){
							method.invoke(obj, sValue);
							
						}
						break;
				}
				
			}
			excelEntityMap.getResults().add(obj);
			
		}
		
		
	}

}
