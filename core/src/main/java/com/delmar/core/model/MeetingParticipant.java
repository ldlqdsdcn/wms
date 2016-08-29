package com.delmar.core.model;

import lombok.Data;
 /**
  * table name core_meeting_participant
  * Date:2016-08-27 16:28:14
  **/
@Data
public class MeetingParticipant extends CoreModel {

private Integer id;
private Integer meetingId;
private String name;
private String role;

}