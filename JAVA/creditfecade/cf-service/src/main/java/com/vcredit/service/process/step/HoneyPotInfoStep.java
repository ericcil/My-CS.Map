package com.vcredit.service.process.step;

import com.vcredit.service.process.CentralizeStep;
import com.vcredit.service.process.dto.DefaultChannelParam;
import com.vcredit.service.process.dto.ProcessContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author chenyubin
 * @Date 2018/7/20
 */
@Component
@Slf4j
public class HoneyPotInfoStep implements CentralizeStep {

    @Override
    public void executeStep(ProcessContext<DefaultChannelParam> context) {
        log.info("执行时间操作");
    }

    @Override
    public void fireCallback(ProcessContext<DefaultChannelParam> context) {

    }

    @Override
    public String getStepName() {
            return "蜜罐信息";
    }
}
