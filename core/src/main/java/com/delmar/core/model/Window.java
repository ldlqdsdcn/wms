package com.delmar.core.model;

import java.util.Date;
import lombok.Data;
 /**
  * table name core_Window
  * Date:2016-08-27 16:44:29
  **/
@Data
public class Window extends CoreModel {

private Integer id;
private String name;
private String descr;
private String help;
private Date created;
private Integer createdby;
private Date updated;
private Integer updatedby;
private String isactive;

}