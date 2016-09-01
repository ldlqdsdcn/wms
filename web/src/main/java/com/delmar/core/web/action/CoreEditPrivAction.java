/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.web.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import com.delmar.common.vo.SearchColumnVo;
import com.delmar.core.dto.SearchColumnDto;
import com.delmar.core.service.SearchService;
import com.delmar.core.web.controller.displaytag.paging.PaginatedListHelper;
import com.delmar.core.web.def.PagingType;
import com.delmar.sys.model.User;
import com.delmar.system.web.WebConst;
import org.apache.struts2.ServletActionContext;

import com.delmar.core.model.CoreModel;
import com.delmar.core.web.util.FacesUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 刘大磊 2015年1月15日 下午4:16:51
 */
public abstract class CoreEditPrivAction extends CoreAction {
	//默认分页方式为内存分页
	protected PagingType pagingType=PagingType.MEMORY;
    protected Integer pageNo;
	protected Integer id;
	@Autowired
	private SearchService searchService;
	protected static final String EDIT="edit";


	protected static final String NOID="noid";
	 public abstract String getModuleName();
	  /*上一条，下一条记录*/
	protected boolean isFirst=false;

	protected boolean isLast=false;
	public abstract String delete();
	public abstract void deleteList(Integer[] ids);
	public abstract Integer getModelId();
	public abstract void editForm();
	public abstract List<CoreModel> search();
	public abstract void createForm();
	public abstract String saveForm();	
	//// TODO: 2016/8/25
	public  String getPurpose()
	{
		return this.getModuleName();
	}
	
	
	public String getSearchWhere()
	{
		List<SearchColumnVo> list=(List)FacesUtils.getValueInHashtableOfSession(WebConst.SESSION_SEARCH_CONDITIONS);

		if(list==null||list.size()==0)
		{
			return null;
		}
		else
		{

			List<SearchColumnDto> searchColumnDtoList=new ArrayList<>();
			for(SearchColumnVo vo:list)
			{
				SearchColumnDto searchColumnDto=new SearchColumnDto();
				searchColumnDto.setOpearType(vo.getOpearType());
				searchColumnDto.setColumnId(vo.getColumnId());
				searchColumnDto.setValue(vo.getValue());
				searchColumnDtoList.add(searchColumnDto);
			}
			return searchService.buildSqlBySearchColumnList(searchColumnDtoList);
		}
	}
	
	/**
	 * 新建表单
	 * @return
	 */
	@SkipValidation
	public String create() 
	{
		if(!PrivilegeOperator.isCreate())
		{
			return NOPRIVILEGE;
		}
		createForm();
		return EDIT;
	}
	/**
	 * 修改单据、查看单据
	 * @return
	 */
	@SkipValidation
	public String edit()
	{
		if(!(PrivilegeOperator.isUpdate()||PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}
		editForm();
		return EDIT;
	}
	
	/**
	 * 保存单据
	 * @return
	 */
	public String save()
	{
		if(!((PrivilegeOperator.isCreate()&&getModelId()==null)||PrivilegeOperator.isUpdate()))
		{
			return NOPRIVILEGE;
		}
		String msgKey="success.update";
		if(this.getModelId()==null)
		{
			msgKey="success.create";
		}
		addActionMessage(getText(msgKey));		
		String returnUrl=saveForm();

		return returnUrl;
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String list()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}
		if(pagingType==PagingType.DATABASE)
        {
            HttpServletRequest request=ServletActionContext.getRequest();
            Enumeration<String> params=request.getParameterNames();
            while (params.hasMoreElements())
            {
                String key= params.nextElement();
                System.out.println("-------------------->"+key+"="+request.getParameter(key));
            }
            FacesUtils.setValueInHashtableOfSession("pageNumber",1);
            FacesUtils.setValueInHashtableOfSession("pageSize",20);

        }
        else
        {
            List<CoreModel> list=search();
            List<Integer> ids=new ArrayList<Integer>();
            for(CoreModel model:list)
            {
                ids.add(model.getId());
            }
            FacesUtils.setValueInHashtableOfSession(getModuleName()+"IdList", ids);
            FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", list);
        }


		return LIST;
	}
	public List<Integer> getIdList()
	{
		return (List)FacesUtils.getValueInHashtableOfSession(getModuleName()+"IdList");
	}
	
	public boolean validateDelete(Integer id)
	{
		return true;
	}
	@SkipValidation
	public String deletes()
	{
		if(!(PrivilegeOperator.isDelete()))
		{
			return NOPRIVILEGE;
		}
		String[] ids=ServletActionContext.getRequest().getParameterValues("ids");
		if(ids==null)
		{
			String message=this.getText("error.deletes.empty");
			ServletActionContext.getRequest().getSession().setAttribute("msg", message);
			
		}
		Integer[] idints=new Integer[ids.length];
		
		for(int i=0;i<idints.length;i++)
		{
			idints[i]=Integer.parseInt(ids[i]);
			if (validateDelete(idints[i])==false)
				return "error";
		}
		deleteList(idints);
		
		ServletActionContext.getRequest().getSession().setAttribute("msg", getText("success.deletes"));
		return list();
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id) 
	{
		this.id = id;
	}
	@SuppressWarnings("unchecked")
	public boolean getIsFirst() 
	{
		List<Integer>ids=(List)FacesUtils.getValueInHashtableOfSession(getModuleName()+"IdList");
		if(id==null||id==0)
		{
			isFirst=true;
		}
		int index=ids.indexOf(id);
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
	public boolean getIsLast() 
	{
		List<Integer>ids=(List)FacesUtils.getValueInHashtableOfSession(getModuleName()+"IdList");
		if(id==null||id==0)
		{
			isLast=true;
		}
		int index=ids.indexOf(id);
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
		
		int i = ids.indexOf(getModelId());
		
		id=ids.get(i - 1);
		return edit();
	}

	@SuppressWarnings("unchecked")
	public String getNextOne()
	{
		List<Integer>ids=(List)FacesUtils.getValueInHashtableOfSession(getModuleName()+"IdList");
		int i = ids.indexOf(getModelId());
		id=ids.get(i+1);
		return edit();
	}
	public User getUserInSession()
	{
		return	(User)FacesUtils.getSession().getAttribute(WebConst.SESSION_ACTUALUSER);
	}


}
