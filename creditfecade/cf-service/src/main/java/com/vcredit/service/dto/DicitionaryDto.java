package com.vcredit.service.dto;

import java.util.List;

import com.vcredit.persistence.po.Dicitionary;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class DicitionaryDto extends Dicitionary{

	private List<DicitionaryDto> sonList;

}
