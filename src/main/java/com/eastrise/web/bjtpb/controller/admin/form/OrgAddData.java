package com.eastrise.web.bjtpb.controller.admin.form;

/**
 * create by gzq on 2018/11/14 18:19
 */
public class OrgAddData {
    private String id;
    private String orgName;
    private String orgOrder;
    private String memo;
    private String orgSeq;
    private String status;
    private  String parentId;
    private String shortName;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgOrder() {
        return orgOrder;
    }

    public void setOrgOrder(String orgOrder) {
        this.orgOrder = orgOrder;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getOrgSeq() {
        return orgSeq;
    }

    public void setOrgSeq(String orgSeq) {
        this.orgSeq = orgSeq;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
