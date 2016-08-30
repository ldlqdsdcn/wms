<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2016-08-30 22:15:08
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<html>
<head>

<title>
	会议纪要
</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" charset="utf-8" />
    <link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
    <script type='text/javascript' src='../js/ea.effect.js'></script>
    <script type='text/javascript' src='../js/ea.validate.js'></script>
    <script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
    <script type="text/javascript" src="../js/jquery/plugin/jquery.datetimepicker.full.min.js"></script>
    <link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" >
    <link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" >
    <link rel="Stylesheet" href="../js/jquery/plugin/jquery.datetimepicker.min.css" type="text/css" >
    <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="../css/bootstrap/bootstrap-theme.min.css" type="text/css" media="all"/>
    <script type='text/javascript' src='../js/bootstrap/bootstrap.js'></script>
    <script type='text/javascript' src='../js/jquery/plugin/bootbox.min.js'></script>
<script type="text/javascript">
 function gotoList()
 {
	 window.location='<c:url value="/cargo/meeting_list.action"/>';
 }
function addMeetingParticipant() {
var editForm = document.getElementById('editForm');
editForm.action = '<c:url value="/cargo/meeting_addMeetingParticipant.action"/>';
editForm.submit();
}
function deleteMeetingParticipants() {
if(isEmptyCheckBox('MeetingParticipant_ids'))
{
alert('请先选择再删除');
return;
}
var editForm = document.getElementById('editForm');
editForm.action = '<c:url value="/cargo/meeting_deleteMeetingParticipants.action"/>';
editForm.submit();
}
function addMeetingTopic() {
var editForm = document.getElementById('editForm');
editForm.action = '<c:url value="/cargo/meeting_addMeetingTopic.action"/>';
editForm.submit();
}
function deleteMeetingTopics() {
if(isEmptyCheckBox('MeetingTopic_ids'))
{
alert('请先选择再删除');
return;
}
var editForm = document.getElementById('editForm');
editForm.action = '<c:url value="/cargo/meeting_deleteMeetingTopics.action"/>';
editForm.submit();
}
 $(document).ready(function(){
    $('#formDetail tr:odd').addClass("query_one");
     $('#formDetail tr:even').addClass("query_two");
     $("#formDetail").find("tr:last").removeClass("query_one").removeClass("query_two");
        $.datetimepicker.setLocale('en');

        $('#bgnTime').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
        format:"Y-m-d H:i:s"
        });

        $('#endTime').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
        format:"Y-m-d H:i:s"
        });
     $("#saveBtn").click(function(){
            if (isEmpty($("#title").val())) {
            alert("标题不允许为空");
            $("#title").focus();
            return false;
            }


            if (!validateDateTime($("#bgnTime").val())) {
            alert("开始时间必须为日期时间");
            $("#bgnTime").focus();
            return false;
            }



            if (isEmpty($("#bgnTime").val())) {
            alert("开始时间不允许为空");
            $("#bgnTime").focus();
            return false;
            }


            if (!validateDateTime($("#bgnTime").val())) {
            alert("开始时间必须为日期时间");
            $("#bgnTime").focus();
            return false;
            }



            if (!validateDateTime($("#endTime").val())) {
            alert("结束时间必须为日期时间");
            $("#endTime").focus();
            return false;
            }



            if (isEmpty($("#endTime").val())) {
            alert("结束时间不允许为空");
            $("#endTime").focus();
            return false;
            }


            if (!validateDateTime($("#endTime").val())) {
            alert("结束时间必须为日期时间");
            $("#endTime").focus();
            return false;
            }





         var validateLine=true;
         var lineMsg="";
$("input[name^=meetingParticipantList]").each(function(i, item){












});
$("input[name^=meetingTopicList]").each(function(i, item){












});
        if(!validateLine)
        {
            bootbox.alert(lineMsg);
            return false;
        }

         return true;
     });
 });
