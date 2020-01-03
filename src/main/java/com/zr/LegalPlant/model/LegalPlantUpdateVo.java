package com.zr.LegalPlant.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LegalPlantUpdateVo {
    @NotBlank(message = "编码不能为空")
    private String legalPlantCode;
    @NotBlank(message = "描述不能为空")
    private String legalPlantDec;
    private Boolean enabled;
}
