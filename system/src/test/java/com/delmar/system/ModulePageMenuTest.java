/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.sys.model.ModuleMenu;
import com.delmar.sys.model.PageMenu;
import com.delmar.sys.service.ModuleMenuService;
import com.delmar.sys.service.ModuleService;
import com.delmar.sys.service.PageMenuService;
import com.google.gson.Gson;

/**
 * @author 刘大磊 2015年1月13日 下午1:22:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ModulePageMenuTest {
	@Autowired
	private PageMenuService pageMenuService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private ModuleMenuService moduleMenuService;
	@Test
	public void initPageMenu()
	{
		Gson gson=new Gson();
		Map param=new HashMap();
		param.put("accessString", "  id<12 ");
		List<PageMenu>pageMenuList=pageMenuService.selectByExample(param);
		for(PageMenu pm:pageMenuList)
		{
			System.out.println(gson.toJson(pm));
			ModuleMenu mm=new ModuleMenu();
			mm.setMenuId(pm.getId());
			mm.setModuleId(1);
			moduleMenuService.save(mm);
			
		}
		
	}
	
}
