package com.zr.planClass.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlanClassUpdateStatusVo {
    @NotNull(message = "id不能为空")
    private Integer id;
    private Boolean status;
}
