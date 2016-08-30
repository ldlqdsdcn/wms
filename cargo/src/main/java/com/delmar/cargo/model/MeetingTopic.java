package com.delmar.cargo.model;

import com.delmar.core.model.CoreModel;
import lombok.Data;
 /**
  * table name b_meeting_topic
  * Date:2016-08-30 17:40:14
  **/
@Data
public class MeetingTopic extends CoreModel {

private Integer id;
private String title;
private Integer meetingId;
private String level;

}