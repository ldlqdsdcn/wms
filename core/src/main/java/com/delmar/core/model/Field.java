package com.delmar.core.model;

import lombok.Data;
 /**
  * table name core_field
  * Date:2016-08-26 17:08:24
  **/
@Data
public class Field extends CoreModel {

private Integer id;
private String name;
private Integer labelId;
private String isRequired;
private Integer typeId;
private Integer tabId;
private Integer columnId;
private Integer showType;
private Integer linkTableId;
private Integer linkValueColumnId;
private Integer linkLabelColumnId;
private String help;
private String defaultValue;
private Integer seqNo;
private String sameline;
private Integer lineTabId;
private String linkTableFilterSql;
private String validateRole;
private String showInList;
private String showInForm;

}