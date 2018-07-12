package com.vcredit.service.impl;

import com.alibaba.fastjson.JSON;
import com.vcredit.ApplicationStarter;
import com.vcredit.persistence.po.CreditConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationStarter.class})
public class CreditConfigServiceImplTest {

    Logger logger = LoggerFactory.getLogger(CreditConfigServiceImplTest.class);

    @Autowired
    private CreditConfigServiceImpl configService;

    @Test
    public void getCreditConfigsByProject() {
        Map<String,Boolean> m =  configService.getCreditConfigsByProject("随意花");
        logger.info("111========{}", JSON.toJSONString(m));
    }

    @Test
    public void getProjectNameList() {
        Set<String> s = configService.getProjectNameList();
        logger.info(">>>>>>{}",JSON.toJSONString(s));
    }

    @Test
    public void insertOrUpdateCreditStep() {
        CreditConfig c = new CreditConfig();
        c.setStepName("同盾");
        c.setProjectName("随意花");
        c.setUseStatus(1);
        c.setSeqNo(2);
        configService.insertOrUpdateCreditStep(c);
        Map<String,Boolean> m =  configService.getCreditConfigsByProject("随意花");
        logger.info("111========{}", JSON.toJSONString(m));
    }
}