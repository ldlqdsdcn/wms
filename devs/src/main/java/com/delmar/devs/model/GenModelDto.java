package com.delmar.devs.model;

import lombok.Data;

/**
 * Created by admin on 2016/8/26.
 */
@Data
public class GenModelDto {
    private String tableName;
    private String modelName;
    private String module;
    private String remark;
    /**
     *
     * @param tableName 表名
     * @param modelName 对应model 名
     * @param module 模块名
     */
    public GenModelDto(String tableName,String modelName,String module,String remark)
    {
        this.tableName=tableName;
        this.modelName=modelName;
        this.module=module;
        this.remark=remark;
    }
}
