package com.delmar.devs.model;

import lombok.Data;

import java.util.List;

/**
 * Created by admin on 2016/8/27.
 */
@Data
public class FormLine {
    private String label;
    private String model;
    private String module;
    private List<JspModelProp> propertyList;
}
