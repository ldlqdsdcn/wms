package com.delmar.core.dto;

import lombok.Data;

/**
 * Created by admin on 2016/8/23.
 */
@Data
public class UniqueIndexDto implements java.io.Serializable{
    private String indexColumnName;
    private String indexName;
}
