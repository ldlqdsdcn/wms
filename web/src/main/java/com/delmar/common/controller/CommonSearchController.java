package com.delmar.common.controller;

import com.delmar.common.vo.SearchColumnVo;
import com.delmar.core.api.ApiResult;
import com.delmar.core.def.RelOperDef;
import com.delmar.core.def.SearchShowTypeDef;
import com.delmar.core.model.CommonSearchResult;
import com.delmar.core.model.Search;
import com.delmar.core.model.SearchColumn;
import com.delmar.core.service.SearchService;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.system.web.WebConst;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author dalei.liu 2016/8/29 20:20.
 *         公共查询类
 */
@Controller
public class CommonSearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/commons/searchPage", method = RequestMethod.GET)
    public ModelAndView init(String action_value,HttpSession session) {
        RelOperDef[] operDefs = RelOperDef.values();
        Gson gson = new Gson();
        ModelAndView modelAndView = new ModelAndView("/commons/common_search.jsp");
        Map<String, Object> param = new HashMap<String,Object>();
        param.put("pageUrl", action_value);
        Search search = searchService.getSearchByPageUrl(action_value);
        if(search==null)
        {
            modelAndView.setViewName("/commons/common_search_empty.jsp");
            return modelAndView;
        }
        List<SearchColumn> searchColumns = searchService.getSearchColumnListBySearchId(search.getId());
        List<SearchColumnVo> searchColumnVos = new ArrayList<>();
        for (SearchColumn searchColumn : searchColumns) {
            SearchColumnVo searchColumnVo = new SearchColumnVo();
            searchColumnVo.setColumnId(searchColumn.getId());
            searchColumnVo.setColumnLabel(searchColumn.getColumnLabel());
            searchColumnVo.setDataType(searchColumn.getDataType());
            searchColumnVo.setInputType(searchColumn.getShowType());
           List<String> relOpearArray=new ArrayList();
            for (String oper : searchColumn.getRelOperList()) {
                for (RelOperDef operDef : operDefs) {
                    if (oper.equals(String.valueOf(operDef.getKey()))) {
                        relOpearArray.add(operDef.getDesc());
                    }
                }
            }
            searchColumnVo.setOpearType(relOpearArray.get(0));
            searchColumnVo.setRelOpearList(relOpearArray);
            if (SearchShowTypeDef.SELECT.getKey() == searchColumn.getShowType()) {
                List<CommonSearchResult> commonSearchResultList = searchService.getCommonSearchListByColumnId(searchColumn.getId());
                searchColumnVo.setCommonSearchResultList(commonSearchResultList);
            }
            searchColumnVos.add(searchColumnVo);
        }
        List<SearchColumnVo> addedSearchColumnVoList=( List<SearchColumnVo>)FacesUtils.getValueInHashtableOfSession(WebConst.SESSION_SEARCH_CONDITIONS,session);
        modelAndView.addObject("searchColumnJson", gson.toJson(searchColumnVos));
        if(addedSearchColumnVoList==null)
        {
            modelAndView.addObject("addedSearchColumnVoListJson","[]");
        }
        else
        {
            modelAndView.addObject("addedSearchColumnVoListJson",gson.toJson(addedSearchColumnVoList));
        }
        return modelAndView;
    }

    @RequestMapping(value = "/commons/search", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Void> saveSearchConditions(@RequestBody List<SearchColumnVo> searchColumnVoList, HttpSession session)
    {
        FacesUtils.setValueInHashtableOfSession(WebConst.SESSION_SEARCH_CONDITIONS,searchColumnVoList,session);
        return ApiResult.success(null);
    }

}
