package com.zr.planClass.controller;

import com.zr.EnumPackage.StatusEnum;
import com.zr.planClass.model.*;
import com.zr.planClass.service.PlanClassService;
import com.zr.util.ResultBuliderVo;
import com.zr.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PlanClassController {

    @Autowired
    private PlanClassService planClassService;

    @PostMapping("planCategory/add")
    public ResultVo addPlanClass(@RequestBody @Valid PlanClassAddVo planClassAddVo, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultBuliderVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        List<PlanClassAddVo> planClassAddVos=new ArrayList<>();
        planClassAddVos.add(planClassAddVo);
        ResultVo resultVo=planClassService.add(planClassAddVos);
        return resultVo;
    }


    @PostMapping("planCategory/update")
    public ResultVo UpdateLegalperson(@RequestBody @Valid PlanClassUpdateVo planClassUpdateVo,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultBuliderVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        ResultVo resultVo=planClassService.UpdateLegalperson(planClassUpdateVo);
        return resultVo;
    }

    @GetMapping("queryById")
    public ResultVo queryById( Integer id){
        if (id==null){
            return  ResultBuliderVo.error("查看时id不能为空");
        }
        ResultVo resultVo= planClassService.queryById(id);

        return resultVo;
    }

    @PostMapping("planCategory/updateStatus")
    public ResultVo updateStatus(@RequestBody @Valid PlanClassUpdateStatusVo planClassUpdateStatusVo,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return  ResultBuliderVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        ResultVo resultVo=planClassService.updateStatus(planClassUpdateStatusVo);
        return resultVo;
    }

    @PostMapping("planCategory/queryPage")
    public ResultVo queryPage(@RequestBody PlanClassSelectVo planClassSelectVo){
        ResultVo resultVo=planClassService.queryPage(planClassSelectVo);
        return  resultVo;
    }

    @PostMapping("quotation/InquiryIntroduction")
    public ResultVo importPlanClass(MultipartFile file)throws Exception{
        ResultVo resultVo=planClassService.importPlanClass(file);
        return resultVo;
    }

    @GetMapping("export")
    public ResultVo export(HttpServletResponse response,String code,String name,Integer status){
        ResultVo resultVo = planClassService.export(response, code, name, status);
        return resultVo;
    }
/*  @PostMapping("metadata/getPullDownList")
    public ResultVo xiala(){
        List<StatusVo> statusVoList=new ArrayList<>();
        *//*LegalPlantUpdateStatusVo statusVo=new LegalPlantUpdateStatusVo();
        statusVo.setLabel(StatusEnum.JINYONG.getName());
        statusVo.setValue(StatusEnum.JINYONG.getValue());
        statusVoList.add(statusVo);
        LegalPlantUpdateStatusVo statusVo2=new LegalPlantUpdateStatusVo();
        statusVo2.setLabel(StatusEnum.QIYONG.getName());
        statusVo2.setValue(StatusEnum.QIYONG.getValue());
        statusVoList.add(statusVo2);*//*
        for (StatusEnum status :StatusEnum.values()) {
            StatusVo statusVo=new StatusVo();
            statusVo.setLabel(status.getName());
            statusVo.setValue(status.getValue());
            statusVoList.add(statusVo);
        }
        return ResultBuliderVo.success(statusVoList);
    }*/
}
