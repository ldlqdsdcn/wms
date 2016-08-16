package com.delmar.sys.model;

import com.delmar.core.model.CoreModel;

public class Privilege extends CoreModel{

    private Integer moduleRoleId;

    private Integer operatorId;
    private ModuleRole moduleRole;

    /**
	 * @return the moduleRole
	 */
	public ModuleRole getModuleRole() {
		return moduleRole;
	}

	/**
	 * @param moduleRole the moduleRole to set
	 */
	public void setModuleRole(ModuleRole moduleRole) {
		this.moduleRole = moduleRole;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModuleRoleId() {
        return moduleRoleId;
    }

    public void setModuleRoleId(Integer moduleRoleId) {
        this.moduleRoleId = moduleRoleId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
}