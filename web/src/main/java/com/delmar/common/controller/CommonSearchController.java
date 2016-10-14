package com.delmar.common.controller;

import com.delmar.common.vo.SearchColumnVo;
import com.delmar.common.vo.SearchFormRow;
import com.delmar.core.api.Result;
import com.delmar.core.api.StatusCode;
import com.delmar.core.def.RelOperDef;
import com.delmar.core.def.SearchPageShowType;
import com.delmar.core.def.SearchShowTypeDef;
import com.delmar.core.model.CommonSearchResult;
import com.delmar.core.model.Search;
import com.delmar.core.model.SearchColumn;
import com.delmar.core.service.SearchService;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.system.web.WebConst;
import com.delmar.utils.StringUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author dalei.liu 2016/8/29 20:20.
 *         公共查询类
 */
@Controller
public class CommonSearchController {
    private static final Logger log=Logger.getLogger(CommonSearchController.class);
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/commons/searchPage", method = RequestMethod.GET)
    public ModelAndView init(String action_value,HttpSession session) {
        RelOperDef[] operDefs = RelOperDef.values();
        Gson gson = new Gson();
        ModelAndView modelAndView = new ModelAndView("/commons/common_search.jsp");
        Search search = searchService.getSearchByPageUrl(action_value);
        if(search==null)
        {
            modelAndView.setViewName("/commons/common_search_empty.jsp");
            return modelAndView;
        }
        else if(search.getSearchShowType()==SearchPageShowType.NORMAL_SEARCH_PAGE.getKey())
        {
            modelAndView.setViewName("/commons/common_search_normal.jsp");
        }
        List<SearchColumn> searchColumns = searchService.getSearchColumnListBySearchId(search.getId());
        List<SearchColumnVo> searchColumnVos = new ArrayList<>();
        for (SearchColumn searchColumn : searchColumns) {
            SearchColumnVo searchColumnVo = new SearchColumnVo();
            searchColumnVo.setColumnId(searchColumn.getId());
            searchColumnVo.setColumnName(searchColumn.getColumnName());
            searchColumnVo.setColumnLabel(searchColumn.getColumnLabel());
            searchColumnVo.setDataType(searchColumn.getDataType());
            searchColumnVo.setShowType(searchColumn.getShowType());
            searchColumnVo.setNewline(searchColumn.getNewline());
           List<String> relOpearArray=new ArrayList<>();
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
        if(search.getSearchShowType()==SearchPageShowType.NORMAL_SEARCH_PAGE.getKey())
        {
            /**
             * 把已经赋值的值，赋给查询界面
             */
            if(addedSearchColumnVoList!=null)
            {
                for(SearchColumnVo addedVo:addedSearchColumnVoList)
                {
                    for(SearchColumnVo searchColumnVo:searchColumnVos)
                    {
                        if(addedVo.getColumnId().equals(searchColumnVo.getColumnId()))
                        {
                            searchColumnVo.setValue(addedVo.getValue());
                        }
                    }
                }
            }
            List<SearchFormRow> searchFormRows=new ArrayList<>();
            SearchFormRow searchFormRow=null;
            for(SearchColumnVo searchColumnVo:searchColumnVos)
            {
                if(searchFormRow==null)
                {
                    searchFormRow=new SearchFormRow();
                }
                else if("Y".equals(searchColumnVo.getNewline()))
                {
                    searchFormRows.add(searchFormRow);
                    searchFormRow=new SearchFormRow();
                }
                searchFormRow.addSearchColumnVo(searchColumnVo);
            }
            searchFormRows.add(searchFormRow);
            modelAndView.addObject("searchFormRows", searchFormRows);
        }
        else
        {

            if(addedSearchColumnVoList==null)
            {
                modelAndView.addObject("addedSearchColumnVoListJson","[]");
            }
            else
            {
                modelAndView.addObject("addedSearchColumnVoListJson",gson.toJson(addedSearchColumnVoList));
            }
        }
        modelAndView.addObject("searchColumnJson", gson.toJson(searchColumnVos));
        return modelAndView;
    }

    @RequestMapping(value = "/commons/search", method = RequestMethod.POST)
    @ResponseBody
    public Result<Void> saveSearchConditions(@RequestBody List<SearchColumnVo> searchColumnVoList, HttpSession session)
    {
        FacesUtils.setValueInHashtableOfSession(WebConst.SESSION_SEARCH_CONDITIONS,searchColumnVoList,session);
        return Result.success(null);
    }
    @RequestMapping(value = "/commons/search2", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result<Void> saveSearchConditions2(@RequestBody String searchColumnVoListStr, HttpSession session)
    {
        String str=null;
        try {
             str= URLDecoder.decode(searchColumnVoListStr,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try
        {
            log.info("str="+str);
            Gson gson=new Gson();
            SearchColumnVo[]  searchColumnVoList=gson.fromJson(str,
                    new TypeToken<SearchColumnVo[]>() {
                    }.getType());
            List<SearchColumnVo> voList=new ArrayList<>();
            for(SearchColumnVo searchColumnVo:searchColumnVoList)
            {
                if(StringUtil.isNotEmpty(searchColumnVo.getValue()))
                voList.add(searchColumnVo);
            }
            FacesUtils.setValueInHashtableOfSession(WebConst.SESSION_SEARCH_CONDITIONS,voList,session);
            return Result.success(null);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return Result.fail(StatusCode.WEB_EXCEPTION.getCode(),e.getMessage());
        }
    }

}
