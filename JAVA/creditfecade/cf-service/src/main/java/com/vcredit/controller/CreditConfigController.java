package com.vcredit.controller;

import com.vcredit.controller.interception.base.ResultResolveController;
import com.vcredit.persistence.po.CreditConfig;
import com.vcredit.service.CreditConfigService;
import com.vcredit.service.dto.WhiteListQueryParam;
import com.vcredit.service.dto.WhiteListQuotaDto;
import enums.UseStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chenyubin
 * @Date 2018/7/11
 */
@RestController
@RequestMapping("/config")
@Slf4j
public class CreditConfigController implements ResultResolveController {

    @Autowired
    private CreditConfigService creditConfigService;

    @GetMapping("/list/{projectName}")
    public Map<String,Boolean> getCreditConfigsByProject(@PathVariable String projectName){
        log.info("======>测试");
        List<CreditConfig> configs = creditConfigService.getFullConfigInfoByProject(projectName);
        Map<String, Boolean> result = null;
        if (configs == null)
            return result;
        result = new HashMap<>(configs.size());
        Integer useStatus = UseStatusEnum.INUSE.getCode();
        for (CreditConfig config : configs) {
            result.put(config.getStepName(), useStatus.equals(config.getUseStatus()) ? true : false);
        }
        return result;
    }

    @PostMapping("/white")
    public WhiteListQuotaDto whiteListCheck(@RequestBody @Valid WhiteListQueryParam param){
        return creditConfigService.whiteListCustomerCheck(param);
    }

    @GetMapping("/projects")
    public List<String> getProjectNameList(){
        return creditConfigService.getProjectNameList();
    }
    
    @GetMapping("/detailList/{projectName}")
    public List<CreditConfig> getCreditConfigsDetailByProject(@PathVariable String projectName){
    	return creditConfigService.getFullConfigInfoByProject(projectName);
    }
    
    @GetMapping("/steps")
    public List<String> getStepNameList(){
        return creditConfigService.getStepNameList();
    }
    
    @PostMapping("/changeUseStatus")
    public void changeUseStatus(String projectName,Long id,boolean flag){
        log.info("{},{},{}",projectName,id,flag);
        creditConfigService.changeUseStatus(projectName,id,flag);
    }
    
    @PostMapping("/addStep")
    public void addStep(@RequestBody CreditConfig config){
        creditConfigService.addStep(config);
    }

    @PostMapping("/reindex")
    public void resetStepIndex(Long id, Integer sort){
        creditConfigService.resetStepIndex(id,sort);
    }

}
