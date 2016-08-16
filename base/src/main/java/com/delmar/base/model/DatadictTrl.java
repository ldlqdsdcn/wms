package com.delmar.base.model;

import com.delmar.core.model.CoreModel;

public class DatadictTrl  extends CoreModel{
    

    private String language;

    private Integer datadictId;

    private String name;

    private String remark;
    
    private String value;
    
    private Integer indexOrder;
    
    private Datadict datadict;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public Integer getDatadictId() {
        return datadictId;
    }

    public void setDatadictId(Integer datadictId) {
        this.datadictId = datadictId;
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

	public Datadict getDatadict() {
		return datadict;
	}

	public void setDatadict(Datadict datadict) {
		this.datadict = datadict;
		if (datadict!=null)
			value=datadict.getValue();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getIndexOrder() {
		return indexOrder;
	}

	public void setIndexOrder(Integer indexOrder) {
		this.indexOrder = indexOrder;
	}
    
    
}