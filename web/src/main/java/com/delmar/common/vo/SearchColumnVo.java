package com.delmar.common.vo;

import com.delmar.core.model.CommonSearchResult;
import lombok.Data;

import java.util.List;

/**
 * @author dalei.liu 2016/8/29 22:53.
 */
@Data
public class SearchColumnVo implements java.io.Serializable{
    private Integer columnId;
    private String columnName;
    private String columnLabel;
    private int dataType;
    private int inputType;
    private String opearType;
    private String value;
    private List<String> relOpearList;
    private List<CommonSearchResult> commonSearchResultList;

}
