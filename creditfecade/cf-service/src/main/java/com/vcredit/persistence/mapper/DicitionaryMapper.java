package com.vcredit.persistence.mapper;

import java.util.List;

import com.vcredit.persistence.po.Dicitionary;
import com.vcredit.service.dto.DicitionaryDto;

public interface DicitionaryMapper {
	public void insert(Dicitionary obj);

	public void update(Dicitionary obj);

	public List<Dicitionary> find(Dicitionary obj);
	
	public List<DicitionaryDto> findByParentId(Long parentId);

	public Dicitionary findById(Long id);
}
