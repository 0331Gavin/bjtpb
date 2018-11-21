package com.eastrise.web.bjtpb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

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
    @JsonProperty(value="text")
    private String categoryname;

    @Column(name = "category_seq", length=500)
    private String categoryseq;
    @Column(name = "category_CODE", length=1000)
    private String categorycode;

    @Column(name = "parent_id", nullable=false)
    private String parentid;

    @Column(name = "article_order", length=500)
    private String articleorder;

    @Column(name = "status", length=500)
    private String status;

    /*
     *下级信息
     */
    @OneToMany(cascade = CascadeType.REFRESH , fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id",insertable = false, updatable = false)
    @Where(clause="status=1")
    private List<TArticleManage> children;

    public List<TArticleManage> getChildren() {
        return children;
    }

    public void setChildren(List<TArticleManage> children) {
        this.children = children;
    }

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

    public String getCategorycode() {
        return categorycode;
    }

    public void setCategorycode(String categorycode) {
        this.categorycode = categorycode;
    }
}
