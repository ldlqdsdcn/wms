<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>



<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><delmar:message key="saleperformance.title" /></title>

<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="stylesheet" href="../css/delmar.css" type="text/css" media="all"/>

<script type='text/javascript' src='../js/dm/delmar.js'></script>
<script type="text/javascript" src="../js/jquery/plugin/delmar/functions.js"/></script>
<script type='text/javascript' src='../js/ea.effect.js'></script>
<script type='text/javascript' src='../js/ea.validate.js'></script>


<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/i18n/datepicker-<s:property value='#session.currentlanguageoriginal'/>.js"/></script>

<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />

<script type="text/javascript" src="../js/jquery/plugin/multiselect/src/jquery.multiselect.js"></script>
<link rel="Stylesheet" href="../js/jquery/plugin/multiselect/jquery.multiselect.css" type="text/css" />
<script type="text/javascript" src="../js/jquery/plugin/multiselect/i18n/jquery.multiselect.<s:property value='#session.currentlanguagelowercase'/>.js"></script>

<link href="../js/jquery/plugin/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<SCRIPT  type="text/javascript" src="../js/jquery/plugin/ligerUI/js/ligerui.min.js"></SCRIPT>

<SCRIPT  type="text/javascript" src="../js/jscharts/jscharts.js"></SCRIPT>



<script type='text/javascript' src='../dwr/interface/orgDwr.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>



<style type="text/css">

.table tbody tr {
   vertical-align:text-top;
}

#chartcontainer {
  clear:both;
  display:block;
  
}



</style>



<script type="text/javascript">

function validateFilter()
{
	

		
	return true;	
	
}

$(document).ready(function() {	
	

	 
	  $("#btnQuery").click(function(event)
	 		 {
	   	     
		        $("#busModeId").val($("#busMode").val());
		        
		        if (document.all.userOrgs)
		        {
		        	$("#orgIds").val($("#userOrgs").val());		        	
		        }		        
		        return validateFilter();
	
	 		 });	
	  

   
    	
       
    	jQuery("#busMode").multiselect(
    	{
    		initValue:$("#busModeId").val(),
    		minWidth:130
    	});
    	
    	var varray=$("#busModeId").val().split(",");
    	for (var i in varray)
    	{
          $("#busMode").find("option[value='"+varray[i]+"']").attr("selected",true);
    	}    
    	

    	if (document.all.userOrgs)
    		{
		    	jQuery("#userOrgs").multiselect(
		    	    	{
		    	    		initValue:$("#orgIds").val(),
		    	    		minWidth:180
		    	    	});
		    	    	
		    	      	
		 	    	var varray=$("#orgIds").val().split(",");
		 	    	for (var i in varray)
		 	    	{
		 	          $("#userOrgs").find("option[value='"+varray[i]+"']").attr("selected",true);
		 	    	}
    		}	    	

       
	});
	
	
	


$(document).ready(function() {	 

				
		
				
			   	jQuery('#monthBegin').datepicker({changeMonth: true,changeYear: true,showOn: "button",
			   		dateFormat:"yy-mm",    		
		            buttonImage: "../images/DatePicker.GIF",
		            buttonImageOnly: true,
		            buttonText: "Select date"
		    	    });
		    	
		    	jQuery('#monthEnd').datepicker({changeMonth: true,changeYear: true,showOn: "button",
		    		dateFormat:"yy-mm",
		            buttonImage: "../images/DatePicker.GIF",
		            buttonImageOnly: true,
		            buttonText: "Select date"
		     	    });
		    					
				


});



 

 
</script>
</head>

<body >


<s:form action="pquery_searchp" namespace="/cargo"  theme="simple" >
<div style="margin:5px">

