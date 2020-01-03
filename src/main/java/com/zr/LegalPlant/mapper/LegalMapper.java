package com.zr.LegalPlant.mapper;

import com.zr.LegalPlant.model.LegalPlantSeleteVo;
import com.zr.LegalPlant.model.LegalSeleteVo;
import com.zr.LegalPlant.model.PlantVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LegalMapper {

    @Select("select count(id) from plant where legalPlantCode=#{legalPlantCode}")
    int queryPCode(String legalPlantCode);

    @Select("select count(id) from plant where factoryName=#{legalPlantName}")
    int queryPName(String legalPlantName);

    @Select("select legalPersonCode,legalPersonName from legalperson where userId=1")
    List<LegalSeleteVo> queryLegal();

    @Select("select * from plant where id=#{id}")
    PlantVo queryPlant(Integer id);

    @Select("select * from plant where legalPlantCode=#{legalPlantCode}")
    PlantVo queryPlantByLegalPlantCode(String legalPlantCode);

    @Insert("insert into plant(legalPlantCode,legalPersonCode,legalPlantDec,enabled,createName,createTime,updateName,updateTime) values(#{legalPlantCode},#{legalPersonCode},#{legalPlantDec},#{enabled},#{createName},#{createTime},#{updateName},#{updateTime})")
    void addPlant(PlantVo plantVo);

    @Update("update plant set enabled=#{enabled},updateName=#{updateName},updateTime=#{updateTime} where id=#{id}")
    void updateStatus(PlantVo plantVo);

    @Update("update plant set legalPlantDec=#{legalPlantDec},enabled=#{enabled},updateName=#{updateName},updateTime=#{updateTime} where legalPlantCode=#{legalPlantCode}")
    void updatePlant(PlantVo plantVo);

    List<PlantVo> queryPageData(LegalPlantSeleteVo legalPlantSeleteVo);

    int queryCount(LegalPlantSeleteVo legalPlantSeleteVo);
}
