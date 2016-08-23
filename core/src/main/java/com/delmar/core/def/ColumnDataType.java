package com.delmar.core.def;

/**
 * Created by admin on 2016/8/23.
 */
public enum  ColumnDataType implements java.io.Serializable{
    INT(0,"整数"),FLOAT(1,"浮点数"),STRING(2,"字符串"),DATE(3,"日期"),OTHER(4,"其他");

    ColumnDataType(int key,String desc)
    {
        this.key=key;
        this.desc=desc;
    }
    private int key;
    private String desc;


    public int getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

}
