package com.vcredit.service.process.step;

import com.vcredit.service.process.CentralizeStep;
import com.vcredit.service.process.dto.DefaultChannelParam;
import com.vcredit.service.process.dto.ProcessContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author chenyubin
 * @Date 2018/7/5
 */
@Component
@Slf4j
public class TongdunReportStep implements CentralizeStep {

    @Override
    public void executeStep(ProcessContext<DefaultChannelParam> context) {
        log.info("执行时间操作");
    }

    @Override
    public void fireCallback(ProcessContext<DefaultChannelParam> context) {
        log.info("执行回调");
    }

    @Override
    public String getStepName() {
        return "同盾报告获取";
    }
}
