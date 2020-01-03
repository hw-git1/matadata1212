package com.zr.qianduan;

import lombok.extern.java.Log;
/*import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;*/

@Log
public class ResultVOBuilder {

    public static  <T> ResultVO<T> success(T t) {
        ResultVO<T> result = new ResultVO<>();
        result.setData(t);
        result.setSuccess(true);
        return result;
    }

    public static ResultVO error(String errorCode, String message){
        ResultVO result = new ResultVO();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        result.setErrorMessage(message);
        return result;
    }
    /*@Autowired
    public ResultVOBuilder(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }*/

    // mockito 测试用
   /* private ResultVOBuilder(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("errorMessage", "constantMessage");
        messageSource.setUseCodeAsDefaultMessage(true);
        this.messageSource = messageSource;
    }*/

//    private final ResourceBundleMessageSource messageSource;



    /*public <T> ResultVO<T> failure(ResultVO<?> origin) {
        return failure(origin.getErrorCode(), origin.getErrorArgs());
    }

    public <T> ResultVO<T> failure(String errorCode, Object... errorArgs) {
        ResultVO<T> result = new ResultVO<>();
        result.setErrorCode(errorCode);
        result.setErrorArgs(errorArgs);
        result.setErrorMessage(messageSource.getMessage(errorCode, errorArgs, LocaleContextHolder.getLocale()));
        result.setSuccess(false);
        log.warning(result.getErrorMessage());
        *//*if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }*//*
        return result;
    }*/

}
