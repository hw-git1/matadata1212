package com.zr.LegalPlant.model;

import lombok.Data;

import java.util.Date;

@Data
public class LegalVo {
    private Integer id;
    private String legalPersonCode;
    private String legalPersonName;
    private Integer userId;
    private String createName;
    private Date createTime;
    private String updateName;
    private Date updateTime;

}
