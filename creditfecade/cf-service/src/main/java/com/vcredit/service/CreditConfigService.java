package com.vcredit.service;

import com.vcredit.persistence.po.CreditConfig;
import com.vcredit.service.dto.WhiteListQueryParam;
import com.vcredit.service.dto.WhiteListQuotaDto;

import java.util.List;
import java.util.Map;

/**
 * @Author chenyubin
 * @Date 2018/7/11
 */
public interface CreditConfigService {

    /**
     * 获取项目名列表
     * @return
     */
    List<String> getProjectNameList();

    /**
     * 获取作为选项的项目名
     * @return
     */
    List<String> getProjectOptions();
    /**
     * 获取完整授信配置信息
     * @param projectName
     * @return
     */
    List<CreditConfig> getFullConfigInfoByProject(String projectName);


    /**
     * 改变应用状态
     * @param flag true 为变为有效，false为置失效
     * @param id
     */
    void changeUseStatus(String projectName,Long id,boolean flag);

    /**
     * 新增或修改授信步骤
     * @param config
     */
	void addStep(CreditConfig config);

	
    /**
     * 获取授信步骤列表
     * @return
     */
    List<String> getStepNameList();

    /**
     * 重新设置步骤顺序
     * @param id
     * @param index
     */
	void resetStepIndex(Long id, Integer index);


    /**
     * 判断用户是否白名单
     * 是白名单则同时返回额度相关参数
     * @param param
     * @return
     */
    WhiteListQuotaDto whiteListCustomerCheck(WhiteListQueryParam param);
}
