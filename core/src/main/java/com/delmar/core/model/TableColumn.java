package com.delmar.core.model;

public class TableColumn extends CoreModel{

    private String columnName;

    private Integer dataType;

    private Integer tableId;

    private String canShow;

    private String columnNameTr;

    private String attributeName;

    private String outLog;

    private String descr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getCanShow() {
        return canShow;
    }

    public void setCanShow(String canShow) {
        this.canShow = canShow == null ? null : canShow.trim();
    }

    public String getColumnNameTr() {
        return columnNameTr;
    }

    public void setColumnNameTr(String columnNameTr) {
        this.columnNameTr = columnNameTr == null ? null : columnNameTr.trim();
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName == null ? null : attributeName.trim();
    }

    public String getOutLog() {
        return outLog;
    }

    public void setOutLog(String outLog) {
        this.outLog = outLog == null ? null : outLog.trim();
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }
}