package com.zr.planSmallClass.service;


import com.zr.planSmallClass.model.PlanSmallClassAddVo;
import com.zr.planSmallClass.model.PlanSmallClassSelectVo;
import com.zr.planSmallClass.model.PlanSmallClassUpdateStatusVo;
import com.zr.planSmallClass.model.PlanSmallClassUpdateVo;
import com.zr.util.ResultVo;

public interface PlanSmallClassService {

    ResultVo add(PlanSmallClassAddVo planSmallClassAddVo);

    ResultVo queryById(Integer id);

    ResultVo update(PlanSmallClassUpdateVo planSmallClassUpdateVo);

    ResultVo updateSmallStatus(PlanSmallClassUpdateStatusVo planSmallClassUpdateStatusVo);

    ResultVo queryPage(PlanSmallClassSelectVo planSmallClassSelectVo);
}
