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
 * @author 刘大磊 2015年4月16日 上午8:44:16
 */
public class FileRelation extends CoreModel{
	
    private Integer fileId;

    private String tableName;

    private Integer tableId;

    private Date created;
    
    private DelmarFile delmarFile;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

	/**
	 * @return the delmarFile
	 */
	public DelmarFile getDelmarFile() {
		return delmarFile;
	}

	/**
	 * @param delmarFile the delmarFile to set
	 */
	public void setDelmarFile(DelmarFile delmarFile) {
		this.delmarFile = delmarFile;
	}
    
}
