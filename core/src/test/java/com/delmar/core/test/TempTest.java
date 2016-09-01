/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                   *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                           *
 *****************************************************************************/
package com.delmar.core.test;

import com.delmar.core.model.Codemapinfo;

/**
 * @author 刘大磊 2014年12月19日 上午10:33:52
 */
public class TempTest<T> {

	/**
	 * 
	 */
	public TempTest(T obj) {
		System.out.println(obj.getClass().getName()); 
	
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Codemapinfo  ci=new Codemapinfo ();
		TempTest tt= new TempTest<>(ci);
		System.out.println();

	}

}
