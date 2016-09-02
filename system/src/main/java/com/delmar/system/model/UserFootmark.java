package com.delmar.system.model;

import java.util.Date;
import com.delmar.core.model.CoreModel;
import lombok.Data;
 /**
  * table name sys_user_footmark
  * Date:2016-09-02 10:18:25
  **/
@Data
public class UserFootmark extends CoreModel {

private Integer id;
private String actionMethod;
private Date visiteDate;
private String actionName;
private String actionPurpose;
private Integer userId;
private Integer orgId;
private String remark;
private String remoteAddr;
private String remoteHost;

}