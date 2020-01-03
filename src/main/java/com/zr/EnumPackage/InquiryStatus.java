package com.zr.EnumPackage;

public enum InquiryStatus {
    WEIBAOJIA("未报价",0),
    YIBAOCUN("已保存",1),
    YITIJIAO("已提交",2),
    YISHENHE("已审核",3),
    YIZUOFEI("已作废",4);

    private String name;
    private  Integer value;

    InquiryStatus(String name,Integer value){
        this.name=name;
        this.value=value;
    }
    // 普通方法
    public static String getName(int value) {
        for (InquiryStatus c : InquiryStatus.values()) {
            if (c.getValue() == value) {
                return c.name;
            }
        }
        return null;
    }
    // 普通方法
    public static Integer getValue(String name) {
        for (InquiryStatus c : InquiryStatus.values()) {
            if (c.getName().equals(name) ) {
                return c.value;
            }
        }
        return null;
    }
    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}
