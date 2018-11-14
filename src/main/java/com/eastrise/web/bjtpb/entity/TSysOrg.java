package com.eastrise.web.bjtpb.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * create by gzq on 2018/11/14 12:59
 */
@Entity
@Table(name = "T_SYS_ORG")
public class TSysOrg {
    /**
     * 唯一主键
     * ID
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    /*
     * 组织名称
     */
    @Column(name = "org_name", length=500)
    @JsonProperty(value="text")
    private String orgName;
    /*
     * 组织缩略名
     */
    @Column(name = "short_name", length=500)
    private String shortName;
    /*
    * 组织索引
    */
    @Column(name = "org_seq", length=2000)
    private String orgSeq;
    /*
     * 上级组织ID
     */
    @Column(name = "parent_id",nullable=false)
    private long parentId;
    /*
     * 备注
     */
    @Column(name = "memo", length=2048)
    private String memo;
    /*
     * 排序
     */
    @Column(name = "org_order", length=500)
    private String orgOrder;
    /*
         * 状态
         */
    @Column(name = "status", length=10)
    private String status;
    /*
       *下级组织信息
       */
    @OneToMany(cascade = CascadeType.REFRESH , fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id",insertable = false, updatable = false)
    @Where(clause="status=1")
    private List<TSysOrg> children;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getOrgSeq() {
        return orgSeq;
    }

    public void setOrgSeq(String orgSeq) {
        this.orgSeq = orgSeq;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getOrgOrder() {
        return orgOrder;
    }

    public void setOrgOrder(String orgOrder) {
        this.orgOrder = orgOrder;
    }

    public List<TSysOrg> getChildren() {
        return children;
    }

    public void setChildren(List<TSysOrg> children) {
        this.children = children;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
