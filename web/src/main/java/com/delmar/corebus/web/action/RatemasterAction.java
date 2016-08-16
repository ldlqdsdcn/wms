/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.corebus.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.DelmarConst;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.action.PrivilegeOperator;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.rate.model.Ratemaster;
import com.delmar.rate.service.RatemasterService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ResourceMessage;


/**
 * @author 刘大磊 2015-02-04 13:49:13
 */
public class RatemasterAction extends CoreEditPrivAction {
	private Ratemaster  ratemaster;
	
	@Autowired
	private RatemasterService ratemasterService;
	
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "运价记录维护");
	}
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "ratemaster";
	}
	
	 /**
	 * <p>Title: list</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.delmar.core.web.action.CoreEditPrivAction#list()
	 */
	@Override
	public String list() {
		
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String mode=request.getParameter("mode");  //判断是空运,海运,陆运
		FacesUtils.setValueInHashtableOfSession("mode", mode);
		
		
		super.list();
		
		return LIST+"_"+mode;
		
		
	}
	
	

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		ratemasterService.deleteRatemaster(ratemaster.getId());
		return "list";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		ratemasterService.deleteRatemasterList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return ratemaster.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		 ratemaster= ratemasterService.selectByPrimaryKey(id);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		return (new ArrayList<Ratemaster>());
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		ratemaster=new Ratemaster();
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {

		ratemasterService.save(ratemaster);
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public Ratemaster getRatemaster() {
		return ratemaster;
	}

	/**
	 * @param usergroup the usergroup to set
	 */
	public void setRatemaster(Ratemaster ratemaster) {
		this.ratemaster = ratemaster;
	}
	
	
	public List searchLand()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		

		
		
		String mode=request.getParameter("mode");
		FacesUtils.setValueInHashtableOfSession("mode", mode);
		
		
		
		String airPortFrom=request.getParameter("airPortFrom");
		FacesUtils.setValueInHashtableOfSession("airPortFrom", airPortFrom);
		
		String airPortFrom2=request.getParameter("airPortFrom2");
		FacesUtils.setValueInHashtableOfSession("airPortFrom2", airPortFrom2);
		
		String airPortFrom3=request.getParameter("airPortFrom3");
		FacesUtils.setValueInHashtableOfSession("airPortFrom3", airPortFrom3);
		
		String airPortFrom4=request.getParameter("airPortFrom4");
		FacesUtils.setValueInHashtableOfSession("airPortFrom4", airPortFrom4);
		
		
		String name=request.getParameter("name");
		FacesUtils.setValueInHashtableOfSession("name", name);
		String address=request.getParameter("address");
		FacesUtils.setValueInHashtableOfSession("address", address);
		
		String statusId=request.getParameter("statusId");
		FacesUtils.setValueInHashtableOfSession("statusId", statusId);
		
		return null;
		
	}

}
