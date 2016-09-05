/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.core.service;

import com.delmar.core.model.LabelTrl;
import com.delmar.core.service.CoreService;
import java.util.List;
/**
 * @author 刘大磊 2016-09-05 14:01:13
 */
public interface LabelTrlService extends CoreService<LabelTrl> {
	/**
	 * @param ids
	 */
	public void deleteLabelTrlList(Integer[] ids);

public Integer saveLabelTrl(LabelTrl labelTrl);

}