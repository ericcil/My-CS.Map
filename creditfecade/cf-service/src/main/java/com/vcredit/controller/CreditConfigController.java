package com.vcredit.controller;

import com.vcredit.controller.interception.base.ResultResolveController;
import com.vcredit.service.CreditConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author chenyubin
 * @Date 2018/7/11
 */
@RestController
@RequestMapping("/config")
public class CreditConfigController implements ResultResolveController {

    @Autowired
    private CreditConfigService creditConfigService;

    @GetMapping("/list/{projectName}")
    public Map<String,Boolean> getCreditConfigsByProject(@PathVariable String projectName){
        return creditConfigService.getCreditConfigsByProject(projectName);
    }

}
