package com.zr.LegalPlant.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PlantAddVo {

    @NotBlank(message = "添加时,法人编码不能为空")
    private String legalPersonCode;
    @NotBlank(message = "添加时,工厂名称不能为空")
    private String legalPlantCode;
    @NotBlank(message = "添加时,描述不能为空")
    private String legalPlantDec;
    private Boolean enabled;
}
