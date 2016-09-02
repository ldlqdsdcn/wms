/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.crm.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.crm.dao.LinkmanDao;
import com.delmar.crm.model.Linkman;

/**
 * @author 刘大磊 2015-03-04 16:06:05
 */
@Repository("linkmanDao") 
public class LinkmanDaoMybatis extends CoreDaoMyBatis<Linkman> implements LinkmanDao {



	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.crm.mybatis.sql.LinkmanMapper";
	}

/* (non-Javadoc)
 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#save(java.lang.Object)
 */
@Override
public Integer save(Linkman model)  {

	if(model.isnew()&&model.getLinkCode().equals(""))
	{
		String linkcode=this.sqlSessionTemplate.selectOne( getSqlName()+".selectMaxlinkCode");
		if(linkcode==null)
		{
			linkcode="0";
		}
		linkcode=linkcode.replace("Link", "");
		int value=Integer.parseInt(linkcode);
		java.text.DecimalFormat df = new java.text.DecimalFormat("0000000000");
		model.setLinkCode("Link"+df.format(value+1));
	}
	return super.save(model);
}
public String getPersonCode(Integer id)
{
	StringBuffer sb=new StringBuffer();
	//if(id/10)
	return sb.toString();
}

public void updateTimesData(Integer id)
{
	this.sqlSessionTemplate.selectList(this.getSqlName()+".updateTimesData", id);
}

public String getNamebyId(Integer id)
{
	return sqlSessionTemplate.selectOne(this.getSqlName()+".selectNameById",id);
}

public String getMaxId()
{
	return sqlSessionTemplate.selectOne(this.getSqlName()+".selectMaxId");
}


}
