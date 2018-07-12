package com.vcredit.service.process;

import com.vcredit.service.process.dto.ProcessContext;
import org.springframework.util.Assert;

/**
 * 授信步骤，包含执行操作
 * @Author chenyubin
 * @Date 2018/7/5
 */
public interface Step<P> {



    /**
     * 执行授信步骤
     * @param context 授信上下文
     */
    void executeStep(ProcessContext<P> context);


    /**
     * 执行回调
     * 默认执行 属性的回调
     * @return
     */
    void fireCallback(ProcessContext<P> context);

    /**
     * 获取当前步骤名称
     * @return
     */
    String getStepName();
}
