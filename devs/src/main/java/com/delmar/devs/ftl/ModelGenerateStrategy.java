package com.delmar.devs.ftl;

import com.delmar.cons.IntelliKeyWord;
import com.delmar.core.def.ColumnDataType;
import com.delmar.core.def.FieldType;
import com.delmar.core.dto.ColumnMetaDataDto;
import com.delmar.core.dto.TableMetaDataDto;
import com.delmar.devs.model.ColumnInfo;
import com.delmar.devs.model.GenModelDto;
import com.delmar.utils.CommonConverter;
import com.delmar.utils.DateTimeDecorator;
import com.delmar.utils.StringUtil;

import java.util.*;

/**
 * Created by admin on 2016/8/26.
 */
public class ModelGenerateStrategy {
    public ModelGenerateStrategy(TableMetaDataDto tableMetaDataDto, GenModelDto genModelDto)
    {
        this.tableMetaDataDto=tableMetaDataDto;
        this.genModelDto=genModelDto;
    }
    private TableMetaDataDto tableMetaDataDto;
    private GenModelDto genModelDto;
    public void generateModel()
    {
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("module",genModelDto.getModule());
        param.put("model",genModelDto.getModelName());
        param.put("tableName",tableMetaDataDto.getName());
        Date date=new Date();
        String datetime= DateTimeDecorator.dateToLongString(date);
        param.put("datetime",datetime);
        param.put("hasDate",false);
        param.put("hasDecimal",false);
        List<ColumnInfo> columnInfoList=new ArrayList<ColumnInfo>();
        ColumnDataType[] columnDataTypes=ColumnDataType.values();
        for(ColumnMetaDataDto cmd:tableMetaDataDto.getColumnList())
        {
            ColumnInfo columnInfo=new ColumnInfo();
            try {
                CommonConverter.copyProperties(cmd,columnInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for(ColumnDataType dataType:columnDataTypes)
            {
                if(columnInfo.getDataType()==dataType.getKey())
                {
                    columnInfo.setPropertyType(dataType.getValue());
                    if(columnInfo.getDataType()==ColumnDataType.DATE.getKey())
                    {
                        param.put("hasDate",true);
                    }
                    if(columnInfo.getDataType()==ColumnDataType.DECIMAL.getKey())
                    {
                        param.put("hasDecimal",true);
                    }
                }
            }

            columnInfo.setPropertyName(StringUtil.fieldToProperty(cmd.getColumnName()));
            System.out.println("columnInfo.getPropertyName()="+columnInfo.getPropertyName()+" type:"+columnInfo.getType());
            columnInfo.setKeyWord(IntelliKeyWord.isKeyWord(columnInfo.getColumnName()));
            columnInfoList.add(columnInfo);
        }
        param.put("propertyList",columnInfoList);
        FreeMarkerHelper.getInstance().outFile("model.ftl",param,"src/main/java/com/delmar/"+genModelDto.getModule()+"/model/"+genModelDto.getModelName()+".java");

    }
}
