package com.vcredit.persistence.po;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 白名单
 **/
@Getter
@Setter
@Accessors(chain = true)
public class WhiteList {
	/** 主键 **/
	private Long id;
	/** 账号 **/
	private String accountName;
	/** 身份证号 **/
	private String idno;
	/** 项目 **/
	private Integer project;
	/** 状态 **/
	private Integer status;
	/** 创建时间 **/
	private Date createdTime;
	/** 更新时间 **/
	private Date updateTime;

}