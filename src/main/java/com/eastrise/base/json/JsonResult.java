package com.eastrise.base.json;

/**
 * create by gzq on 2018/10/31 10:35
 */
public class JsonResult {
    protected boolean success;
    protected String message;
    protected int errorCode;

    public JsonResult() {
        this.errorCode = 0;
    }

    public JsonResult(boolean success, String message) {
        this.errorCode = 0;
        this.success = success;
        this.message = message;
    }

    public JsonResult(boolean success) {
        this(success, (String)null);
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static JsonResult result(boolean success) {
        return new JsonResult(success);
    }

    public static JsonResult result(boolean success, String msg) {
        return new JsonResult(success, msg);
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
