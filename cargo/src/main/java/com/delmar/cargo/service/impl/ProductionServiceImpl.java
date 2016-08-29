/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

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
import com.delmar.cargo.model.ProductionLine;
import com.delmar.cargo.dao.ProductionLineDao;
/**
 * @author 刘大磊 2016-08-29 15:01:00
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
	Integer id=save(production);
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
		return productionLineDao.selectByExample(param);
	}
}
