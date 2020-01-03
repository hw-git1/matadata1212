package com.zr.Inquiry.model;

import lombok.Data;

@Data
public class UpdateInquiryStatusVo {
    private Integer requestForQuotationId;
    private Integer inquiryStatus;
}
