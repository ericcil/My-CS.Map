package com.vcredit.service.process.step;

import com.vcredit.service.process.CentralizeStep;
import com.vcredit.service.process.dto.DefaultChannelParam;
import com.vcredit.service.process.dto.ProcessContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author chenyubin
 * @Date 2018/7/5
 */
@Component
public class BlackListStep implements CentralizeStep {

    private static Logger logger = LoggerFactory.getLogger(BlackListStep.class);

    @Override
    public void executeStep(ProcessContext<DefaultChannelParam> context) {
        logger.info("执行时间操作");
    }

    @Override
    public void fireCallback(ProcessContext<DefaultChannelParam> context) {
        logger.info("执行回调");
    }

    @Override
    public String getStepName() {
        return "集团黑名单";
    }
}
