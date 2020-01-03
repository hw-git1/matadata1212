package com.zr.planClass.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PlanClassUpdateVo {
    @NotNull(message = "id不能为空")
    private Integer id;
    @NotBlank(message = "编码不能为空")
    private String code;
    @NotBlank(message = "名称不能为空")
    private String name;
    private String remark;
    @NotNull(message = "状态不能为空")
    private Boolean status;
}
