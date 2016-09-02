
/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.service;

import java.util.List;

import com.delmar.core.service.CoreService;
import com.delmar.sys.model.Operator;

/**
 * @author 刘大磊 2015-01-13 09:38:52
 */
public interface OperatorService extends CoreService<Operator> {

	/**
	 * @param ids
	 */
	void deleteOperators(Integer[] ids);
	
	public void saveOperator(Operator operator,List<Integer> modIds);

}