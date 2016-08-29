package com.delmar.core.model;

import lombok.Data;
 /**
  * table name core_meeting_topic
  * Date:2016-08-27 16:28:14
  **/
@Data
public class MeetingTopic extends CoreModel {

private Integer id;
private String title;
private Integer meetingId;
private String level;

}