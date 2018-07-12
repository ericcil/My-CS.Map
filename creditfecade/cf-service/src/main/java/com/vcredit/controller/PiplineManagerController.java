package com.vcredit.controller;

import com.alibaba.fastjson.JSON;
import com.vcredit.service.process.boot.DefaultCreditPiplineManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author chenyubin
 * @Date 2018/7/5
 */
@RestController
public class PiplineManagerController {

    @Autowired
    DefaultCreditPiplineManager piplineManager;

    @GetMapping("/stepkeys")
    public Set<String> getStepKeys(){
        return piplineManager.getAllStepKeys();
    }

    @GetMapping("/plkeys")
    public Set<String> getPiplineKeys(){
        return piplineManager.getAllPiplineKeys();
    }

    @GetMapping("/plstepkeys/{key}")
    public List<String> getPiplineKeys(@PathVariable("key") String key){
        return piplineManager.getStepsByPipline(key);
    }

    @PostMapping("/addstep")
    public void getPiplineKeys(@RequestBody Map<String,String> param){
        String stepKey = param.get("stepKey");
        String plkey = param.get("plKey");
        String index = param.get("index");
        piplineManager.addStep(plkey,Integer.valueOf(index),stepKey);
    }

    @PostMapping("/remove-step")
    public void removeStepOfPl(@RequestBody Map<String,String> param){
        String plkey = param.get("plKey");
        String index = param.get("index");
        System.out.println("========="+JSON.toJSONString(param));
        piplineManager.removeStep(plkey,Integer.valueOf(index));
    }
}
