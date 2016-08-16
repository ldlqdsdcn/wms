/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.base.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.base.dao.PortModeDao;
import com.delmar.base.model.PortMode;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2014-12-29 16:12:54
 */
@Repository("portModeDao") 
public class PortModeDaoMybatis extends CoreDaoMyBatis<PortMode> implements PortModeDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.base.mybatis.sql.PortModeMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.base.dao.PortModeDao#getPortModeListByPortId(java.lang.Integer)
	 */
	public List<PortMode> getPortModeListByPortId(Integer portId) {
		Map param=new HashMap();
		param.put("basePortId", portId);
		return this.selectByExample(param);
	}



}
