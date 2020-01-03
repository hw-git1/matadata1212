package com.zr.planSmallClass.model;

import com.zr.util.PageVo;
import lombok.Data;

@Data
public class PlanSmallClassSelectVo extends PageVo {
    private String code;
    private String name;
    private Integer status;
}
