package com.delmar.core.def;

/**
 * Created by admin on 2016/8/19.
 */
public enum ShowType {
    SELECT(0,"Select"),
    DATE_PICKER(1,"Date Picker"),
    RADIO(2,"Radio"),
    CHECK_BOX(3,"Check Box"),
    TEXT_FIELD(4,"Text Field"),
    AUTO_COMPLETE(5,"AutoComplete"),
    DIALOG(6,"Dialog"),
    DETAIL_TABLE(7,"DetailTable");
     ShowType(int key,String desc)
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
