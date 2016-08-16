/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.base.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.CityTrl;
import com.delmar.base.service.CityService;
import com.delmar.base.service.CityTrlService;
import com.delmar.core.web.action.CoreSearchAction;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;

/**
 * @author 刘大磊 2015年2月11日 上午10:25:48
 */
public class CitySearchAction extends CoreSearchAction{
	@Autowired
	private CityService cityService;
	@Autowired
	private CityTrlService cityTrlService;
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreSearchAction#search()
	 */
	@Override
	public List search() {
		if(value==null)
			value="";
		Map param=new HashMap();
		System.out.println("value="+value);
		UserResource ur=(UserResource)FacesUtils.getSession().getAttribute("resource");
		param.put("accessString", "language='"+ur.getLocale().toString()+"' or name like '%"+value.trim()+"%'");
		List<CityTrl> list=cityTrlService.selectByExample(param);
		return list;
	}

}
