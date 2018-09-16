package com.system.core.bean;

public class ServiceException extends Exception{

    private int errCode ;  //异常对应的返回码
    private String errMessage;  //异常对应的描述信息

    public ServiceException() {
        super();
    }

    public ServiceException(String errMessage) {
        super(errMessage);
        errMessage = errMessage;
    }

    public ServiceException(int errCode, String errMessage) {
        super();
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
