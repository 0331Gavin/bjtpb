package com.eastrise.web.bjtpb.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 友情链接
 * create by lh on 2018/11/19 9:21
 */
@Entity
@Table(name = "T_SYS_YQLJ")
public class TSysYqlj {

    /**
     * 唯一主键
     * ID
     */
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "ID",insertable = true, updatable = true, nullable = false, length=50)
    private String id;
    /*
     * 连接名称
     */
    @Column(name = "LJMC", length=1000)
    private String ljmc;
    /*
    * 链接地址
    **/

    @Column(name = "LJURL")

    private String ljurl;
    /*
    * 链接顺序
    **/

    @Column(name="LJSX")
    private String ljsx;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLjmc() {
        return ljmc;
    }

    public void setLjmc(String ljmc) {
        this.ljmc = ljmc;
    }

    public String getLjurl() {
        return ljurl;
    }

    public void setLjurl(String ljurl) {
        this.ljurl = ljurl;
    }

    public String getLjsx() {
        return ljsx;
    }

    public void setLjsx(String ljsx) {
        this.ljsx = ljsx;
    }
}
