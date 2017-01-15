package com.cyb.log.output;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cyb.log.annotation.Testt;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LogSlf4jOutputTest {

	@Autowired
	Testt t;
	
	@Test
	public void test() {
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		/*com.cyb.log.annotation.Test t = new com.cyb.log.annotation.Test();
		t.tests("ww");*/
		//Testt t = (Testt) context.getBean("test");
		t.tests("www");
	
	}

}
