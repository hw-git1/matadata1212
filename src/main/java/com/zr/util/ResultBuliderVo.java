package com.zr.util;

import lombok.Data;

@Data
public class ResultBuliderVo {

    public  static<T> ResultVo success(T data){
        ResultVo resultVo=new ResultVo();
        resultVo.setData(data);
        resultVo.setSuccess(true);
        return resultVo;
    }

    public static<T> ResultVo success(){
        ResultVo resultVo=new ResultVo();
        resultVo.setData(null);
        resultVo.setSuccess(true);
        return resultVo;
    }

    public static ResultVo error(String errorMessage){
        ResultVo resultVo=new ResultVo();
        resultVo.setErrorMessage(errorMessage);
        resultVo.setSuccess(false);
        return resultVo;
    }
}
