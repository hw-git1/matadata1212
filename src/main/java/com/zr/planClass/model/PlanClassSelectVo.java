package com.zr.planClass.model;

import com.zr.util.PageVo;
import lombok.Data;

@Data
public class PlanClassSelectVo extends PageVo {
    private String code;
    private String name;
    private Integer status;
}
