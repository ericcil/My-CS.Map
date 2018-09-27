package com.atomec.serviceport.impl;

import com.atomec.serviceport.ExcelWorker;
import com.atomec.serviceport.ExcelWorkerFactory;

public class ExcelWorkerFactoryImpl implements ExcelWorkerFactory {

	
	private ExcelWorkerFactoryImpl(){};
	
	private static class innerFactory{
		private static ExcelWorkerFactory excelWorkerFactory
			= new ExcelWorkerFactoryImpl();
	}
	
	public static ExcelWorkerFactory getExcelWorkerFactory() {
		return innerFactory.excelWorkerFactory;
	}

	public ExcelWorker newExcelWorker() {
		ExcelWorker excelWork = new ExcelSimpleWorker();
		return excelWork;
	}

}
