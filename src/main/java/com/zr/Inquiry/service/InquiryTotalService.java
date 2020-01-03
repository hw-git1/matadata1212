package com.zr.Inquiry.service;

import com.zr.Inquiry.model.*;
import com.zr.util.ResultVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface InquiryTotalService {
    ResultVo quereyLegalPerson();

    ResultVo querySupplier(Integer legalPersonId);

    ResultVo xiaLa();

    ResultVo queryPage(InquirySelectVo inquirySelectVo);

    ResultVo see(String requestForQuotationId);

    ResultVo addInquiry(InquiryTotalAddVo inquiryTotalAddVo);

    ResultVo update(InquiryTotalAddVo inquiryTotalAddVo);

    ResultVo shenHe(InquiryTotalShenHeVo inquiryTotalShenHeVo);

    ResultVo shenPi(InquiryTotalShenPiVo inquiryTotalShenPiVo);

    ResultVo importPlanClass(MultipartFile file) throws IOException;

    ResultVo baoJia(BaoJiaSelectVo baoJiaSelectVo);
}
