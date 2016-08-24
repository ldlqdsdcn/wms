package com.delmar.utils;

import org.junit.Test;

/**
 * Created by admin on 2016/8/24.
 */
public class StringTest {
    @Test
    public void testFieldtoColumn()
    {
        System.out.println(StringUtil.fieldToProperty("m_inout_id"));
        System.out.println(StringUtil.propertyToField("orgId"));
    }
}
