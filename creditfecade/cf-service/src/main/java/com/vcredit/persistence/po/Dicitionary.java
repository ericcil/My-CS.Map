package com.vcredit.persistence.po;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 字典表
 **/
@Getter
@Setter
@Accessors(chain = true)
public class Dicitionary {

	private Long id;
	/** 值 **/
	private String value;
	/** 父id **/
	private Long parentId;
	/** 注释 **/
	private String comment;
	/** 创建时间 **/
	private Date createdTime;
	/** 更新时间 **/
	private Date updateTime;


}