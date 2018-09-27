package com.vcredit.service.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author chenyubin
 * @Date 2018/7/18
 */
@Getter
@Setter
@Accessors(chain = true)
public class WhiteListQueryParam {

    @NotEmpty(message = "账户名不能为空")
    private String accountName;
    @NotEmpty(message = "项目名不能为空")
    private String projectName;
    private Integer projectCode;
}