</script>
</head>
<body>
<s:form id="editForm" action="meeting_edit" namespace='/cargo' theme="simple">
<s:hidden id="id" name="meeting.id"></s:hidden>
<!--table 01 bgn-->
<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td valign="top">
        <!--table 02 bgn-->
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr>
          <td align="left" class="navig">	会议纪要</td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">

         <div class="C_S_F_L">
			<c:if test="${!isFirst}">

				<s:submit method="getPrevionsOne" value="%{#session.resource.get('common.button.previous')}"  cssClass="input_submit"></s:submit>
			</c:if>


			<c:if test="${!isLast}">
			<s:submit method="getNextOne" value="%{#session.resource.get('common.button.next')}"  cssClass="input_submit"></s:submit>

			</c:if>
		</div></td>
        </tr>
      </table>
        <!--table 02 end-->
        <!--table 03 bgn-->
    	<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="table_blue">
        <tr align="center" valign="top">
          <td>
          <!--table 04 bgn-->
            <table width="100%" cellpadding="10" cellspacing="0" >
              <tr>
                <td align="center">
                    <!--table 05 bgn-->
                    <table width="100%" border="0" cellpadding="10" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                <tr>
                <td>


                    <!--table 06 bgn-->
                <table width="100%" border="0" cellpadding="0" cellspacing="1" id="formDetail">
                        <tr>
                        <td width="20%"><s:label for="title" value="标题" ></s:label></td>
                        <td width="30%">
                        
                          <s:textfield name="meeting.title" id="title"   ></s:textfield>
                            <span style="color:red">*</span>
                            <s:fielderror fieldName="meeting.title"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="bgnTime" value="开始时间" ></s:label></td>
                        <td width="30%">
                        
                        <s:textfield name="meeting.bgnTime" id="bgnTime" >
                         <s:param name="value"><s:date name="meeting.bgnTime"  format="yyyy-MM-dd HH:mm:ss"/></s:param>
                        </s:textfield>
                        <span style="color:red">*</span>
                            <s:fielderror fieldName="meeting.bgnTime"   cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="endTime" value="结束时间" ></s:label></td>
                        <td width="30%">
                        
                        <s:textfield name="meeting.endTime" id="endTime" >
                         <s:param name="value"><s:date name="meeting.endTime"  format="yyyy-MM-dd HH:mm:ss"/></s:param>
                        </s:textfield>
                        <span style="color:red">*</span>
                            <s:fielderror fieldName="meeting.endTime"   cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="descr" value="描述" ></s:label></td>
                        <td width="30%">
                        
                          <s:textfield name="meeting.descr" id="descr"  cssStyle="width:500px;" ></s:textfield>
                            
                            <s:fielderror fieldName="meeting.descr"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="created" value="创建时间" ></s:label></td>
                        <td width="30%">
                        
                          <s:textfield name="meeting.created" id="created" readonly="true"  ></s:textfield>
                            <span style="color:red">*</span>
                            <s:fielderror fieldName="meeting.created"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="createdby" value="创建人" ></s:label></td>
                        <td width="30%">
                        
                           <delmar:user userId="${meeting.createdby}" module="user"/>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="updated" value="修改时间" ></s:label></td>
                        <td width="30%">
                        
                          <s:textfield name="meeting.updated" id="updated" readonly="true"  ></s:textfield>
                            <span style="color:red">*</span>
                            <s:fielderror fieldName="meeting.updated"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="updatedby" value="修改人" ></s:label></td>
                        <td width="30%">
                        
                           <delmar:user userId="${meeting.updatedby}" module="user"/>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="mainContent" value="mainContent" ></s:label></td>
                        <td width="30%">
                        
                          <s:textfield name="meeting.mainContent" id="mainContent"   ></s:textfield>
                            
                            <s:fielderror fieldName="meeting.mainContent"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="userId" value="userId" ></s:label></td>
                        <td width="30%">
                        
                           <delmar:user userId="${meeting.userId}" module="user"/>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="orgId" value="组织" ></s:label></td>
                        <td width="30%">
                        
                           <delmar:user userId="${meeting.orgId}" module="org"/>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="clientId" value="实体" ></s:label></td>
                        <td width="30%">
                        
                           <delmar:user userId="${meeting.clientId}" module="client"/>
                          </td>
                        </tr>

<tr>
    <td colspan="4" style="padding-left: 0px;">
        <!-- table 页 bgn -->
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                        <td  style="height: 26px;width: 90px;" align="center" background="../images/table_page_1.gif">
                            参与人
                        </td>
                <td background="../images/table_page_bg.gif" width="*"  style="height: 26px;"><div class="C_S_F_L">
                    &nbsp;
                </div></td>
            </tr>
        </table>
        <!-- table 页 end -->
    </td>

</tr>
<tr>
    <td colspan="2" style="padding-left: 20px;text-align: left;">
        <input value="添加参与人"
        type="button" class="input_submit"
        onclick="javascript:addMeetingParticipant()"/> &nbsp;&nbsp;
        <input class="input_submit" type="button"  value="删除选中参与人"
        onclick="javascript:deleteMeetingParticipants()"/>
    </td>
    <td colspan="2"></td>
