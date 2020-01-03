package com.zr.planClass.service.Impl;

import com.zr.EnumPackage.StatusEnum;
import com.zr.planClass.mapper.PlanClassMapper;
import com.zr.planClass.model.*;
import com.zr.planClass.service.PlanClassService;
import com.zr.util.*;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class PlanClassServiceImpl implements PlanClassService {

    @Autowired
    private PlanClassMapper planClassMapper;

    @Override
    @Transactional
    public ResultVo add(List<PlanClassAddVo> planClassAddVoList) {
        //根据前段传过来的名称查询数据库.查看是否存在数据
       /* int nameCount=planClassMapper.queryName(planClassAddVo.getName());
        if (nameCount>0){
            return ResultBuliderVo.error("名称已经存在");
        }
        //根据前段传过来的编码查询数据库,查看是否存在数据
        int codeCount=planClassMapper.queryByCode(planClassAddVo.getCode());
        if (codeCount>0){
            return ResultBuliderVo.error("编码已经存在");
        }*/
       /* Date date=new Date();
        PlanClassVo planClassVo=new PlanClassVo();
        BeanUtils.copyProperties(planClassAddVo,planClassVo);
      *//*  planClassVo.setCreateName("张三");
        planClassVo.setCreateTime(date);
        planClassVo.setUpdateName("张三");
        planClassVo.setUpdateTime(date);*/

        planClassMapper.insertPlanClass(planClassAddVoList);
        return ResultBuliderVo.success();
    }

    @Override
    public ResultVo queryById(Integer id) {
        PlanClassVo planClassVo=planClassMapper.queryById(id);
        if (!planClassVo.getStatus()){
            planClassVo.setStatusName("禁用");
        }
        if (planClassVo.getStatus()){
            planClassVo.setStatusName("启用");
        }
        if (planClassVo==null) {
            return ResultBuliderVo.error("不存在此对象");
        }
        return ResultBuliderVo.success(planClassVo);
    }

    @Override
    @Transactional
    public ResultVo UpdateLegalperson(PlanClassUpdateVo planClassUpdateVo) {
        PlanClassVo planClassVo = planClassMapper.queryById(planClassUpdateVo.getId());
        if (planClassVo==null){
            return ResultBuliderVo.error("此对象不存在");
        }
        //验证名称是否存在
        List<PlanClassVo> planClassVoList = planClassMapper.queryByName(planClassUpdateVo.getName());
        if (!CollectionUtils.isEmpty(planClassVoList)){
            if (planClassVoList.get(0).getId()!=planClassUpdateVo.getId()){
                return ResultBuliderVo.error("名称已存在");
            }
        }
        PlanClassVo planClassVo1=new PlanClassVo();
        Date date=new Date();
        BeanUtils.copyProperties(planClassUpdateVo,planClassVo1);
        planClassVo1.setUpdateName("李四");
        planClassVo1.setUpdateTime(date);
        planClassMapper.UpdateLegalperson(planClassVo1);
        return ResultBuliderVo.success();
    }

    @Override
    @Transactional
    public ResultVo updateStatus(PlanClassUpdateStatusVo planClassUpdateStatusVo) {
        PlanClassVo planClassVo = planClassMapper.queryById(planClassUpdateStatusVo.getId());
        if (planClassVo==null){
            return ResultBuliderVo.error("此对象不存在");
        }
        PlanClassVo planClassVo1=new PlanClassVo();
        Date date=new Date();
        BeanUtils.copyProperties(planClassUpdateStatusVo,planClassVo1);
        planClassVo1.setUpdateName("李四");
        planClassVo1.setUpdateTime(date);
        planClassMapper.UpdatePlanClassStatus(planClassVo1);
        return ResultBuliderVo.success();

    }

    @Override
    public ResultVo queryPage(PlanClassSelectVo planClassSelectVo) {
        //1.查询条数
        //2.查询数据
        List<PlanClassVo> planClassVoList = planClassMapper.queryPageData(planClassSelectVo);
        if (!CollectionUtils.isEmpty(planClassVoList)){
            for (PlanClassVo planClassVo:planClassVoList){
                if (!planClassVo.getStatus()){
                    planClassVo.setStatusName("禁用");
                }
                if (planClassVo.getStatus()){
                    planClassVo.setStatusName("启用");
                }
            }
        }

        int count = planClassMapper.queryCount(planClassSelectVo);
        AllRecords allRecords = new AllRecords();
        allRecords.setDataList(planClassVoList);
        allRecords.setTotalNumber(count);
        allRecords.setPageIndex(planClassSelectVo.getPageIndex());
        allRecords.setPageSize(planClassSelectVo.getPageSize());
        allRecords.resetTotalNumber(count);
        //3.设置响应参数
        return ResultBuliderVo.success(allRecords);
    }

    @Override
    public ResultVo importPlanClass(MultipartFile file) throws Exception{
        //1.给用户提供导入模板
        //2.提示版本问题，询问产品经理支持哪种版本的导入格式，使用2007版本的导入模板，根据版本不同，poi导入技术的读取方式不同
        if (!file.getOriginalFilename().contains("xlsx")){
            return ResultBuliderVo.error("文件必须是2007版本");
        }
        ResultVo<List<PlanClassAddVo>> resultVo = readFile(file);
        if (!resultVo.getSuccess()){
            return resultVo;
        }
        List<PlanClassAddVo> planClassAddVoList=resultVo.getData();
        ResultVo validate = validate(planClassAddVoList);
        if (!validate.getSuccess()){
            return validate;
        }
        ResultVo add = add(planClassAddVoList);
        if (!add.getSuccess()){
            return add;
        }
        return ResultBuliderVo.success();
    }



    //        5.验证不为空字段是否为空
//        6.验证字符长度是否符合需求
//        7.验证数据是否存在也就是是否合法
//        8.验证数据是否重复
//        9.对于需要转换的数据进行数据转换，比如数据库存的数据为数字格式，用户输入的为汉字，需要把汉字转换为数字存储
    //10.验证状态输入的是否是启用禁用。
    public ResultVo validate(List<PlanClassAddVo> planClassAddVoList){
        List<String> codeList=new ArrayList<>();
        for (PlanClassAddVo planClassAddVo:planClassAddVoList){
            if (StringUtils.isEmpty(planClassAddVo.getCode())){
                return ResultBuliderVo.error("编码不能为空");
            }else{
                if (planClassAddVo.getCode().length()>30){
                    return ResultBuliderVo.error("编码长度不能大于30");
                }
            }
            if (StringUtils.isEmpty(planClassAddVo.getName())){
                return ResultBuliderVo.error("名称不能为空");
            }else {
                if (planClassAddVo.getName().length()>30){
                    return ResultBuliderVo.error("名称长度不能大于30");
                }
            }
            if (StatusEnum.getValue(planClassAddVo.getStatusName())==null){
                return ResultBuliderVo.error("状态只能是启用或者禁用");
            }else {
                Integer status=StatusEnum.getValue(planClassAddVo.getStatusName());
                Boolean booleanStatus=status==1?true:false;
                planClassAddVo.setStatus(booleanStatus);
            }
            codeList.add(planClassAddVo.getCode());
        }
        Set<String> setCode=new HashSet<>(codeList);
        if (setCode.size()!=codeList.size()){
            return ResultBuliderVo.error("编码不能重复");
        }
        return ResultBuliderVo.success(planClassAddVoList);
    }

    public ResultVo readFile(MultipartFile file) throws IOException {
        //3.模板是否是需要导入的模板，验证模板是否正确
        XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet=workbook.getSheetAt(0);
        if (!String.valueOf(sheet.getRow(sheet.getFirstRowNum()).getCell(0)).equals("计划大类导入模板")){
            return ResultBuliderVo.error("模板错误,请检查模板");
        }
        List<PlanClassAddVo> planClassAddVoList=new ArrayList<>();
        //遍历excel表格中的所有数据，从第五行开始读取，没有数据时终止遍历。
        for (int i=sheet.getFirstRowNum()+5;i<=sheet.getLastRowNum();i++){
            XSSFRow xssfRow=sheet.getRow(i);
            if (xssfRow!=null){
                if(String.valueOf(xssfRow.getCell(1)).trim().equals("") && String.valueOf(xssfRow.getCell(1)).trim().equals("null")
                        || String.valueOf(xssfRow.getCell(2)).trim().equals("") && String.valueOf(xssfRow.getCell(2)).trim().equals("null")
                        || String.valueOf(xssfRow.getCell(3)).trim().equals("") && String.valueOf(xssfRow.getCell(3)).trim().equals("null")
                        || String.valueOf(xssfRow.getCell(4)).trim().equals("") && String.valueOf(xssfRow.getCell(4)).trim().equals("null")){

                    ResultVo<PlanClassAddVo> resultVo=build(xssfRow);
                    if (!resultVo.getSuccess()){
                        return resultVo;
                    }
                    planClassAddVoList.add(resultVo.getData());
                }
            }
        }
        return ResultBuliderVo.success(planClassAddVoList);
    }
    private ResultVo<PlanClassAddVo> build(XSSFRow xssfRow) {
        PlanClassAddVo planClassAddVo=new PlanClassAddVo();
        planClassAddVo.setCode(String.valueOf(xssfRow.getCell(1)));
        planClassAddVo.setName(String.valueOf(xssfRow.getCell(2)));
        planClassAddVo.setRemark(String.valueOf(xssfRow.getCell(3)));
        planClassAddVo.setStatusName(String.valueOf(xssfRow.getCell(4)));
        return ResultBuliderVo.success(planClassAddVo);
    }



    //导出
    @SneakyThrows
    @Override
    public ResultVo export(HttpServletResponse response, String code, String name, Integer status) {
        PlanClassSelectVo planClassSelectVo=new PlanClassSelectVo();
        planClassSelectVo.setCode(code);
        planClassSelectVo.setName(name);
        planClassSelectVo.setStatus(status);
        List<PlanClassVo> planClassVoList=planClassMapper.queryExport(planClassSelectVo);
        //验证条数
        if (planClassVoList.size()>10000){
            return ResultBuliderVo.error("最多只能导出10000条数据");
        }
        //数据转换
        for(PlanClassVo planClassVo:planClassVoList){
            Integer newStatus=planClassVo.getStatus()==true?1:0;
            planClassVo.setStatusName(StatusEnum.getName(newStatus));
        }
        //把数据写入excel表格中
        //输出到浏览器

        //获取响应输出流
        ServletOutputStream out = response.getOutputStream();
        //给输出文件设置名称
        POIClass.toPackageOs(response, "计划大类导出");
        //读取模板中的数据
        InputStream in = ExportUtil.toPackageIn("templates/计划大类导出模板.xlsx");
        //根据模板的数据、把查询出来的数据给摸版SHeet1组中的数据赋值、把excel输出到浏览器上
        writeDataToExcel(in, "Sheet1", planClassVoList, out);
        if (in != null) {
            in.close();
            out.close();
        }
        return ResultBuliderVo.success();
    }

    // 由于此方法不能通用, 所以单独写在这里
    private void writeDataToExcel(InputStream in, String sheetName,
                                  List<PlanClassVo> resultList, ServletOutputStream out) throws Exception {
        //POi读取模板
        XSSFWorkbook wb = new XSSFWorkbook(in);
        //读取sheet1中的数据
        Sheet sheet = wb.getSheet(sheetName);
        if (sheet != null) {
            //向sheet1中赋值，设置样式
            toResultListValueInfo(sheet, resultList);
        }
        //把数据写入到输出流中
        wb.write(out);
        //关闭poi方法
        wb.close();
    }

    /**
     * 插入excel表中项目信息
     *
     * @param sheet
     */
    private void toResultListValueInfo(Sheet sheet, List<PlanClassVo> plantList) {
        //从第4行开始赋值
        int row_column = 5;
        //遍历数据集合
        for (PlanClassVo obj : plantList) {
            //创建一行的方法
            Row row = sheet.createRow(row_column);
            // 给第一列序号赋值赋值
            POIClass.toCellValue(row,0, obj.getId() + "");
            // 给第二列编码赋值
            POIClass.toCellValue(row, 1, obj.getCode() + "");
            // 给第3列名称赋值
            POIClass.toCellValue(row, 2, obj.getName() + "");
            // 给状态赋值
            POIClass.toCellValue(row, 3, obj.getStatusName() + "");
        /*    //给描述赋值
            POIClass.toCellValue(row, 4, obj.getRemark() + "");*/
            row_column++;
        }
    }
}
