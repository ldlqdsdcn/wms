package com.delmar.cargo.model;

import java.util.Date;
import com.delmar.core.model.CoreModel;
import lombok.Data;
 /**
  * table name b_meeting_topic
  * Date:2016-08-31 15:25:16
  **/
@Data
public class MeetingTopic extends CoreModel {

private Integer id;
private String title;
private Integer meetingId;
private String level;
private Integer userId;
private Integer clientId;
private Date created;
private Integer createdby;
private Date updated;
private Integer updatedby;

}