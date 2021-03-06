package com.eastrise.web.bjtpb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

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
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "ID",insertable = true, updatable = true, nullable = false, length=50)
    private String id;
    /*
     * 文章类别ID
     */
    @Column(name = "ARTICLE_TYPE_ID", length=1000)
    private String articleTypeId;
    /*
     * 文章标题
     */
    @Column(name = "TITLE", length=1000)
    private String title;
    /*
    * 文章内容
    **/
    @Lob
//    @Column(name = "CONTENT", columnDefinition = "CLOB")
    @Column(columnDefinition = "text")
    private String content;
    /*
    * 文章HTML内容
    **/
    @Lob
//    @Column(name="CONTENT_HTML", columnDefinition = "CLOB")
    @Column(columnDefinition = "text")
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

    /*
    * 置顶 （0：没置顶，1：已置顶）
    * */
    @Column(name="IS_TOP")
    private String isTop="0";
    /*
    * 对外开放 （0：否，1：是）
    * */
    @Column(name="IS_OPEN")
    private String isOpen="1";
    /*
    * 排序
    * */
    @Column(name="SEQ")
    private double seq=999;

    /*
    *
    * */
    @Column(name="LOOK_TOTAL")
    private int lookTotal=0;

    @Column(name="CREATE_LOGINNAME")
    private String createLoginName;

    public String getCreateLoginName() {
        return createLoginName;
    }

    public void setCreateLoginName(String createLoginName) {
        this.createLoginName = createLoginName;
    }

    @Column(name="ARTICLE_TAG")
    private String articleTag;

    public String getArticleTag() {
        return articleTag;
    }

    public void setArticleTag(String articleTag) {
        this.articleTag = articleTag;
    }
    public String getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(String articleTypeId) {
        this.articleTypeId = articleTypeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public int getLookTotal() {
        return lookTotal;
    }

    public void setLookTotal(int lookTotal) {
        this.lookTotal = lookTotal;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public double getSeq() {
        return seq;
    }

    public void setSeq(double seq) {
        this.seq = seq;
    }
}
