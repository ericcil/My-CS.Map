package com.vcredit.domain.helper;

import com.vcredit.service.dto.WhiteListQueryParam;
import com.vcredit.service.dto.WhiteListQuotaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 橙分期授信功能类
 * @Author chenyubin
 * @Date 2018/7/23
 */
@Component("Credit-橙分期")
@Slf4j
public class CreditCFQFunction implements ProjectCreditFunction {

    @Override
    public WhiteListQuotaDto whiteListQuotaResultBuild(WhiteListQueryParam param) {
    	String quotaResult ="{\"decisionResultCode\":1,\"xinYanCreditScore\":9999,\"monthlyInterestRate\":\"0.00958\",\"creditAccount\":2000,\"rejectReason\":[]}";
    	WhiteListQuotaDto dto = new WhiteListQuotaDto()
    			.setWhiteCustomer(Boolean.TRUE)
    			.setQuotaResult(quotaResult);
        return dto;
    }
}
