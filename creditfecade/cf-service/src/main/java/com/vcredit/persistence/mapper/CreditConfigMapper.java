package com.vcredit.persistence.mapper;

import com.vcredit.persistence.po.CreditConfig;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CreditConfigMapper {

    int insertSelective(CreditConfig record);


    CreditConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CreditConfig record);

    List<CreditConfig> selectByCondition(CreditConfig config);
}