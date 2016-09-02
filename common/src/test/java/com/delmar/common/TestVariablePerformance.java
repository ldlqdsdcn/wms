/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.common;

/**
 * @author 刘大磊 2015年4月30日 上午8:48:10
 */
public class TestVariablePerformance {
	//java.util.Random ra=new java.util.Random(10);
	
	public long testStringVariable()
	{
		
	 
		return 200+300;
		
	}
	public long testStringVariable2()
	{

		return System.currentTimeMillis();
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestVariablePerformance tp=new TestVariablePerformance();
		long bgn=System.currentTimeMillis();
		System.out.println("bgntime="+bgn);

		for(int i=0;i<2000000000;i++)
		{
				tp.testStringVariable();
		}
		long end=System.currentTimeMillis();
		System.out.println("endtime="+end);
		System.out.println("total:"+(end-bgn));
		/*for(int i=0;i<100000000;i++)
		{
			tp.testStringVariable();
		}
		long end2=System.currentTimeMillis();
		System.out.println("endtime2="+end2);
		System.out.println("total:"+(end2-end));*/
	}

}
