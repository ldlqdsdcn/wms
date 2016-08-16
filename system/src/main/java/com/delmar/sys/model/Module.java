package com.delmar.sys.model;

import com.delmar.core.model.CoreModel;

/**
 * @author 刘大磊 2015年1月15日 上午10:17:14
 */
public class Module extends CoreModel{
    private String name;

    private String remark;

    private String dataFilter;

    private String init;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getDataFilter() {
        return dataFilter;
    }

    public void setDataFilter(String dataFilter) {
        this.dataFilter = dataFilter == null ? null : dataFilter.trim();
    }

    public String getInit() {
        return init;
    }

    public void setInit(String init) {
        this.init = init == null ? null : init.trim();
    }
}