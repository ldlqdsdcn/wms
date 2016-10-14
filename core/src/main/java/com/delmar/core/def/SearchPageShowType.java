package com.delmar.core.def;

/**
 * Created by 刘大磊 on 2016/10/8 16:10.
 */
public enum SearchPageShowType {
    NORMAL_SEARCH_PAGE(1,"普通查询"),COMBINE_SEARCH_PAGE(2,"组合查询");
    SearchPageShowType(int key,String desc)
    {
        this.key=key;
        this.desc=desc;
    }
    private final int key;
    private final String desc;

    public int getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}
