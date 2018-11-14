package com.eastrise.base.exception;

import org.apache.commons.lang.StringUtils;

import java.text.MessageFormat;

public class NRAPException extends RuntimeException {

    private int errorCode;
    private String messagePattern = null;

    public NRAPException() {
    }

    public NRAPException(ErrorCodeValuedEnum errorType, Object... params) {
        super(NRAPException.ErrorCodeMessageFormatter.format(errorType.getName(), params));
        this.errorCode = errorType.getValue();
    }

    public NRAPException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public static NRAPException createNewInstance(ErrorCodeValuedEnum errorType, Object... params) {
        NRAPException ex = new NRAPException(errorType, params);
        return ex;
    }

    public static class ErrorCodeMessageFormatter {
        public ErrorCodeMessageFormatter() {
        }

        public static String format(String msg, Object... params) {
            if (StringUtils.isNotEmpty(msg)) {
                String rsult = MessageFormat.format(msg, params);
                return rsult;
            } else {
                return null;
            }
        }
    }
}
