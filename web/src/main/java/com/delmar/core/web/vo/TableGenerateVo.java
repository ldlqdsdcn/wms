package com.delmar.core.web.vo;

import lombok.Data;

/**
 * Created by admin on 2016/8/18.
 */
@Data
public class TableGenerateVo {
    private String tableName;
    private String primaryKey;
    private String uniqueIndex;
    private String exportedFK;
    private String importedFK;
    private String columnList;
}
