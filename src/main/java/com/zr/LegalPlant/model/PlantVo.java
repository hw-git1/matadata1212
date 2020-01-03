package com.zr.LegalPlant.model;

import lombok.Data;

import java.util.Date;

@Data
public class PlantVo {
    private Integer id;
    private String legalPlantCode;
    private String legalPlantName;
    private String legalPersonCode;
    private String legalPersonName;
    private String legalPlantDec;
    private Boolean enabled;
    private String StatusName;
    private String createName;
    private Date createTime;
    private String updateName;
    private Date updateTime;

}
