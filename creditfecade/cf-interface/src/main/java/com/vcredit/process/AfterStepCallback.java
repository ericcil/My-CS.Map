package com.vcredit.process;

import com.vcredit.process.dto.ProcessContext;

/**
 * 步骤完成后执行的回调
 * @Author chenyubin
 * @Date 2018/7/2
 */
@FunctionalInterface
public interface AfterStepCallback {


    /**
     * 回调方法
     * @param context 授信上下文
     */
    void afterExecute(ProcessContext context);
}
