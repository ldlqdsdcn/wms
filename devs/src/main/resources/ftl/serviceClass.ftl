/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package ${packagename};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${interfacefulldaoname};
import ${modelpackage}.${modelname};
import ${interfacefullservicename};
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 2${datetime}
 */
${serviceName}
public class ${modelname}ServiceImpl extends CoreServiceImpl<${modelname}> implements
		${modelname}Service {
	@Autowired
	private ${modelname}Dao ${repositoryname};
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<${modelname}> getCoreDao() {
		return ${repositoryname};
	}
	public void delete${modelname}List(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			${repositoryname}.deleteByPrimaryKey(id);
		}
	}
	
}
