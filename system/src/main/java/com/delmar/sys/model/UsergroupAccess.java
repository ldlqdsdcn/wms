package com.delmar.sys.model;

import com.delmar.core.model.CoreModel;

public class UsergroupAccess extends CoreModel{
  
    private Integer userId;

    private Integer usergroupId;
    
    private Usergroup usergroup;
    
    private User user;
    
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

    public Integer getUsergroupId() {
        return usergroupId;
    }

    public void setUsergroupId(Integer usergroupId) {
        this.usergroupId = usergroupId;
    }

	/**
	 * @return the usergroup
	 */
	public Usergroup getUsergroup() {
		return usergroup;
	}

	/**
	 * @param usergroup the usergroup to set
	 */
	public void setUsergroup(Usergroup usergroup) {
		this.usergroup = usergroup;
	}

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
    
}