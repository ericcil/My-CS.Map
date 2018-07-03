package com.vcredit.controller;


import com.alibaba.fastjson.JSON;
import com.vcredit.persistence.Invoice;
import com.vcredit.persistence.InvoiceDao;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private InvoiceDao invoiceDao;

    @RequestMapping("/")
    String home() {
        Invoice i = invoiceDao.findById(2l);
        logger.info(JSON.toJSONString(i));
        return "Hello World!";
    }


}
