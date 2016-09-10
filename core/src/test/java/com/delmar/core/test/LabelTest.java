package com.delmar.core.test;

import com.delmar.core.model.Label;
import com.delmar.core.service.LabelService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * Created by 刘大磊 on 2016/9/10 11:24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LabelTest {
    private static final Logger logger=Logger.getLogger(LabelTest.class);
    @Autowired
    private LabelService labelService;
    @Test
    public void testGetLabelByKey()
    {
        Label label= labelService.getLabelByValue("window.label.name");
        logger.debug(label.getMsgtext());
        Assert.notNull(label);
    }
}
