package com.eastrise.web.bjtpb.controller.admin.form;

public class ArticleTypeForm {

    private String id;

    private String categoryname;

    private String status;

    private String articleorder;

    private String memo;

    private String parentid;

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArticleorder() {
        return articleorder;
    }

    public void setArticleorder(String articleorder) {
        this.articleorder = articleorder;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
