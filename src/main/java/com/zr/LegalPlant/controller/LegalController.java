package com.zr.LegalPlant.controller;

import com.zr.LegalPlant.model.*;
import com.zr.LegalPlant.service.LegalService;
import com.zr.util.ResultBuliderVo;
import com.zr.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class LegalController {

    @Autowired
    private LegalService legalService;

    //查询所有的法人
    /*@GetMapping("legalPerson/ofCurrentUser")
    public ResultVo queryLegal(){
        ResultVo resultVo = legalService.queryLegal();
        return  resultVo;
    }*/

    //添加
    @PostMapping("legalPlant/add")
    public ResultVo add(@RequestBody @Valid PlantAddVo plantAddVo, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultBuliderVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        ResultVo resultVo = legalService.addPlant(plantAddVo);
        return resultVo;
    }

    //分页
    @PostMapping("legalPlant/query")
    public ResultVo queryPage(@RequestBody LegalPlantSeleteVo legalPlantSeleteVo){
        ResultVo resultVo = legalService.queryPage(legalPlantSeleteVo);
        return  resultVo;
    }

    //修改状态
    @PostMapping("legalPlant/updatestatus")
    public ResultVo updatestatus(@RequestBody @Valid LegalPlantUpdateStatusVo legalPlantUpdateStatusVo,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultBuliderVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        ResultVo resultVo = legalService.updateStatus(legalPlantUpdateStatusVo);
        return resultVo;
    }

    //修改法人工厂
    @PostMapping("legalPlant/update")
    public ResultVo updatePlant(@RequestBody @Valid LegalPlantUpdateVo legalPlantUpdateVo,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultBuliderVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        ResultVo resultVo = legalService.updatePlant(legalPlantUpdateVo);
        return resultVo;
    }
}
