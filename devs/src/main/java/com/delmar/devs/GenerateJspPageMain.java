package com.delmar.devs;

import com.delmar.cons.IntelliKeyWord;
import com.delmar.core.api.Result;
import com.delmar.core.def.ColumnDataType;
import com.delmar.core.dto.ColumnMetaDataDto;
import com.delmar.core.dto.TableMetaDataDto;
import com.delmar.core.service.TableService;
import com.delmar.devs.def.DecoratorType;
import com.delmar.devs.def.ValidationDef;
import com.delmar.devs.ftl.FreeMarkerHelper;
import com.delmar.devs.model.ColumnInfo;
import com.delmar.devs.model.FormLine;
import com.delmar.devs.model.GenModelDto;
import com.delmar.devs.model.JspModelProp;
import com.delmar.utils.CommonConverter;
import com.delmar.utils.DateTimeDecorator;
import com.delmar.utils.StringUtil;

import java.util.*;

/**
 * Created by admin on 2016/8/25.
 */
public class GenerateJspPageMain {
    private String user = "刘大磊";
    private TableService tableService;
    /**
     * 填写对应的模块名
     */
    private GenModelDto model;
    private String namespace;
    private TableMetaDataDto tableMetaDataDto;

    public GenerateJspPageMain(GenModelDto model, String user, String module, TableMetaDataDto tableMetaDataDto, TableService tableService) {
        this.model = model;
        this.user = user;
        this.namespace = "/" + module;
        this.tableMetaDataDto = tableMetaDataDto;
        this.tableService = tableService;
    }

    public void generateJspPage() {

        generateListPage();
        generateFormPage();

    }

    private void generateFormPage() {
        List<JspModelProp> jspFormPropList = getOutPutList(com.delmar.utils.StringUtil.lowerFirstChar(model.getModelName()), this.tableMetaDataDto);
        Map<String,Object> root = new HashMap();
        root.put("mode", com.delmar.utils.StringUtil.lowerFirstChar(model.getModelName()));
        root.put("title", model.getRemark());
        root.put("namespace", namespace);
        root.put("user", user);

        Date date = new Date();

        String datetime = DateTimeDecorator.dateToLongString(date);

        root.put("datetime", datetime);
        root.put("propertyList", jspFormPropList);
        List<FormLine> formLines = new ArrayList<>();
        List<GenModelDto> genModelDtoList = this.model.getIncludeModelList();
        if (genModelDtoList != null)
            for (GenModelDto genModelDto : genModelDtoList) {
                FormLine formLine = new FormLine();
                formLine.setLabel(genModelDto.getRemark());
                formLine.setModel(genModelDto.getModelName());
                formLine.setTrl(genModelDto.isTrl());
                Result<TableMetaDataDto> result = tableService.getTableDescription(genModelDto.getTableName());
                TableMetaDataDto tableMetaDataDto = result.getData();

                List<JspModelProp> linePropList = getOutPutList(com.delmar.utils.StringUtil.lowerFirstChar(model.getModelName()), tableMetaDataDto);
                formLine.setPropertyList(linePropList);
                formLines.add(formLine);
            }
        if (formLines.size() > 0) {
            root.put("lineList", formLines);
        }


        FreeMarkerHelper.getInstance().outFile("formPage.ftl", root, "src/main/webapp" + namespace + "/" + com.delmar.utils.StringUtil.lowerFirstChar(model.getModelName()) + "Form.jsp", true);
    }

    private void generateListPage() {
        List<JspModelProp> jspListPropList = getOutPutList(com.delmar.utils.StringUtil.lowerFirstChar(model.getModelName()), this.tableMetaDataDto);
        Map root = new HashMap();
        root.put("mode", com.delmar.utils.StringUtil.lowerFirstChar(model.getModelName()));
        root.put("title", model.getRemark());
        root.put("namespace", namespace);
        root.put("module",model.getModule());
        root.put("user", user);
        root.put("pagingByDb",model.isPagingByDb());
        Date date = new Date();
        String datetime = DateTimeDecorator.dateToLongString(date);
        root.put("datetime", datetime);
        root.put("propertyList", jspListPropList);
        FreeMarkerHelper.getInstance().outFile("listPage.ftl", root, "src/main/webapp" + namespace + "/" + com.delmar.utils.StringUtil.lowerFirstChar(model.getModelName()) + "List.jsp", true);
    }

