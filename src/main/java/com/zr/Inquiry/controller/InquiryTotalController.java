package com.zr.Inquiry.controller;

import com.zr.Inquiry.model.BaoJiaSelectVo;
import com.zr.Inquiry.model.InquirySelectVo;
import com.zr.Inquiry.model.InquiryTotalAddVo;
import com.zr.Inquiry.model.InquiryTotalVo;
import com.zr.Inquiry.service.InquiryTotalService;
import com.zr.util.ResultBuliderVo;
import com.zr.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class InquiryTotalController {

    @Autowired
    private InquiryTotalService inquiryTotalService;

    @GetMapping("legalPerson/ofCurrentUser")
    public ResultVo legalPerson(){
        ResultVo resultVo = inquiryTotalService.quereyLegalPerson();
        return resultVo;
    }

    @PostMapping("inquiryList/add")
    public ResultVo add(@RequestBody @Valid InquiryTotalAddVo inquiryTotalAddVo, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultBuliderVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        ResultVo resultVo = inquiryTotalService.addInquiry(inquiryTotalAddVo);
        return resultVo;
    }
    @GetMapping("partner/queryByLegalPerson")
    public ResultVo querySupplier(@RequestParam("legalPersonId") Integer legalPersonId){
        legalPersonId=1;
        ResultVo resultVo = inquiryTotalService.querySupplier(legalPersonId);
        return resultVo;
    }
    @PostMapping("metadata/getPullDownList")
    public ResultVo xiala(){
        ResultVo resultVo = inquiryTotalService.xiaLa();
        return  resultVo;
    }

    @PostMapping("inquiryList/search")
    public ResultVo queryPage(@RequestBody InquirySelectVo inquirySelectVo){
        ResultVo resultVo = inquiryTotalService.queryPage(inquirySelectVo);
        return resultVo;
    }

    @PostMapping("inquirySheet/see")
    public ResultVo see(@RequestBody InquiryTotalVo inquiryTotalVo){
        ResultVo resultVo = inquiryTotalService.see(inquiryTotalVo.getRequestForQuotationId());
        return resultVo;
    }

    @PostMapping("importInquirySpare")
    public ResultVo importPlanClass(MultipartFile file)throws Exception{
        ResultVo resultVo=inquiryTotalService.importPlanClass(file);
        return resultVo;
    }

    @PostMapping("baojia")
    public ResultVo baojia(@RequestBody BaoJiaSelectVo baoJiaSelectVo){
        ResultVo resultVo=inquiryTotalService.baoJia(baoJiaSelectVo);
        return resultVo;
    }

}
