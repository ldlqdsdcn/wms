/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.core.service;

import com.delmar.core.model.Label;
import com.delmar.core.service.CoreService;
import com.delmar.core.model.LabelTrl;
import java.util.List;
/**
 * @author 刘大磊 2016-09-03 23:33:53
 */
public interface LabelService extends CoreService<Label> {
	/**
	 * @param ids
	 */
	public void deleteLabelList(Integer[] ids);
    public List<LabelTrl> getLabelTrlListByLabelId(Integer labelId);

public Integer saveLabel(Label label,List<LabelTrl> labelTrlList);

}