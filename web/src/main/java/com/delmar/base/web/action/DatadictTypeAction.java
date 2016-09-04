/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.base.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictTypeService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.utils.ResourceMessage;
import com.delmar.web.model.ObjSelect;


/**
 * @author 刘大磊 2015-01-21 10:21:47
 */
public class DatadictTypeAction extends CoreEditPrivAction {
	private DatadictType  datadictType;
	
	@Autowired
	private DatadictTypeService datadictTypeService;
	
	private List<ObjSelect> bePublicList;
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "datadictType";
	}
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "数据字典类型");
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		datadictTypeService.deleteByPrimaryKey(datadictType.getId());
		return "list";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		datadictTypeService.deleteDatadictTypeList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return datadictType.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		
		 bePublicList=new ArrayList<ObjSelect>();
		 
		 bePublicList.add(new ObjSelect(0,ResourceMessage.getMessage("datadictType.column.bepublic"),ResourceMessage.getMessage("datadicttype.column.bepublic")));
		 bePublicList.add(new ObjSelect(1,ResourceMessage.getMessage("datadictType.column.bepublicnot"),ResourceMessage.getMessage("datadicttype.column.bepublicnot")));
		 

		
		 datadictType= datadictTypeService.selectByPrimaryKey(id);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		Map<String,Object> param=new HashMap();
		param.put("searchString",getSearchWhere());
		return datadictTypeService.selectByExample(param);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		 datadictType=new DatadictType();
		 datadictType.setBePublic(0);
		
		 bePublicList=new ArrayList<ObjSelect>();
		 
		 bePublicList.add(new ObjSelect(0,ResourceMessage.getMessage("datadictType.column.bepublic"),ResourceMessage.getMessage("datadicttype.column.bepublic")));
		 bePublicList.add(new ObjSelect(1,ResourceMessage.getMessage("datadictType.column.bepublicnot"),ResourceMessage.getMessage("datadicttype.column.bepublicnot")));
		 
		 		
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {

		datadictTypeService.save(datadictType);
		
		 bePublicList=new ArrayList<ObjSelect>();
		 
		 bePublicList.add(new ObjSelect(0,ResourceMessage.getMessage("datadictType.column.bepublic"),ResourceMessage.getMessage("datadicttype.column.bepublic")));
		 bePublicList.add(new ObjSelect(1,ResourceMessage.getMessage("datadictType.column.bepublicnot"),ResourceMessage.getMessage("datadicttype.column.bepublicnot")));
		 
		 
		 
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public DatadictType getDatadictType() {
		return datadictType;
	}

	/**
	 * @param usergroup the usergroup to set
	 */
	public void setDatadictType(DatadictType datadictType) {
		this.datadictType = datadictType;
	}

	public List<ObjSelect> getBePublicList() {
		return bePublicList;
	}

	public void setBePublicList(List<ObjSelect> bePublicList) {
		this.bePublicList = bePublicList;
	}
	
	
	

}
