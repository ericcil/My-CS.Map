package com.vcredit.controller.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author chenyubin
 * @Date 2018/7/9
 */
@Getter
@Setter
@Accessors(chain = true)
public class DefaultCreditResultVo {

    private Integer code;
    private String msg;

}
