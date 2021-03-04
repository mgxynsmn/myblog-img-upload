package com.xiao.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UploadFile {

    private long id;                   //id
    private String originalFileName;           //文件名称
    private String savedFileName;       //文件全名称
    private String ext;                //文件扩展
    private Long size;               //文件大小
    private String relatedBusiness;    //相关业务
    private String remark;             //备注
    private String absolutePath;        //相对路径
    private String publicLink;           //公网连接
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;           //创建时间



    public UploadFile(long id, String originalFileName, String savedFileName, String ext, Long size, String relatedBusiness, String remark, String absolutePath, String publicLink, Date createTime) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.savedFileName = savedFileName;
        this.ext = ext;
        this.size = size;
        this.relatedBusiness = relatedBusiness;
        this.remark = remark;
        this.absolutePath = absolutePath;
        this.publicLink = publicLink;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getSavedFileName() {
        return savedFileName;
    }

    public void setSavedFileName(String savedFileName) {
        this.savedFileName = savedFileName;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getRelatedBusiness() {
        return relatedBusiness;
    }

    public void setRelatedBusiness(String relatedBusiness) {
        this.relatedBusiness = relatedBusiness;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPublicLink() {
        return publicLink;
    }

    public void setPublicLink(String publicLink) {
        this.publicLink = publicLink;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
}
