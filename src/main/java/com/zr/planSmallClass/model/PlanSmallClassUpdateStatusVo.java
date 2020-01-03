package com.zr.planSmallClass.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlanSmallClassUpdateStatusVo {
    @NotNull(message = "id不能为空")
    private Integer id;
    @NotNull(message = "状态不能为空")
    private Boolean status;
}
