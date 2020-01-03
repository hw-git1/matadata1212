package com.zr.planClass.mapper;

import com.zr.LegalPlant.model.PlantVo;
import com.zr.planClass.model.PlanClassAddVo;
import com.zr.planClass.model.PlanClassSelectVo;
import com.zr.planClass.model.PlanClassUpdateVo;
import com.zr.planClass.model.PlanClassVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlanClassMapper {

    @Select("select * from planClass where name=#{name}")
    List<PlanClassVo> queryByName(String name);

    @Select("select count(name) from planClass where name=#{name}")
    int queryName(String name);

    @Select("select count(code) from planClass where code=#{code}")
    int queryByCode(String code);

    @Insert("insert into planClass(code,name,remark,status) values(#{code},#{name},#{remark},#{status})")
    void addPlanClass(PlanClassVo planClassVo);

    @Select("select * from planClass where id=#{id}")
    PlanClassVo queryById(Integer id);

    @Update("update planClass set name=#{name},remark=#{remark},status=#{status},updateName=#{updateName},updateTime=#{updateTime} where id=#{id}")
    void UpdateLegalperson(PlanClassVo planClassVo);




    @Update("update planClass set status=#{status},updateName=#{updateName},updateTime=#{updateTime} where id=#{id}")
    void UpdatePlanClassStatus(PlanClassVo planClassVo);

    int queryCount(PlanClassSelectVo planClassSelectVo);

    List<PlanClassVo> queryPageData(PlanClassSelectVo planClassSelectVo);

    void insertPlanClass(List<PlanClassAddVo> planClassAddVoList);

    List<PlanClassVo> queryExport(PlanClassSelectVo planClassSelectVo);
}
