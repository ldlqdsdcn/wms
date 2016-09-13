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
  * table name b_meeting_participant
  * Date:2016-09-12 14:56:11
  **/
@Data
public class MeeingParticipant extends CoreModel {

private Integer id;
private Integer meetingId;
private String name;
private String role;
private Integer userId;
private Integer orgId;
private Integer clientId;
private Date created;
private Integer createdby;
private Date updated;
private Integer updatedby;

}