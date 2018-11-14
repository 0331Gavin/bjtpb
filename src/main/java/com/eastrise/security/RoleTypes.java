package com.eastrise.security;

public enum RoleTypes {
    ROLE_ADMIN("系统管理员"),
    ROLE_RECORDER("文章录入员");

    private String label;

    private RoleTypes(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
