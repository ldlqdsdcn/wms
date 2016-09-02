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

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.model.Port;
import com.delmar.base.model.PortMode;
import com.delmar.base.model.PortTrl;
import com.delmar.base.service.DatadictService;
import com.delmar.base.service.PortModeService;
import com.delmar.base.service.PortService;
import com.delmar.base.service.PortTrlService;
import com.delmar.core.model.Language;
import com.delmar.core.service.LanguageService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.bean.UserResource;
import com.delmar.utils.ResourceMessage;


/**
 * @author 刘大磊 2015-02-06 16:15:39
 */
public class PortAction extends CoreEditPrivAction {
	private Port  port;
	private List<Integer> portModes;
	private List<DatadictTrl> modes;
	private List<PortTrl> portTrlList;
	@Autowired
	private PortModeService portModeService;
	@Autowired
	private PortService portService;
	@Autowired
	private PortTrlService portTrlService;
	@Autowired
	private DatadictService datadictService;
	@Autowired
	private LanguageService languageService;
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "港口代码维护");
	}

	
	
	private void init()
	{
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		
	//	datadictList=datadictService.getDatadictTrlByValue(DatadictType.ACCESS_LEVEL, ur.getLocale().toString());
			modes=datadictService.getDatadictTrlByValue(DatadictType.MODE, ur.getLocale().toString(),null);
			if(port.getId()!=null)
			{
				portModes=new ArrayList();
				List<PortMode>portModeList=portModeService.getPortModeListByPortId(port.getId());
				if(portModeList!=null)
				{
					for(PortMode pm:portModeList)
					{
						portModes.add(pm.getDatadictId());
					}
				}
				
			}

			
			if(port.getId()!=null)
			{
				Map  param=new HashMap();
				param.put("portId", port.getId());
				portTrlList=	portTrlService.selectByExample(param);
				
				List<Language> list=languageService.selectByExample(null);
				List<Language> noList=new ArrayList<Language>();
				if(portTrlList==null||portTrlList.size()==0)
				{
					portTrlList=new ArrayList<PortTrl>();
					noList=list;
				}
				else
				{
					
					for(Language lang:list)
					{
						boolean has=false;
						for(PortTrl trl:portTrlList)
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
					PortTrl trl=new PortTrl();
					trl.setLanguage(lang.getCode());
					trl.setPortId(port.getId());
					portTrlList.add(trl);
				}
				
			}
			
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "port";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		portService.deleteByPrimaryKey(port.getId());
		return "list";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		portService.deletePortList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return port.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		 port= portService.selectByPrimaryKey(id);
		 init();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		return portService.selectByExample(null);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		port=new Port();
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
		portService.savePort(port, portTrlList, portModes);
	//	portService.save(port);
		this.id=port.getId();
		edit();
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public Port getPort() {
		return port;
	}

	/**
	 * @param usergroup the usergroup to set
	 */
	public void setPort(Port port) {
		this.port = port;
	}

	/**
	 * @return the modes
	 */
	public List<DatadictTrl> getModes() {
		return modes;
	}

	/**
	 * @param modes the modes to set
	 */
	public void setModes(List<DatadictTrl> modes) {
		this.modes = modes;
	}

	/**
	 * @return the portModes
	 */
	public List<Integer> getPortModes() {
		return portModes;
	}

	/**
	 * @param portModes the portModes to set
	 */
	public void setPortModes(List<Integer> portModes) {
		this.portModes = portModes;
	}

	/**
	 * @return the portTrlList
	 */
	public List<PortTrl> getPortTrlList() {
		return portTrlList;
	}

	/**
	 * @param portTrlList the portTrlList to set
	 */
	public void setPortTrlList(List<PortTrl> portTrlList) {
		this.portTrlList = portTrlList;
	}

}
