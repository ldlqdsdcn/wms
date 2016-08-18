package com.delmar.core.dto;

import lombok.Data;

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
     *
     */
    private String pk_column;

    private String exportedFK;

    private String importedFK;

    private String columnList;

    private String uniqueKey;
}
