package com.vcredit.service.impl;

import com.vcredit.persistence.mapper.CreditConfigMapper;
import com.vcredit.persistence.po.CreditConfig;
import com.vcredit.service.CreditConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @Author chenyubin
 * @Date 2018/7/11
 */
@Service
public class CreditConfigServiceImpl implements CreditConfigService {

    @Autowired
    private CreditConfigMapper creditConfigMapper;

    @Override
    public Map<String, Boolean> getCreditConfigsByProject(String projectName) {
        CreditConfig condition = new CreditConfig();
        condition.setProjectName(projectName);
        List<CreditConfig> configs = creditConfigMapper.selectByCondition(condition);
        Map<String,Boolean> result = null;
        if(configs == null) return result;
        result = new HashMap<>(configs.size());
        Integer useStatus = 1;
        for(CreditConfig config : configs){
            result.put(config.getStepName(), useStatus.equals(config.getUseStatus()) ? true : false);
        }
        return result;
    }

    @Override
    public Set<String> getProjectNameList() {
        //TODO 暂时如此处理，后续做字典表
        CreditConfig condition = new CreditConfig();
        condition.setSeqNo(1);
        List<CreditConfig> configs = creditConfigMapper.selectByCondition(condition);
        Set<String> names = new HashSet<>();
        if(configs == null || configs.size() <= 0 ) return names;
        for(CreditConfig config : configs){
            names.add(config.getProjectName());
        }
        return names;
    }

    @Override
    public void insertOrUpdateCreditStep(CreditConfig config) {
        Assert.notNull(config,"作为条件的config不能为null");
        Assert.hasText(config.getProjectName(),"作为条件的projectName不能为空");
        Assert.hasText(config.getStepName(),"作为条件的stepName不能为空");
        CreditConfig condition = new CreditConfig();
        condition.setProjectName(config.getProjectName());
        condition.setStepName(config.getStepName());
        List<CreditConfig> configs = creditConfigMapper.selectByCondition(condition);
        if(configs!=null && configs.size() > 0) {
            if(configs.size() > 1) return;
            config.setId(configs.get(0).getId());
            creditConfigMapper.updateByPrimaryKeySelective(config);
            return;
        }
        Assert.notNull(config.getSeqNo(),"作为条件的seqNo不能为null");
        config.setUseStatus(1);//TODO 魔法值后续管理
        creditConfigMapper.insertSelective(config);

    }
}
