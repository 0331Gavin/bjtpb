package com.eastrise.web.bjtpb.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "T_ATTACHMENT")
public class TAttachment {

    /*
     * ID
     */
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "ID",insertable = true, updatable = true, nullable = false ,length=50)
    private String id;

    /**
     * 附件名
     */
    @Column(name = "ATTACHMENT_NAME",length=1000)
    private String attachmentName;

    /**
     * 附件保存路径
     */
    @Column(name = "ATTACHMENT_PATH",length=4000)
    private String attachmentPath;

    /**
     * 业务主键id
     */
    @Column(name = "BUZ_ID",length=50)
    private String buzId;

    /**
     * 上传人
     */
    @Column(name = "UPLOAD_USER_ID",length=50)
    private String uploadUserId;

    /**
     * 上传人姓名
     */
    @Column(name = "UPLOAD_USER_NAME",length=500)
    private String uploadUserName;

    /**
     * 上传部门
     */
    @Column(name = "UPLOAD_DEPT_ID",length=50)
    private String uploadDeptId;

    /**
     * 上传部门名称
     */
    @Column(name = "UPLOAD_DEPT_NAME",length=500)
    private String uploadDeptName;

    /**
     * 附件上传时间
     */
    @Column(name = "UPLOAD_TIME",length=50)
    private String uploadTime;


    /**
     * 业务表
     */
    @Column(name = "BUZ_TABLE",length=50)
    private String buzTable;

    /**
     * 附件类型
     */
    @Column(name = "ATTACHMENT_TYPE",length=50)
    private String attachmentType;

    /**
     * 文件类型 图片 视频 其他
     */
    @Column(name = "file_Type",length=50)
    private String fileType;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getBuzId() {
        return buzId;
    }

    public void setBuzId(String buzId) {
        this.buzId = buzId;
    }

    public String getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(String uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    public String getUploadUserName() {
        return uploadUserName;
    }

    public void setUploadUserName(String uploadUserName) {
        this.uploadUserName = uploadUserName;
    }

    public String getUploadDeptId() {
        return uploadDeptId;
    }

    public void setUploadDeptId(String uploadDeptId) {
        this.uploadDeptId = uploadDeptId;
    }

    public String getUploadDeptName() {
        return uploadDeptName;
    }

    public void setUploadDeptName(String uploadDeptName) {
        this.uploadDeptName = uploadDeptName;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getBuzTable() {
        return buzTable;
    }

    public void setBuzTable(String buzTable) {
        this.buzTable = buzTable;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}

