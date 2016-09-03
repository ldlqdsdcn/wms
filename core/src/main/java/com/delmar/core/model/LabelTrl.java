/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.core.model;

import lombok.Data;
 /**
  * table name core_label_trl
  * Date:2016-09-03 23:25:22
  **/
@Data
public class LabelTrl extends CoreModel {

private Integer id;
private String language;
private Integer labelId;
private String msgtext;

}