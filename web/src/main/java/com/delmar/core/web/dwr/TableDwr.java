package com.delmar.core.web.dwr;

import com.delmar.core.dto.TableMetaDataDto;
import com.delmar.core.service.TableService;
import com.delmar.core.web.vo.TableGenerateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2016/8/18.
 */
@Repository("tableDwr")
public class TableDwr {
    @Autowired
    private TableService tableService;

    public TableGenerateVo getTableInfo(String tableName)
    {
        TableMetaDataDto tableMetaDataDto=tableService.getTableDescription(tableName);
        TableGenerateVo tableGenerateVo=new TableGenerateVo();
        return tableGenerateVo;

    }
}
