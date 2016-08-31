package com.delmar.cargo.model;

import java.util.Date;
import com.delmar.core.model.CoreModel;
import lombok.Data;
 /**
  * table name b_meeting
  * Date:2016-08-31 15:25:16
  **/
@Data
public class Meeting extends CoreModel {

private Integer id;
private String title;
private Date bgnTime;
private Date endTime;
private String descr;
private Date created;
private Integer createdby;
private Date updated;
private Integer updatedby;
private String mainContent;
private Integer userId;
private Integer orgId;
private Integer clientId;

}