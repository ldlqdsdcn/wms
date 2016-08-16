package com.delmar.corebus.model;

import com.delmar.core.model.CoreModel;

/**
 * @author 刘大磊 2015年3月18日 下午3:43:08
 */
public class EBusinessAssign extends CoreModel{

    private Integer clientId;

    private Integer orgId;

    private Integer userId;

    private Integer ebusinessId;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEbusinessId() {
        return ebusinessId;
    }

    public void setEbusinessId(Integer ebusinessId) {
        this.ebusinessId = ebusinessId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}