package com.vcredit.domain.helper;

import com.vcredit.service.dto.WhiteListQueryParam;
import com.vcredit.service.dto.WhiteListQuotaDto;
import org.springframework.stereotype.Component;

/**
 * 项目授信功能接口
 * 定义每个项目的通用授信功能
 * @Author chenyubin
 * @Date 2018/7/23
 */
@Component
public interface ProjectCreditFunction {

    /**
     * 生成白名单返回
     * @param param
     * @return
     */
    WhiteListQuotaDto whiteListQuotaResultBuild(WhiteListQueryParam param);
}
