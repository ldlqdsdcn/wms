package com.delmar.base.model;

import com.delmar.core.model.CoreModel;

public class CityCompany extends CoreModel {
    private Integer id;

    private Integer companyId;

    private Integer baseCityId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getBaseCityId() {
        return baseCityId;
    }

    public void setBaseCityId(Integer baseCityId) {
        this.baseCityId = baseCityId;
    }
}