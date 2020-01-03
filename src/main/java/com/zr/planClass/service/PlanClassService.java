package com.zr.planClass.service;

import com.zr.planClass.model.*;
import com.zr.util.ResultVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PlanClassService {
    ResultVo add(List<PlanClassAddVo> planClassAddVo);

    ResultVo queryById(Integer id);

    ResultVo UpdateLegalperson(PlanClassUpdateVo planClassUpdateVo);

    ResultVo updateStatus(PlanClassUpdateStatusVo planClassUpdateStatusVo);

    ResultVo queryPage(PlanClassSelectVo planClassSelectVo);

    ResultVo importPlanClass(MultipartFile file)throws Exception;

    ResultVo export(HttpServletResponse response,String code,String name,Integer status);
}
