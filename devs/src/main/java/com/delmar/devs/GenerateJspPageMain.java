package com.delmar.devs;

import com.delmar.cons.IntelliKeyWord;
import com.delmar.core.model.*;
import com.delmar.core.model.Window;
import com.delmar.devs.model.JspListProp;
import com.delmar.utils.BeanHelper;
import com.delmar.utils.DateTimeDecorator;
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
    private  String genmodelpath = "d:/IdeaProjects/MyHome/";
    private  String user = "刘大磊";
    /**
     * 填写对应的模块名
     */

    private  String[] modeList;
    private String[] modeNameList;
    private String module;
    private  String namespace = "/core";
    private  Configuration config;
    public GenerateJspPageMain(Configuration config,String namespace,String[] modeList,String[] modeNameList,String user,String genmodelpath,String module)
    {
        this.config=config;
        this.namespace=namespace;
        this.modeList=modeList;
        this.modeNameList=modeNameList;
        this.user=user;
        this.genmodelpath=genmodelpath;
        this.module=module;


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
//        File filepath = new File(GenerateDaoMain.class.getResource("/").getFile());
//        config = new Configuration();
//        try {
//            config.setDirectoryForTemplateLoading(filepath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public  void generateFormPage(String mode,String title) {
        List<JspListProp> jspFormPropList = getOutPutList(mode);

        Template template = null;
        try {
            template = config.getTemplate("formPage.flt", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map root = new HashMap();
        root.put("mode", mode);
        root.put("title", title);
        root.put("namespace", namespace);
        root.put("user", user);

        Date date = new Date();

        String datetime = DateTimeDecorator.dateToLongString(date);

        root.put("datetime", datetime);
        root.put("propertyList", jspFormPropList);
        File file = new File(genmodelpath + "src/main/webapp" + namespace + "/" + mode + "Form.jsp");
        storeFile(file, template, root);
    }

    public  void generateListPage(String mode,String title) {
        List<JspListProp> jspListPropList = getOutPutList(mode);
        Template template = null;
        try {
            template = config.getTemplate("listPage.flt", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map root = new HashMap();
        root.put("mode", mode);
        root.put("title", title);
        root.put("namespace", namespace);
        root.put("user", user);
        Date date = new Date();

        String datetime = DateTimeDecorator.dateToLongString(date);

        root.put("datetime", datetime);
        root.put("propertyList", jspListPropList);

        File file = new File(genmodelpath + "src/main/webapp" + namespace + "/" + mode + "List.jsp");
        storeFile(file, template, root);

    }
    private  List<JspListProp> getOutPutList(String mode)
    {
       String modeName= com.delmar.utils.StringUtil.upperFirstChar(mode);
        Class cla=null;
        try {
            System.out.println("com.delmar."+module+".model."+modeName);
            cla=Class.forName("com.delmar."+module+".model."+modeName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<JspListProp> jspFormPropList = new ArrayList<JspListProp>();
        java.lang.reflect.Field[] fields = BeanHelper.getAllFields(cla);
        for (java.lang.reflect.Field f : fields) {
            if(IntelliKeyWord.hasSkipped(f.getName()))
            {
                continue;
            }
            boolean date = false;
            if (f.getType().equals(Date.class)) {
                date = true;
            }
            boolean isreadOnly = IntelliKeyWord.isReadOnly(f.getName());
            String label = IntelliKeyWord.getLabel(f.getName());
            if (label == null) {
                label = f.getName();
            }
            JspListProp jspListProp= new JspListProp(f.getName(), label, date, !isreadOnly);
            if(IntelliKeyWord.isBooleanTag(f.getName()))
            {
                jspListProp.setBooleanTag(true);
            }
            jspListProp.setModule(IntelliKeyWord.getModule(f.getName()));
            if(jspListProp.getModule()!=null)
            {
                jspListProp.setForeign(true);
            }
            String width=IntelliKeyWord.getWidth(f.getName());
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
    private static void storeFile(File file, Template template, Map root) {
        try {
            if (!file.exists()) {
                //System.out.println("file exist");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
            template.process(root, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
