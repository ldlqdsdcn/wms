package com.delmar.core.dto;

import lombok.Data;

/**
 * Created by admin on 2016/8/23.
 * 表字段信息
 */
@Data
public class ColumnMetaDataDto implements java.io.Serializable{
    private String columnName;
    private String type;
    private int dataType;
    private int columnSize;
    private int decimalDigits;
    private boolean nullable;
    private String remarks;
    private String columnDefault;

}
