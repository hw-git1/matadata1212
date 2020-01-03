package com.zr.EnumPackage;

public enum CheckedEnum {
    LEGALPLANTCODE("没有此对象");
    private String name;

    CheckedEnum(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
