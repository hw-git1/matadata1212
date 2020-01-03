package com.zr.planClass.model;

import lombok.Data;

import java.util.Date;

@Data
public class PlanClassVo {
    private Integer id;
    private String code;
    private String name;
    private String remark;
    private Boolean status;
    private String statusName;
    private String createName;
    private Date createTime;
    private String updateName;
    private Date updateTime;
}
