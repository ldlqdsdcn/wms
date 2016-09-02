/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.base.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.enumdef.DataDictPublicType;
import com.delmar.base.model.Datadict;
import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.base.service.DatadictTrlService;
import com.delmar.base.service.DatadictTypeService;
import com.delmar.core.model.Language;
import com.delmar.core.service.LanguageService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.crm.model.CustomerExtra;
import com.delmar.sys.model.Client;
import com.delmar.sys.model.Org;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ResourceMessage;
import com.delmar.utils.StringUtil;


/**
 * @author 刘大磊 2015-01-21 10:21:47
 */
public class DatadictAction extends CoreEditPrivAction {
	private Datadict  datadict;
	private List<DatadictType> datadictTypeList;
	private List<DatadictTrl> datadictTrlList;
	@Autowired
	private LanguageService languageService;
	@Autowired
	private DatadictTrlService  datadictTrlService;

	@Autowired
	private DatadictService datadictService;
	@Autowired
	private DatadictTypeService datadictTypeService;
	
	protected PrivilegesDataFilter up;	
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "数据字典维护");
	}
	
	private void init()
	{
		
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		Map param=new HashMap();
		param.put("orderByClause", " value  ");
		datadictTypeList=datadictTypeService.selectByExample(param);
		
		if(datadict!=null&&datadict.getId()!=null)
		{
			param=new HashMap();
			param.put("datadictId", datadict.getId());
			datadictTrlList=	datadictTrlService.selectByExample(param);
			
			List<Language> list=languageService.selectByExample(null);
			List<Language> noList=new ArrayList<Language>();
			if(datadictTrlList==null||datadictTrlList.size()==0)
			{
				datadictTrlList=new ArrayList<DatadictTrl>();
				noList=list;
			}
			else
			{
				
				for(Language lang:list)
				{
					boolean has=false;
					for(DatadictTrl trl:datadictTrlList)
					{
						if(trl.getLanguage().equals(lang.getCode()))
						{
							has=true;
							break;
						}
					}
					if(!has)
					{
						noList.add(lang);
					}
				}
			}
			for(Language lang:noList)
			{
				DatadictTrl trl=new DatadictTrl();
				trl.setLanguage(lang.getCode());
				trl.setDatadictId(datadict.getId());
				datadictTrlList.add(trl);
			}
			
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "datadict";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		datadictService.deleteByPrimaryKey(datadict.getId());
		
		return list();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		datadictService.deleteDatadictList(ids);

	}
	public String copy()
	{
		this.id=null;
		this.datadict.setId(null);
		if(this.datadictTrlList!=null)
		{
			for(DatadictTrl trl:datadictTrlList)
			{
				trl.setId(null);
			}
		}
		init();
		addActionMessage(getText("message.copy.success"));
		return "edit";
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return datadict.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		
		 datadict= datadictService.selectByPrimaryKey(id);
		 init();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		Map<String,Object> param=new HashMap<String,Object>();
		//param.put("bePublic", 0);
		datadictTypeList= datadictTypeService.selectByExample(param);		
		
		HttpServletRequest request=ServletActionContext.getRequest();	
	
		
		String datadictTypeId=request.getParameter("datadictTypeId");
		if (datadictTypeId==null)
		{
			if (datadict!=null)
				datadictTypeId=datadict.getDatadictTypeId().toString();
		}
			
		FacesUtils.setValueInHashtableOfSession("datadictTypeId", datadictTypeId);
		
		DatadictType currentDatadictType=null;
		
		if (StringUtil.isEmpty(datadictTypeId))
		{
			if (datadictTypeList.size()>0)
			{
   			   currentDatadictType=((DatadictType)datadictTypeList.get(0));
			   datadictTypeId=((DatadictType)datadictTypeList.get(0)).getId().toString();
			   FacesUtils.setValueInHashtableOfSession("datadictTypeId", datadictTypeId);					   
			}
			//return new ArrayList<Datadict>();
		} else
		{
			currentDatadictType=datadictTypeService.selectByPrimaryKey(new Integer(datadictTypeId));
		}
		
		
		
		
		Map<String,Object> searchparam=new HashMap<String,Object>();
		searchparam.put("datadictTypeId", new Integer(datadictTypeId));
		if (currentDatadictType.getBePublic().intValue()==DataDictPublicType.PRIVATE.getType())
		    searchparam.put("clientId", up.getUserClientId());
		
		searchparam.put("orderByClause", " indexOrder asc ");
		
		return datadictService.selectByExample(searchparam);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {

		HttpServletRequest request=ServletActionContext.getRequest();	
		String datadictTypeId=request.getParameter("datadictTypeId");
		
		if (datadict!=null)
		{
			if (StringUtil.isEmpty(datadictTypeId)) 
			{
				datadictTypeId=datadict.getDatadictTypeId().toString();
			}
		}
		
		datadict=new Datadict();
		//Charles 此时是为了新建的时候填充上相应的类型
		if (StringUtil.isNotEmpty(datadictTypeId)) 
		    datadict.setDatadictTypeId(new Integer(datadictTypeId));
		
		
		
		datadictTrlList=new ArrayList<DatadictTrl>();
		id=null;
		datadict.setIndexOrder(0);
		init();
	}
	
	public void indexOrder()
	{
		HttpServletRequest request=ServletActionContext.getRequest();	
	    String datadictId=request.getParameter("id"); 
	    String mode=request.getParameter("mode");
	    if (mode==null)
	    	mode="";
	    //首先检查此人员是否具有维护此ID的权限
	    HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");   	    
	    try
	    {

            	    	
	    Datadict currentDataDict=datadictService.selectByPrimaryKey(new Integer(datadictId));
	    if (currentDataDict==null)
	    {
	        response.getWriter().write("Error:"+getText("private.datadictaction.idobj.null"));
	        return;
	    }
	    
	    DatadictType currentDatadictType=datadictTypeService.selectByPrimaryKey(currentDataDict.getDatadictTypeId());	    
	    
	    //得到这个对象前面的第一个对象
	    Map<String,Object> param=new HashMap<String,Object>();		
		param.put("datadictTypeId", currentDataDict.getDatadictTypeId());
		if (mode.equals("up"))
		{
			param.put("accessString", " indexOrder<"+currentDataDict.getIndexOrder());
			param.put("orderByClause","indexorder desc");
		}
		else
		{
			param.put("accessString", " indexOrder>"+currentDataDict.getIndexOrder());
			param.put("orderByClause","indexorder asc");
		}
		
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		if (currentDatadictType.getBePublic().intValue()==DataDictPublicType.PRIVATE.getType())
			param.put("clientId", up.getUserClientId());
		
		
		List<Datadict> datadictList=	datadictService.selectByExample(param);
		Datadict nearDatadict=null;
		if (datadictList.size()>0)
		{
			nearDatadict=datadictList.get(0);
			//Integer nearIndexOrder=nearDatadict.getIndexOrder();
			//nearDatadict.setIndexOrder(currentDataDict.getIndexOrder());
			//currentDataDict.setIndexOrder(nearIndexOrder);
			
			datadictService.saveIndexOrder(nearDatadict.getIndexOrder(), currentDataDict.getId(), currentDataDict.getIndexOrder(), nearDatadict.getId());
			
			response.getWriter().write("Success");
			
		}
		
		



	      		
	      	    
	    } catch (Exception ex)
	    {
	    	
	    }
	    
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {

		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		Date now=new Date();
		if(datadict.isnew())
		{
			datadict.setCreated(now);
			datadict.setUser(up.getUser());
			datadict.setUserId(up.getUserId());
			datadict.setUserName(up.getUser().getName());
			datadict.setCreatedBy(up.getLoginUserId());
			datadict.setCreatedByName(up.getLoginUser().getName());			
			datadict.setCreatedByUser(up.getLoginUser());
			datadict.setOrgId(up.getLoginOrgId());
			datadict.setClientId(up.getLoginClientId());
		}
		

		datadict.setUpdated(now);
		datadict.setUpdatedBy(up.getLoginUserId());
		datadict.setUpdatedByUser(up.getLoginUser());
		datadict.setUpdatedByName(up.getLoginUser().getName());
		
		if (datadict.getIsActive()==null)
			datadict.setIsActive(1);		
		
		datadictService.saveDatadict(datadict,datadictTrlList);
		id=datadict.getId();
		editForm();
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public Datadict getDatadict() {
		return datadict;
	}

	/**
	 * @param usergroup the usergroup to set
	 */
	public void setDatadict(Datadict datadict) {
		this.datadict = datadict;
	}

	/**
	 * @return the datadictTypeList
	 */
	public List<DatadictType> getDatadictTypeList() {
		return datadictTypeList;
	}

	/**
	 * @param datadictTypeList the datadictTypeList to set
	 */
	public void setDatadictTypeList(List<DatadictType> datadictTypeList) {
		this.datadictTypeList = datadictTypeList;
	}

	/**
	 * @return the datadictTrlList
	 */
	public List<DatadictTrl> getDatadictTrlList() {
		return datadictTrlList;
	}

	/**
	 * @param datadictTrlList the datadictTrlList to set
	 */
	public void setDatadictTrlList(List<DatadictTrl> datadictTrlList) {
		this.datadictTrlList = datadictTrlList;
	}

}
