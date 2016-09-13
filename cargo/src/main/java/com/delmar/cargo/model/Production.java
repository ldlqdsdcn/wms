/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.cargo.model;

import java.util.Date;
import com.delmar.core.model.CoreModel;
import lombok.Data;
 /**
  * table name b_production
  * Date:2016-09-13 11:28:19
  **/
@Data
public class Production extends CoreModel {

private String documentno;
private String name;
private Date completeDate;
private Date created;
private Integer createdby;
private Date updated;
private Integer updatedby;
private Integer orgId;
private Integer clientId;
private Integer userId;
private String status;

}