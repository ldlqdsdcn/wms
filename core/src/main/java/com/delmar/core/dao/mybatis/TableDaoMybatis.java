/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.core.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.TableDao;
import com.delmar.core.model.Table;

/**
 * @author 刘大磊 2015-01-09 10:48:25
 */
@Repository("tableDao") 
public class TableDaoMybatis extends CoreDaoMyBatis<Table> implements TableDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.core.mybatis.sql.TableMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.TableDao#getTablebyClassName(java.lang.String)
	 */
	public Table getTablebyClassName(String className) {
		Map<String,String> param=new HashMap<String,String>();
		param.put("className", className);
		List<Table>  tableList=this.selectByExample(param);
		if(tableList!=null&&tableList.size()>1)
		{
			return tableList.get(0);
		}
		return null;
	}



}
