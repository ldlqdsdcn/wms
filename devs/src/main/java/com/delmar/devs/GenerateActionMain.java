/******************************************************************************
 * 刘大磊  2013-07-01											  *
 * 作者：刘大磊								                                  *
 * 电话：13336390671                                                        *
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/
package com.delmar.devs;

import com.delmar.cons.IntelliKeyWord;
import com.delmar.core.def.ColumnDataType;
import com.delmar.core.def.FieldType;
import com.delmar.core.dto.ColumnMetaDataDto;
import com.delmar.core.dto.TableMetaDataDto;
import com.delmar.devs.ftl.FreeMarkerHelper;
import com.delmar.devs.model.FormLine;
import com.delmar.devs.model.GenModelDto;
import com.delmar.utils.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.*;
import java.util.*;

/**
 * @author 刘大磊 2015年1月21日 上午10:02:58
 */
public class GenerateActionMain {

    public static void main(String[] args) {

    }
    TableMetaDataDto tableMetaDataDto;
    private GenModelDto genModelDto;


    public GenerateActionMain(GenModelDto genModelDto, TableMetaDataDto tableMetaDataDto) {
       this.genModelDto=genModelDto;
        this.tableMetaDataDto=tableMetaDataDto;
    }

    public void generateActionclass() {
        Map root = new HashMap();

        Date date = new Date();

        String classpackage = "com.delmar." + genModelDto.getModule() + ".web.action";
        String modelpackage = "com.delmar." + genModelDto.getModule()  + ".model";
        String servicepackage = "com.delmar." + genModelDto.getModule()  + ".service";
        String datetime = com.delmar.utils.DateTimeDecorator.dateToLongString(date);
        root.put("packagename", classpackage);
        root.put("modelpackage", modelpackage);
        root.put("servicepackage", servicepackage);
        root.put("pagingByDb",genModelDto.isPagingByDb());
        List<String> requiredStrings=new ArrayList<String>();
        List<String> requiredFields=new ArrayList<>();

        boolean hasCreated=false;
        boolean hasUpdated=false;
        boolean hasUserId=false;
        boolean hasOrgId=false;
        boolean hasClientId=false;
        for(ColumnMetaDataDto columnMetaDataDto:tableMetaDataDto.getColumnList())
        {
            if(!IntelliKeyWord.isNotValidate(columnMetaDataDto.getColumnName()))
            { if(!columnMetaDataDto.getNullable())
            {


                if(columnMetaDataDto.getDataType()== ColumnDataType.STRING.getKey())
                {

                    requiredStrings.add(StringUtil.fieldToProperty(columnMetaDataDto.getColumnName()));
                }
                else
                {
                    requiredFields.add(StringUtil.fieldToProperty(columnMetaDataDto.getColumnName()));
                }
            }
            }


            if(columnMetaDataDto.getColumnName().equalsIgnoreCase("created"))
            {
                 hasCreated=true;
            } else
            if(columnMetaDataDto.getColumnName().equalsIgnoreCase("updated"))
            {
                 hasUpdated=true;
            } else
            if(columnMetaDataDto.getColumnName().equalsIgnoreCase("user_id"))
            {
                hasUserId=true;
            }
            else
            if(columnMetaDataDto.getColumnName().equalsIgnoreCase("org_id"))
            {
                hasOrgId=true;
            }
            else
            if(columnMetaDataDto.getColumnName().equalsIgnoreCase("client_id"))
            {
                hasClientId=true;
            }

        }
        root.put("requiredStrings",requiredStrings);
        root.put("requiredFields",requiredFields);
        if(requiredStrings.size()>0||requiredFields.size()>0)
        {
            root.put("needValidate",true);
        }
        else
        {
            root.put("needValidate",false);
        }
        root.put("datetime", datetime);
        root.put("hasCreated", hasCreated);
        root.put("hasUpdated", hasUpdated);
        root.put("hasClientId", hasClientId);
        root.put("hasOrgId", hasOrgId);
        root.put("hasUserId", hasUserId);
        //gc.setTime(date);
        try {

                //toUpperCase
                root.put("modelname", genModelDto.getModelName());
                String bgnChar = genModelDto.getModelName().substring(0, 1);
                root.put("modelObjname", bgnChar.toLowerCase() + genModelDto.getModelName().substring(1));

                if(genModelDto.getIncludeModelList()!=null)
                {
                    List<FormLine> formLineList=new ArrayList<FormLine>();
                    for(GenModelDto gm:genModelDto.getIncludeModelList())
                    {
                        FormLine fl=new FormLine();
                        fl.setLabel(gm.getRemark());
                        fl.setModel(gm.getModelName());
                        fl.setModule(gm.getModule());
                        formLineList.add(fl);
                    }
                    root.put("lineList",formLineList);
                }

                FreeMarkerHelper.getInstance().outFile("StrutsActionClass.ftl", root, "src/main/java/" + classpackage.replace(".", "/") + "/" + genModelDto.getModelName() + "Action.java",true);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
