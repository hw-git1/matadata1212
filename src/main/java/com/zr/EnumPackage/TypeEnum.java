package com.zr.EnumPackage;

public enum TypeEnum {
    goumai("购买询价",0),
    chuli("处理询价",1),
    baofei("报废询价",2);

    private String name;
    private Integer value;

    TypeEnum(String name, Integer value){
        this.name=name;
        this.value=value;
    }

    // 普通方法
    public static String getName(int value) {
        for (TypeEnum c : TypeEnum.values()) {
            if (c.getValue() == value) {
                return c.name;
            }
        }
        return null;
    }
    // 普通方法
    public static Integer getValue(String name) {
        for (TypeEnum c : TypeEnum.values()) {
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
