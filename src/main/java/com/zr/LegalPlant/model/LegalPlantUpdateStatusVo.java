package com.zr.LegalPlant.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LegalPlantUpdateStatusVo {
    @NotNull(message = "ID不能为空")
    private Integer id;
    private Boolean enabled;
}
