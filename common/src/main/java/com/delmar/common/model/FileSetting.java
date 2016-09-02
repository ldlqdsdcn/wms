/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.common.model;

import java.util.Date;

import com.delmar.core.model.CoreModel;

/**
 * @author 刘大磊 2015年4月16日 上午8:44:21
 */
public class FileSetting extends CoreModel{
    private Integer id;

    private String name;

    private String rootDirectory;

    private Integer fileSize;

    private String fileTypes;

    private Integer storageMode;

    private Integer ftpcommectionId;

    private Date created;

    private Integer moduleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRootDirectory() {
        return rootDirectory;
    }

    public void setRootDirectory(String rootDirectory) {
        this.rootDirectory = rootDirectory == null ? null : rootDirectory.trim();
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileTypes() {
        return fileTypes;
    }

    public void setFileTypes(String fileTypes) {
        this.fileTypes = fileTypes == null ? null : fileTypes.trim();
    }

    public Integer getStorageMode() {
        return storageMode;
    }

    public void setStorageMode(Integer storageMode) {
        this.storageMode = storageMode;
    }

    public Integer getFtpcommectionId() {
        return ftpcommectionId;
    }

    public void setFtpcommectionId(Integer ftpcommectionId) {
        this.ftpcommectionId = ftpcommectionId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }
}