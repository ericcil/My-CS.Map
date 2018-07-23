package com.vcredit.service;

import java.util.List;

import com.vcredit.service.dto.DicitionaryDto;

/**
 * @Author xpf
 * @Date 2018/7/17
 */
public interface DicitionaryService {

	/**
	 * 获取字典目录
	 * 
	 * @return
	 */
	List<DicitionaryDto> getDicitionaryList();

	/**
	 * 获取子目录
	 * 
	 * @return
	 */
	List<DicitionaryDto> getSonDicitionary(Long parentId);

}
