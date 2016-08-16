package com.delmar.sys.model;

import com.delmar.core.model.CoreModel;

public class ModuleRole extends CoreModel{

    private Integer moduleId;

    private Integer roleId;
    
    private Integer dataFilterLevel;
    
    private Module module;
    /**
	 * @return the module
	 */
	public Module getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(Module module) {
		this.module = module;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

	/**
	 * @return the dataFilterLevel
	 */
	public Integer getDataFilterLevel() {
		return dataFilterLevel;
	}

	/**
	 * @param dataFilterLevel the dataFilterLevel to set
	 */
	public void setDataFilterLevel(Integer dataFilterLevel) {
		this.dataFilterLevel = dataFilterLevel;
	}
    
}