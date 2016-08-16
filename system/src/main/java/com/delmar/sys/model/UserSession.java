package com.delmar.sys.model;

import java.util.Date;

import com.delmar.core.model.CoreModel;

public class UserSession extends CoreModel{

    private Integer userId;

    private String sessionId;

    private Date loginDate;

    private Date logoutDate;

    private String remoteAddr;

    private String remoteHost;

    private Date created;

    private Date updated;
    private User user;
    
    private Integer loginOrgId;
    
    /**
	 * @return the loginOrgId
	 */
	public Integer getLoginOrgId() {
		return loginOrgId;
	}

	/**
	 * @param loginOrgId the loginOrgId to set
	 */
	public void setLoginOrgId(Integer loginOrgId) {
		this.loginOrgId = loginOrgId;
	}

	/**
	 * @return the loginClientId
	 */
	public Integer getLoginClientId() {
		return loginClientId;
	}

	/**
	 * @param loginClientId the loginClientId to set
	 */
	public void setLoginClientId(Integer loginClientId) {
		this.loginClientId = loginClientId;
	}

	private Integer loginClientId;
    
    /**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(Date logoutDate) {
        this.logoutDate = logoutDate;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr == null ? null : remoteAddr.trim();
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost == null ? null : remoteHost.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}