/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 * 作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               *
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.devs;

import com.delmar.devs.ftl.FreeMarkerHelper;
import com.delmar.devs.model.FormLine;
import com.delmar.devs.model.GenModelDto;
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

    private GenModelDto genModelDto;


    public GenerateActionMain(GenModelDto genModelDto) {
       this.genModelDto=genModelDto;
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


        root.put("datetime", datetime);
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
