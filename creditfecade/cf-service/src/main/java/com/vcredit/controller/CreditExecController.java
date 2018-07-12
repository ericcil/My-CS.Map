package com.vcredit.controller;

import com.alibaba.fastjson.JSON;
import com.vcredit.controller.vo.DefaultCreditParamVo;
import com.vcredit.controller.vo.DefaultCreditResultVo;
import com.vcredit.service.process.boot.DefaultCreditPiplineManager;
import com.vcredit.service.process.dto.DefaultChannelParam;
import com.vcredit.service.process.dto.ProcessContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chenyubin
 * @Date 2018/7/9
 */
@RestController
@RequestMapping("/credit")
public class CreditExecController {

    private Logger logger = LoggerFactory.getLogger(CreditExecController.class);

    @Autowired
    private DefaultCreditPiplineManager piplineManager;

    @RequestMapping("/exec-pipline")
    public DefaultCreditResultVo piplineCredit(@RequestBody DefaultCreditParamVo mainParam){

        ProcessContext<DefaultChannelParam> processResult = piplineManager.exectueProcess(mainParam.getPlName(),mainParam.getChannelParam());
        logger.info("流程结果==={}", JSON.toJSONString(processResult));
        DefaultCreditResultVo result = new DefaultCreditResultVo();
        //TODO 实际执行操作，待补充
        result.setCode(1);
        result.setMsg("执行成功");
        return result;

    }
}
