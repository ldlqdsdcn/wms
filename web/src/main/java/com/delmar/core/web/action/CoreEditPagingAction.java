package com.delmar.core.web.action;

import com.delmar.core.model.CoreModel;
import com.delmar.core.web.displaytag.paging.PaginatedListHelper;
import com.delmar.core.web.util.FacesUtils;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 刘大磊 on 2016/9/2.
 * 仅仅实现物理分页
 */
public abstract class CoreEditPagingAction extends CoreEditBasePrivAction{
    @Setter
    protected Integer page;
    protected boolean isDelete;
    public abstract PaginatedListHelper searchPaginatedList(Map<String,Object> param);
    @SkipValidation
    public String list()
    {
        if(!PrivilegeOperator.isView())
        {
            return NO_PRIVILEGE;
        }

        HttpServletRequest request=ServletActionContext.getRequest();

        if(page!=null)
        {
            isDelete=false;
        } else
        if(request.getServletPath().indexOf("delete")!=-1||request.getParameter("method:delete")!=null||request.getParameter("method:deletes")!=null)
        {
            isDelete=true;
        }
        if(page==null)
        {
            page=(Integer)FacesUtils.getValueInHashtableOfSession("page");
        }


        if(isDelete)
        {
            page=(Integer)FacesUtils.getValueInHashtableOfSession("page");
        }
        else
        {
            if(page==null||page==0)
            {
                page=1;
            }
        }
        FacesUtils.setValueInHashtableOfSession("page",page);
        Map<String,Object> param=new HashMap<>();
        param.put("searchString", getSearchWhere());
        param.put("pageNo", page);
        param.put("pageSize",20);
        PaginatedListHelper paginatedListHelper=searchPaginatedList( param);

        List<CoreModel> list=paginatedListHelper.getList();
        List<Integer> ids=new ArrayList<Integer>();
        for(CoreModel model:list)
        {
            ids.add(model.getId());
        }
        FacesUtils.setValueInHashtableOfSession(getModuleName() + "IdList", ids);
        FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", paginatedListHelper);
        return LIST;
    }
}
