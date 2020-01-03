package com.zr.Inquiry.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class InquirySpareDetail {
    private Integer id;
    @NotBlank(message = "备件编码不能为空")
    private String spareCoding;

    private String spareName;

    private String measurementUnit;
    @NotNull(message = "moq不能为空")
    private Integer moq;

    private Integer currencyCode;
    @NotNull(message = "询价数量不能为空")
    private Integer inquiryQty;
    @NotNull(message = "交货周期不能为空")
    private Integer deliveryCycle;

    private Integer inquiryId;
}
