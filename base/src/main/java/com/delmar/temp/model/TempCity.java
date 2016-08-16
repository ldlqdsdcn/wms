package com.delmar.temp.model;

import com.delmar.core.model.CoreModel;

public class TempCity extends CoreModel {

    private String code;

    private String name;

    private String parentCode;

    private Integer cityLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public Integer getCityLevel() {
        return cityLevel;
    }

    public void setCityLevel(Integer cityLevel) {
        this.cityLevel = cityLevel;
    }
}