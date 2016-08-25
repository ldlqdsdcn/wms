package com.delmar.core.model;

public class Field extends CoreModel {

    private String name;

    private Integer labelId;

    private String isRequired;

    private Integer typeId;

    private Integer tabId;

    private Integer columnId;

    private Integer showType;

    private Integer linkTableId;

    private Integer linkValueColumnId;

    private Integer linkLabelColumnId;

    private String help;

    private String defaultValue;

    private Integer seqNo;

    private String sameline;

    private Integer lineTabId;

    private String linkTableFilterSql;

    private String validateRole;

    private String showInList;

    private String showInForm;

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

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getTabId() {
        return tabId;
    }

    public void setTabId(Integer tabId) {
        this.tabId = tabId;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public Integer getLinkTableId() {
        return linkTableId;
    }

    public void setLinkTableId(Integer linkTableId) {
        this.linkTableId = linkTableId;
    }

    public Integer getLinkValueColumnId() {
        return linkValueColumnId;
    }

    public void setLinkValueColumnId(Integer linkValueColumnId) {
        this.linkValueColumnId = linkValueColumnId;
    }

    public Integer getLinkLabelColumnId() {
        return linkLabelColumnId;
    }

    public void setLinkLabelColumnId(Integer linkLabelColumnId) {
        this.linkLabelColumnId = linkLabelColumnId;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getSameline() {
        return sameline;
    }

    public void setSameline(String sameline) {
        this.sameline = sameline;
    }

    public Integer getLineTabId() {
        return lineTabId;
    }

    public void setLineTabId(Integer lineTabId) {
        this.lineTabId = lineTabId;
    }

    public String getLinkTableFilterSql() {
        return linkTableFilterSql;
    }

    public void setLinkTableFilterSql(String linkTableFilterSql) {
        this.linkTableFilterSql = linkTableFilterSql;
    }

    public String getValidateRole() {
        return validateRole;
    }

    public void setValidateRole(String validateRole) {
        this.validateRole = validateRole;
    }

    public String getShowInList() {
        return showInList;
    }

    public void setShowInList(String showInList) {
        this.showInList = showInList;
    }

    public String getShowInForm() {
        return showInForm;
    }

    public void setShowInForm(String showInForm) {
        this.showInForm = showInForm;
    }
}