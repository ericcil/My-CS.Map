package com.vcredit.persistence.mapper;

import com.alibaba.fastjson.JSON;
import com.vcredit.ApplicationStarter;
import com.vcredit.persistence.po.CreditConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationStarter.class})
public class CreditConfigMapperTest {

    @Autowired
    private CreditConfigMapper configDao;

    @Test
    public void insert() {
        CreditConfig config = new CreditConfig();
        config.setProjectName("随意花");
        config.setSeqNo(1);
        config.setStepName("黑名单");
        config.setUseStatus(3);
        configDao.insertSelective(config);
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void selectByCondition() {
        CreditConfig condition = new CreditConfig();
        condition.setSeqNo(1);
        List<CreditConfig> list = configDao.selectByCondition(condition);
        System.out.println("========="+ JSON.toJSONString(list));
    }
}