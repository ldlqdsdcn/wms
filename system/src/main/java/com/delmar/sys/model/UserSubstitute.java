package com.delmar.sys.model;

import com.delmar.core.model.CoreModel;

public class UserSubstitute extends CoreModel {

    private Integer userId;

    private Integer suserId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSuserId() {
        return suserId;
    }

    public void setSuserId(Integer suserId) {
        this.suserId = suserId;
    }
}