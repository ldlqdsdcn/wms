/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.common.web.bean;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.delmar.common.model.DelmarFile;
import com.delmar.common.service.DelmarFileService;
import com.delmar.core.web.bean.EaContext;

/**
 * @author 刘大磊 2015年8月19日 上午9:02:47
 */
public class FileDownload {
	private static DelmarFileService delmarFileService=EaContext.getBean("delmarFileService", DelmarFileService.class);
	
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
