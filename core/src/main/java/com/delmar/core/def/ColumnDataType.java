package com.delmar.core.def;

/**
 * Created by admin on 2016/8/23.
 */
public enum  ColumnDataType implements java.io.Serializable{
    INT(0,"整数","Integer"),FLOAT(1,"浮点数","BigDecimal"),STRING(2,"字符串","String"),DATE(3,"日期","Date"),OTHER(4,"其他","Object");

    ColumnDataType(int key,String desc,String value)
    {
        this.key=key;
        this.desc=desc;
        this.value=value;
    }
    private int key;
    private String desc;
    private String value;

    public int getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public String getValue() {
        return value;
    }
}
