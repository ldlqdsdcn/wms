package com.delmar.system.model;

import java.util.Date;
import com.delmar.core.model.CoreModel;
import lombok.Data;
 /**
  * table name sys_user_footmark
  * Date:2016-09-01 17:30:01
  **/
@Data
public class UserFootmark extends CoreModel {

private Integer id;
private String ActionMethod;
private Date visiteDate;
private String ActionName;
private String ActionPurpose;
private Integer userId;
private Integer orgId;
private String remark;
private String remoteAddr;
private String remoteHost;

}