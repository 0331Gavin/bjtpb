package com.eastrise.web.bjtpb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * 文章管理
 * create by gzq on 2018/11/19 9:21
 */
@Entity
@Table(name = "T_ARTICLE")
public class TArticle {

    /**
     * 唯一主键
     * ID
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    /*
     * 文章类别ID
     */
    @Column(name = "ARTICLE_TYPE_ID", length=1000)
    private long articleTypeId;
    /*
     * 文章标题
     */
    @Column(name = "TITLE", length=1000)
    private String tiltle;
    /*
    * 文章内容
    **/
    @Column(name = "CONTENT", length=1000)
    private String content;
    /*
    * 文章HTML内容
    **/
    @Lob
    @Column(name="CONTENT_HTML", columnDefinition = "CLOB")
    private String contentHtml;
    /*
    * 发布部门
    * */
    @Column(name="PUBLISH_DEPT_NAME")
    private String publishDept;
    /*
    * 发布时间
    * */
    @Column(name="PUBLISH_TIME")
    private String publishTime;
    /*
    * 创建人id
    * */
    @Column(name="CREATE_USER_ID")
    private String createUserId;
    /*
    * 创建人
    * */
    @Column(name="CREATE_USER_NAME")
    private String createUserName;
    /*
    * 创建时间
    * */
    @Column(name="CREATE_TIME")
    private String createTime;
    /*
    * 状态 （0：起草中，1：发布）
    * */
    @Column(name="STATUS")
    private String status;


    public long getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(long articleTypeId) {
        this.articleTypeId = articleTypeId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTiltle() {
        return tiltle;
    }

    public void setTiltle(String tiltle) {
        this.tiltle = tiltle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getPublishDept() {
        return publishDept;
    }

    public void setPublishDept(String publishDept) {
        this.publishDept = publishDept;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}