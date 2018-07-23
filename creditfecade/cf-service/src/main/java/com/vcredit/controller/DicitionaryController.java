package com.vcredit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcredit.controller.interception.base.ResultResolveController;
import com.vcredit.service.DicitionaryService;
import com.vcredit.service.dto.DicitionaryDto;

/**
 * @Author xpf
 * @Date 2018/7/17
 */
@RestController
@RequestMapping("/dic")
public class DicitionaryController implements ResultResolveController {
	Logger logger = LoggerFactory.getLogger(DicitionaryController.class);

	@Autowired
	private DicitionaryService dicitionaryService;

	@GetMapping("dicitionarys")
	public List<DicitionaryDto> getDicitionarys() {
		return dicitionaryService.getDicitionaryList();
	}
	
	@GetMapping("sonDics/{parentId}")
	public List<DicitionaryDto> getSonDicitionary(@PathVariable Long parentId) {
		return dicitionaryService.getSonDicitionary(parentId);
	}
}
