package com.vcredit.persistence;

import com.alibaba.fastjson.JSON;
import com.vcredit.ApplicationStarter;
import com.vcredit.persistence.mapper.CreditConfigMapper;
import com.vcredit.persistence.po.CreditConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationStarter.class})
public class DataSourcePWDManagerTest {

    @Autowired
    private DBManager dbManager;
    @Autowired
    private CreditConfigMapper configDao;

    private Logger logger = LoggerFactory.getLogger(DataSourcePWDManagerTest.class);

    @Test
    public void changeDBPWD() {

        CreditConfig condition = new CreditConfig();
        condition.setSeqNo(1);
        List<CreditConfig> list = configDao.selectByCondition(condition);
        System.out.println("========="+ JSON.toJSONString(list));

        dbManager.changeDBPWD("A!23333333q");

        try {
            list = configDao.selectByCondition(condition);
            System.out.println("=========" + JSON.toJSONString(list));
        }catch (Exception e){
            logger.error("数据库密码修改完成",e);
        }
    }
}