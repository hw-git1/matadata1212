package com.zr.Inquiry.model;

import lombok.Data;

@Data
public class InquirySpareSelectVo {
    private String spareCoding;
    private String spareName;
    private String measurementUnit;
    private Integer inquiryQty;
    private Integer deliveryCycle;
    //private Integer moq;
    private Integer currencyCode;
    private String currencyName;
}
