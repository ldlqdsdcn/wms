/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/package com.delmar.cargo.service;
import com.delmar.cargo.model.Production;
import com.delmar.core.service.CoreService;
import com.delmar.cargo.model.ProductionLine;
import java.util.List;
/**
 * @author 刘大磊 2016-09-13 11:28:19
 */
public interface ProductionService extends CoreService<Production> {
	void deleteProductionList(Integer[] ids);
	List<ProductionLine> getProductionLineListByProductionId(Integer productionId);
	Integer saveProduction(Production production,List<ProductionLine> productionLineList);
}