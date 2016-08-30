package com.delmar.core.dto;

/**
 * Created by admin on 2016/8/30.
 */

import lombok.Data;

@Data
public class SearchColumnDto {
    private String opearType;
    private String value;
    private Integer columnId;
}
