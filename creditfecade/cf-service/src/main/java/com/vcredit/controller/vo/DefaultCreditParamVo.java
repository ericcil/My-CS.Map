package com.vcredit.controller.vo;

import com.vcredit.service.process.dto.DefaultChannelParam;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author chenyubin
 * @Date 2018/7/9
 */
@Getter
@Setter
@Accessors(chain = true)
public class DefaultCreditParamVo {

    private String plName;//授信流程名（管道名称
    private DefaultChannelParam channelParam;//授信参数


}
