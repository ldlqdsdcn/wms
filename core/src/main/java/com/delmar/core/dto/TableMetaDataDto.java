package com.delmar.core.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by admin on 2016/8/18.
 */
@Data
public class TableMetaDataDto {
    /**
     * 表名
     */
    private String name;
    /**
     * 表注释
     */
    private String tableTrlName;
    /**
     * 是否出错日志
     */
    private boolean outLog;
    /**
     * model类名字
     */
    private String className;
    /**
     * 业务主键
     */
    private String busPk;
    /**
     * 备注
     */
    private String remark;
    /**
     *
     */
    private String pkColumn;

    private List<ForeignKey> exportedFK;

    private List<ForeignKey> importedFK;

    private List<ColumnMetaDataDto> columnList;

    private List<UniqueIndexDto> uniqueKeyList;
}
