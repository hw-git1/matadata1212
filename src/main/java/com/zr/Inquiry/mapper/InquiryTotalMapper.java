package com.zr.Inquiry.mapper;

import com.zr.Inquiry.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface InquiryTotalMapper {

    @Select("select legalPersonId,legalPersonName from legalperson where userId=1")
    List<LegalPullDownVo> queryLegalPerson();

    List<SupplierSelectVo> querySupplier(Integer legalPersonId);

    List<InquiryTotalVo> queryPageData(InquirySelectVo inquirySelectVo);

    List<String> queryByCodeList(List<String> codeList);

    List<SpareSupplierVo> querySpareSupplierBySpareCodeList(List<String> codeList);

    void insertInquiryTotal(InquiryTotalVo inquiryTotalVo);

    int queryCount(InquirySelectVo inquirySelectVo);

    InquiryTotalVo queryInquiryTotal(String requestForQuotationId);

    List<InquirySpareSelectVo> querySpareById(Integer inquiryId);

    List<InquirySupplierSelectVo> querySupplierById(Integer inquiryId);

    List<String> queryBySupplierCodeList(List<String> codeList);

    void insertSpareDetail(List<InquirySpareDetail> inquirySpareDetailList);

    void insertSupplierDetail(List<InquirySupplierDetail> inquirySupplierDetailList);

    void updateInquiryTotal(InquiryTotalVo inquiryTotalVo);

    void updateSpareDetail(List<InquirySpareDetail> inquirySpareDetailList);

    void updateSupplierDetail(List<InquirySupplierDetail> inquirySupplierDetailList);

    @Update("update inquirytotal set auditorExplain=#{auditorExplain},auditorStatus=#{auditorStatus} where requestForQuotationId=#{requestForQuotationId} ")
    void shenHe(InquiryTotalShenHeVo inquiryTotalShenHeVo);

    @Update("update inquirytotal set approverExplain=#{approverExplain},approverStatus=#{approverStatus} where requestForQuotationId=#{requestForQuotationId} ")
    void shenPi(InquiryTotalShenPiVo inquiryTotalShenPiVo);

    List<InquirySpareSelectVo> queryBaoJia(BaoJiaSelectVo baoJiaSelectVo);
}

