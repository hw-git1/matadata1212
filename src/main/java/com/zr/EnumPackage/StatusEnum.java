package com.zr.EnumPackage;

public enum StatusEnum {
    QIYONG("启用",1),JINYONG("禁用",0);

    private String name;
    private Integer value;

    StatusEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法 通过value获取名称
    public static String getName(int value) {
        for (StatusEnum c : StatusEnum.values()) {
            if (c.getValue() == value) {
                return c.name;
            }
        }
        return null;
    }
    // 普通方法 通过名称获取value
    public static Integer getValue(String name) {
        for (StatusEnum c : StatusEnum.values()) {
            if (c.getName().equals(name)) {
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
