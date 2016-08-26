package com.delmar.core.model;

import lombok.Data;
 /**
  * table name core_page
  * Date:2016-08-26 16:58:45
  **/
@Data
public class Page extends CoreModel {

private Integer id;
private String name;
private String descr;
private String help;
private Integer windowId;
private Integer tableId;
private Integer filterColumnId;
private String isactive;
private String showInTab;
private Integer seqNo;
private String filterSql;

}