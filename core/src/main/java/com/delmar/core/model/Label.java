package com.delmar.core.model;

import java.util.Date;
import lombok.Data;
 /**
  * table name core_label
  * Date:2016-08-27 10:31:45
  **/
@Data
public class Label extends CoreModel {

private Integer id;
private String value;
private String msgtext;
private Date compDate;
private Date bgnTime;

}