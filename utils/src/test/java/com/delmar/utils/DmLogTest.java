/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.utils;

import org.junit.Test;


/**
 * @author 刘大磊 2015年7月21日 上午11:54:23
 */

public class DmLogTest {

	
	@Test
	public void testLog()
	{
		DmLog dmLog=DmLog.getLogger("DmLogTest");
		dmLog.debug("刘大磊 test");
		DmLog dmLog2=DmLog.getLogger(DmLogTest.class);
		dmLog2.debug("刘大磊 test");
		dmLog2.info("ldl test");
		dmLog2.warn("test");
		dmLog2.error("~~~~~~~~~~~~~~~");
	}
}
