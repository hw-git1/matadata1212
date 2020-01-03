package com.zr.planSmallClass.mapper;

import com.zr.planClass.model.PlanClassSelectVo;
import com.zr.planClass.model.PlanClassVo;
import com.zr.planSmallClass.model.PlanSmallClassSelectVo;
import com.zr.planSmallClass.model.PlanSmallClassVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PlanSmallClassMapper {

    @Select("select count(name) from plansmallclass where name=#{name}")
    int queryByName(String name);

    @Select("select count(code) from plansmallclass where code=#{code}")
    int queryByCode(String code);

    @Insert("insert into plansmallclass(code,name,remark,status) values(#{code},#{name},#{remark},#{status})")
    void addPlanSmallClass(PlanSmallClassVo planSmallClassVo);

    @Select("select * from plansmallclass where id=#{id}")
    PlanSmallClassVo queryById(Integer id);

    @Update("update plansmallclass set name=#{name},remark=#{remark},status=#{status},updateName=#{updateName},updateTime=#{updateTime} where id=#{id}")
    void UpdateLegalperson(PlanSmallClassVo planSmallClassVo);




    @Update("update plansmallclass set status=#{status},updateName=#{updateName},updateTime=#{updateTime} where id=#{id}")
    void UpdatePlanSmallClassStatus(PlanSmallClassVo planSmallClassVo);

    int queryCount(PlanSmallClassSelectVo planSamllClassSelectVo);

    List<PlanSmallClassVo> queryPageData(PlanSmallClassSelectVo planSamllClassSelectVo);
}
