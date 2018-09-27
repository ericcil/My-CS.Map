package com.atomec.service;

import java.util.List;

public interface DetailWriter {
	
	/**将对象写入excel
	 * @param objs
	 * @return
	 */
	public String writeExcel(List<?> objs);
}
