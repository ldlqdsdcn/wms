package com.delmar.core.web.vo;

import com.delmar.core.dto.ColumnMetaDataDto;
import com.delmar.core.dto.ForeignKey;
import com.delmar.core.dto.UniqueIndexDto;
import lombok.Data;

import java.util.List;

/**
 * Created by admin on 2016/8/18.
 */
@Data
public class TableGenerateVo {
    /**
     * 表名
     */
    private String name;
    /**
     *
     */
    private String pkColumn;

    private List<ForeignKey> exportedFK;

    private List<ForeignKey> importedFK;

    private List<ColumnMetaDataDto> columnList;

    private List<UniqueIndexDto> uniqueKeyList;


}
