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
  * table name core_label
  * Date:2016-09-05 14:01:13
  **/
@Data
public class Label extends CoreModel {

private Integer id;
private String value;
private String msgtext;
private Date compDate;
private Date bgnTime;

}