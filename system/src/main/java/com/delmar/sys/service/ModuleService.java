
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service;

import java.util.List;

import com.delmar.sys.model.Module;
import com.delmar.sys.model.ModuleJavabean;
import com.delmar.sys.model.ModuleMenu;
import com.delmar.sys.model.ModulePage;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-01-13 09:38:52
 */
public interface ModuleService extends CoreService<Module> {

	/**
	 * @param ids
	 */
	void deleteList(Integer[] ids);

	/**
	 * @param module
	 * @param idList
	 * @return
	 */
	Integer saveModuleJavabean(Module module, List<Integer> idList);

	/**
	 * @param module
	 * @param idList
	 * @return
	 */
	Integer saveModulePage(Module module, List<Integer> idList);

	/**
	 * @param module
	 * @param idList
	 * @return
	 */
	Integer saveModule(Module module, List<Integer> idList);

	/**
	 * @param id
	 * @return
	 */
	List<ModulePage> getModulePages(Integer id);

	/**
	 * @param id
	 * @return
	 */
	List<ModuleMenu> getModuleMenus(Integer id);

	/**
	 * @param id
	 * @return
	 */
	List<ModuleJavabean> getJavabeans(Integer id);

}