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
    private static String genmodelpath = "d:/IdeaProjects/MyHome/";
    private static String user = "刘大磊";
    /**
     * 填写对应的模块名
     */
    private static String mode = "window";
    private static String title = "窗体";
    private static String namespace = "/core";


    private static Configuration config;

    public static void main(String[] args) {
        File filepath = new File(GenerateDaoMain.class.getResource("/").getFile());
        config = new Configuration();
        try {
            config.setDirectoryForTemplateLoading(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        generateListPage();
        generateFormPage();
    }

    public static void generateFormPage() {
        List<JspListProp> jspFormPropList = getOutPutList();

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

    public static void generateListPage() {
        List<JspListProp> jspListPropList = getOutPutList();
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
    private static List<JspListProp> getOutPutList()
    {
        List<JspListProp> jspFormPropList = new ArrayList<JspListProp>();
        java.lang.reflect.Field[] fields = BeanHelper.getAllFields(Window.class);
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
            Writer out = new BufferedWriter(new FileWriter(file));
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
