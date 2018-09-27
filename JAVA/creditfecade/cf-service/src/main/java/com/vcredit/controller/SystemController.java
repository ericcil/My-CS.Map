package com.vcredit.controller;

import com.vcredit.controller.interception.base.ResultResolveController;
import com.vcredit.persistence.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chenyubin
 * @Date 2018/7/12
 */
@RestController
@RequestMapping("/sys")
public class SystemController implements ResultResolveController {

    @Autowired
    private DBManager dbManager;

    @PostMapping("/changeDBP/{pwd}")
    public String changeDBPwd(@PathVariable String pwd){

        if( dbManager.changeDBPWD(pwd) ) return "修改成功";
        return "修改失败";
    }
}
