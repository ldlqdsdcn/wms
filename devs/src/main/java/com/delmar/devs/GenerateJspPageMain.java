package com.delmar.devs;

import com.delmar.cons.IntelliKeyWord;
import com.delmar.core.def.ColumnDataType;
import com.delmar.core.dto.ColumnMetaDataDto;
import com.delmar.core.dto.TableMetaDataDto;
import com.delmar.core.model.*;
import com.delmar.core.model.Window;
import com.delmar.devs.ftl.FreeMarkerHelper;
import com.delmar.devs.model.ColumnInfo;
import com.delmar.devs.model.JspListProp;
import com.delmar.utils.BeanHelper;
import com.delmar.utils.CommonConverter;
import com.delmar.utils.DateTimeDecorator;
import com.delmar.utils.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.beanutils.BeanUtils;

import java.awt.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.List;

/**
 * Created by admin on 2016/8/25.
 */
public class GenerateJspPageMain {
    private  String user = "刘大磊";
    /**
     * 填写对应的模块名
     */

    private  String[] modeList;
    private String[] modeNameList;
    private String module;
    private  String namespace ;
    private TableMetaDataDto tableMetaDataDto;
    public GenerateJspPageMain(String[] modeList,String[] modeNameList,String user,String module,TableMetaDataDto tableMetaDataDto)
    {
        this.modeList=modeList;
        this.modeNameList=modeNameList;
        this.user=user;
        this.module=module;
        this.namespace="/"+module;
        this.tableMetaDataDto=tableMetaDataDto;
    }
    public void generateJspPage()
    {
        for(int i=0;i<modeList.length;i++)
        {
            String mode=com.delmar.utils.StringUtil.lowerFirstChar(modeList[i]);
            generateListPage(mode, modeNameList[i]);
            generateFormPage(mode,modeNameList[i]);
        }
    }
    public static void main(String[] args) {

    }

    public  void generateFormPage(String mode,String title) {
        List<JspListProp> jspFormPropList = getOutPutList(mode);
        Map root = new HashMap();
        root.put("mode", mode);
        root.put("title", title);
        root.put("namespace", namespace);
        root.put("user", user);

        Date date = new Date();

        String datetime = DateTimeDecorator.dateToLongString(date);

        root.put("datetime", datetime);
        root.put("propertyList", jspFormPropList);

        FreeMarkerHelper.getInstance().outFile("formPage.ftl",root,"src/main/webapp" + namespace + "/" + mode + "Form.jsp",true);
    }

    public  void generateListPage(String mode,String title) {
        List<JspListProp> jspListPropList = getOutPutList(mode);
        Map root = new HashMap();
        root.put("mode", mode);
        root.put("title", title);
        root.put("namespace", namespace);
        root.put("user", user);
        Date date = new Date();
        String datetime = DateTimeDecorator.dateToLongString(date);
        root.put("datetime", datetime);
        root.put("propertyList", jspListPropList);
        FreeMarkerHelper.getInstance().outFile("listPage.ftl",root,"src/main/webapp" + namespace + "/" + mode + "List.jsp",true);
    }
    private  List<JspListProp> getOutPutList(String mode)
    {
       String modeName= com.delmar.utils.StringUtil.upperFirstChar(mode);

        List<JspListProp> jspFormPropList = new ArrayList<JspListProp>();
        List<ColumnMetaDataDto> columnMetaDataDtoList=tableMetaDataDto.getColumnList();

        for (ColumnMetaDataDto columnMetaDataDto : columnMetaDataDtoList) {
            ColumnInfo columnInfo=new ColumnInfo();
            try {
                CommonConverter.copyProperties(columnMetaDataDto,columnInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            columnInfo.setPropertyName(StringUtil.fieldToProperty(columnMetaDataDto.getColumnName()));
            if(IntelliKeyWord.hasSkipped(columnInfo.getPropertyName()))
            {
                continue;
            }
            boolean date = false;
            if (columnInfo.getDataType()== ColumnDataType.DATE.getKey()) {
                date = true;
            }
            boolean isreadOnly = IntelliKeyWord.isReadOnly(columnInfo.getPropertyName());
            String label = IntelliKeyWord.getLabel(columnInfo.getPropertyName());
            if (label == null) {
                label = columnInfo.getPropertyName();
            }
            JspListProp jspListProp= new JspListProp(columnInfo.getPropertyName(), label, date, !isreadOnly);
            if(IntelliKeyWord.isBooleanTag(columnInfo.getPropertyName()))
            {
                jspListProp.setBooleanTag(true);
            }
            jspListProp.setModule(IntelliKeyWord.getModule(columnInfo.getPropertyName()));
            if(jspListProp.getModule()!=null)
            {
                jspListProp.setForeign(true);
            }
            String width=IntelliKeyWord.getWidth(columnInfo.getPropertyName());
            //todo 不合理，代码
            if(width!=null)
            {
                jspListProp.setCssStyle("cssStyle=\""+width+"\"");
            }
            else
            {
                jspListProp.setCssStyle("");
            }
            jspFormPropList.add(jspListProp);
        }
        return jspFormPropList;
    }
}
