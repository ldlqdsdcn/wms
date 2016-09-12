/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.core.model;

import lombok.Data;
 /**
  * table name core_message_trl
  * Date:2016-09-12 10:38:53
  **/
@Data
public class MessageTrl extends CoreModel {

private Integer id;
private Integer messageId;
private String language;
private String msgtext;

}