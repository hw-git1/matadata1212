package com.zr.LegalPlant.service;

import com.zr.LegalPlant.model.LegalPlantSeleteVo;
import com.zr.LegalPlant.model.LegalPlantUpdateStatusVo;
import com.zr.LegalPlant.model.LegalPlantUpdateVo;
import com.zr.LegalPlant.model.PlantAddVo;
import com.zr.util.ResultVo;

public interface LegalService {
    ResultVo queryLegal();

    ResultVo addPlant(PlantAddVo plantAddVo);

    ResultVo queryPage(LegalPlantSeleteVo legalPlantSeleteVo);

    ResultVo updateStatus(LegalPlantUpdateStatusVo legalPlantUpdateStatusVo);

    ResultVo updatePlant(LegalPlantUpdateVo legalPlantUpdateVo);
}
