package com.vcredit.process;

import com.vcredit.process.dto.ProcessContext;
import org.springframework.util.Assert;

/**
 * 授信步骤，包含执行操作
 * @Author chenyubin
 * @Date 2018/7/2
 */
public interface CreditStep<T> {

    /**
     * 执行授信步骤
     * @param context 授信上下文
     */
    void executeStep(ProcessContext<T> context);


    /**
     * 执行回调
     * @return
     */
    default void fireCallback(ProcessContext<T> context,AfterStepCallback callback){
        Assert.notNull(callback,"触发回调时 callback 不能为空");
        callback.afterExecute(context);
    }

    /**
     * 获取当前步骤名称
     * @return
     */
    String getStepName();
}
