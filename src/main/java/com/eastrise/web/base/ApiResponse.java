package com.eastrise.web.base;

/**
 * API 格式封装
 * create by gzq on 2018/6/8 9:41
 */
public class ApiResponse {
    private int code;
    private String message;
    private Object data;
    private boolean more;

    public ApiResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse() {
        this.code = Status.SUCCESS.getCode();
        this.message = Status.SUCCESS.getStandardMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public static ApiResponse ofMessage(int code,String message){
        return new ApiResponse(code,message,null);
    }
    public static ApiResponse ofMessage(Object data){
        return new ApiResponse(Status.SUCCESS.getCode(), Status.SUCCESS.getStandardMessage(),data);
    }
    public static ApiResponse ofStatus(Status status){
        return new ApiResponse(status.getCode(),status.getStandardMessage(),null);
    }
    public static ApiResponse ofSuccess(Object data) {
        return new ApiResponse(Status.SUCCESS.getCode(), Status.SUCCESS.getStandardMessage(), data);
    }

    public enum Status{

        SAVE_SUCCESS(201,"保存成功"),
        EDIT_SUCCESS(202,"保存成功"),
        DEL_SUCCESS(203,"删除成功"),
        SAVE_FAILD(204,"保存失败"),
        DEL_FAILD(205,"删除失败"),
        DATA_EXISTED(206,"保存失败"),
        SUCCESS(200,"OK"),
        BAS_REQUEST(400,"Bad Request"),
        NOT_FOUND(404,"NOT FOUNT"),

        INTERNAL_SERVER_ERROR(500,"Unknown Internal Error"),
        NOT_VALID_PARAM(4005, "Not Valid Params"),
        NOT_SUPPORTED_OPERATION(4006,"Operation Not Supported"),
        NOT_LOGIN(5000, "Not Login");

        private int code;
        private String standardMessage;

        Status(int code, String standardMessage) {
            this.code = code;
            this.standardMessage = standardMessage;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getStandardMessage() {
            return standardMessage;
        }

        public void setStandardMessage(String standardMessage) {
            this.standardMessage = standardMessage;
        }
    }

}
