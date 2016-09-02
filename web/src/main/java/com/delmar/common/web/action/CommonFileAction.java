/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.common.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.delmar.common.model.FileRelation;
import com.delmar.common.service.FileRelationService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.utils.DmLog;

/**
 * @author 刘大磊 2015年8月19日 上午9:35:26
 */
public class CommonFileAction extends CoreEditPrivAction {
	private DmLog log=DmLog.getLogger(CommonFileAction.class);
	private FileRelationService fileRelationService;
	private FileRelation fileRelation;
	/**
	 * @return the fileRelation
	 */
	public FileRelation getFileRelation() {
		return fileRelation;
	}

	/**
	 * @param fileRelation the fileRelation to set
	 */
	public void setFileRelation(FileRelation fileRelation) {
		this.fileRelation = fileRelation;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "commonFile";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		fileRelationService.deleteByPrimaryKey(fileRelation.getId());
		return list();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		fileRelationService.deleteFileRelationList(ids);
		
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {
		return fileRelation.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		fileRelation=fileRelationService.selectByPrimaryKey(id);
		log.debug(fileRelation);
		
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		Map<String,Object> example=new HashMap<String,Object>();
		String tableId=(String)FacesUtils.getSession().getAttribute("tableId");
		String tableName=(String)FacesUtils.getSession().getAttribute("tableName");
		example.put("tableName", tableName);
		example.put("tableId", Integer.parseInt(tableId));
		return fileRelationService.selectByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		edit();
		
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
		fileRelationService.save(fileRelation);
		return "edit";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getPurpose()
	 */
	@Override
	public String getPurpose() {
		return "文件管理";
	}


	/**
	 * @param fileRelationService the fileRelationService to set
	 */
	public void setFileRelationService(FileRelationService fileRelationService) {
		this.fileRelationService = fileRelationService;
	}

}
