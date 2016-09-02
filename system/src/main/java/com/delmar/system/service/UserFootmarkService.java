
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01											  *
 *	作者：刘大磊								                              *
 * 电话：0532-66701118                                                        *
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.system.service;

import com.delmar.system.model.UserFootmark;
import com.delmar.core.service.CoreService;
import java.util.List;
/**
 * @author 刘大磊 2016-09-02 10:18:25
 */
public interface UserFootmarkService extends CoreService<UserFootmark> {
	/**
	 * @param ids
	 */
	public void deleteUserFootmarkList(Integer[] ids);

public Integer saveUserFootmark(UserFootmark userFootmark);

}