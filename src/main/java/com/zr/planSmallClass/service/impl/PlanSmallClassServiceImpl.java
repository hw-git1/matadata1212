package com.zr.planSmallClass.service.impl;

import com.zr.planSmallClass.mapper.PlanSmallClassMapper;
import com.zr.planSmallClass.model.*;
import com.zr.planSmallClass.service.PlanSmallClassService;
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
public class PlanSmallClassServiceImpl implements PlanSmallClassService {

    @Autowired
    private PlanSmallClassMapper planSmallClassMapper;

    @Override
    @Transactional
    public ResultVo add(PlanSmallClassAddVo planSmallClassAddVo) {
        int code = planSmallClassMapper.queryByCode(planSmallClassAddVo.getCode());
        if (code>0){
            return ResultBuliderVo.error("编码已存在");
        }
        int name=planSmallClassMapper.queryByName(planSmallClassAddVo.getName());
        if (name>0){
            return ResultBuliderVo.error("名称已存在");
        }
        Date date=new Date();
        PlanSmallClassVo planSmallClassVo=new PlanSmallClassVo();
        BeanUtils.copyProperties(planSmallClassAddVo,planSmallClassVo);
        planSmallClassVo.setCreateName("李四");
        planSmallClassVo.setCreateTime(date);
        planSmallClassVo.setUpdateName("李四");
        planSmallClassVo.setCreateTime(date);
        planSmallClassMapper.addPlanSmallClass(planSmallClassVo);
        return ResultBuliderVo.success();
    }

    @Override
    public ResultVo queryById(Integer id) {
        if (id==null){
            return ResultBuliderVo.error("id不能为空");
        }
        PlanSmallClassVo planSmallClassVo = planSmallClassMapper.queryById(id);
        return ResultBuliderVo.success(planSmallClassVo);

    }

    @Override
    @Transactional
    public ResultVo update(PlanSmallClassUpdateVo planSmallClassUpdateVo) {
        PlanSmallClassVo planSmallClassVo1 = planSmallClassMapper.queryById(planSmallClassUpdateVo.getId());
        if (planSmallClassVo1==null){
            return ResultBuliderVo.error("此对象不存在");
        }
        int name = planSmallClassMapper.queryByName(planSmallClassUpdateVo.getName());
        if (name>0){
            return ResultBuliderVo.error("此名称已存在");
        }
        PlanSmallClassVo planSmallClassVo=new PlanSmallClassVo();
        Date date=new Date();
        BeanUtils.copyProperties(planSmallClassUpdateVo,planSmallClassVo);
        planSmallClassVo.setUpdateName("王五");
        planSmallClassVo.setUpdateTime(date);
        planSmallClassMapper.UpdateLegalperson(planSmallClassVo);
        return ResultBuliderVo.success();
    }

    @Override
    public ResultVo updateSmallStatus(PlanSmallClassUpdateStatusVo planSmallClassUpdateStatusVo) {
        PlanSmallClassVo planSmallClassVo = planSmallClassMapper.queryById(planSmallClassUpdateStatusVo.getId());
        if (planSmallClassVo==null){
            return ResultBuliderVo.error("此对象不存在");
        }
        PlanSmallClassVo planSmallClassVo2=new PlanSmallClassVo();
        Date date=new Date();
        BeanUtils.copyProperties(planSmallClassUpdateStatusVo,planSmallClassVo2);
        planSmallClassVo2.setUpdateName("王五");
        planSmallClassVo2.setUpdateTime(date);
        planSmallClassMapper.UpdatePlanSmallClassStatus(planSmallClassVo2);
        return ResultBuliderVo.success();
    }

    @Override
    public ResultVo queryPage(PlanSmallClassSelectVo planSmallClassSelectVo) {
        List<PlanSmallClassVo> planSmallClassVoList=planSmallClassMapper.queryPageData(planSmallClassSelectVo);
        if (!CollectionUtils.isEmpty(planSmallClassVoList)){
            for (PlanSmallClassVo planSmallClassVo:planSmallClassVoList){
                if (!planSmallClassVo.getStatus()){
                    planSmallClassVo.setStatusName("禁用");
                }
                if (planSmallClassVo.getStatus()){
                    planSmallClassVo.setStatusName("启用");
                }
            }
        }
        int count = planSmallClassMapper.queryCount(planSmallClassSelectVo);
        AllRecords allRecords=new AllRecords();
        allRecords.setDataList(planSmallClassVoList);
        allRecords.setTotalNumber(count);
        allRecords.setPageSize(planSmallClassSelectVo.getPageSize());
        allRecords.setPageIndex(planSmallClassSelectVo.getPageIndex());
        allRecords.resetTotalNumber(count);
        return ResultBuliderVo.success(allRecords);

    }


}
