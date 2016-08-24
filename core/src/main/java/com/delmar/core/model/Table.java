package com.delmar.core.model;

import java.util.List;

/**
 * @author 刘大磊 2015年1月9日 上午10:47:13
 */
public class Table extends CoreModel {
  
    private String name;

    private String tableNameTr;

    private String className;

    private String outLog;

    private String buPk;

    private String descr;

    private String externJson;

    private List<TableColumn> tableColumnList;

    /**
	 * @return the tableColumnList
	 */
	public List<TableColumn> getTableColumnList() {
		return tableColumnList;
	}

	/**
	 * @param tableColumnList the tableColumnList to set
	 */
	public void setTableColumnList(List<TableColumn> tableColumnList) {
		this.tableColumnList = tableColumnList;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTableNameTr() {
        return tableNameTr;
    }

    public void setTableNameTr(String tableNameTr) {
        this.tableNameTr = tableNameTr == null ? null : tableNameTr.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getOutLog() {
        return outLog;
    }

    public void setOutLog(String outLog) {
        this.outLog = outLog == null ? null : outLog.trim();
    }

    public String getBuPk() {
        return buPk;
    }

    public void setBuPk(String buPk) {
        this.buPk = buPk == null ? null : buPk.trim();
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public String getExternJson() {
        return externJson;
    }

    public void setExternJson(String externJson) {
        this.externJson = externJson;
    }
}