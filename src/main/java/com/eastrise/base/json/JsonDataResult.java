package com.eastrise.base.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * create by gzq on 2018/10/31 10:34
 */
public class JsonDataResult<T> {
    protected boolean success;
    protected String message;
    protected int errorCode = 0;
    @JsonProperty("result")
    protected T data;

    public JsonDataResult() {
        this.success = true;
    }

    public JsonDataResult(boolean f) {
        this.success = f;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
