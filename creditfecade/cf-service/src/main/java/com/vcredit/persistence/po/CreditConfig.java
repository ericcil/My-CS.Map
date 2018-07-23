package com.vcredit.persistence.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class CreditConfig {
    private Long id;

    private String projectName;

    private Integer seqNo;

    private String stepName;

    private Integer useStatus;

    private Date createdTime;

    private Date updatedTime;

}