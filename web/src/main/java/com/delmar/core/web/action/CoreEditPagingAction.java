package com.delmar.core.web.action;

import com.delmar.core.model.CoreModel;
import com.delmar.core.web.controller.displaytag.paging.PaginatedListHelper;
import com.delmar.core.web.util.FacesUtils;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘大磊 on 2016/9/2.
 * 仅仅实现物理分页
 */
public abstract class CoreEditPagingAction extends CoreEditBasePrivAction{
    @Setter
    protected Integer page;
    protected boolean isDelete;
    public abstract PaginatedListHelper searchPaginatedList();
    @SkipValidation
    public String list()
    {
        if(!(!PrivilegeOperator.isView()))
        {
            return NOPRIVILEGE;
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
        PaginatedListHelper paginatedListHelper=searchPaginatedList();
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
