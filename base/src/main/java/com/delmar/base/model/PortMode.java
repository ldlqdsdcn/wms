package com.delmar.base.model;

import com.delmar.core.model.CoreModel;

public class PortMode extends CoreModel{

    private Integer basePortId;

    private String mode;
    
    private Integer datadictId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBasePortId() {
        return basePortId;
    }

    public void setBasePortId(Integer basePortId) {
        this.basePortId = basePortId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode == null ? null : mode.trim();
    }

	/**
	 * @return the datadictId
	 */
	public Integer getDatadictId() {
		return datadictId;
	}

	/**
	 * @param datadictId the datadictId to set
	 */
	public void setDatadictId(Integer datadictId) {
		this.datadictId = datadictId;
	}
    
    
}