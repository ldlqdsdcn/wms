package com.delmar.core.web.controller;

import com.delmar.core.dto.TableMetaDataDto;
import com.delmar.core.service.TableService;
import com.delmar.core.web.vo.TableGenerateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2016/8/18.
 */
@Controller
@RequestMapping("/core")
public class TableController {
    @Autowired
    private TableService tableService;
    @RequestMapping(value="/getTableInfo", method= RequestMethod.GET)
    @ResponseBody
    public TableGenerateVo getTableInfo(String tableName)
    {
        TableMetaDataDto tableMetaDataDto=tableService.getTableDescription(tableName);
        TableGenerateVo tableGenerateVo=new TableGenerateVo();
        tableGenerateVo.setTableName(tableName);
        tableGenerateVo.setExportedFK(tableMetaDataDto.getExportedFK());
        tableGenerateVo.setImportedFK(tableMetaDataDto.getImportedFK());
        tableGenerateVo.setPrimaryKey(tableMetaDataDto.getPk_column());
        tableGenerateVo.setUniqueIndex(tableMetaDataDto.getUniqueKey());
        tableGenerateVo.setColumnList(tableMetaDataDto.getColumnList());
        return     tableGenerateVo;
    }
}
