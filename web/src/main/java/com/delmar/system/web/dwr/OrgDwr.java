/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.system.web.dwr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.core.web.dwr.DwrPrivilegeDataFilter;
import com.delmar.core.web.dwr.DwrPrivilegeFilter;
import com.delmar.sys.model.Client;
import com.delmar.sys.model.Org;
import com.delmar.sys.service.ClientService;
import com.delmar.sys.service.OrgService;
import com.delmar.utils.StringUtil;

/**
 * @author 刘大磊 2015年1月15日 下午5:17:39
 */
@Repository("orgDwr") 
public class OrgDwr {
	@Autowired
	private OrgService orgService;
	@Autowired
	private ClientService clientService;
	public Org[] getOrgs(Integer superDepaId)
	{
		
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		Map map=new HashMap();
		if(superDepaId==null)
		{
			map.put("accessString"," parent_org_id is null ");
			//map.put("accessString", );
		}
		else
		{
			map.put("accessString"," parent_org_id = "+superDepaId);
		}
		
		
		List<Org> orgList=orgService.selectByExample(map);
		//System.out.println(menuList.size());
		Org[] menus=new Org[orgList.size()];
		for(int i=0;i<orgList.size();i++)
		{
			
			menus[i]=orgList.get(i);
			Client client=clientService.selectByPrimaryKey(menus[i].getSysClientId());
			menus[i].setClient(client);
			if(menus[i].getParentOrgId()!=null&&menus[i].getParentOrgId()!=0)
			{
			Org pOrg=orgService.selectByPrimaryKey(menus[i].getParentOrgId());
			menus[i].setParentOrg(pOrg);
			}
			
		}
		return menus;
	}
  	public Org getOrg(Integer orgId, Integer superOrgId)
	{
  		
  		
  		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
  		Org org=new Org();
		if(orgId==null||orgId==0)
		{
			if(superOrgId!=null)
			{
				Org parentOrg=(Org)orgService.selectByPrimaryKey(superOrgId);
				org.setParentOrg(parentOrg);
				org.setParentOrgId(superOrgId);
				org.setOrgLevel(parentOrg.getOrgLevel()+1);
			}
			else
			{
				org.setOrgLevel(Org.ORG_LEVEL_1);
			}
		}
		else
		{
			org=orgService.selectByPrimaryKey(orgId);
		}
		return org;
	}
	
	/**
	 * 保存菜单信息
	 * @param pageMenu
	 * @return
	 */
	public Org saveOrg(Org org)
	{
		
		if(!DwrPrivilegeFilter.isCreate(this.getClass().getName()))
		{
			if(org.getId()==null)
			return null;
		}
		if(!DwrPrivilegeFilter.isUpdate(this.getClass().getName()))
		{
			if(org.getId()!=null)
			return null;
		}
		orgService.save(org);
		
		return org;
		
	}
	public String removeOrg(Integer orgId)
	{
		if(!DwrPrivilegeFilter.isDelete(this.getClass().getName()))
		{
			return null;
		}
		Org temp=orgService.selectByPrimaryKey(orgId);
		if(temp.getOrgLevel()<4)
		{
		
			Map param=new HashMap();
			//map.setSuperDepaId(menuId);
			param.put("parentOrgId", orgId);
			Integer count=orgService.countObjects(param);
			if(count>0)
			{
				return "N";
				
			}
			
		}
		orgService.deleteByPrimaryKey(orgId);
		return "Y";
	}
	public Org[] getOrgList(String orgName)
	{
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		
		Map map=new HashMap();
		map.put("accessString","name like '%"+StringUtil.fullYhStr(orgName.trim())+
				"%' and org_id in ("+DwrPrivilegeDataFilter.getInstance().getOrgListStr()+')');
		
	
		List<Org> orgList=orgService.selectByExample(map);
	
		if(orgList!=null&&orgList.size()>0)
		{
			Org[] orgArray=new Org[orgList.size()];
			orgList.toArray(orgArray);
			return orgArray;
		}
		else
		{
			return null;
		}
		
	}
	
	
		
}
