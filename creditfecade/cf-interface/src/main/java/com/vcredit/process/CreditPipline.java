package com.vcredit.process;

import com.vcredit.process.dto.ProcessContext;

import java.util.Collection;
import java.util.List;

/**
 * 授信管道，作为授信流程容器，维护一个授信流程集合
 * @Author chenyubin
 * @Date 2018/7/2
 * @Description
 */
public interface CreditPipline<T>{


    /**
     * 添加所有授信步骤到当前授信管道
     * @param collection
     */
    boolean addAllCreditStepAbsent(Collection<CreditStep<T>> collection);

    /**
     * 清除管道内所有步骤
     */
    void clean();

    /**
     * 移除指定位置的授信步骤
     * @param index 指定位置,从0开始
     */
    boolean remove(int index);

    /**
     * 获取管道内所有授信步骤
     * @return
     */
    List<CreditStep<T>> getCreditStep();

    /**
     * 执行管道流程
     * 按照管道内配置的步骤走完流程
     * @param context 授信上下文
     */
    void startProcessLine(ProcessContext<T> context);

    /**
     * 触发授信流程内的特定一步，并且执行管道内后续的所有步骤
     * 授信流程的重入入口，提供重入性和灵活跳转功能
     * 灵活的模型，根据使用方式，可能会造成死循环
     * @param index 步骤序号，从0开始
     * @param context 授信上下文
     */
    void fireProcess(int index,ProcessContext<T> context);
}
