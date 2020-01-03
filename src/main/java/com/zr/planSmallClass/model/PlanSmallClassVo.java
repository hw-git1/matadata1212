package com.zr.planSmallClass.model;

import lombok.Data;

import java.util.Date;

@Data
public class PlanSmallClassVo {
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
