package com.zr.planSmallClass.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PlanSmallClassAddVo {
    @NotBlank(message = "编码不能为空")
    private String code;
    @NotBlank(message = "名称不能为空")
    private String name;
    private String remark;
    @NotNull(message = "状态不能为空")
    private Boolean status;

    private String statusName;

}
