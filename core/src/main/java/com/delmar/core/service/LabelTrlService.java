/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.core.service;

import com.delmar.core.model.LabelTrl;
/**
 * @author 刘大磊 2016-09-03 23:33:53
 */
public interface LabelTrlService extends CoreService<LabelTrl> {
	/**
	 * @param ids
	 */
	void deleteLabelTrlList(Integer[] ids);

	Integer saveLabelTrl(LabelTrl labelTrl);

}