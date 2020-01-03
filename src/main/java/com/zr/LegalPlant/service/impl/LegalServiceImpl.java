package com.zr.LegalPlant.service.impl;

import com.zr.EnumPackage.CheckedEnum;
import com.zr.LegalPlant.mapper.LegalMapper;
import com.zr.LegalPlant.model.*;
import com.zr.LegalPlant.service.LegalService;
import com.zr.util.AllRecords;
import com.zr.util.ResultBuliderVo;
import com.zr.util.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class LegalServiceImpl implements LegalService {

    @Autowired
    private LegalMapper legalMapper;


    @Override
    public ResultVo queryLegal() {
        List<LegalSeleteVo> legalVoList = legalMapper.queryLegal();
        return ResultBuliderVo.success(legalVoList);
    }

    @Override
    @Transactional
    public ResultVo addPlant(PlantAddVo plantAddVo) {
        int pCode = legalMapper.queryPCode(plantAddVo.getLegalPlantCode());
        if (pCode>0) {
            return ResultBuliderVo.error(CheckedEnum.LEGALPLANTCODE.getName());
        }
        Date date=new Date();
        PlantVo plantVo=new PlantVo();
        BeanUtils.copyProperties(plantAddVo,plantVo);
        plantVo.setCreateName("张三");
        plantVo.setCreateTime(date);
        plantVo.setUpdateName("张三");
        plantVo.setUpdateTime(date);
        legalMapper.addPlant(plantVo);
        return ResultBuliderVo.success();
    }

    @Override
    public ResultVo queryPage(LegalPlantSeleteVo legalPlantSeleteVo) {
        int count = legalMapper.queryCount(legalPlantSeleteVo);
        List<PlantVo> plantVoList = legalMapper.queryPageData(legalPlantSeleteVo);
        if (!CollectionUtils.isEmpty(plantVoList)){
            for (PlantVo plantVo:plantVoList){
                if (!plantVo.getEnabled()){
                    plantVo.setStatusName("禁用");
                }
                if (plantVo.getEnabled()){
                    plantVo.setStatusName("启用");
                }
            }
        }
        AllRecords allRecords=new AllRecords();
        allRecords.setDataList(plantVoList);
        allRecords.setTotalNumber(count);
        allRecords.setPageIndex(legalPlantSeleteVo.getPageIndex());
        allRecords.setPageSize(legalPlantSeleteVo.getPageSize());
        allRecords.resetTotalNumber(count);
        return ResultBuliderVo.success(allRecords);
    }

    @Override
    @Transactional
    public ResultVo updateStatus(LegalPlantUpdateStatusVo legalPlantUpdateStatusVo) {
        PlantVo plantVo = legalMapper.queryPlant(legalPlantUpdateStatusVo.getId());
        if (plantVo==null){
            return ResultBuliderVo.error(CheckedEnum.LEGALPLANTCODE.getName());
        }
        Date date=new Date();
        PlantVo plant=new PlantVo();
        BeanUtils.copyProperties(legalPlantUpdateStatusVo,plant);
        plant.setUpdateName("李四");
        plant.setUpdateTime(date);
        legalMapper.updateStatus(plant);
        return ResultBuliderVo.success();
    }

    @Override
    @Transactional
    public ResultVo updatePlant(LegalPlantUpdateVo legalPlantUpdateVo) {
        PlantVo plantVo = legalMapper.queryPlantByLegalPlantCode(legalPlantUpdateVo.getLegalPlantCode());
        if (plantVo==null){
            return ResultBuliderVo.error(CheckedEnum.LEGALPLANTCODE.getName());
        }
        Date date=new Date();
        PlantVo plantVo1=new PlantVo();
        BeanUtils.copyProperties(legalPlantUpdateVo,plantVo1);
        plantVo1.setUpdateName("李四");
        plantVo1.setUpdateTime(date);
        legalMapper.updatePlant(plantVo1);
        return ResultBuliderVo.success();
    }
}
