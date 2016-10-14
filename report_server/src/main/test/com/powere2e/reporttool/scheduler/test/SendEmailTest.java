package com.powere2e.reporttool.scheduler.test;

import com.powere2e.reporttool.scheduler.SendEmail;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by 刘大磊 on 2016/9/9 17:06.
 */
public class SendEmailTest {
    @Test
    public void testSendEmail()
    {
        SendEmail se=new SendEmail();
        boolean result=se.SendMail("smtp.126.com", "swms_admin@126.com", new String[]{"ldlqdsd@126.com"}, "swmsadmin1", "你好啊", "中国人民",
                null);
        Assert.assertTrue(result);
    }
}
