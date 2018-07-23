package com.vcredit.persistence.mapper;
import java.util.List;

import com.vcredit.persistence.po.WhiteList;
public interface WhiteListMapper{
	public void insert(WhiteList obj);
	public void update(WhiteList obj);

	public List<WhiteList> find(WhiteList obj);
	public WhiteList findById(Long id);
}
