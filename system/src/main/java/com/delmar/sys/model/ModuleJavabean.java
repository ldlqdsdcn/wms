package com.delmar.sys.model;

import com.delmar.core.model.CoreModel;

public class ModuleJavabean extends CoreModel{

    private Integer moduleId;

    private Integer javabeanId;
    private Javabean javabean;


	/**
	 * @return the javabean
	 */
	public Javabean getJavabean() {
		return javabean;
	}

	/**
	 * @param javabean the javabean to set
	 */
	public void setJavabean(Javabean javabean) {
		this.javabean = javabean;
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

    public Integer getJavabeanId() {
        return javabeanId;
    }

    public void setJavabeanId(Integer javabeanId) {
        this.javabeanId = javabeanId;
    }
}