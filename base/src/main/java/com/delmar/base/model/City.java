package com.delmar.base.model;

import com.delmar.core.model.CoreModel;

public class City extends CoreModel{


    private String code;

    private String cname;

    private String ename;

    private Integer parentId;
    
    private CityTrl parent;

    private Integer levelint;

    private Integer relateCityId;
    
    private CityTrl relateCity;

    private String remark;

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

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevelint() {
        return levelint;
    }

    public void setLevelint(Integer levelint) {
        this.levelint = levelint;
    }

    public Integer getRelateCityId() {
        return relateCityId;
    }

    public void setRelateCityId(Integer relateCityId) {
        this.relateCityId = relateCityId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	/**
	 * @return the parent
	 */
	public CityTrl getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(CityTrl parent) {
		this.parent = parent;
	}

	/**
	 * @return the relateCity
	 */
	public CityTrl getRelateCity() {
		return relateCity;
	}

	/**
	 * @param relateCity the relateCity to set
	 */
	public void setRelateCity(CityTrl relateCity) {
		this.relateCity = relateCity;
	}
    
    
}