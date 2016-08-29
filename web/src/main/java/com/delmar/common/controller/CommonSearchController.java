package com.delmar.common.controller;

import com.delmar.common.vo.SearchColumnVo;
import com.delmar.core.api.ApiResult;
import com.delmar.core.def.RelOperDef;
import com.delmar.core.def.SearchShowTypeDef;
import com.delmar.core.def.ShowType;
import com.delmar.core.model.CommonSearchResult;
import com.delmar.core.model.Search;
import com.delmar.core.model.SearchColumn;
import com.delmar.core.service.SearchService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author dalei.liu 2016/8/29 20:20.
 *         公共查询类
 */
@Controller
public class CommonSearchController {
    private SearchService searchService;

    @RequestMapping(value = "/commons/searchPage", method = RequestMethod.GET)
    public ModelAndView init(String action_value) {
        RelOperDef[] operDefs = RelOperDef.values();
        Gson gson = new Gson();
        ModelAndView modelAndView = new ModelAndView("/commons/common_search.jsp");
        Map<String, Object> param = new HashMap<>();
        param.put("pageUrl", action_value);
        Search search = searchService.getSearchByPageUrl(action_value);
        List<SearchColumn> searchColumns = searchService.getSearchColumnListBySearchId(search.getId());
        List<CommonSearchResult> relOpearList = new ArrayList<>();
        List<SearchColumnVo> searchColumnVos = new ArrayList<>();
        for (SearchColumn searchColumn : searchColumns) {
            SearchColumnVo searchColumnVo = new SearchColumnVo();
            searchColumnVo.setColumnId(searchColumn.getId());
            searchColumnVo.setColumnLabel(searchColumn.getColumnLabel());
            searchColumnVo.setDataType(searchColumn.getDataType());
            searchColumnVo.setInputType(searchColumn.getShowType());
            for (String oper : searchColumn.getRelOperList()) {
                for (RelOperDef operDef : operDefs) {
                    if (oper.equals(String.valueOf(operDef.getKey()))) {
                        CommonSearchResult cr = new CommonSearchResult();
                        cr.setKey(String.valueOf(operDef.getKey()));
                        cr.setLabel(operDef.getDesc());
                        relOpearList.add(cr);
                    }
                }
            }
            searchColumnVo.setRelOpearList(relOpearList);
            if (SearchShowTypeDef.SELECT.getKey() == searchColumn.getShowType()) {
                List<CommonSearchResult> commonSearchResultList = searchService.getCommonSearchListByColumnId(searchColumn.getId());
                searchColumnVo.setCommonSearchResultList(commonSearchResultList);
            }
            searchColumnVos.add(searchColumnVo);
        }
        modelAndView.addObject("searchColumnJson", gson.toJson(searchColumnVos));
        return modelAndView;
    }

    @RequestMapping(value = "/commons/searchOperator", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<CommonSearchResult>> getSelectList(Integer columnId) {
        List<CommonSearchResult> commonSearchResultList = searchService.getCommonSearchListByColumnId(columnId);
        return ApiResult.success(commonSearchResultList);
    }
}
