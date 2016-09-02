/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.common.model;

import com.delmar.core.model.CoreModel;

/**
 * @author 刘大磊 2015年4月16日 上午8:42:37
 */
public class FtpConnection extends CoreModel{


    private String name;

    private String ftpserver;

    private String username;

    private String password;

    private String ftpfolder;

    private Integer ftpport;

    private Integer baseftp;

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

    public String getFtpserver() {
        return ftpserver;
    }

    public void setFtpserver(String ftpserver) {
        this.ftpserver = ftpserver == null ? null : ftpserver.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getFtpfolder() {
        return ftpfolder;
    }

    public void setFtpfolder(String ftpfolder) {
        this.ftpfolder = ftpfolder == null ? null : ftpfolder.trim();
    }

    public Integer getFtpport() {
        return ftpport;
    }

    public void setFtpport(Integer ftpport) {
        this.ftpport = ftpport;
    }

    public Integer getBaseftp() {
        return baseftp;
    }

    public void setBaseftp(Integer baseftp) {
        this.baseftp = baseftp;
    }
}