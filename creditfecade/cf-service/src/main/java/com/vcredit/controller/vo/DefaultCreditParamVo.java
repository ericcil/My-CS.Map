package com.vcredit.controller.vo;

import com.vcredit.service.process.dto.DefaultChannelParam;

/**
 * @Author chenyubin
 * @Date 2018/7/9
 */
public class DefaultCreditParamVo {

    private String plName;//授信流程名（管道名称
    private DefaultChannelParam channelParam;//授信参数

    public String getPlName() {
        return plName;
    }

    public void setPlName(String plName) {
        this.plName = plName;
    }

    public DefaultChannelParam getChannelParam() {
        return channelParam;
    }

    public void setChannelParam(DefaultChannelParam channelParam) {
        this.channelParam = channelParam;
    }
}
