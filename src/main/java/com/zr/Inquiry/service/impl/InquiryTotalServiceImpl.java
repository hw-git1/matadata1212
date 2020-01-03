package com.zr.Inquiry.service.impl;

import com.zr.EnumPackage.*;
import com.zr.Inquiry.mapper.InquiryTotalMapper;
import com.zr.Inquiry.model.*;
import com.zr.Inquiry.service.InquiryTotalService;
import com.zr.planClass.model.StatusVo;
import com.zr.util.AllRecords;
import com.zr.util.ResultBuliderVo;
import com.zr.util.ResultVo;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class InquiryTotalServiceImpl implements InquiryTotalService {

    @Autowired
    private InquiryTotalMapper inquiryTotalMapper;

    @Override
    public ResultVo quereyLegalPerson() {
        List<LegalPullDownVo> legalPullDownVoList = inquiryTotalMapper.queryLegalPerson();
        return ResultBuliderVo.success(legalPullDownVoList);
    }

    @Override
    public ResultVo querySupplier(Integer legalPersonId) {
        List<SupplierSelectVo> supplierSelectVos = inquiryTotalMapper.querySupplier(legalPersonId);
        return ResultBuliderVo.success(supplierSelectVos);
    }

    @Override
    public ResultVo xiaLa() {
        List<XiaLaVo> isTaxList=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        for (IsTax isTax:IsTax.values()){
            XiaLaVo xiaLaVo=new XiaLaVo();
            xiaLaVo.setLable(isTax.getName());
            xiaLaVo.setValue(isTax.getValue());
            isTaxList.add(xiaLaVo);
            map.put("isTax",isTaxList);
        }
        List<XiaLaVo> typeList=new ArrayList<>();
        for (TypeEnum type:TypeEnum.values()){
            XiaLaVo xiaLaVo=new XiaLaVo();
            xiaLaVo.setLable(type.getName());
            xiaLaVo.setValue(type.getValue());
            typeList.add(xiaLaVo);
            map.put("type",typeList);
        }
        List<XiaLaVo> inquiryStatusList=new ArrayList<>();
        for (InquiryStatus inquiryStatus:InquiryStatus.values()){
            XiaLaVo xiaLaVo=new XiaLaVo();
            xiaLaVo.setLable(inquiryStatus.getName());
            xiaLaVo.setValue(inquiryStatus.getValue());
            inquiryStatusList.add(xiaLaVo);

            map.put("inquiryStatus",inquiryStatusList);
        }
        List<XiaLaVo> currencyCodeList=new ArrayList<>();
        for (CurrencyCode currencyCode:CurrencyCode.values()){
            XiaLaVo xiaLaVo=new XiaLaVo();
            xiaLaVo.setLable(currencyCode.getName());
            xiaLaVo.setValue(currencyCode.getValue());
            currencyCodeList.add(xiaLaVo);
            map.put("currencyCode",currencyCodeList);
        }
        List<StatusVo> statusVoList=new ArrayList<>();
        for (StatusEnum status : StatusEnum.values()) {
            StatusVo statusVo=new StatusVo();
            statusVo.setLabel(status.getName());
            statusVo.setValue(status.getValue());
            statusVoList.add(statusVo);
        }
        return ResultBuliderVo.success(map);
    }

    @Override
    public ResultVo queryPage(InquirySelectVo inquirySelectVo) {
        List<InquiryTotalVo> inquiryTotalVoList=inquiryTotalMapper.queryPageData(inquirySelectVo);
        int count = inquiryTotalMapper.queryCount(inquirySelectVo);
        AllRecords allRecords=new AllRecords();
        allRecords.setDataList(inquiryTotalVoList);
        allRecords.setTotalNumber(count);
        allRecords.setPageSize(inquirySelectVo.getPageSize());
        allRecords.setPageIndex(inquirySelectVo.getPageIndex());
        allRecords.resetTotalNumber(count);
        return ResultBuliderVo.success(allRecords);
    }

    @Override
    public ResultVo see(String requestForQuotationId) {
        List<InquiryTotalVo> inquiryTotalVoList=new ArrayList<>();
        InquiryTotalVo inquiryTotalVo = inquiryTotalMapper.queryInquiryTotal(requestForQuotationId);
        //inquiryTotalVo.setTypeName(TypeEnum.getName(inquiryTotalVo.getType()));
        //根据单号查询询价信息
        List<InquirySpareSelectVo> inquirySpareSelectVos = inquiryTotalMapper.querySpareById(inquiryTotalVo.getId());

        List<InquirySupplierSelectVo> inquirySupplierSelectVos = inquiryTotalMapper.querySupplierById(inquiryTotalVo.getId());
        inquiryTotalVo.setInquirySpareSelectVoList(inquirySpareSelectVos);
        inquiryTotalVo.setInquirySupplierSelectVoList(inquirySupplierSelectVos);
        inquiryTotalVoList.add(inquiryTotalVo);
        return ResultBuliderVo.success(inquiryTotalVoList);
    }

    @Override
    public ResultVo addInquiry(InquiryTotalAddVo inquiryTotalAddVo) {
        //业务验证
        //验证询价类型、询价币别、是否含税、询价开始日期是否在结束日期之前
        ResultVo resultVo = check(inquiryTotalAddVo);
        if (!resultVo.getSuccess()){
            return  resultVo;
        }
        InquiryTotalVo inquiryTotalVo=new InquiryTotalVo();
        BeanUtils.copyProperties(inquiryTotalAddVo,inquiryTotalVo);
        Date nowDate = new Date();
        inquiryTotalVo.setCreateTime(nowDate);
        inquiryTotalVo.setCreatorName("王五");
        inquiryTotalVo.setUpdateTime(nowDate);
        inquiryTotalVo.setUpdateName("王五");
        inquiryTotalMapper.insertInquiryTotal(inquiryTotalVo);
        for (InquirySpareDetail inquirySpareDetail:inquiryTotalAddVo.getInquirySpareDetailList()){
            inquirySpareDetail.setInquiryId(inquiryTotalVo.getId());
        }
        inquiryTotalMapper.insertSpareDetail(inquiryTotalAddVo.getInquirySpareDetailList());
        for (InquirySupplierDetail inquirySupplierDetail:inquiryTotalAddVo.getInquirySupplierDetailList())
        {
            inquirySupplierDetail.setInquiryId(inquiryTotalVo.getId());
        }
        inquiryTotalMapper.insertSupplierDetail(inquiryTotalAddVo.getInquirySupplierDetailList());
        return ResultBuliderVo.success();
    }

    @Override
    public ResultVo update(InquiryTotalAddVo inquiryTotalAddVo) {
        InquiryTotalVo inquiryTotalVo1 = inquiryTotalMapper.queryInquiryTotal(inquiryTotalAddVo.getRequestForQuotationId());
        if (inquiryTotalVo1==null){

        }
        ResultVo resultVo = check(inquiryTotalAddVo);
        if (!resultVo.getSuccess()){
            return  resultVo;
        }
        InquiryTotalVo inquiryTotalVo=new InquiryTotalVo();
        BeanUtils.copyProperties(inquiryTotalAddVo,inquiryTotalVo);
        inquiryTotalVo.setUpdateName("李四");
        inquiryTotalVo.setUpdateTime(new Date());
        inquiryTotalMapper.updateInquiryTotal(inquiryTotalVo);
        for (InquirySpareDetail inquirySpareDetail:inquiryTotalAddVo.getInquirySpareDetailList()){
            inquirySpareDetail.setInquiryId(inquiryTotalVo.getId());
        }
        inquiryTotalMapper.updateSpareDetail(inquiryTotalAddVo.getInquirySpareDetailList());
        for (InquirySupplierDetail inquirySupplierDetail:inquiryTotalAddVo.getInquirySupplierDetailList())
        {
            inquirySupplierDetail.setInquiryId(inquiryTotalVo.getId());
        }
        inquiryTotalMapper.updateSupplierDetail(inquiryTotalAddVo.getInquirySupplierDetailList());
        return ResultBuliderVo.success();
    }

    @Override
    public ResultVo shenHe(InquiryTotalShenHeVo inquiryTotalShenHeVo) {
        inquiryTotalMapper.shenHe(inquiryTotalShenHeVo);
        return ResultBuliderVo.success();
    }

    @Override
    public ResultVo shenPi(InquiryTotalShenPiVo inquiryTotalShenPiVo) {
        inquiryTotalMapper.shenPi(inquiryTotalShenPiVo);
        return ResultBuliderVo.success();

    }

    @Override
    public ResultVo importPlanClass(MultipartFile file) throws IOException {
        //1.给用户提供导入模板
        //2.提示版本问题，询问产品经理支持哪种版本的导入格式，使用2007版本的导入模板，根据版本不同，poi导入技术的读取方式不同
        if (!file.getOriginalFilename().contains("xlsx")){
            return ResultBuliderVo.error("文件必须是2007版本");
        }
        ResultVo<List<InquirySpareSelectVo>> resultVo = readFile(file);
        if (!resultVo.getSuccess()){
            return resultVo;
        }
        List<InquirySpareSelectVo> inquirySpareSelectVoList=resultVo.getData();
        ResultVo validate = validate(inquirySpareSelectVoList);
        if (!validate.getSuccess()){
            return validate;
        }

        return ResultBuliderVo.success();
    }

    @Override
    public ResultVo baoJia(BaoJiaSelectVo baoJiaSelectVo) {
        List<InquirySpareSelectVo> inquirySpareSelectVoList=inquiryTotalMapper.queryBaoJia(baoJiaSelectVo);
        return ResultBuliderVo.success(inquirySpareSelectVoList);
    }

    public ResultVo validate(List<InquirySpareSelectVo> inquirySpareSelectVoList){
        List<String> codeList=new ArrayList<>();
        for (InquirySpareSelectVo inquirySpareSelectVo:inquirySpareSelectVoList){
            if (StringUtils.isEmpty(inquirySpareSelectVo.getCurrencyCode())){
                return ResultBuliderVo.error("编码不能为空");
            }else {
                if (inquirySpareSelectVo.getSpareCoding().length()>30){
                    return ResultBuliderVo.error("编码长度不能超过30");
                }
            }
            if (StringUtils.isEmpty(inquirySpareSelectVo.getSpareName())){
                return ResultBuliderVo.error("名称不能为空");
            }else {
                if (inquirySpareSelectVo.getSpareName().length()>30){
                    return ResultBuliderVo.error("名称长度不能超过30");
                }
            }
            if (CurrencyCode.getValue(inquirySpareSelectVo.getCurrencyName())==null){
                return ResultBuliderVo.error("币种只能是人民币或者美元");
            }else {
                /*Integer currencyCode=CurrencyCode.getValue(inquirySpareSelectVo.getCurrencyName());
                Integer newCurrencyCode=currencyCode==0?CurrencyCode.RMB.getValue():CurrencyCode.＄.getValue();
                System.out.println("newCurrencyCode=========="+newCurrencyCode);*/
               /* CurrencyCodeEnum.getIndex(inquirySpareCodeVo.getCurrencyName())*/
                inquirySpareSelectVo.setCurrencyCode(CurrencyCode.getValue(inquirySpareSelectVo.getCurrencyName()));
            }
            codeList.add(inquirySpareSelectVo.getSpareCoding());
        }
        Set<String> setCode=new HashSet<>(codeList);
        if (setCode.size()!=codeList.size()){
            return ResultBuliderVo.error("编码不能重复");
        }
        return ResultBuliderVo.success(inquirySpareSelectVoList);
    }
    public ResultVo readFile(MultipartFile file) throws IOException {
        XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet=workbook.getSheetAt(0);
        if (!String.valueOf(sheet.getRow(sheet.getFirstRowNum()).getCell(0)).equals("询价导入模板")){
            return ResultBuliderVo.error("模板错误,请检查模板");
        }
        System.out.println(sheet.getRow(sheet.getFirstRowNum()).getCell(0));
        List<InquirySpareSelectVo> inquirySpareSelectVoList=new ArrayList<>();
        for (int i=sheet.getFirstRowNum()+5;i<sheet.getLastRowNum();i++){
            XSSFRow xssfRow=sheet.getRow(i);
            if (xssfRow!=null){
                System.out.println(String.valueOf(xssfRow.getCell(1)));
                System.out.println(String.valueOf(xssfRow.getCell(2)));
                System.out.println(String.valueOf(xssfRow.getCell(3)));
                System.out.println(String.valueOf(xssfRow.getCell(4)));
                if(String.valueOf(xssfRow.getCell(1)).trim().equals("") && String.valueOf(xssfRow.getCell(1)).trim().equals("null")
                        || String.valueOf(xssfRow.getCell(2)).trim().equals("") && String.valueOf(xssfRow.getCell(2)).trim().equals("null")
                        || String.valueOf(xssfRow.getCell(3)).trim().equals("") && String.valueOf(xssfRow.getCell(3)).trim().equals("null")
                        || String.valueOf(xssfRow.getCell(4)).trim().equals("") && String.valueOf(xssfRow.getCell(4)).trim().equals("null")){
                    ResultVo<InquirySpareSelectVo> resultVo=build(xssfRow);
                    if (!resultVo.getSuccess()){
                        return resultVo;
                    }
                    inquirySpareSelectVoList.add(resultVo.getData());
               }
            }
        }
        return ResultBuliderVo.success();
    }

    private ResultVo<InquirySpareSelectVo> build(XSSFRow xssfRow) {
        InquirySpareSelectVo inquirySpareSelectVo=new InquirySpareSelectVo();
        try {
            inquirySpareSelectVo.setSpareCoding(String.valueOf(xssfRow.getCell(1)));
            inquirySpareSelectVo.setSpareName(String.valueOf(xssfRow.getCell(2)));
            inquirySpareSelectVo.setMeasurementUnit(String.valueOf(xssfRow.getCell(3)));
            inquirySpareSelectVo.setCurrencyName(String.valueOf(xssfRow.getCell(4)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResultBuliderVo.error("请检查文本格式");
        }
        return ResultBuliderVo.success();
    }

    public ResultVo check(InquiryTotalAddVo inquiryTotalAddVo){
        ResultVo resultVo = checkEnum(inquiryTotalAddVo);
        if (!resultVo.getSuccess()){
            return resultVo;
        }
        ResultVo resultVo1 = checkRepeat(inquiryTotalAddVo);
        if (!resultVo1.getSuccess()){
            return resultVo1;
        }
        ResultVo resultVo2 = checkSupplier(inquiryTotalAddVo);
        if (!resultVo2.getSuccess()){
            return resultVo2;
        }
        ResultVo resultVo3 = checkSpareCunZai(inquiryTotalAddVo);
        if (!resultVo3.getSuccess()){
            return resultVo3;
        }
        ResultVo resultVo4 = checkHeFa(inquiryTotalAddVo);
        if(!resultVo4.getSuccess()){
            return resultVo4;
        }
        return ResultBuliderVo.success();

    }
    public ResultVo checkHeFa(InquiryTotalAddVo inquirytotalAddVo){
        List<String> codeList = new ArrayList<>();
        for (InquirySpareDetail inquirySpareDetail: inquirytotalAddVo.getInquirySpareDetailList()){
            codeList.add(inquirySpareDetail.getSpareCoding());
        }
        List<String> SuppliserCodeList = new ArrayList<>();
        for (InquirySupplierDetail inquirySupplierDetail: inquirytotalAddVo.getInquirySupplierDetailList()){
            SuppliserCodeList.add(inquirySupplierDetail.getVendorCode());
        }
        //全部的中间表信息
        List<SpareSupplierVo> spareSupplierVoList = inquiryTotalMapper.querySpareSupplierBySpareCodeList(codeList);
        //把每个备件能被生产的供应商找出来放到集合中。
        for (String code:codeList){
            //每个备件的供应商集合
            List<String> SuppliserCodeList2 = new ArrayList<>();
            for (SpareSupplierVo spareSupplierVo:spareSupplierVoList){
                if (code.equals(spareSupplierVo.getSpareCoding())){
                    SuppliserCodeList2.add(spareSupplierVo.getVendorCode());
                }
            }
            //disjoint判断是否存在交集，true代表没有交集，false代表存在交集。
            if (Collections.disjoint(SuppliserCodeList2,SuppliserCodeList)){
                return ResultBuliderVo.error("备件编码："+code+"没有合法的供应商，它的合法供应商为："+SuppliserCodeList2);
            }
        }
        return ResultBuliderVo.success();
    }
    //验证备件是否都存在
    public ResultVo checkSpareCunZai(InquiryTotalAddVo inquiryTotalAddVo){
        List<String> codeList=new ArrayList<>();
        for (InquirySpareDetail inquirySpareDetail:inquiryTotalAddVo.getInquirySpareDetailList()){
            codeList.add(inquirySpareDetail.getSpareCoding());
        }
        List<String> inquirySpareDetailList=inquiryTotalMapper.queryByCodeList(codeList);
        if (inquirySpareDetailList.size()!=codeList.size()){
            codeList.removeAll(inquirySpareDetailList);
            return  ResultBuliderVo.error("存在不合法的备件,不合法的备件为:"+codeList);
        }
        List<String> vendorCodeList=new ArrayList<>();
        for (InquirySupplierDetail inquirySupplierDetail:inquiryTotalAddVo.getInquirySupplierDetailList()){
            vendorCodeList.add(inquirySupplierDetail.getVendorCode());
        }
        List<String> inquirySupplierDetailList=inquiryTotalMapper.queryBySupplierCodeList(vendorCodeList);
        if (inquirySupplierDetailList.size()!=vendorCodeList.size()){
            vendorCodeList.removeAll(inquirySupplierDetailList);
            return  ResultBuliderVo.error("存在不合法的供应商,不合法的供应商为:"+vendorCodeList);
        }
        return ResultBuliderVo.success();
    }


        //5.验证备件明细不能重复
    public ResultVo checkRepeat(InquiryTotalAddVo inquiryTotalAddVo){
        List<String> spareNameList=new ArrayList<>();
        List<InquirySpareDetail> inquirySpareDetailList=inquiryTotalAddVo.getInquirySpareDetailList();
        for (InquirySpareDetail inquirySpareDetail:inquirySpareDetailList){
            spareNameList.add(inquirySpareDetail.getSpareCoding());
        }
        List<String> newSpareNameList=new ArrayList<>();
        List<String> repeatSpareNameList=new ArrayList<>();
        for (String code:spareNameList){
            if (newSpareNameList.contains(code)){
                repeatSpareNameList.add(code);
            }
            newSpareNameList.add(code);
        }
        if (repeatSpareNameList.size()>0){
            return  ResultBuliderVo.error("存在重复的备件编码,重复的备件编码为:"+repeatSpareNameList);
        }
        return  ResultBuliderVo.success();
    }
    //        6.验证供应商明细不能重复
    public ResultVo checkSupplier(InquiryTotalAddVo inquiryTotalAddVo){
        List<String> supplierNameList=new ArrayList<>();
        List<InquirySupplierDetail> inquirySupplierDetailList=inquiryTotalAddVo.getInquirySupplierDetailList();
        for (InquirySupplierDetail inquirySupplierDetail:inquirySupplierDetailList){
            supplierNameList.add(inquirySupplierDetail.getVendorCode());
        }
        List<String> newSupplierNameList=new ArrayList<>();
        List<String> repeatSupplierNameList=new ArrayList<>();
        for (String code:supplierNameList){
            if (newSupplierNameList.contains(code)){
                repeatSupplierNameList.add(code);
            }
            newSupplierNameList.add(code);
        }
        if (repeatSupplierNameList.size()>0){
            return ResultBuliderVo.error("存在重复的供应商编码,重复的供应商编码为:"+repeatSupplierNameList);
        }
        return ResultBuliderVo.success();
    }

    //10.验证询价类型、询价币别、是否含税、询价开始日期是否在结束日期之前
    public ResultVo checkEnum(InquiryTotalAddVo inquiryTotalAddVo){

        if (TypeEnum.getName(inquiryTotalAddVo.getType())==null){
            return ResultBuliderVo.error("没有此询价类型");
        }
        if (CurrencyCode.getName(inquiryTotalAddVo.getCurrencyCode())==null){
            return ResultBuliderVo.error("没有此询价币种");
        }
        if (IsTax.getName(inquiryTotalAddVo.getIsTax())==null){
            return ResultBuliderVo.error("是否含税不存在");
        }
        if (InquiryStatus.getName(inquiryTotalAddVo.getInquiryStatus())==null){
            return ResultBuliderVo.error("没有此询价币种");
        }
        int res= inquiryTotalAddVo.getStartTime().compareTo(inquiryTotalAddVo.getEndTime());
        if(res>0){
            return ResultBuliderVo.error("询价开始日期必须在询价结束日期之前");
        }
        return ResultBuliderVo.success();
    }
}
