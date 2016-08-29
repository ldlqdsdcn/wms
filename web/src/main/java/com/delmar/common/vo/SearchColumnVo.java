package com.delmar.common.vo;

import com.delmar.core.model.CommonSearchResult;
import lombok.Data;

import java.util.List;

/**
 * @author dalei.liu 2016/8/29 22:53.
 */
@Data
public class SearchColumnVo {
    private Integer columnId;
    private String columnName;
    private String columnLabel;
    private int dataType;
    private int inputType;
    private int opearType;
    private String value;
    private List<CommonSearchResult> relOpearList;
    private List<CommonSearchResult> commonSearchResultList;

}
