package com.vcredit.service.process;

import com.vcredit.service.process.dto.ProcessContext;
import org.springframework.util.Assert;

/**
 * 组合式实现
 * 步骤和回调组合
 * 用于相同step，在不同pipline中，callback不同的情况
 * @Author chenyubin
 * @Date 2018/7/2
 */
public abstract class AssembleStep<P> implements Step<P> {

    private AfterStepCallback callback = null;

    public AssembleStep(AfterStepCallback callback) {
        this.callback = callback;
    }

    /**
     * 赋予回调
     * @param callback 授信上下文
     */
    public AssembleStep<P> setCallbackRender(AfterStepCallback callback){
        Assert.notNull(this.callback," callback 不能为空");
        this.callback = callback;
        return this;
    }

    /**
     * 默认提供的回调触发方法
     * 执行 成员变脸的回调
     * @return
     */
    public void fireCallback(ProcessContext<P> context){
        Assert.notNull(this.callback,"触发回调时 callback 不能为空");
        this.callback.afterExecute(context);
    }

}
