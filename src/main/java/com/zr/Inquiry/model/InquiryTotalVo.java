package com.zr.Inquiry.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InquiryTotalVo {
    private Integer id;
    private String name;
    private String requestForQuotationId;
    private Integer currencyCode;
    private Integer inquiryStatus;
    private Integer inquiryStatusName;
    private Integer legalPersonId;
    private String legalPersonName;
    private Integer type;
    private String typeName;
    private Integer isTax;
    private String isTaxName;
    private Date startTime;
    private Date endTime;
    private String creatorName;
    private Date createTime;
    private String updateName;
    private Date updateTime;
    private String auditorName;
    private String auditorExplain;
    private Integer auditorStatus;
    private Date auditorTime;
    private String approverName;
    private String approverExplain;
    private Integer approverStatus;
    private Date approverTime;

    private List<InquirySpareSelectVo> inquirySpareSelectVoList;
    private List<InquirySupplierSelectVo> inquirySupplierSelectVoList;
}
