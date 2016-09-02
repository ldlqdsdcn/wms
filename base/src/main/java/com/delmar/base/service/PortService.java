
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
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
	Integer GetIdByCode(String code);
	List<Port> selectPortByMode(Map<String, Object> param) ;
	Port getPortByportcodeAndMode(String portcode, String mode);
	/**
	 * @param ids
	 */
	void deletePortList(Integer[] ids);
	
	
	void savePort(Port port, List<PortTrl> trls, List<Integer> modIds);
	
	boolean validatePortMode(Integer portId, String mode);
	
	Port selectPortByCityIdAndMode(Integer cityId, String mode);
}