/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.cargo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.cargo.dao.ProductionDao;
import com.delmar.cargo.model.Production;
import com.delmar.cargo.service.ProductionService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import com.delmar.cargo.model.ProductionLine;
import com.delmar.cargo.dao.ProductionLineDao;
/**
 * @author 刘大磊 2016-09-13 11:28:19
 */
@Service("productionService")
public class ProductionServiceImpl extends CoreServiceImpl<Production> implements
		ProductionService {
	@Autowired
	private ProductionDao productionDao;
    @Autowired
    private ProductionLineDao productionLineDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	protected CoreDao<Production> getCoreDao() {
		return productionDao;
	}
	public void deleteProductionList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			deleteByPrimaryKey(id);
		}
	}


public Integer saveProduction(Production production,List<ProductionLine> productionLineList) {
	boolean isNew=false;
	if(production.getId()==null||production.getId()==0)
	{
	isNew=true;
	}
	if(!isNew)
	{	
			List<ProductionLine> oldProductionLineList=getProductionLineListByProductionId(production.getId());
    		for(ProductionLine productionLine: productionLineList)
			{
				for(int i=oldProductionLineList.size()-1;i>=0;i--)
				{
					if(oldProductionLineList.get(i).getId().equals(productionLine.getId()))
					{
						oldProductionLineList.remove(i);
					}
				}
			}
			for(ProductionLine productionLine:oldProductionLineList)
			{
				productionLineDao.deleteByPrimaryKey(productionLine.getId());
			}
    }
	Integer id=save(production);
	Date now=new Date();
		for(ProductionLine productionLine: productionLineList)
		{
			productionLine.setProductionId(id);
			productionLineDao.save(productionLine);
		}
	return id;
}


    public Integer deleteByPrimaryKey(Integer id) {
    Map<String,Object> productionLineParam=new HashMap<String,Object>();
productionLineParam.put("productionId",id);
productionLineDao.deleteByExample(productionLineParam);
    return super.deleteByPrimaryKey(id);
    }

	public List<ProductionLine> getProductionLineListByProductionId(Integer productionId)
	{
		Map<String,Object> param=new HashMap<String,Object>();
        param.put("productionId",productionId);
		if(productionId==null)
		{
			return new java.util.ArrayList<ProductionLine>();
		}
		return productionLineDao.selectByExample(param);
	}
}
