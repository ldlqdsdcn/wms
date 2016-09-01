package com.delmar.utils;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by 刘大磊 on 2016/9/1.
 */
public class Md5Test {
    /**
     * 888888 md5值
     */
    private String encryptedPassword="21218cca77804d2ba1922c33e0151105";
    @Test
    public void testMd5GenerateString()
    {
        MD5 md5=new MD5();
        Assert.assertEquals(encryptedPassword,md5.getMD5ofStr("888888"));
    }

}
