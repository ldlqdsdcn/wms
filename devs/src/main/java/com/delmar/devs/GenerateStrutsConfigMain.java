/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 * 作者：刘大磊								                                              *
 * 电话：13336390671                                                               *
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.devs;

import com.delmar.devs.ftl.FreeMarkerHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘大磊 2015年1月21日 上午10:02:58
 */
public class GenerateStrutsConfigMain {
    private String[] modelList;
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
        Map<String,Object>  root = new HashMap();
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
