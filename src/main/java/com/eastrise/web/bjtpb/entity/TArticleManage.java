package com.eastrise.web.bjtpb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "T_SYS_ARTICLEMANAGE")
public class TArticleManage {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "memo", length=500)
    private String memo;

    @Column(name = "category_name", length=500)
    private String categoryname;

    @Column(name = "category_seq", length=500)
    private String categoryseq;

    @Column(name = "parent_id", length=500)
    private String parentid;

    @Column(name = "article_order", length=500)
    private String articleorder;

    @Column(name = "status", length=500)
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getCategoryseq() {
        return categoryseq;
    }

    public void setCategoryseq(String categoryseq) {
        this.categoryseq = categoryseq;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getArticleorder() {
        return articleorder;
    }

    public void setArticleorder(String articleorder) {
        this.articleorder = articleorder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
