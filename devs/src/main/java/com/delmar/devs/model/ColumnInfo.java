package com.delmar.devs.model;

import lombok.Data;

/**
 * Created by admin on 2016/8/26.
 */
@Data
public class ColumnInfo implements java.io.Serializable{
    private String columnName;
    private String type;
    private int dataType;
    private int columnSize;
    private int decimalDigits;
    private Boolean nullable;
    private String remarks;
    private String columnDefault;
    private String propertyName;
    private boolean keyWord;
    private String propertyType;
}
