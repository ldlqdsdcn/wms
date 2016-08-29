package com.delmar.core.def;

/**
 * @author dalei.liu 2016/8/29 20:38.
 */
public enum SearchDataTypeDef {
    NUMBER(1,"字符"),CHAR(2,"整数"),FLOAT(3,"数值"),DATE(3,"日期"),DATETIME(4,"日期时间");
    SearchDataTypeDef(int key,String desc)
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
