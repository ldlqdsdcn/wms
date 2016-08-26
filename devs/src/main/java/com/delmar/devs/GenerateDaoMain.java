/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.devs;

import com.delmar.devs.ftl.FreeMarkerHelper;
import com.delmar.utils.DateTimeDecorator;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘大磊 2014年12月22日 上午10:28:48
 */
public class GenerateDaoMain {
    String[] modelList;

    private String modulename;


    /**
     *
     */
    public GenerateDaoMain(String modulename, String[] modelList) {
        this.modulename = modulename;

        this.modelList = modelList;
    }

    public void generateInterface() {


        Map root = new HashMap();

        Date date = new Date();
        String interfacepackage = "com.delmar." + modulename + ".dao";
        String modelpackage = "com.delmar." + modulename + ".model";
        String datetime = DateTimeDecorator.dateToLongString(date);
        root.put("packagename", interfacepackage);
        root.put("modelpackage", modelpackage);
        root.put("datetime", datetime);
        //gc.setTime(date);
        try {
            for (String model : modelList) {

                root.put("modelname", model);
                FreeMarkerHelper.getInstance().outFile("daoInterface.ftl", root, "src/main/java/" + interfacepackage.replace(".", "/") + "/" + model + "Dao.java");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void generatedaoclass() {
        Map root = new HashMap();
        Date date = new Date();
        String classpackage = "com.delmar." + modulename + ".dao.mybatis";
        String modelpackage = "com.delmar." + modulename + ".model";
        String datetime = DateTimeDecorator.dateToLongString(date);
        root.put("packagename", classpackage);

        root.put("modelpackage", modelpackage);
        root.put("datetime", datetime);
        //gc.setTime(date);
        try {
            for (String model : modelList) {
                //toUpperCase
                root.put("interfacefullname", "com.delmar." + modulename + ".dao." + model + "Dao");

                root.put("modelname", model);
                String bgnChar = model.substring(0, 1);
                root.put("repositoryname", bgnChar.toLowerCase() + model.substring(1) + "Dao");
                root.put("mappername", "com.delmar." + modulename + ".mybatis.sql." + model + "Mapper");
                FreeMarkerHelper.getInstance().outFile("daoclass.ftl", root, "src/main/java/" + classpackage.replace(".", "/") + "/" + model + "DaoMybatis.java");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
