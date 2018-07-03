package com.vcredit.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


@Component
public interface InvoiceDao{


	public Invoice findById(Long id);

}
