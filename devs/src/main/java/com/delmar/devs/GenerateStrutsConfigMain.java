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
public class GenerateStrutsConfigMain {
    /*public static String[] modelList={"Usergroup"};

    public  static String modulename="sys";
    public static String genmodelpath="D:/code/platform/delmar_system/";
    public static Configuration config;*/
    public static void main(String[] args) {


    }
    String[] modelList;
    private String modulename;


    public GenerateStrutsConfigMain( String modulename,  String[] modelList) {
        this.modulename = modulename;
        this.modelList = modelList;
        for(String s:modelList)
        {
            System.out.println("---------------------->s="+s);
        }
    }

    public void generateStrutsConfig() {
        Map root = new HashMap();
        Date date = new Date();
        String datetime = com.delmar.utils.DateTimeDecorator.dateToLongString(date);
        root.put("datetime", datetime);
        root.put("modelList",modelList);
        root.put("modulename",modulename);
        //gc.setTime(date);
        try {
            FreeMarkerHelper.getInstance().outFile("struts.ftl",root,"src/main/java/add_struts.xml",true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
