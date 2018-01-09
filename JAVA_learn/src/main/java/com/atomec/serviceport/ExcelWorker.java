package com.atomec.serviceport;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.atomec.conveyentity.ExcelEntityMap;

public interface ExcelWorker {

	public void writeExcel();
	
	public void readExcel(ExcelEntityMap excelEntityMap) throws NoSuchFieldException, SecurityException,
	InstantiationException, IllegalAccessException, NoSuchMethodException, 
	IllegalArgumentException, InvocationTargetException, IOException;
}
