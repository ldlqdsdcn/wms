package com.delmar.sys.model;

import com.delmar.core.model.CoreModel;

public class PageMenu extends CoreModel{
    private String name;

    private String menuType;

    private String seqNo;

    private Integer parentMenuId;

    private String remark;

    private String icoPath;

    private String pageUrl;

    private String messagekey;
    
    private PageMenu parentMenu;

    private Integer windowId;
    /**
	 * @return the parentMenu
	 */
	public PageMenu getParentMenu() {
		return parentMenu;
	}

	/**
	 * @param parentMenu the parentMenu to set
	 */
	public void setParentMenu(PageMenu parentMenu) {
		this.parentMenu = parentMenu;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIcoPath() {
        return icoPath;
    }

    public void setIcoPath(String icoPath) {
        this.icoPath = icoPath == null ? null : icoPath.trim();
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl == null ? null : pageUrl.trim();
    }

    public String getMessagekey() {
        return messagekey;
    }

    public void setMessagekey(String messagekey) {
        this.messagekey = messagekey == null ? null : messagekey.trim();
    }

    public Integer getWindowId() {
        return windowId;
    }

    public void setWindowId(Integer windowId) {
        this.windowId = windowId;
    }
}