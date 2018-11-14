package com.eastrise.base.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * create by gzq on 2018/10/31 10:35
 */
public class JsonDataRowsResult<T> {
    protected boolean success;
    protected String message;
    protected int errorCode = 0;
    @JsonProperty("result")
    protected JsonDataRowsResult<T>.JsonDataRows<T> data;

    public JsonDataRowsResult() {
        this.success = true;
    }

    public JsonDataRowsResult(boolean f) {
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

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public JsonDataRowsResult<T>.JsonDataRows<T> getData() {
        return this.data;
    }

    public void setData(List<T> dataList) {
        JsonDataRowsResult<T>.JsonDataRows<T> jsonDataRows = new JsonDataRowsResult.JsonDataRows();
        jsonDataRows.setRows(dataList);
        this.data = jsonDataRows;
    }

    public void setData(List<? extends T> dataList, long totalNumber) {
        JsonDataRowsResult<T>.JsonDataRows<T> jsonDataRows = new JsonDataRowsResult.JsonDataRows();
        jsonDataRows.setRows(dataList);
        jsonDataRows.setTotalNumber(totalNumber);
        this.data = jsonDataRows;
    }

    protected class JsonDataRows<W> {
        @JsonProperty("rows")
        private List<? extends W> rows;
        @JsonProperty("totalNum")
        private long totalNumber;

        protected JsonDataRows() {
        }

        public long getTotalNumber() {
            return this.totalNumber;
        }

        public void setTotalNumber(long totalNumber) {
            this.totalNumber = totalNumber;
        }

        public List<? extends W> getRows() {
            return this.rows;
        }

        public void setRows(List<? extends W> rows) {
            this.rows = rows;
        }
    }
}
