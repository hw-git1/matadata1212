package com.zr.LegalPlant.model;

import com.zr.util.PageVo;
import lombok.Data;

@Data
public class LegalPlantSeleteVo extends PageVo {

    private String legalPersonCode;
    private String legalPersonName;
    private String legalPlantCode;
    private String legalPlantDec;
    private Boolean enabled;
}