<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="saleperformance.location" /></td>
          <td class="navig" align="right"> 
          </td>
         
        </tr>
 </table>

 <div id="filterpage">
        <div class="d-filterheader">
            <div id="filterpageheader" style="cursor:pointer; width:90%">
                <img id="filterpagetoggleimage" class="toggle" alt="toggler"
                     src="../images/core/icon_expand_l.gif"/>
                <delmar:message key="public.list.filter"/>
            </div>
        </div>
        
        <div id="filterForm" class="d-hidden">
            <s:hidden id="queryStatus" value="%{#session.MAP_KEY_OF_SESSION.queryStatus}"/>
			<table border="0" cellpadding="0" cellspacing="0"  class="cTableBorder" style="padding-top:5px;padding-bottom:5px">
		       <tr>
		       <td class="d-tdlabel">      	
			     <delmar:message key="buscustomer.column.yesoutin" />
			     <s:checkbox name="soutIn" id="soutIn" fieldValue="1"  />
			      
			   </td>		      
  		        <td   class="d-tdinput"> 
			      <s:checkbox name="outInOut" id="outInOut" fieldValue="0" />
			      <delmar:message key="buscustomer.column.outinout" /> 
			      <s:checkbox name="outInIn" id="outInIn" fieldValue="1"   />
			      <delmar:message key="buscustomer.column.outinin" /> 
			   </td>
			   <td class="d-tdlabel">
			      	<delmar:message key="cargobusiness.column.busmode" />
                    <s:checkbox name="smode" id="smode" fieldValue="1" />		      	
			   </td>
			      <td class="d-tdinput"> 	
			    	<s:select list="busModeList" listKey="value" listValue="name"  name="busMode"  multiple="true" value="%{#session.MAP_KEY_OF_SESSION.status}"  id="busMode" ></s:select>
			    	<s:hidden name="busModeId" id="busModeId" value="%{#session.MAP_KEY_OF_SESSION.busModeId}" />       
			      </td>			   
		       <td class="d-tdlabel">      	
			     <delmar:message key="salesperformance.column.month" />
			   </td>			   		      		       
			   <td   class="d-tdinput"> 
                  <s:textfield name="monthBegin" id="monthBegin" value="%{#session.MAP_KEY_OF_SESSION.monthBegin}"  cssClass="d-searchtext"></s:textfield>
                  <delmar:message key="public.column.to" /> 
			      <s:textfield name="monthEnd" id="monthEnd" value="%{#session.MAP_KEY_OF_SESSION.monthEnd}"  cssClass="d-searchtext" ></s:textfield>
			   </td>
 

			      
			    <td class="d-tdlabel"> 	
		       	<!--To check whether the user have the group privilege  -->
		       	<c:if test="${MAP_KEY_OF_SESSION.orgVisible=='true'}">
		    	<delmar:message key="cargobusiness.column.org" />
		    	</c:if>
		       </td>
		      <td class="d-tdinput" > 
		       
		       
		        <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='true'}">
			    	<s:select list="userOrgAccessList" listKey="id" listValue="name"  name="userOrgs"  multiple="true"   id="userOrgs" ></s:select>
                    <s:textfield name="userName" id="userName"  value="%{#session.MAP_KEY_OF_SESSION.userName}" cssClass="d-searchtext"  title="%{#session.resource.get('customer.inputtitle.username')}"></s:textfield>			    	
		       	</c:if>
		       	
		        <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='org'}">
			    	<delmar:message key="public.accessprivilege.org"/>
		       	</c:if>
		       			       	
		        <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='group'}">
			    	<delmar:message key="public.accessprivilege.usergroup"/>
			    	<s:checkbox name="beGroup" id="beGroup" fieldValue="true" value="%{#session.MAP_KEY_OF_SESSION.beGroup}" />
			    	<s:textfield name="userName" id="userName"  value="%{#session.MAP_KEY_OF_SESSION.userName}" cssClass="d-searchtext"  title="%{#session.resource.get('customer.inputtitle.username')}"></s:textfield>
		       	</c:if>		
		       			       			       	
		       	<s:hidden name="orgIds" id="orgIds" value="%{#session.MAP_KEY_OF_SESSION.orgIds}"></s:hidden>
		      </td>					      
			    </tr>
			
			              
			       <tr>
			       <td colspan=6 style="text-align:left; padding:5px;">     
			       <s:submit method="searchp" cssClass="input_submit" id="btnQuery" value="%{#session.resource.get('common.button.search')}" ></s:submit>
                   <s:reset cssClass="input_submit" id="btnReset" value="%{#session.resource.get('common.button.reset')}" ></s:reset>			       
			       </td>    
			       </tr>
			       </table>        
        </div>

 </div>
    


<!-- <c:out value="${buttons}" escapeXml="false"/> -->
  <display:table name="sessionScope.MAP_KEY_OF_SESSION.businessList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true" >

		    
	<display:column  titleKey="common.label.sequence" media="html csv excel xml pdf rtf" style="width:40px;">
		   <c:out value="${list_rowNum}"/>
    </display:column>   
    

	  <display:column property="code"  escapeXml="true"  titleKey="salesperformance.column.code"  sortable="true" />	  
	  <display:column property="name"  escapeXml="true"  titleKey="salesperformance.column.name"  sortable="true" />	
	    
	    <c:if test="${list.groupList[3]!=null}">
	   	<display:column escapeXml="true"  titleKey="buscustomer.column.group1"  sortable="true" >
	          <c:out value="${list.groupList[3]}"/>
	   	</display:column>
	    </c:if>>
	    
	    <c:if test="${list.groupList[4]!=null}">
	   	<display:column escapeXml="true"  titleKey="buscustomer.column.group2"  sortable="true" >
	          <c:out value="${list.groupList[4]}"/>
	   	</display:column>
	    </c:if>>    
      <display:column property="imonth"  escapeXml="true"  titleKey="salesperformance.column.imonth"  sortable="true" />            
      <display:column property="inumber"  escapeXml="true"  titleKey="salesperformance.column.inumber"  sortable="true" />
      <display:column  property="profit"   titleKey="salesperformance.column.profit" sortable="true"/>
      <display:column  titleKey="salesperformance.column.yourpos" sortable="true">
        <c:out value="${list.yourPos}"/>
        <a href="javascript:showDetailPos('<c:out value="${list.topToYouProfit}"/>')"><delmar:message key="salesperformance.column.toptoyou"/></a> 
      </display:column>      
      <display:column   titleKey="salesperformance.column.ssprofit"  sortable="true" >
       <c:out value="${list.ssProfit}"/>
       <c:if test="${list.ssPercent>0}">
         <font color='#ff0000'> (<c:out value="${list.ssPercent}"/>)</font>
       </c:if>
       <c:if test="${list.ssPercent<0}">
         <font color='#33cc00'> (<c:out value="${list.ssPercent}"/>)</font>
       </c:if>
      
      </display:column>
      <display:column    titleKey="salesperformance.column.yyprofit"  sortable="true" >
       <c:out value="${list.yyProfit}"/>
       <c:if test="${list.yyPercent>0}">
         <font color='#ff0000'> (<c:out value="${list.yyPercent}"/>)</font>
       </c:if>
       <c:if test="${list.yyPercent<0}">
         <font color='#33cc00'> (<c:out value="${list.yyPercent}"/>)</font>
       </c:if>      
      </display:column>
      
	  </display:table>

