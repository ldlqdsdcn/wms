package com.delmar.core.model;

import java.util.Date;
import lombok.Data;
 /**
  * table name core_meeting
  * Date:2016-08-27 16:28:14
  **/
@Data
public class Meeting extends CoreModel {

private Integer id;
private String name;
private Date bgnTime;
private Date endTime;
private String descr;

}