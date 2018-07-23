package com.vcredit.domain;

import com.vcredit.domain.helper.ProjectCreditFunction;
import com.vcredit.persistence.mapper.WhiteListMapper;
import com.vcredit.persistence.po.WhiteList;
import com.vcredit.service.dto.WhiteListQueryParam;
import com.vcredit.service.dto.WhiteListQuotaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 白名单domain
 * 负责白名单相关原子操作
 * @Author chenyubin
 * @Date 2018/7/18
 */
@Component
@Slf4j
public class CreditWhileListDomain {


    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    private WhiteListMapper whiteListMapper;


    /**
     * 查询用户是否白名单
     *
     * @param param
     * @return
     */
    public boolean isWhiteListCustomer(WhiteListQueryParam param) {
        WhiteList obj = new WhiteList().setAccountName(param.getAccountName()).setProject(param.getProjectCode());
        List<WhiteList> list = whiteListMapper.find(obj);
        if (list == null || list.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 组装白名单用户的授信结果
     *
     * @param param
     * @return
     */
    public WhiteListQuotaDto whiteListQuotaResultGenerate(WhiteListQueryParam param) {
        String beanName = functionNameBuild(param.getProjectName());
        ProjectCreditFunction function = applicationContext.getBean(beanName, ProjectCreditFunction.class);
        WhiteListQuotaDto result = function.whiteListQuotaResultBuild(param);
        log.info("项目{}，白名单回参生成===={}",param.getProjectName(),result);
        return result;
    }


    /**
     * 生成项目对应的功能类名称
     *
     * @param projectName
     * @return
     */
    private String functionNameBuild(String projectName) {
    	StringBuilder sb = new StringBuilder("Credit-");
    	return sb.append(projectName).toString();
    }
}
