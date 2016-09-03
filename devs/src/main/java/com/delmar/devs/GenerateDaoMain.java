/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.devs;

import com.delmar.devs.ftl.FreeMarkerHelper;
import com.delmar.devs.model.GenModelDto;
import com.delmar.utils.DateTimeDecorator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘大磊 2014年12月22日 上午10:28:48
 */
public class GenerateDaoMain {
    private String[] modelList;

    private String modulename;

    private GenModelDto model;


    /**
     *
     */
    public GenerateDaoMain(String modulename, String[] modelList,GenModelDto model) {
        this.modulename = modulename;

        this.modelList = modelList;

        this.model=model;
    }

    public void generateInterface() {


        Map<String,Object> root = new HashMap();

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
                FreeMarkerHelper.getInstance().outFile("daoInterface.ftl", root, this.model.getOutputPath().getAbsolutePath()+"/src/main/java/" + interfacepackage.replace(".", "/") + "/" + model + "Dao.java");

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
                FreeMarkerHelper.getInstance().outFile("daoclass.ftl", root, this.model.getOutputPath().getAbsolutePath()+"/src/main/java/" + classpackage.replace(".", "/") + "/" + model + "DaoMybatis.java");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
