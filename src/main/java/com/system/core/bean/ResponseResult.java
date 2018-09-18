//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.system.core.bean;

import java.io.Serializable;

public class ResponseResult implements Serializable {
    private static final long serialVersionUID = 1L;
    protected final String version = "1.0";
    protected int errorCode = 0;
    protected String errorMsg = null;
    protected Object data = "";

    public ResponseResult() {
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getVersion() {
        return "1.0";
    }
}
