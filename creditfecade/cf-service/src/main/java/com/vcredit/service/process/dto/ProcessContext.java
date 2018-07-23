package com.vcredit.service.process.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author chenyubin
 * @Date 2018/7/2
 * @Description 流程上下文，包含流程的元数据和实体数据
 */
@Setter
@Getter
public class ProcessContext<T> {

    //元数据
    private Date receiveTime;//结束任务时间
    private Date submitTime;//任务结束时间
    private int currentIndex;//当前执行序号

    private boolean processInterrupt;//流程是否被中断


    //实体数据
    private T processParam;//流程参数
    private StepResult currentStepResult;//当前步骤结果


}
