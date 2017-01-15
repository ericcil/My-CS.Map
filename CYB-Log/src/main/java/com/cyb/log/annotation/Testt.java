package com.cyb.log.annotation;

import org.springframework.stereotype.Service;

@Service
public class Testt {

	@LogParam
	public String tests(String un){
		
		System.out.println("====开始");
		return "结束";
	}
}
