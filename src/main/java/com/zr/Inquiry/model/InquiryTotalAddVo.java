package com.zr.Inquiry.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class InquiryTotalAddVo {
    private String requestForQuotationId;
    @NotNull(message = "币别不能为空！")
    private Integer currencyCode;
    @NotNull(message = "状态不能为空！")
    private Integer inquiryStatus;
    @NotNull(message = "法人不能为空！")
    private Integer legalPersonId;
    @NotNull(message = "类型不能为空！")
    private Integer type;
    @NotNull(message = "是否含税不能为空！")
    private Integer isTax;
    @NotNull(message = "开始日期不能为空！")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date startTime;
    @NotNull(message = "结束日期不能为空！")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date endTime;
    @NotEmpty(message = "备件集合不能为空")
    @Valid
    private List<InquirySpareDetail> inquirySpareDetailList;
    @NotEmpty(message = "供应商集合不能为空")
    @Valid
    private List<InquirySupplierDetail> inquirySupplierDetailList;
}
