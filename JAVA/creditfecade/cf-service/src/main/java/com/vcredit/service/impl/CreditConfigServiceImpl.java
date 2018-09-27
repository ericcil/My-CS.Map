package com.vcredit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcredit.domain.CreditProcessDomain;
import com.vcredit.domain.CreditWhileListDomain;
import com.vcredit.persistence.po.CreditConfig;
import com.vcredit.service.CreditConfigService;
import com.vcredit.service.dto.WhiteListQueryParam;
import com.vcredit.service.dto.WhiteListQuotaDto;
import com.vcredit.service.process.boot.DefaultCreditPiplineManager;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author chenyubin
 * @Date 2018/7/11
 */
@Service
@Slf4j
public class CreditConfigServiceImpl implements CreditConfigService {

	@Autowired
	private DefaultCreditPiplineManager piplineManager;
	@Autowired
	private CreditWhileListDomain whiteListDomain;
	@Autowired
	private CreditProcessDomain processDomain;

	@Override
	public List<String> getProjectNameList() {
		return processDomain.queryExistsProjectProcessName();
	}

	@Override
	public List<String> getProjectOptions() {
		return processDomain.creditConfigProjectList();
	}

	@Override
	public List<String> getStepNameList() {
		return processDomain.getAllExistsStepNames();
	}

	@Override
	public List<CreditConfig> getFullConfigInfoByProject(String projectName) {
		return processDomain.getFullConfigInfoByProject(projectName);
	}

	public void changeUseStatus(String projectName, Long id, boolean flag) {
		processDomain.changeProcessUseStatus(id, flag);
	}

	@Override
	public void addStep(CreditConfig config) {// step名称不能重复
		List<CreditConfig> list = processDomain.findProjectStep(config);
		if (list != null && list.size() > 0) {
			log.error("授信环节名称重复");
			throw new RuntimeException("授信环节名称重复");
		}
		int seqNo = processDomain.addNewStep2Process(config);
		piplineManager.addStep(config.getProjectName(), seqNo - 1, config.getStepName());
	}

	@Override
	public void resetStepIndex(Long id, Integer index) {
		List<CreditConfig> sortedProcess = processDomain.resortProcess(id, index);
		piplineManager.reindexPiplineByDB(sortedProcess);
	}

	@Override
	public WhiteListQuotaDto whiteListCustomerCheck(WhiteListQueryParam param) {

		if (!whiteListDomain.isWhiteListCustomer(param)) {
			return new WhiteListQuotaDto().setWhiteCustomer(false);
		}
		WhiteListQuotaDto quotaResult = whiteListDomain.whiteListQuotaResultGenerate(param);
		quotaResult.setWhiteCustomer(true);
		return quotaResult;
	}

}
