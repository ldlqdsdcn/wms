package com.delmar.sys.model;

import com.delmar.core.model.CoreModel;

/**
 * @author 刘大磊 2015年1月15日 上午10:36:55
 */
public class ModuleMenu extends CoreModel{

    private Integer moduleId;

    private Integer menuId;
    private PageMenu pageMenu;
    /**
	 * @return the pageMenu
	 */
	public PageMenu getPageMenu() {
		return pageMenu;
	}

	/**
	 * @param pageMenu the pageMenu to set
	 */
	public void setPageMenu(PageMenu pageMenu) {
		this.pageMenu = pageMenu;
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

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}