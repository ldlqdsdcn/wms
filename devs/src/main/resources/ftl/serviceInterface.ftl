
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package ${packagename};

import ${modelpackage}.${modelname};
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 ${datetime}
 */
public interface ${modelname}Service extends CoreService<${modelname}> {
	/**
	 * @param ids
	 */
	public void delete${modelname}List(Integer[] ids);
}