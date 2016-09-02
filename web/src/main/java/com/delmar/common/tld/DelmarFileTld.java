/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.common.tld;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.delmar.common.model.FileRelation;
import com.delmar.common.model.FileSetting;
import com.delmar.common.service.DelmarFileService;
import com.delmar.common.service.FileRelationService;
import com.delmar.common.service.FileSettingService;
import com.delmar.core.web.action.PrivilegeOperator;
import com.delmar.core.web.action.PrivilegeOperatorComon;
import com.delmar.core.web.bean.EaContext;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.Module;
import com.delmar.utils.DmLog;

/**
 * @author 刘大磊 2015年8月18日 下午6:03:33
 */
public class DelmarFileTld extends BodyTagSupport {
	private static final DmLog logger=DmLog.getLogger(DelmarFileTld.class);
	/**
	 * 是否可以上传多个文件
	 */
	private boolean multiple;
	/**
	 * 关联主键 模块的ID值
	 */
	private Integer tableId;
	/**
	 * 表名称
	 */
	private String tableName;
	
	/*回调函数
	 * */
	private String callbackFunc;
	/**
	 * 是否显示缩写文件名
	 */
	private boolean issort;
	
	private DelmarFileService delmarFileService;
	
	private FileRelationService fileRelationService;
	
	private FileSettingService fileSettingService;
	
	public DelmarFileTld()
	{
		delmarFileService=EaContext.getBean("delmarFileService", DelmarFileService.class);
		fileRelationService=EaContext.getBean("fileRelationService", FileRelationService.class);
		fileSettingService=EaContext.getBean("fileSettingService", FileSettingService.class);
	}
	
	
	
	
	
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
		
		
		
		Module module=PrivilegeOperatorComon.getModule(request);
		FacesUtils.setValueInHashtableOfSession("module", module);
		Map<String,Object> param1=new HashMap<String,Object>();
		param1.put("moduleId", module.getId());
		FileSetting fs=fileSettingService.getByExample(param1);
		FacesUtils.setValueInHashtableOfSession("fileSetting", fs,request.getSession());
		
		
		
		UserResource resource= (UserResource)request.getSession().getAttribute("resource");
		request.getSession().setAttribute("tableId", tableId+"");
		request.getSession().setAttribute("tableName", tableName);
		
		request.getServletPath();
		StringBuffer sb=new StringBuffer();
		if(tableId!=null&&tableId!=0)
		{
			sb.append("<div id='delmar_file_div' class='d-file-panel'>");
			sb.append("<table id='delmar_file_table' >");
			sb.append("<tr><td width='6%'>");
			sb.append("<input type=\"button\"  class=\"input_submit\" onclick=\"javascript:uploadFileDelmar('"+tableName+"',"+tableId+","+callbackFunc+",'"+resource.get("public.uploadfile.title")+"');\" value=\""+resource.get("public.uploadfile.title")+"\">");
			sb.append("</td>");
			Map param=new HashMap();
			param.put("tableName", tableName);
			param.put("tableId", tableId);
			List<FileRelation>fileRelationList=fileRelationService.selectByExample(param);
			int count=0;
			for(FileRelation fileR:fileRelationList)
			{
				count ++;
				if(count>3)
				{
					String moreUrl=request.getContextPath()+"/commons/commonFile_list.action?tableName="+tableName+"&tableId="+tableId;	
					sb.append("<td class='d-file-more' >");
					//sb.append("<input type=\"button\"  class=\"input_submit\" onclick=\"viewFileList('"+resource.get("public.uploadfile.title")+"')\" value=\""+resource.get("public.uploadfile.morefile")+"\">");					
					sb.append("<a href=\"#\"  onclick=\"viewFileList('"+resource.get("public.uploadfile.title")+"')\">More....</a>");
					sb.append("</td>");
					break;
				}
				
				
				String fileDownloadUrl=request.getContextPath()+"/commons/dmfiledownload.jsp?id="+fileR.getDelmarFile().getId();	
				sb.append("<td width='15%'>");
				sb.append("<a href='#' onclick='javascript:window.open(\""+fileDownloadUrl+"\")'>");
				sb.append(fileR.getDelmarFile().getFilename());
				sb.append("</a>");
				//sb.append("&nbsp;");
				String imgUrl=request.getContextPath()+"/images/filedetail.png";
				sb.append("<a onclick='viewFileDescr("+fileR.getId()+",\""+resource.get("public.uploadfile.title")+"\")' href='#' style='margin-left:3px;'><img src=\""+imgUrl+"\" title=\""+resource.get("public.uploadfile.view")+"\" >");
				sb.append("</a>");
				sb.append("</td>");
			}
			
			if (count==0)
			{
				sb.append("<td>");
				sb.append("<span class='d-file-nofile'>"+resource.get("public.uploadfile.nofile")+"</span>");
				sb.append("</td>");
				sb.append("</tr>");
			}
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</div>");
		}
		
		try {
			pageContext.getOut().print(sb.toString());
		} catch (IOException e) {

			logger.error("out put fileTag error", e);
		}
		return super.doEndTag();
	}
	
	/**
	 * @return the multiple
	 */
	public boolean isMultiple() {
		return multiple;
	}

	/**
	 * @param multiple the multiple to set
	 */
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	/**
	 * @return the tableId
	 */
	public Integer getTableId() {
		return tableId;
	}

	/**
	 * @param tableId the tableId to set
	 */
	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the issort
	 */
	public boolean isIssort() {
		return issort;
	}

	/**
	 * @param issort the issort to set
	 */
	public void setIssort(boolean issort) {
		this.issort = issort;
	}






	public String getCallbackFunc() {
		return callbackFunc;
	}






	public void setCallbackFunc(String callbackFunc) {
		this.callbackFunc = callbackFunc;
	}
	
	
	
	
}
