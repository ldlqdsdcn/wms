/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.sys.dao.UserExtraDao;
import com.delmar.sys.model.ClientExtra;
import com.delmar.sys.model.UserExtra;
import com.delmar.sys.service.UserExtraService;
import com.delmar.utils.StringUtil;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-08-27 18:03:14
 */
@Service("userExtraService")
public class UserExtraServiceImpl extends CoreServiceImpl<UserExtra> implements
		UserExtraService {
	@Autowired
	private UserExtraDao userExtraDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<UserExtra> getCoreDao() {
		return userExtraDao;
	}
	public void deleteUserExtraList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			userExtraDao.deleteByPrimaryKey(id);
		}
	}
	
	public String getExtraPropValue(String propCode,Integer userId)
	{
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("accessString", " user_id="+userId+" and prop_id in (Select id from base_datadict where value='"+StringUtil.fullYhStr(propCode)+")" );
		
		List<UserExtra> userextraList=this.selectByExample(param);
		
		StringBuffer extraPropValue=new StringBuffer();
		for (UserExtra userExtra:userextraList)
		{
			extraPropValue.append(userExtra.getValue()).append(",");	
		}
		
		if (extraPropValue.length()>0)
			extraPropValue.deleteCharAt(extraPropValue.length()-1);
		
		
		return extraPropValue.toString();
	}		
	
}
