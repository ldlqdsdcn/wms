package com.delmar.core.model;

import lombok.Data;
 /**
  * table name core_label
  * Date:2016-08-26 23:24:10
  **/
@Data
public class Label extends CoreModel {

private Integer id;
private String value;
private String msgtext;

}