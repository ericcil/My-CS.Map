package com.vcredit.domain.helper;

import com.vcredit.service.dto.WhiteListQueryParam;
import com.vcredit.service.dto.WhiteListQuotaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 随意花授信功能类
 * @Author chenyubin
 * @Date 2018/7/23
 */
@Component("")
@Slf4j
public class CreditSYHFunction implements ProjectCreditFunction {

    @Override
    public WhiteListQuotaDto whiteListQuotaResultBuild(WhiteListQueryParam param) {
    	WhiteListQuotaDto dto = new WhiteListQuotaDto().setWhiteCustomer(Boolean.TRUE);
        return null;
    }
}
