/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.sys.dao.OperatorDao;
import com.delmar.sys.dao.OperatorModuleDao;
import com.delmar.sys.model.Operator;
import com.delmar.sys.model.OperatorModule;
import com.delmar.sys.service.OperatorService;

/**
 * @author 刘大磊 22015-01-13 09:38:52
 */
@Service("operatorService")
public class OperatorServiceImpl extends CoreServiceImpl<Operator> implements
		OperatorService {
	@Autowired
	private OperatorDao operatorDao;
	@Autowired
	private OperatorModuleDao operatorModuleDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Operator> getCoreDao() {
		return operatorDao;
	}
	/** (non-Javadoc)
	 * @see com.delmar.sys.service.OperatorService#deleteOperators(Integer[])
	 */
	@Override
	public void deleteOperators(Integer[] ids) {
		for(Integer id:ids)
		{
			operatorDao.deleteByPrimaryKey(id);
		}
		
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.OperatorService#saveOperator(com.delmar.sys.model.Operator, java.util.List)
	 */
	@Override
	public void saveOperator(Operator operator, List<Integer> modIds) {
		this.save(operator);
		Map param=new HashMap();
		param.put("operatorId", operator.getId());
	
		operatorModuleDao.deleteByExample(param);
		for(Integer modId:modIds)
		{
			OperatorModule om=new OperatorModule();
			om.setModuleId(modId);
			om.setOperatorId(operator.getId());
			operatorModuleDao.insert(om);
			
		}
		
	}

	
}
