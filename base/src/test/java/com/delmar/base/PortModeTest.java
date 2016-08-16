/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.base;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.base.model.Port;
import com.delmar.base.model.PortMode;
import com.delmar.base.service.PortModeService;
import com.delmar.base.service.PortService;

/**
 * @author 刘大磊 2014年12月29日 下午4:16:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PortModeTest {
	@Autowired
	private PortModeService portModeService;
	@Autowired
	private PortService portService;
	@Test
	public void testSave()
	{
		/*String ocean="Ocean";
		String air="Air";
		String oceanc="海运";
		String airc="空运";
		List<Port>portModeList=portService.selectByExample(null);
		boolean modify=false;
		portModeService.deleteByExample(null);
		for(Port port:portModeList)
		{
			
			modify=false;
			if(port.getPlaneocean()!=null)
			{
				if(port.getPlaneocean().indexOf(ocean)!=-1)
				{
					PortMode pm=new PortMode();
					pm.setBasePortId(port.getId());
					pm.setMode(ocean);
					portModeService.insert(pm);
					modify=true;
				}
				if(port.getPlaneocean().indexOf(air)!=-1)
				{
					PortMode pm=new PortMode();
					pm.setBasePortId(port.getId());
					pm.setMode(air);
					portModeService.insert(pm);
					modify=true;
				}
				if(port.getPlaneocean().indexOf(oceanc)!=-1)
				{
					PortMode pm=new PortMode();
					pm.setBasePortId(port.getId());
					pm.setMode(ocean);
					portModeService.insert(pm);
					modify=true;
				}
				if(port.getPlaneocean().indexOf(airc)!=-1)
				{
					PortMode pm=new PortMode();
					pm.setBasePortId(port.getId());
					pm.setMode(air);
					portModeService.insert(pm);
					modify=true;
				}
				if(modify)
				{
					port.setRemark("comp");
					portService.save(port);
				}
			}
		}*/
	}
}
