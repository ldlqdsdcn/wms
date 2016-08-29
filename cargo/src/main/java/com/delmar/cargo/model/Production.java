package com.delmar.cargo.model;

import java.util.Date;
import com.delmar.core.model.CoreModel;
import lombok.Data;
 /**
  * table name b_production
  * Date:2016-08-29 15:01:00
  **/
@Data
public class Production extends CoreModel {

private Integer id;
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