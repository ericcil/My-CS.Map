package com.vcredit.persistence.mapper;

import com.vcredit.persistence.po.CreditConfig;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CreditConfigMapper {

	void insert(CreditConfig obj);

	void update(CreditConfig obj);

	List<CreditConfig> find(CreditConfig obj);

	CreditConfig findById(Long id);

	List<CreditConfig> findOrderConfigByName(String projectName);

}