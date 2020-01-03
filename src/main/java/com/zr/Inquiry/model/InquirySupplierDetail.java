package com.zr.Inquiry.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class InquirySupplierDetail {

    private Integer id;
    @NotBlank(message = "供应商编码不能为空")
    private String vendorCode;

    private String vendor;

    private Integer inquiryId;
}
