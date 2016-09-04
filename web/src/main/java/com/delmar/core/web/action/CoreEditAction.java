/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.action;

import com.delmar.common.vo.SearchColumnVo;
import com.delmar.core.dto.SearchColumnDto;
import com.delmar.core.model.CoreModel;
import com.delmar.core.service.SearchService;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.system.web.WebConst;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘大磊 2015年1月14日 下午3:50:17
 */
public abstract class CoreEditAction  extends CoreAction{
	protected Integer id;

	protected static final String EDIT="edit";

	 public abstract String getModuleName();
	  /*上一条，下一条记录*/
	protected boolean isFirst=false;
	@Autowired
	private SearchService searchService;
	protected boolean isLast=false;
	public abstract String delete();
	public abstract void deleteList(Integer[] ids);
	public abstract Integer getModelId();
	public abstract void editForm();
	public abstract List<CoreModel> search();
	public String getSearchWhere()
	{
		List<SearchColumnDto> searchColumnDtoList=FacesUtils.getSearchColumnList();
		if(searchColumnDtoList==null)
		{
			return null;
		}
		else
		{
			return searchService.buildSqlBySearchColumnList(searchColumnDtoList);
		}
	}
	/**
	 * 新建表单
	 * @return
	 */
	public String create()
	{
		
		return EDIT;
	}
	/**
	 * 修改单据
	 * @return
	 */
	public String edit()
	{
		editForm();
		return EDIT;
	}
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String list()
	{
		List<CoreModel> list=search();
		List<Integer> ids=new ArrayList<Integer>();
		for(CoreModel model:list)
		{
			ids.add(model.getId());
		}
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"IdList", ids);
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", list);
		return LIST;
	}
	public List<Integer> getIdList()
	{
		return (List)FacesUtils.getValueInHashtableOfSession(getModuleName()+"IdList");
	}
	public String deletes()
	{
		String[] ids=ServletActionContext.getRequest().getParameterValues("ids");
		if(ids==null)
		{
			String message=this.getText("error.deletes.empty");
			ServletActionContext.getRequest().getSession().setAttribute("msg", message);
			return list();
		}
		Integer[] idints=new Integer[ids.length];
		
		for(int i=0;i<idints.length;i++)
		{
			idints[i]=Integer.parseInt(ids[i]);
		}
		deleteList(idints);
		
		return list();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@SuppressWarnings("unchecked")
	public boolean getIsFirst() {
		List<Integer>ids=(List)FacesUtils.getValueInHashtableOfSession(getModuleName()+"IdList");
		if(id==null||id==0)
		{
			isFirst=true;
		}
		int index= ids != null ? ids.indexOf(id) : 0;
		if(index==-1)
		{
			return true;
		}
		if(index==0)
		{
			isFirst=true;
		}
		return isFirst;
	}
	@SuppressWarnings("unchecked")
	public boolean getIsLast() {
		List<Integer>ids=(List)FacesUtils.getValueInHashtableOfSession(getModuleName()+"IdList");
		if(id==null||id==0)
		{
			isLast=true;
		}
		int index= ids != null ? ids.indexOf(id) : 0;
		if(index==-1)
		{
			return true;
		}
		if(index+1==ids.size())
		{
			isLast=true;
		}
		return isLast;
	}
	@SuppressWarnings("unchecked")
	public String getPrevionsOne()
	{
		List<Integer>ids=(List)FacesUtils.getValueInHashtableOfSession(getModuleName()+"IdList");
		
		int i = ids != null ? ids.indexOf(getModelId()) : 0;
		
		id=ids.get(i - 1);
		return edit();
	}

	@SuppressWarnings("unchecked")
	public String getNextOne()
	{
		List<Integer>ids=(List)FacesUtils.getValueInHashtableOfSession(getModuleName()+"IdList");
		int i = ids != null ? ids.indexOf(getModelId()) : 0;
		id=ids.get(i+1);
		return edit();
	}
	public String getMessage(String key)
	{
		return "刘大磊";
	}
}