    private List<JspModelProp> getOutPutList(String mode, TableMetaDataDto tableData) {

        List<JspModelProp> jspFormPropList = new ArrayList<>();
        List<ColumnMetaDataDto> columnMetaDataDtoList = tableData.getColumnList();

        for (ColumnMetaDataDto columnMetaDataDto : columnMetaDataDtoList) {

            List<Integer> validationList = new ArrayList<>();
            ColumnInfo columnInfo = CommonConverter.convertObject(columnMetaDataDto,ColumnInfo.class);

            columnInfo.setPropertyName(StringUtil.fieldToProperty(columnMetaDataDto.getColumnName()));
            if (IntelliKeyWord.hasSkipped(columnInfo.getPropertyName())) {
                continue;
            }

            boolean date = false;
            ColumnDataType[] columnDataTypes = ColumnDataType.values();

            boolean isreadOnly = IntelliKeyWord.isReadOnly(columnInfo.getPropertyName());
            if(!isreadOnly)
            {
                switch (columnDataTypes[columnInfo.getDataType()]) {
                    case DATE: {
                        validationList.add(ValidationDef.DATE.getKey());
                        break;
                    }
                    case INT: {
                        validationList.add(ValidationDef.INT.getKey());
                        break;
                    }
                    case FLOAT:
                    case DECIMAL: {
                        validationList.add(ValidationDef.FLOAT.getKey());
                        break;
                    }
                }
                if (!columnInfo.getNullable()) {
                    validationList.add(ValidationDef.REQUIRED.getKey());
                }
                if (columnInfo.getDataType() == ColumnDataType.DATE.getKey()) {
                    date = true;
                    validationList.add(ValidationDef.DATE.getKey());
                }
            }

            String label = IntelliKeyWord.getLabel(columnInfo.getPropertyName());
            if (label == null) {
                label = columnInfo.getPropertyName();
            }
            JspModelProp jspListProp = new JspModelProp(columnInfo.getPropertyName(), label, date, !isreadOnly);

            jspListProp.setRequired(!columnInfo.getNullable());
            if (IntelliKeyWord.isBooleanTag(columnInfo.getPropertyName())) {
                jspListProp.setBooleanTag(true);
            }
            jspListProp.setModule(IntelliKeyWord.getModule(columnInfo.getPropertyName()));
            if (jspListProp.getModule() != null) {
                jspListProp.setForeign(true);
            }
            String width = IntelliKeyWord.getWidth(columnInfo.getPropertyName());
            //todo 不合理，代码
            if (width != null) {
                jspListProp.setCssStyle("cssStyle=\"" + width + "\"");
            } else {
                jspListProp.setCssStyle("");
            }
            if (validationList.size() > 0) {
                jspListProp.setValidationList(validationList);
            }

            if(columnInfo.getDataType()==ColumnDataType.DATE.getKey())
            {
                if(columnInfo.getColumnName().toUpperCase().contains("TIME"))
                {
                    jspListProp.setDecoratorType(DecoratorType.DATETIME.getKey());
                }
                else
                {
                    jspListProp.setDecoratorType(DecoratorType.DATE.getKey());
                }
            }
            else
            {
                jspListProp.setDecoratorType(IntelliKeyWord.getDecorator(jspListProp.getProp()));
            }
            if(IntelliKeyWord.isNotValidate(columnInfo.getPropertyName()))
            {
                jspListProp.setValidationList(null);

            }

            jspFormPropList.add(jspListProp);
        }
        return jspFormPropList;
    }
}
