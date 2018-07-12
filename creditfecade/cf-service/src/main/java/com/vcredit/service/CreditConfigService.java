package com.vcredit.service;

import com.vcredit.persistence.po.CreditConfig;

import java.util.Map;
import java.util.Set;

/**
 * @Author chenyubin
 * @Date 2018/7/11
 */
public interface CreditConfigService {

    /**
     * 获取授信配置
     * @param projectName 项目名
     * @return
     */
    Map<String,Boolean> getCreditConfigsByProject(String projectName);

    /**
     * 获取项目名列表
     * @return
     */
    Set<String> getProjectNameList();

    /**
     * 新增或修改授信步骤
     * @param config
     */
    void insertOrUpdateCreditStep(CreditConfig config);


}
