package com.vcredit.process.boot;

import com.vcredit.process.CreditPipline;
import com.vcredit.process.CreditStep;

import java.util.Collection;
import java.util.List;

/**
 * 对称式的管道，授信步骤必须和回调一一对应
 * 实现相对简单
 * @Author chenyubin
 * @Date 2018/7/3
 * @Description
 */
public interface SymmetricCreditPipline<T> extends CreditPipline<T> {


    /**
     * 废弃方法
     * 对称式管道内不允许单独操作授信步骤而不操作回调
     * 弱调用则直接抛出异常
     * @param collection
     * @return
     */
    @Override
    @Deprecated
    default boolean addAllCreditStepAbsent(Collection<CreditStep<T>> collection) {
        throw new UnsupportedOperationException();
    }


    /**
     * 添加授信步骤和回调组合的集合
     * @param combinList 步骤和回调组合
     * @return
     */
    boolean addAllCombin(List<SymmetricCreditStepCombin<T>> combinList);

    /**
     * 向指定的位置添加步骤
     * @param index 指定的位置，从0开始
     * @param combin  步骤和回调组合
     * @return
     */
    boolean addCombin(int index,SymmetricCreditStepCombin<T> combin);
}
