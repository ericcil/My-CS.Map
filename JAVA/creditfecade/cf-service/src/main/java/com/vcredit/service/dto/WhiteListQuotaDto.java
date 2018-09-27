package com.vcredit.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author chenyubin
 * @Date 2018/7/18
 */
@Getter
@Setter
@Accessors(chain = true)
public class WhiteListQuotaDto {

    private boolean whiteCustomer;//是否白名单
    private String quotaResult;//白名单用户额度回参
}
