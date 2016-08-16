package com.delmar.sys.model;

import com.delmar.core.model.CoreModel;

public class Operator extends CoreModel{

    private String value;

    private String name;

    private String remark;
    
    private String init;

    /**
	 * @return the init
	 */
	public String getInit() {
		return init;
	}

	/**
	 * @param init the init to set
	 */
	public void setInit(String init) {
		this.init = init;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}