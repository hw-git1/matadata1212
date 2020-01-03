package com.zr.EnumPackage;

public enum IsTax {
    SHI("是",0),
    FOU("否",1);

    private String name;
    private Integer value;

    IsTax(String name,Integer value){
        this.name=name;
        this.value=value;
    }
    // 普通方法
    public static String getName(int value) {
        for (IsTax c : IsTax.values()) {
            if (c.getValue() == value) {
                return c.name;
            }
        }
        return null;
    }
    // 普通方法
    public static Integer getValue(String name) {
        for (IsTax c : IsTax.values()) {
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
