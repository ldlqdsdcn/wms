/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 * 作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               *
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.devs;

import com.delmar.devs.ftl.FreeMarkerHelper;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘大磊 2015年1月21日 上午10:02:58
 */
public class GenerateActionMain {
    public static void main(String[] args) {


        GenerateActionMain gam = new GenerateActionMain("core", new String[]{"Window", "Field", "Page"});
        gam.generateActionclass();
    }


    String[] modelList;
    private String modulename;

    public GenerateActionMain(String modulename, String[] modelList) {
        this.modulename = modulename;

        this.modelList = modelList;
    }

    public void generateActionclass() {
        Map root = new HashMap();

        Date date = new Date();

        String classpackage = "com.delmar." + modulename + ".web.action";
        String modelpackage = "com.delmar." + modulename + ".model";
        String servicepackage = "com.delmar." + modulename + ".service";
        String datetime = com.delmar.utils.DateTimeDecorator.dateToLongString(date);
        root.put("packagename", classpackage);
        root.put("modelpackage", modelpackage);
        root.put("servicepackage", servicepackage);


        root.put("datetime", datetime);
        //gc.setTime(date);
        try {
            for (String model : modelList) {
                //toUpperCase
                root.put("modelname", model);
                String bgnChar = model.substring(0, 1);
                root.put("modelObjname", bgnChar.toLowerCase() + model.substring(1));
                FreeMarkerHelper.getInstance().outFile("StrutsActionClass.ftl", root, "src/main/java/" + classpackage.replace(".", "/") + "/" + model + "Action.java",true);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
