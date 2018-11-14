package com.eastrise.base.exception;

import org.apache.commons.lang.enums.ValuedEnum;

public class ErrorCodeValuedEnum extends ValuedEnum  {

    private String description = null;

    public ErrorCodeValuedEnum(String name, int value) {
        super(name, value);
    }

    public ErrorCodeValuedEnum(int value, String name) {
        super(name, value);
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
