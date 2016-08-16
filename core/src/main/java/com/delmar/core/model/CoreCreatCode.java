package com.delmar.core.model;

public class CoreCreatCode extends CoreModel {

    private String code;

    private Integer maxvalue;

    private Integer clientId;

    private String name;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(Integer maxvalue) {
        this.maxvalue = maxvalue;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}