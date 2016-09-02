/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.common.model;

import java.util.Date;

import com.delmar.core.model.CoreModel;

/**
 * @author 刘大磊 2015年4月16日 上午8:43:38
 */
public class DelmarFile extends CoreModel{
    
    private String filename;

    private String fileType;

    private String path;

    private String extension;

    private Long fileSize;

    private Date fileCreated;

    private Date fileUpdated;

    private Integer fileIsreadonly;

    private Integer fileIshidden;

    private Date created;

    private Integer commonFileSettingId;

    private String fileAbstract;

    private String fileKeyword;

    private Integer fileMode;

    private byte[] fileBlob;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension == null ? null : extension.trim();
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getFileCreated() {
        return fileCreated;
    }

    public void setFileCreated(Date fileCreated) {
        this.fileCreated = fileCreated;
    }

    public Date getFileUpdated() {
        return fileUpdated;
    }

    public void setFileUpdated(Date fileUpdated) {
        this.fileUpdated = fileUpdated;
    }

    public Integer getFileIsreadonly() {
        return fileIsreadonly;
    }

    public void setFileIsreadonly(Integer fileIsreadonly) {
        this.fileIsreadonly = fileIsreadonly;
    }

    public Integer getFileIshidden() {
        return fileIshidden;
    }

    public void setFileIshidden(Integer fileIshidden) {
        this.fileIshidden = fileIshidden;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getCommonFileSettingId() {
        return commonFileSettingId;
    }

    public void setCommonFileSettingId(Integer commonFileSettingId) {
        this.commonFileSettingId = commonFileSettingId;
    }

    public String getFileAbstract() {
        return fileAbstract;
    }

    public void setFileAbstract(String fileAbstract) {
        this.fileAbstract = fileAbstract == null ? null : fileAbstract.trim();
    }

    public String getFileKeyword() {
        return fileKeyword;
    }

    public void setFileKeyword(String fileKeyword) {
        this.fileKeyword = fileKeyword == null ? null : fileKeyword.trim();
    }

    public Integer getFileMode() {
        return fileMode;
    }

    public void setFileMode(Integer fileMode) {
        this.fileMode = fileMode;
    }

    public byte[] getFileBlob() {
        return fileBlob;
    }

    public void setFileBlob(byte[] fileBlob) {
        this.fileBlob = fileBlob;
    }
}