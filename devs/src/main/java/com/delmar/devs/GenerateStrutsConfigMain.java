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
public class GenerateStrutsConfigMain {
    /*public static String[] modelList={"Usergroup"};

    public  static String modulename="sys";
    public static String genmodelpath="D:/code/platform/delmar_system/";
    public static Configuration config;*/
    public static void main(String[] args) {

        File filepath = new File(GenerateStrutsConfigMain.class.getResource("/").getFile());
        Configuration config = new Configuration();
        try {
            config.setDirectoryForTemplateLoading(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        config.setObjectWrapper(new DefaultObjectWrapper());


		GenerateStrutsConfigMain gam=new GenerateStrutsConfigMain(config,"core"
				,"d:/IdeaProjects/MyHome/",new String[]{"Window","Field","Page"});
		gam.generateActionclass();
    }


    String[] modelList;
    private Configuration config;
    private String modulename;
    private String genmodelpath;

    public GenerateStrutsConfigMain(Configuration config, String modulename, String genmodelpath, String[] modelList) {
        this.config = config;
        this.modulename = modulename;
        this.genmodelpath = genmodelpath;
        this.modelList = modelList;
    }

    public void generateActionclass() {
        Template template = null;
        try {
            template = config.getTemplate("struts.flt", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        File filepath = new File(GenerateDaoMain.class.getResource("/").getFile());
        Map root = new HashMap();

        Date date = new Date();


        String datetime = com.delmar.utils.DateTimeDecorator.dateToLongString(date);
        root.put("datetime", datetime);
        root.put("modelList",modelList);
        root.put("modulename",modulename);
        //gc.setTime(date);
        try {

            File file = new File(genmodelpath + "src/main/java/struts.xml");



            if (!file.exists()) {
                //System.out.println("file exist");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                } else {
                    file.delete();
                }
                file.createNewFile();
            }
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
            template.process(root, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
