package com.delmar.core.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by admin on 2016/8/24.
 */
@Data
public class Window extends CoreModel {
    private String name;
    private String desc;
    private String help;
    private Date created;
    private Integer createdBy;
    private Date updated;
    private Integer updatedBy;
    private String isactive;
}
