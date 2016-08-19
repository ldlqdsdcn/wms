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

    private String isUnique;

    private Integer columnSize;

    private Integer digits;

    private Integer nullable;

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

    public String getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(String isUnique) {
        this.isUnique = isUnique;
    }

    public Integer getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(Integer columnSize) {
        this.columnSize = columnSize;
    }

    public Integer getDigits() {
        return digits;
    }

    public void setDigits(Integer digits) {
        this.digits = digits;
    }

    public Integer getNullable() {
        return nullable;
    }

    public void setNullable(Integer nullable) {
        this.nullable = nullable;
    }
}