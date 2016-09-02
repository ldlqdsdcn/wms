/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.base.dao.mybatis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.base.dao.ChargenameExtraDao;
import com.delmar.base.model.ChargenameExtra;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2015-03-24 11:04:26
 */
@Repository("chargenameExtraDao") 
public class ChargenameExtraDaoMybatis extends CoreDaoMyBatis<ChargenameExtra> implements ChargenameExtraDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.base.mybatis.sql.ChargenameExtraMapper";
	}

	/** (non-Javadoc)
	 */
	public List<ChargenameExtra> selectConformCharenameExtra(BigDecimal grossWeight,
			BigDecimal volume,String extraType) {
		Map<String,Object> param=new HashMap<>();
		if (extraType.equals("FCL"))   //整车价格
		{
		   param.put("accessString", " LimitWeight >= "+grossWeight+" and LimitSize >= "+volume+" and ExtraType='FCL' ");
		}
		else
		{
			//首先获得重货和泡货的比例
			//grossWeight  单位为公斤
			BigDecimal weightSize=grossWeight.divide(volume,4, BigDecimal.ROUND_HALF_UP);
			   param.put("accessString", " ((LimitWeight >= "+grossWeight+" and LimitWeightMin <= "+grossWeight+") "+
			" or (LimitSize >= "+volume+" and LimitSizeMin <= "+volume+"))"+
			   " and WeightSizeMin<="+weightSize+" and WeightSizeMax>="+weightSize+" and ExtraType='LCL' ");			
			
		}
		param.put("orderByClause", "LimitWeight,LimitSize");
		List<ChargenameExtra>  extraList= sqlSessionTemplate.selectList(getSqlName()+".selectSuitTrunk", param);
		if(extraList==null||extraList.size()==0)
		{
			return null;
		}
		return extraList;
	}



}
