package com.atomec.service;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.poi.ss.usermodel.Workbook;

import com.atomec.conveyentity.ExcelEntityMap;


public interface DetailReader {
	
	/**读取excel
	 * @param excelEntityMap
	 * @throws IOException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public void readExcel(Workbook workBook,ExcelEntityMap excelEntityMap) throws IOException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException;
}
