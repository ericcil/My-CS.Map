package com.vcredit.process.boot;

import com.vcredit.process.AfterStepCallback;
import com.vcredit.process.CreditStep;

/**
 * 用于对称式管道的数据结构
 * @Author chenyubin
 * @Date 2018/7/3
 */
public class SymmetricCreditStepCombin<T> {

    private CreditStep<T> creditStep;//授信步骤
    private AfterStepCallback callback;//回调

    public SymmetricCreditStepCombin(){}

    public SymmetricCreditStepCombin(CreditStep<T> creditStep, AfterStepCallback callback) {
        this.creditStep = creditStep;
        this.callback = callback;
    }

    public CreditStep<T> getCreditStep() {
        return creditStep;
    }

    public void setCreditStep(CreditStep<T> creditStep) {
        this.creditStep = creditStep;
    }

    public AfterStepCallback getCallback() {
        return callback;
    }

    public void setCallback(AfterStepCallback callback) {
        this.callback = callback;
    }
}
