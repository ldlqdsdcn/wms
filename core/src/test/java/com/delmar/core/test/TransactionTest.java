package com.delmar.core.test;

import com.delmar.core.model.Label;
import com.delmar.core.model.LabelTrl;
import com.delmar.core.service.LabelService;
import com.delmar.core.service.LanguageService;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 刘大磊 on 2016/9/9.
 * 事物是否可用测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TransactionTest {
    private Logger logger= Logger.getLogger(TransactionTest.class);
    @Autowired
    private LabelService labelService;
    @Autowired
    private LanguageService languageService;

    /**
     * 测试框架事物是否运行正常
     */
    @Test
    public void testTransaction()
    {
        int countBeforeSaved=labelService.countObjects(null);
        logger.debug("countBeforeSaved="+countBeforeSaved);
        Date now=new Date();
        Label label=new Label();
        label.setMsgtext("刘大磊测试");
        label.setValue("ldl.test");
        List<LabelTrl> trlList=new ArrayList<>();
        LabelTrl labelZhCN=new LabelTrl();
        labelZhCN.setMsgtext("刘大磊测试");
        labelZhCN.setLanguage("zh_CN");
        trlList.add(labelZhCN);
        LabelTrl labelEnUs=new LabelTrl();
        labelEnUs.setMsgtext("solomon test");
        labelEnUs.setLanguage("en_US");
        trlList.add(labelEnUs);
        LabelTrl labelZhTW=new LabelTrl();
        labelZhTW.setMsgtext("刘大磊测试");
        labelZhTW.setLanguage("zh_CN");
        trlList.add(labelZhTW);

        try
        {
            labelService.saveLabel(label,trlList);
        }
        catch (Exception e)
        {
            logger.error("保存Label异常",e);
        }
        int countSaved=labelService.countObjects(null);
        logger.debug("countSaved="+countSaved);
        Assert.assertEquals(countBeforeSaved,countSaved);
    }
}
