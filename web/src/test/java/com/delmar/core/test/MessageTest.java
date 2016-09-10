package com.delmar.core.test;

import org.junit.Test;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by 刘大磊 on 2016/9/10 11:00.
 */

public class MessageTest {

    @Test
    public void testI18NMessage()
    {
        ResourceBundle rb=ResourceBundle.getBundle("ApplicationMessages", Locale.getDefault());
        Enumeration<String> enumeration=rb.getKeys();
        while (enumeration.hasMoreElements())
        {
            String key=enumeration.nextElement();
            System.out.println(key+"  --- "+rb.getString(key));

        }
    }
}
