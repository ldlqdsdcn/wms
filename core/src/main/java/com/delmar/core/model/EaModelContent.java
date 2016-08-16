/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.model;

import java.util.List;
import java.util.Map;

/**
 * @author 刘大磊 2015年1月12日 下午2:14:06
 */
public class EaModelContent {
	//public static List<Table> logTableList;
	public static Map<String,Table> logTableMap;
	/**
	 * 日志 数据类型
	 */
	public static final int TYPE_NUMBER=1;
	public static final int TYPE_CHAR=2;
	public static final int TYPE_DATE=3;
	/**
	 * 日志 操作类型
	 */
	public static final String OPERATE_INSERT="I";
	public static final String OPERATE_UPDATE="U";
	public static final String OPERATE_DELETE="D";
	
	
}
