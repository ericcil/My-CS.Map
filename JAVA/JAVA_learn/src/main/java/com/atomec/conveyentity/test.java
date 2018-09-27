package com.atomec.conveyentity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.atomec.service.impl.DetailReaderImpl;
import com.atomec.serviceport.ExcelWorker;
import com.atomec.serviceport.ExcelWorkerFactory;
import com.atomec.serviceport.impl.ExcelWorkerFactoryImpl;

public class test {

	public static void main(String[] args) {
		String path  = "E://BaiduYunDownload//1.xls";
		//String path  = "E://BaiduYunDownload//2.xlsx";
		try {
			
			FileInputStream eis = new FileInputStream(new File(path));
			String pPath = "com//atomec//field.properties";
			InputStream is = test.class.getClassLoader().getResourceAsStream("field.properties");
			
			Properties p =new Properties();
			p.load(is);
			System.out.println(p.get("0"));
			ExcelEntityMap em = new ExcelEntityMap();
			em.setExcelInputStream(eis);
			em.setMappingProperties(p);
			em.setResultClass(Stu.class);
			em.setResults(new ArrayList<Object>());
			//DetailReaderImpl x1 = new DetailReaderImpl();
			//XlsxExcelReaderImpl x1 = new XlsxExcelReaderImpl();
			try {
				ExcelWorkerFactory f = ExcelWorkerFactoryImpl.getExcelWorkerFactory();
				ExcelWorker worker = f.newExcelWorker();
				worker.readExcel(em);
				//x1.readExcel(em);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<Object> stus = em.getResults();
			for(Object o:stus){
				Stu s = (Stu)o;
				if(!StringUtils.isEmpty(s.getName()))
					System.out.println("name = "+s.getName());
				if(!StringUtils.isEmpty(s.getPhone()))
					System.out.println("phone = "+s.getPhone());
				System.out.println("=========");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
