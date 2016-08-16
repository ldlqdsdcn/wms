/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core;

import java.text.MessageFormat;

/**
 * @author 刘大磊 2015年3月9日 上午11:46:25
 */
public class MessageTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String message="{0},你好啊";
		int a=1234;
		java.text.DecimalFormat df = new java.text.DecimalFormat("0000000000");
		System.out.println(df.format(66));
	}

}
