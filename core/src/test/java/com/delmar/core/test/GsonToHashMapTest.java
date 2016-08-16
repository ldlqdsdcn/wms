/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.test;

import java.util.HashMap;
import java.util.Map;

import com.delmar.core.model.Table;
import com.google.gson.Gson;

/**
 * @author 刘大磊 2015年1月20日 上午11:39:35
 */
public class GsonToHashMapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Gson gson=new Gson();
		Table tb=new Table();
		tb.setName("ldl");
		tb.setDescr("test");
		String gsonString=gson.toJson(tb);
		System.out.println(gson.toJson(tb));
		
		Map map=gson.fromJson(gsonString, HashMap.class);
		System.out.println(map.get("name"));

	}

}
