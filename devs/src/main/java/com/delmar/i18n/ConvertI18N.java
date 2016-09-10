package com.delmar.i18n;

import com.delmar.core.dao.LabelTrlDao;
import com.delmar.core.model.Label;
import com.delmar.core.model.LabelTrl;
import com.delmar.core.service.LabelService;
import com.delmar.core.service.LabelTrlService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

/**
 * Created by 刘大磊 on 2016/9/10 11:14.
 *
 * 数据库 和 国际化资源文件相互转化
 */
public class ConvertI18N {
    private LabelService labelService;
    private LabelTrlDao labelTrlDao;
    private ConvertI18N(ApplicationContext applicationContext)
    {
        labelService=applicationContext.getBean(LabelService.class);
        labelTrlDao=applicationContext.getBean(LabelTrlDao.class);
    }
    /**
     *
     * @param args 国际化文件名 语言区域
     */
    public static void main(String[] args)throws Exception
    {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        ConvertI18N convertI18N=new ConvertI18N(applicationContext);
        convertI18N.insertPropertiesToTable("zh_CN");
        convertI18N.insertPropertiesToTable("en_US");
    }
    private void insertPropertiesToTable(String local)
    {
        Properties properties=new Properties();
        File file=new File("");
        File readFile=new File(file.getAbsolutePath()+"/web/src/main/resources/ApplicationMessages_"+local+".properties");
        try {
            InputStream i18nInputStream=new FileInputStream(readFile);
            properties.load(i18nInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<Object> keys=properties.keySet();
        for (Object key:keys)
        {
            System.out.println(key+" "+properties.get(key));
            insertToDb(local,key.toString(),properties.get(key).toString());
        }
    }

    private void insertToDb(String local,String key,String value)
    {
        Label label=labelService.getLabelByValue(key);
        if(label==null)
        {
            label=new Label();
            label.setValue(key);
            label.setMsgtext(value);
            labelService.save(label);

        }
        LabelTrl labelTrl=new LabelTrl();
        labelTrl.setMsgtext(value);
        labelTrl.setLanguage(local);
        labelTrl.setLabelId(label.getId());
        labelTrlDao.save(labelTrl);
    }

}
