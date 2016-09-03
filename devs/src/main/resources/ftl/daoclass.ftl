<#include "inc/class_header.ftl"/>
package ${packagename};

import org.springframework.stereotype.Repository;

import ${interfacefullname};
import ${modelpackage}.${modelname};
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 ${datetime}
 */
@Repository("${repositoryname}") 
public class ${modelname}DaoMybatis extends CoreDaoMyBatis<${modelname}> implements ${modelname}Dao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "${mappername}";
	}



}
