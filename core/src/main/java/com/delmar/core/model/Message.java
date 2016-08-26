package com.delmar.core.model;

import lombok.Data;
 /**
  * table name core_message
  * Date:2016-08-26 17:30:05
  **/
@Data
public class Message extends CoreModel {

private Integer id;
private String value;
private String msgtext;

}