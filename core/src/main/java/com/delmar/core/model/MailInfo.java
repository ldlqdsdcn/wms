package com.delmar.core.model;

import java.util.Date;

/**
 * @author 刘大磊 2015年3月25日 上午9:17:55
 * 邮件发送日志
 */
public class MailInfo extends CoreModel{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fromaddress;

    private String subject;

    private Date sendtime;

    private String mailtype;

    private Integer clientId;

    private Integer orgId;

    private Date created;

    private Integer createdby;

    private String createdbyname;

    private Date updated;

    private Integer updatedby;

    private String updatedbyname;

    private Integer befinish;

    private Integer userId;

    private String userName;
    
    private String toaddress;

    private String ccaddress;

    private String bccaddress;

    private String mailcontent;
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromaddress() {
        return fromaddress;
    }

    public void setFromaddress(String fromaddress) {
        this.fromaddress = fromaddress == null ? null : fromaddress.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getMailtype() {
        return mailtype;
    }

    public void setMailtype(String mailtype) {
        this.mailtype = mailtype == null ? null : mailtype.trim();
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Integer createdby) {
        this.createdby = createdby;
    }

    public String getCreatedbyname() {
        return createdbyname;
    }

    public void setCreatedbyname(String createdbyname) {
        this.createdbyname = createdbyname == null ? null : createdbyname.trim();
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(Integer updatedby) {
        this.updatedby = updatedby;
    }

    public String getUpdatedbyname() {
        return updatedbyname;
    }

    public void setUpdatedbyname(String updatedbyname) {
        this.updatedbyname = updatedbyname == null ? null : updatedbyname.trim();
    }

    public Integer getBefinish() {
        return befinish;
    }

    public void setBefinish(Integer befinish) {
        this.befinish = befinish;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
    public String getToaddress() {
        return toaddress;
    }

    public void setToaddress(String toaddress) {
        this.toaddress = toaddress == null ? null : toaddress.trim();
    }

    public String getCcaddress() {
        return ccaddress;
    }

    public void setCcaddress(String ccaddress) {
        this.ccaddress = ccaddress == null ? null : ccaddress.trim();
    }

    public String getBccaddress() {
        return bccaddress;
    }

    public void setBccaddress(String bccaddress) {
        this.bccaddress = bccaddress == null ? null : bccaddress.trim();
    }

    public String getMailcontent() {
        return mailcontent;
    }

    public void setMailcontent(String mailcontent) {
        this.mailcontent = mailcontent == null ? null : mailcontent.trim();
    }


    
    
}