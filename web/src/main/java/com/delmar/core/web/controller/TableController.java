package com.delmar.core.web.controller;

import com.delmar.core.api.ApiResult;
import com.delmar.core.api.StatusCode;
import com.delmar.core.def.ColumnDataType;
import com.delmar.core.dto.TableMetaDataDto;
import com.delmar.core.excep.ValidateException;
import com.delmar.core.service.TableService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by admin on 2016/8/18.
 */
@Controller
public class TableController {
    @Autowired
    private TableService tableService;

    @RequestMapping(value = "/core/getTableInfo", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<TableMetaDataDto> getTableInfo(String tableName) {
        return tableService.getTableDescription(tableName);
    }

    @RequestMapping(value = "/core/initTableInfo", method = RequestMethod.GET)
    public ModelAndView initTableInfo() {
        ModelAndView modelAndView = new ModelAndView("/core/tableAddByWizard2.jsp");
        ColumnDataType[] columnDataTypes = ColumnDataType.values();
        JsonArray jsonArray = new JsonArray();
        for (ColumnDataType columnDataType : columnDataTypes) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("key", columnDataType.getKey());
            jsonObject.addProperty("desc", columnDataType.getDesc());
            jsonArray.add(jsonObject);
        }

        modelAndView.addObject("columnDataTypes", jsonArray.toString());

        return modelAndView;
    }

    @RequestMapping(value = "/core/saveTableInfo", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<TableMetaDataDto> saveTableInfo(@RequestBody TableMetaDataDto tableInfo) {
        try {
            tableService.saveTableInfoByWizard(tableInfo);
        } catch (ValidateException validateException) {
            ApiResult.fail(StatusCode.BUSINESS_EXCEPTION.getCode(), validateException.getMessage());
        }
        //System.out.println(tableInfo.toString());
        return ApiResult.success(tableInfo);
    }
}
