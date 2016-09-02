
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service;

import java.util.List;

import com.delmar.base.model.PortMode;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2014-12-29 16:12:54
 */
public interface PortModeService extends CoreService<PortMode> {
	public  List<PortMode> getPortModeListByPortId(Integer portId);
}