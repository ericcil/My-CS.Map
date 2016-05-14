package com.atomec.serviceport.impl;



import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.atomec.conveyentity.ExcelEntityMap;
import com.atomec.service.WorkBookReader;
import com.atomec.service.impl.WorkBookReaderImpl;
import com.atomec.serviceport.ExcelWorker;

public class ExcelSimpleWorker implements ExcelWorker {

	public void writeExcel() {
		
		
	}

	public void readExcel(ExcelEntityMap excelEntityMap) throws NoSuchFieldException, SecurityException,
		InstantiationException, IllegalAccessException, NoSuchMethodException, 
		IllegalArgumentException, InvocationTargetException, IOException 
	{
		WorkBookReader wbReader = new WorkBookReaderImpl();
		wbReader.readExcelWorkBook(excelEntityMap);
	}

	

}
