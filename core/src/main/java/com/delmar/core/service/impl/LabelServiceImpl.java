/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.LabelDao;
import com.delmar.core.model.Label;
import com.delmar.core.service.LabelService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import com.delmar.core.model.LabelTrl;
import com.delmar.core.dao.LabelTrlDao;
/**
 * @author 刘大磊 2016-09-10 10:28:27
 */
@Service("labelService")
public class LabelServiceImpl extends CoreServiceImpl<Label> implements
		LabelService {
	@Autowired
	private LabelDao labelDao;
    @Autowired
    private LabelTrlDao labelTrlDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	protected CoreDao<Label> getCoreDao() {
		return labelDao;
	}
	public void deleteLabelList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			deleteByPrimaryKey(id);
		}
	}


public Integer saveLabel(Label label,List<LabelTrl> labelTrlList) {
	Integer id=save(label);
	Date now=new Date();
		for(LabelTrl labelTrl: labelTrlList)
		{
			labelTrl.setLabelId(id);
			labelTrlDao.save(labelTrl);
		}
	return id;
}


    public Integer deleteByPrimaryKey(Integer id) {
    Map<String,Object> labelTrlParam=new HashMap<String,Object>();
labelTrlParam.put("labelId",id);
labelTrlDao.deleteByExample(labelTrlParam);
    return super.deleteByPrimaryKey(id);
    }

	public List<LabelTrl> getLabelTrlListByLabelId(Integer labelId)
	{
		Map<String,Object> param=new HashMap<String,Object>();
        param.put("labelId",labelId);
		if(labelId==null)
		{
			return new java.util.ArrayList<LabelTrl>();
		}
		return labelTrlDao.selectByExample(param);
	}
}
