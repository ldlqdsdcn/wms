/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.sys.dao.OrgExtraDao;
import com.delmar.sys.model.OrgExtra;
import com.delmar.sys.service.OrgExtraService;
import com.delmar.utils.StringUtil;

/**
 * @author 刘大磊 22015-08-27 18:05:33
 */
@Service("orgExtraService")
public class OrgExtraServiceImpl extends CoreServiceImpl<OrgExtra> implements
		OrgExtraService {
	@Autowired
	private OrgExtraDao orgExtraDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<OrgExtra> getCoreDao() {
		return orgExtraDao;
	}
	public void deleteOrgExtraList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			orgExtraDao.deleteByPrimaryKey(id);
		}
	}
	
	
	public String getExtraPropValue(String propCode,Integer orgId)
	{
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("accessString", " org_id="+orgId+" and prop_id in (Select id from base_datadict where value='"+StringUtil.fullYhStr(propCode)+")" );
		
		List<OrgExtra> orgextraList=this.selectByExample(param);
		
		StringBuffer extraPropValue=new StringBuffer();
		for (OrgExtra orgExtra:orgextraList)
		{
			extraPropValue.append(orgExtra.getValue()).append(",");	
		}
		
		if (extraPropValue.length()>0)
			extraPropValue.deleteCharAt(extraPropValue.length()-1);
		
		
		return extraPropValue.toString();
	}
	
}