</div>


</s:form>


<div id="chartcontainer" style="clear:both;display:block;padding-top:20px;margin-top:20px;margin-left:20px"></div> 




<script type="text/javascript">




	
$(document).ready(function() {

	   // $(document).D_Validate();
	    
        if ("<delmar:session key='soutIn'/>"=="1")
         {
        	 $("#soutIn").attr("checked","true");
         }
	      
        if ("<delmar:session key='outInOut'/>"=="0")
        {
       	 $("#outInOut").attr("checked","true");
        }
	      
        if ("<delmar:session key='outInIn'/>"=="1")
        {
       	 $("#outInIn").attr("checked","true");
        }
	      
        if ("<delmar:session key='smode'/>"=="1")
        {
       	 $("#smode").attr("checked","true");
        }
	      
	    if ($('input[name="haveBusiness"]:checked').val()=="0")
	    	$("#nobusiness").addClass("d-hidden");


});  
 

  function viewExport(id) {
       window.location='<c:url value="/crm/customer_edit.action"/>?id='+id;
   }
    highlightTableRows("list");
    

    if ($('#filterpageheader')) {
        if (typeof(Event) == "undefined") {
        } else {
        	$('#filterpageheader').bind('click', function() {
                $('#filterForm').toggle();
                if ($('#filterForm').hasClass("d-visible")) {
                	$('#filterForm').removeClass("d-visible");
                	$('#filterForm').addClass("d-hidden");
                    $('#filterpagetoggleimage').attr('src','../images/core/icon_collapse_l.gif');
                } else {
                	$('#filterForm').removeClass("d-hidden");
                	$('#filterForm').addClass("d-visible");
                    $('#filterpagetoggleimage').attr('src','../images/core/icon_expand_l.gif');
                }
            }) ;
        }
    }
    
    if ($("#queryStatus").val()=="true")  
    {
    	$('#filterForm').removeClass("d-hidden");
    	$('#filterForm').addClass("d-visible");
        $('#filterpagetoggleimage').attr('src','../images/core/icon_expand_l.gif');
    }
    
    
 
     function showDetailPos(topToYou)
     {
      	 $.ligerDialog.success(topToYou);
     }
     
     function getDataArray(jsonlineObject)
     {
         var  array=new Array();    
         for (var i=0;i<jsonlineObject.length;i++)
         {
        	 var arr=new Array();
        	 arr[0]=jsonlineObject[i].unit;
        	 arr[1]=jsonlineObject[i].value*1;
        	// arr[2]=jsonlineObject[i].value+100;
        	 array[i]=arr;

         }  
         
         return array;
     }
    
    
     $(document).ready(function() {

          var mydatajson='<c:out value="${sessionScope.MAP_KEY_OF_SESSION.mydatajson}" escapeXml="false"/>';
          //alert(mydatajson);
          var jsonobj=eval('['+mydatajson+']');
          var jsonLineArray=jsonobj[0].datasets;
          var currentLine=new Array();
          var yyline=new Array();
          currentLine=getDataArray(jsonLineArray[0].data);
          yyline=getDataArray(jsonLineArray[1].data);
          var mycharts=new JSChart("chartcontainer","line");
          mycharts.setTitle("业绩曲线图");
          mycharts.setTitleColor("#5555AA");
          mycharts.setTitleFontSize(12);
          mycharts.setBackgroundColor("#efe");
          mycharts.setAxisNameX("月份");
          mycharts.setAxisNameY("毛利");
          mycharts.setIntervalStartY(0);
          //mycharts.setAxisAlignX(true);
          //mycharts.setAxisAlignY(true);          
          mycharts.setSize(700,400);
          mycharts.setDataArray(currentLine,"当年的数据");          
          mycharts.setLineColor("#ff0f0f","当年的数据");
          //mycharts.setTooltip([15,"当年的数据","current line"]);
          mycharts.setDataArray(yyline,"上一年的数据");
          mycharts.setLineColor("#ff0fff","上一年的数据");
          //mycharts.setTooltip([15,"上一年的数据","yy line"]);
          
         
          mycharts.setLegendPosition("right top");
          mycharts.setLegendShow(true);
          
          mycharts.draw();

          
     
     });
    
</script>

</body>
</html>
