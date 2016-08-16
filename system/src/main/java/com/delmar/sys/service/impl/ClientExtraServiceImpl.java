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

import com.delmar.sys.dao.ClientExtraDao;
import com.delmar.sys.model.ClientExtra;
import com.delmar.sys.model.OrgExtra;
import com.delmar.sys.service.ClientExtraService;
import com.delmar.utils.StringUtil;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-08-27 18:04:57
 */
@Service("clientExtraService")
public class ClientExtraServiceImpl extends CoreServiceImpl<ClientExtra> implements
		ClientExtraService {
	@Autowired
	private ClientExtraDao clientExtraDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<ClientExtra> getCoreDao() {
		return clientExtraDao;
	}
	public void deleteClientExtraList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			clientExtraDao.deleteByPrimaryKey(id);
		}
	}
	
	public String getExtraPropValue(String propCode,Integer clientId)
	{
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("accessString", " client_id="+clientId+" and prop_id in (Select id from base_datadict where value='"+StringUtil.fullYhStr(propCode)+")" );
		
		List<ClientExtra> clientextraList=this.selectByExample(param);
		
		StringBuffer extraPropValue=new StringBuffer();
		for (ClientExtra clientExtra:clientextraList)
		{
			extraPropValue.append(clientExtra.getValue()).append(",");	
		}
		
		if (extraPropValue.length()>0)
			extraPropValue.deleteCharAt(extraPropValue.length()-1);
		
		
		return extraPropValue.toString();
	}	
	
}
