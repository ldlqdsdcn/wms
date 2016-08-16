
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.base.service;

import java.util.List;
import java.util.Map;

import com.delmar.base.model.Port;
import com.delmar.base.model.PortTrl;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2014-12-22 13:26:54
 */
public interface PortService extends CoreService<Port> {
	public Integer GetIdByCode(String code) throws Exception;
	public List<Port> selectPortByMode(Map<String,Object> param) ;
	public Port getPortByportcodeAndMode(String portcode,String mode);
	/**
	 * @param ids
	 */
	public void deletePortList(Integer[] ids);
	
	
	public void savePort(Port port,List<PortTrl>trls,List<Integer>modIds);
	
	public boolean validatePortMode(Integer portId,String mode);
	
	public Port selectPortByCityIdAndMode(Integer cityId,String mode);
}