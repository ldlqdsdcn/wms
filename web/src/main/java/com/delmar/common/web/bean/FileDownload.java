/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.common.web.bean;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.delmar.common.model.DelmarFile;
import com.delmar.common.service.DelmarFileService;
import com.delmar.core.web.bean.SystemContextHelper;

/**
 * @author 刘大磊 2015年8月19日 上午9:02:47
 */
public class FileDownload {
	private static DelmarFileService delmarFileService=SystemContextHelper.getBean("delmarFileService", DelmarFileService.class);
	
	public static DelmarFile getFilePath(HttpServletRequest request)
	{
		String id=request.getParameter("id");
		DelmarFile delmarFile=delmarFileService.selectByPrimaryKey(Integer.parseInt(id));
		
		if(StringUtils.isEmpty(delmarFile.getFileType()))
		{
			delmarFile.setFileType("application/x-download");
		}
		else if("NULL".equals(delmarFile.getFileType()))
		{
			delmarFile.setFileType("application/x-download");
		}
		return delmarFile;
	}
	

	
}
