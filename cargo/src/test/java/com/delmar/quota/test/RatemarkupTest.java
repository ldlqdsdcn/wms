/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.quota.test;

/**
 * @author 刘大磊 2014年12月25日 下午3:55:29
 */
public class RatemarkupTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			
		int[] a1={-1,100};
		int[] a2={-1,30};
		int [] a3={-1,40};
		int [] a4={-1,222};
		int count=0;
		for(int ai1:a1)
		{
			for(int ai2:a2)
			{
				for(int ai3:a3)
				{
					for(int ai4:a4)
					{
						System.out.println(ai1+" "+ai2+" "+ai3+" "+ai4);
						count ++;
					}
				}
			}
		}
		
System.out.println("count="+count);
	}

}
