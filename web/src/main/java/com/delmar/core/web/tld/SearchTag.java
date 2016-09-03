package com.delmar.core.web.tld;

import com.delmar.core.model.Search;
import com.delmar.core.model.SearchColumn;
import com.delmar.core.service.SearchService;
import com.delmar.core.web.bean.EaContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/8/29.
 */
public class SearchTag extends BodyTagSupport {
    private SearchService searchService= EaContext.ApplicationContext.getBean(SearchService.class);
    @Override
    public int doEndTag() throws JspException {
        HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
        String url=request.getRequestURI();
        Map<String,String> param=new HashMap<>();
        param.put("pageUrl",url);
        List<Search> searchList= searchService.selectByExample(param);
        if(searchList!=null&&searchList.size()>1)
        {
            Search search=searchList.get(0);
            List<SearchColumn> searchColumnList=searchService.getSearchColumnListBySearchId(search.getId());
            if(searchColumnList!=null)
            for(SearchColumn searchColumn:searchColumnList)
            {

            }
        }

        return super.doEndTag();
    }
}
