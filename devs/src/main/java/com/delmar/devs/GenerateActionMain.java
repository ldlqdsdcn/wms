/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 * 作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               *
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.devs;

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
    /*public static String[] modelList={"Usergroup"};

    public  static String modulename="sys";
    public static String genmodelpath="D:/code/platform/delmar_system/";
    public static Configuration config;*/
    public static void main(String[] args) {

        File filepath = new File(GenerateDaoMain.class.getResource("/").getFile());
        Configuration config = new Configuration();
        try {
            config.setDirectoryForTemplateLoading(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        config.setObjectWrapper(new DefaultObjectWrapper());


//		GenerateActionMain gam=new  GenerateActionMain(config,"sys"
//				,"G:/IdeaProjects/MyHome/",new String[]{"SysOrgExtra"});
//		gam.generateActionclass();
    }


    String[] modelList;
    private Configuration config;
    private String modulename;
    private String genmodelpath;

    public GenerateActionMain(Configuration config, String modulename, String genmodelpath, String[] modelList) {
        this.config = config;
        this.modulename = modulename;
        this.genmodelpath = genmodelpath;
        this.modelList = modelList;
    }

    public void generateActionclass() {
        Template template = null;
        try {
            template = config.getTemplate("StrutsActionClass.flt", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        File filepath = new File(GenerateDaoMain.class.getResource("/").getFile());
        Map root = new HashMap();

        Date date = new Date();

        String classpackage = "com.delmar." + modulename + ".web.action";
        String modelpackage = "com.delmar." + modulename + ".model";
        String servicepackage = "com.delmar." + modulename + ".service";
        String datetime = com.delmar.util.DateTimeDecorator.dateToLongString(date);
        root.put("packagename", classpackage);

        root.put("modelpackage", modelpackage);
        root.put("servicepackage", servicepackage);


        root.put("datetime", datetime);
        //gc.setTime(date);
        try {
            for (String model : modelList) {
                //toUpperCase

                System.out.println(genmodelpath + "src/main/java/" + classpackage.replace(".", "/") + "/" + model + "Action.java");
                File file = new File(genmodelpath + "src/main/java/" + classpackage.replace(".", "/") + "/" + model + "Action.java");
                root.put("modelname", model);
                String bgnChar = model.substring(0, 1);
                root.put("modelObjname", bgnChar.toLowerCase() + model.substring(1));

                if (!file.exists()) {
                    //System.out.println("file exist");
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    } else {
                        file.delete();
                    }
                    file.createNewFile();
                }
                Writer out = new BufferedWriter(new FileWriter(file));
                template.process(root, out);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
