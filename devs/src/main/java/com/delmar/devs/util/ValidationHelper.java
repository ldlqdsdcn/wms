package com.delmar.devs.util;

import com.delmar.cons.IntelliKeyWord;
import com.delmar.core.dto.ColumnMetaDataDto;
import com.delmar.devs.model.JspModelProp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/29.
 */
public class ValidationHelper {

    public static List<JspModelProp> getValidationList(List<ColumnMetaDataDto> columnMetaDataDtoList) {
        List<JspModelProp> jspModelProps = new ArrayList<>();
        for (ColumnMetaDataDto columnMetaDataDto : columnMetaDataDtoList) {
            if (!IntelliKeyWord.isNotValidate(columnMetaDataDto.getColumnName())) {
                if (!columnMetaDataDto.getNullable()) {

                }
            }

        }
        return jspModelProps;
    }
}
