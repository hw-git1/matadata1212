package com.zr.EnumPackage;

public enum CurrencyCode {
    RMB("人民币",0),
    ＄("美元",1);

    private String name;
    private Integer value;
    CurrencyCode(String name,Integer value){
        this.name=name;
        this.value=value;
    }
    // 普通方法
    public static String getName(int value) {
        for (CurrencyCode c : CurrencyCode.values()) {
            if (c.getValue() == value) {
                return c.name;
            }
        }
        return null;
    }
    // 普通方法
    public static Integer getValue(String name) {
        for (CurrencyCode c : CurrencyCode.values()) {
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
