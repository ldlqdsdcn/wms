package com.delmar.core.model;

public class Page extends CoreModel {
    private Integer id;

    private String name;

    private String descr;

    private String help;

    private Integer windowId;

    private Integer tableId;

    private Integer filterColumnId;

    private String isactive;

    private String showInTab;

    private Integer seqNo;

    private String filterSql;

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
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public Integer getWindowId() {
        return windowId;
    }

    public void setWindowId(Integer windowId) {
        this.windowId = windowId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getFilterColumnId() {
        return filterColumnId;
    }

    public void setFilterColumnId(Integer filterColumnId) {
        this.filterColumnId = filterColumnId;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getShowInTab() {
        return showInTab;
    }

    public void setShowInTab(String showInTab) {
        this.showInTab = showInTab;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getFilterSql() {
        return filterSql;
    }

    public void setFilterSql(String filterSql) {
        this.filterSql = filterSql;
    }
}