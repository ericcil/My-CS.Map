package com.vcredit.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcredit.persistence.mapper.DicitionaryMapper;
import com.vcredit.service.DicitionaryService;
import com.vcredit.service.dto.DicitionaryDto;

/**
 * @Author xpf
 * @Date 2018/7/17
 */
@Service
public class DicitionaryServiceImpl implements DicitionaryService {

	@Autowired
	private DicitionaryMapper dicitionaryMapper;

    Logger logger = LoggerFactory.getLogger(DicitionaryServiceImpl.class);

	@Override
	public List<DicitionaryDto> getDicitionaryList() {
		List<DicitionaryDto> resultList = dicitionaryMapper.findByParentId(0L);
		packResult(resultList,1);
		return resultList;
	}
	
	private void packResult(List<DicitionaryDto> resultList,int n){
		if(resultList == null || resultList.isEmpty() || n >=3) {
			return;
		}else {
			for(DicitionaryDto temp:resultList) {
				List<DicitionaryDto> list = dicitionaryMapper.findByParentId(temp.getId());
				if(list == null || list.isEmpty() ) return;
				packResult(list,n+1);
				temp.setSonList(list);
			}
		}
	}

	@Override
	public List<DicitionaryDto> getSonDicitionary(Long parentId) {
		return dicitionaryMapper.findByParentId(parentId);
	}


}
