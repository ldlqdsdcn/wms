/******************************************************************************
 * 版权所有 刘大磊 2013-07-01										      *
 *	作者：刘大磊								                              *
 * 电话：13336390671                                                        *
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/
package com.delmar.cargo.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.web.action.CoreEditPrivAction;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import org.springframework.beans.factory.annotation.Autowired;
import com.delmar.cargo.model.Production;
import com.delmar.cargo.service.ProductionService;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import com.delmar.cargo.model.ProductionLine;
/**
 * @author 刘大磊 2016-08-29 15:01:00
 */
@Validations(requiredStrings = {@RequiredStringValidator(type = ValidatorType.FIELD,
trim=true, fieldName = "production.documentno", message = "不允许为空") ,@RequiredStringValidator(type = ValidatorType.FIELD,
trim=true, fieldName = "production.name", message = "不允许为空") ,@RequiredStringValidator(type = ValidatorType.FIELD,
trim=true, fieldName = "production.status", message = "不允许为空") },requiredFields = {@RequiredFieldValidator(type =ValidatorType.FIELD,fieldName = "production.completeDate",message = "不允许为空"),@RequiredFieldValidator(type =ValidatorType.FIELD,fieldName = "production.orgId",message = "不允许为空"),@RequiredFieldValidator(type =ValidatorType.FIELD,fieldName = "production.clientId",message = "不允许为空"),@RequiredFieldValidator(type =ValidatorType.FIELD,fieldName = "production.userId",message = "不允许为空")})
public class ProductionAction extends CoreEditPrivAction {
	private Production  production;
	private List<ProductionLine> productionLineList=new ArrayList<ProductionLine>();;
	@Autowired
	private ProductionService productionService;
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "production";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		productionService.deleteByPrimaryKey(production.getId());
		return list();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		productionService.deleteProductionList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return production.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		 production= productionService.selectByPrimaryKey(id);
		productionLineList=productionService.getProductionLineListByProductionId(id);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		return productionService.selectByExample(null);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		production=new Production();
		productionLineList=new ArrayList<ProductionLine>();
	}
    @SkipValidation
    public String addProductionLine()
    {
    ProductionLine  productionLine=new ProductionLine();
	productionLineList.add(productionLine);
    return "edit";
    }
    @SkipValidation
    public String deleteProductionLines()
    {
    String[] ids=ServletActionContext.getRequest().getParameterValues("ProductionLine_ids");
    List<String> idList=new ArrayList<String>();

        //
        Integer[] intids=new Integer[ids.length];

        for(int i=0;i<ids.length;i++)
        {
        idList.add(ids[i]);
        Integer index=Integer.parseInt(ids[i]);
	   ProductionLine column=productionLineList.get(index);
        if(column.getId()!=null&&column.getId()!=0)
        {
        intids[i]=column.getId();
        }
        }
        java.util.Collections.sort(idList);
        for(int i=idList.size()-1;i>=0;i--)
        {
        	productionLineList.remove(Integer.parseInt(idList.get(i)));
        }
        return "edit";
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
Date date=new Date();
Integer currentUserId=getCurrentUser();
if(production.isnew())
{
production.setCreated(date);
production.setCreatedby(currentUserId);
}
production.setUpdated(date);
production.setUpdatedby(currentUserId);
		productionService.saveProduction(production,productionLineList);
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public Production getProduction() {
		return production;
	}

	/**
	 * @param production the production to set
	 */
	public void setProduction(Production production) {
		this.production = production;
	}
public List<ProductionLine> getProductionLineList()
{
	return productionLineList;
}
public void setProductionLineList(List<ProductionLine> productionLineList)
{
	this.productionLineList=productionLineList;
}
}
