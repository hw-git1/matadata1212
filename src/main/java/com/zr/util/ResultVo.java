package com.zr.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVo<T> implements Serializable {


    private boolean success;

    private String errorMessage;

    private T data;

    public boolean getSuccess() {
        return success;
    }
}
