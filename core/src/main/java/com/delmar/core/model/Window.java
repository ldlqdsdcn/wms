/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.core.model;

import java.util.Date;
import lombok.Data;
 /**
  * table name core_window
  * Date:2016-09-12 15:10:29
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
private Integer typeId;

}