</tr>
<tr>
    <td colspan="4">
        <table id="MeetingParticipantTable" class="table">
            <thead>
            <th>
                <input type="checkbox"
                       onclick="selectAll('MeetingParticipant_ids',this);"/>
            </th>
            <th>序号</th>
            <th >名称</th>
            <th >角色</th>
            <th >userId</th>
            <th >组织</th>
            <th >实体</th>
            </thead>
            <tbody>
            <s:iterator value="meetingParticipantList" status="st">

                <tr class="<s:property value="#st.index%2==0?'odd':'even'"/>">
                    <td>
                        <input type="checkbox"  name="MeetingParticipant_ids"
                               value="<s:property value="#st.index"/>"/>
                    </td>
                    <td>
                        <s:property value="#st.index+1"/>
                        <s:hidden
                                name="%{'meetingParticipantList['+#st.index+'].id'}"/>
                    </td>
                <td>
                    <s:textfield
                            name="%{'meetingParticipantList['+#st.index+'].name'}">
                    </s:textfield> 
                </td>
                <td>
                    <s:textfield
                            name="%{'meetingParticipantList['+#st.index+'].role'}">
                    </s:textfield> 
                </td>
                <td>
                    <s:textfield
                            name="%{'meetingParticipantList['+#st.index+'].userId'}">
                    </s:textfield> <span style="color:red">*</span>
                </td>
                <td>
                    <s:textfield
                            name="%{'meetingParticipantList['+#st.index+'].orgId'}">
                    </s:textfield> <span style="color:red">*</span>
                </td>
                <td>
                    <s:textfield
                            name="%{'meetingParticipantList['+#st.index+'].clientId'}">
                    </s:textfield> <span style="color:red">*</span>
                        <s:hidden name="%{'meetingParticipantList['+#st.index+'].meetingId'}"></s:hidden>
                </td>


                </tr>

            </s:iterator>

            </tbody>
        </table>
    </td>
</tr>
<tr>
    <td colspan="4" style="padding-left: 0px;">
        <!-- table 页 bgn -->
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                        <td  style="height: 26px;width: 90px;" align="center" background="../images/table_page_1.gif">
                            讨论内容
                        </td>
                <td background="../images/table_page_bg.gif" width="*"  style="height: 26px;"><div class="C_S_F_L">
                    &nbsp;
                </div></td>
            </tr>
        </table>
        <!-- table 页 end -->
    </td>

</tr>
<tr>
    <td colspan="2" style="padding-left: 20px;text-align: left;">
        <input value="添加讨论内容"
        type="button" class="input_submit"
        onclick="javascript:addMeetingTopic()"/> &nbsp;&nbsp;
        <input class="input_submit" type="button"  value="删除选中讨论内容"
        onclick="javascript:deleteMeetingTopics()"/>
    </td>
    <td colspan="2"></td>
</tr>
<tr>
    <td colspan="4">
        <table id="MeetingTopicTable" class="table">
            <thead>
            <th>
                <input type="checkbox"
                       onclick="selectAll('MeetingTopic_ids',this);"/>
            </th>
            <th>序号</th>
            <th >标题</th>
            <th >等级</th>
            <th >userId</th>
            <th >组织</th>
            <th >实体</th>
            </thead>
            <tbody>
            <s:iterator value="meetingTopicList" status="st">

                <tr class="<s:property value="#st.index%2==0?'odd':'even'"/>">
                    <td>
                        <input type="checkbox"  name="MeetingTopic_ids"
                               value="<s:property value="#st.index"/>"/>
                    </td>
                    <td>
                        <s:property value="#st.index+1"/>
                        <s:hidden
                                name="%{'meetingTopicList['+#st.index+'].id'}"/>
                    </td>
                <td>
                    <s:textfield
                            name="%{'meetingTopicList['+#st.index+'].title'}">
                    </s:textfield> 
                </td>
                <td>
                    <s:textfield
                            name="%{'meetingTopicList['+#st.index+'].level'}">
                    </s:textfield> 
                </td>
                <td>
                    <s:textfield
                            name="%{'meetingTopicList['+#st.index+'].userId'}">
                    </s:textfield> <span style="color:red">*</span>
                </td>
                <td>
                    <s:textfield
                            name="%{'meetingTopicList['+#st.index+'].orgId'}">
                    </s:textfield> <span style="color:red">*</span>
                </td>
                <td>
                    <s:textfield
                            name="%{'meetingTopicList['+#st.index+'].clientId'}">
                    </s:textfield> <span style="color:red">*</span>
                        <s:hidden name="%{'meetingTopicList['+#st.index+'].meetingId'}"></s:hidden>
                </td>


                </tr>

            </s:iterator>

            </tbody>
        </table>
    </td>
</tr>
                      <tr>
                          <td colspan="4" class="td_page_right" style="text-align:right;height: 24px;">
                              <s:submit method="create" value="%{#session.resource.get('common.button.create')}"  cssClass="input_submit"></s:submit>
                              <s:submit id="saveBtn" method="save" value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit" ></s:submit>
                              <c:if test="${meeting.id!=null}">
                                  <s:submit method="delete"  value="%{#session.resource.get('common.button.delete')}"  cssClass="input_submit" onclick="return confirmDelete()"></s:submit>
                              </c:if>
                              <input onclick="gotoList()"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
                          </td>
                      </tr>
                </table>
                    <!--table 06 end-->
                </td>
                </tr>
                <tr>
                <td >
                <s:actionmessage cssStyle="color:red"/>

                </td>
                </tr>
                </table>
                <!--table 05 end-->
                </td></tr></table>
                <!--table 04 end-->
</td></tr></table>
    </td></tr></table>
</s:form>
</body>
</html>
