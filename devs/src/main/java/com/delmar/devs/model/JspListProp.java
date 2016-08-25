package com.delmar.devs.model;

import lombok.Data;

/**
 * Created by admin on 2016/8/25.
 */
@Data
public class JspListProp {
    public JspListProp(String prop,String label)
    {
        this(prop,label,false);
    }
    public JspListProp(String prop,String label,boolean isDate)
    {
        this(prop,label,isDate,true);
    }
    public JspListProp(String prop,String label,boolean isDate,boolean edit)
    {
        this.prop=prop;
        this.label=label;
        this.date=isDate;
        this.edit=edit;
    }

    private String prop;
    private String label;
    private boolean date;
    private boolean edit;
    private boolean booleanTag;
    private String cssStyle;
    private String module;
    private boolean foreign;
}
