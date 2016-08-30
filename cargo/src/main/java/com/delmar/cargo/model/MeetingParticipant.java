package com.delmar.cargo.model;

import com.delmar.core.model.CoreModel;
import lombok.Data;
 /**
  * table name b_meeting_participant
  * Date:2016-08-30 22:15:07
  **/
@Data
public class MeetingParticipant extends CoreModel {

private Integer id;
private Integer meetingId;
private String name;
private String role;
private Integer userId;
private Integer orgId;
private Integer clientId;

}