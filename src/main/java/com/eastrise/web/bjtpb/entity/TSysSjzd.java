package com.eastrise.web.bjtpb.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * create by gzq on 2018/11/14 12:59
 */
@Entity
@Table(name = "T_SYS_SJZD")
public class TSysSjzd {
    /**
     * 唯一主键
     * ID
     */
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "ID",insertable = true, updatable = true, nullable = false, length=50)
    private String id;

    /**
     * 用户名
     */
    @Column(name = "sjlx",length = 50)
    private String sjlx;
    /**
     * 登录名
     * 非空、索引
     */
    @Column(name = "sjbm",length = 50)
    private String sjbm;
    /**
     * 密码
     */
    @Column(name = "sjmc",length = 50)
    private String sjmc;
    @Column(name = "xssx")
    private String xssx;
    @Column(name = "status", length=10)
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSjlx() {
        return sjlx;
    }

    public void setSjlx(String sjlx) {
        this.sjlx = sjlx;
    }

    public String getSjbm() {
        return sjbm;
    }

    public void setSjbm(String sjbm) {
        this.sjbm = sjbm;
    }

    public String getSjmc() {
        return sjmc;
    }

    public void setSjmc(String sjmc) {
        this.sjmc = sjmc;
    }

    public String getXssx() {
        return xssx;
    }

    public void setXssx(String xssx) {
        this.xssx = xssx;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
