package com.zr.planSmallClass.controller;

import com.zr.planClass.model.PlanClassUpdateStatusVo;
import com.zr.planSmallClass.model.*;
import com.zr.planSmallClass.service.PlanSmallClassService;
import com.zr.util.ResultBuliderVo;
import com.zr.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PlanSmallClassController {

    @Autowired
    private PlanSmallClassService planSmallClassService;

    @PostMapping("subClass/add")
    public ResultVo add(@RequestBody @Valid PlanSmallClassAddVo planSmallClassAddVo, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultBuliderVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        ResultVo resultVo = planSmallClassService.add(planSmallClassAddVo);
        return resultVo;
    }

    @GetMapping("queryByid")
    public ResultVo queryByid(Integer id){
        if (id==null){
            return  ResultBuliderVo.error("查看时ID不能为空");
        }
        ResultVo resultVo = planSmallClassService.queryById(id);
        return resultVo;
    }



    @PostMapping("subClass/update")
    public ResultVo updateByid(@RequestBody @Valid PlanSmallClassUpdateVo planSmallClassUpdateVo,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultBuliderVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        ResultVo resultVo = planSmallClassService.update(planSmallClassUpdateVo);
        return resultVo;
    }

    @PostMapping("subClass/queryPage")
    public ResultVo queryCurrentPage(@RequestBody PlanSmallClassSelectVo planSmallClassSelectVo){
        ResultVo resultVo = planSmallClassService.queryPage(planSmallClassSelectVo);
        return resultVo;
    }

    @PostMapping("subClass/updateStatus")
    public ResultVo updateStatus(@RequestBody @Valid PlanSmallClassUpdateStatusVo planSmallClassUpdateStatusVo, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return  ResultBuliderVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        ResultVo resultVo=planSmallClassService.updateSmallStatus(planSmallClassUpdateStatusVo);
        return resultVo;
    }

    /*@GetMapping("metadata/getPullDownList")
    public ResultVo xiala(){
        List<StatusVo> statusVoList = new ArrayList<>();
        StatusVo statusVo = new StatusVo();
        statusVo.setLabel(0);
        statusVo.setValue("禁用");
        statusVoList.add(statusVo);
        StatusVo statusVo2 = new StatusVo();
        statusVo2.setLabel(1);
        statusVo2.setValue("启用");
        statusVoList.add(statusVo2);
        return ResultBuliderVo.success(statusVoList);
    }
*/
}
