/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.quota.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.delmar.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.enumdef.ContainerType;
import com.delmar.base.enumdef.ModeType;
import com.delmar.base.model.Chargename;
import com.delmar.base.model.City;
import com.delmar.base.model.Currency;
import com.delmar.base.model.Datadict;
import com.delmar.base.model.Nameaccounts;
import com.delmar.base.model.Port;
import com.delmar.base.model.Unit;
import com.delmar.base.service.ChargenameService;
import com.delmar.base.service.CityService;
import com.delmar.base.service.CurrencyService;
import com.delmar.base.service.NameaccountsService;
import com.delmar.base.service.PortService;
import com.delmar.base.service.UnitService;
import com.delmar.quota.service.QuotaService;
import com.delmar.quota.service.busmodel.QuotaBusModel;
import com.delmar.quota.service.busmodel.QuotaBusModelResult;
import com.delmar.quota.service.busmodel.QuotaBusParam;
import com.delmar.rate.model.Ratedetail;
import com.delmar.rate.model.Ratemarkup;
import com.delmar.rate.model.Ratemaster;
import com.delmar.rate.service.RatedetailService;
import com.delmar.rate.service.RatemarkupService;
import com.delmar.rate.service.RatemasterService;
import com.delmar.utils.DmLog;

/**
 * @author 刘大磊 2014年12月25日 下午2:01:45
 */
@Service("landQuotaService")
public class LandQuotaServiceImpl implements QuotaService{
	private DmLog logger =DmLog.getLogger("InfoLogger."+getClass().getName()); 
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private RatemasterService ratemasterService;
	@Autowired
	private RatedetailService ratedetailService;
	@Autowired
	private ChargenameService chargenameService;
	@Autowired
	private PortService portService;
	@Autowired
	private UnitService unitService;
	@Autowired
	private RatemarkupService ratemarkupService;
	@Autowired
	private NameaccountsService nameaccountsService;
	@Autowired
	private CityService cityService;
	/**
	 * 根据港口获得城市
	 * @param portId
	 * @return
	 */
	public City getCityByPolId(Integer portId)
	{
		Port port=portService.selectByPrimaryKey(portId);
		City city=this.cityService.selectByPrimaryKey(port.getCityId());
		return city;
	}
	/**
	 * 根据城市获得港口
	 * @param cityId
	 * @return
	 */
	public Port getPortByCityId(Integer cityId)
	{
		Port port= portService.selectPortByCityIdAndMode(cityId, ModeType.Land.toString());
		if(port==null)
		{
			City city=cityService.selectByPrimaryKey(cityId);
			if(city.getLevelint()>3)
			{
				return getPortByCityId(city.getParentId());
			}
			else
			{
				return null;
			}
		}
		return port;
	}
	private List<Ratemaster> getRatermasterList(Map<String,Object> param)
	{
		//首先按照传递过来的POL POA 进行查询
		List<Ratemaster> ratemasterList=ratemasterService.selectByExample(param);
		
		
		if(ratemasterList==null||ratemasterList.size()==0)
		{
			//按照之前的查询不到
			Integer polId=(Integer)param.get("pol");
			Integer poaId=(Integer)param.get("poa");
			City polcity=getCityByPolId(polId);
			City poacity=getCityByPolId(poaId);
			if(polcity.getLevelint()>3)
			{
				Port polport=getPortByCityId(polcity.getParentId());
				if(polport==null)
				{
					return null;
				}
				param.put("pol", polport.getId());
			}
			else if(poacity.getLevelint()>3)
			{
				Port poaport=getPortByCityId(poacity.getParentId());
				if(poaport==null)
				{
					return null;
				}
				param.put("poa", poaport.getId());
			}
			else
				 return null;
			
			
			return this.getRatermasterList(param);
		}
		return ratemasterList;
	}
	/** (non-Javadoc)
	 * @throws DataNotFondException 
	 * @see com.delmar.quota.service.QuotaService#quote(com.delmar.quota.service.busmodel.QuotaBusParam)
	 * 
	 * 1.	所需求的吨车
	 *	2.	数量
	 *	3.	货类
	 *	4.	始发地
	 *	5.	目的地
	 *
	 */
	public QuotaBusModelResult quote(QuotaBusParam param)
			throws QuotaParamException, DataNotFondException {

		QuotaBusModelResult qbmr=new QuotaBusModelResult();
		//根据 始发港 目的港 获得 报价单
		StringBuilder sb=new StringBuilder();
		  if(!portService.validatePortMode(param.getPolPort().getId(), ModeType.Land.toString()))
			{
		    	sb.append("发货港输入不正确");
			}
		    if(!portService.validatePortMode(param.getPoaPort().getId(), ModeType.Land.toString()))
		    {
		    	sb.append("目的港输入不正确");
		    }
	    if(StringUtil.isNotEmpty(sb.toString()))
	    {
	    
	    		throw new QuotaParamException(sb.toString());
	    
	    }
	    Map<String,Object> modelSearch=new HashMap<String,Object>();
    	modelSearch.put("pol", param.getPolPort().getId());
    	modelSearch.put("poa", param.getPoaPort().getId());
    	modelSearch.put("mode",ModeType.Land.toString());
    	modelSearch.put("clientId", param.getProvider().getId());
    	modelSearch.put("accessString", " flag =0  and isActive=1 ");
		List<Ratemaster> ratemasterList=getRatermasterList(modelSearch);
		if(ratemasterList==null||ratemasterList.size()==0)
		{
			throw new DataNotFondException("找不到报价单");
		}
		
				//ratemasterService.selectByExample(modelSearch);
		//取最低报价报给客户
    	TotalQuota minTotalQuota=null;
    	
    	for(Ratemaster rm:ratemasterList)
    	{
    		try
    		{
    			TotalQuota totalQuota=caculateQuota(rm,param);	
    			//设置备注
    			String remark="运输天数:"+String.valueOf(rm.getTranstime())+"  "+rm.getRemark();
    			totalQuota.setRemark(remark);    			
    			if(minTotalQuota==null)
        		{
        			minTotalQuota=totalQuota;
        		}
        		else
        		{
        			if(minTotalQuota.getTotalPrice().compareTo(totalQuota.getTotalPrice())<0)
        			{
        				minTotalQuota=totalQuota;
        			}
        		}
    		}
    		catch(DataNotFondException e)
    		{
    			logger.error(e);
    		}
    		
    		
    	
    	}
	    if(minTotalQuota==null)
	    {
	    	throw	new DataNotFondException("找不到报价");
	    }
	    qbmr.setMasterId(minTotalQuota.getMasterrateId());	    
    	qbmr.setQuotaList(minTotalQuota.getQuotaList());
    	qbmr.setRemark(minTotalQuota.getRemark());
		return qbmr;
		

	}
	/**
	 * 获取报价单总价格
	 * @param master
	 * @return
	 * @throws DataNotFondException 
	 */
	private TotalQuota caculateQuota(Ratemaster master,QuotaBusParam param)throws QuotaParamException, DataNotFondException 
	{
			
		//step 01 取单价
		Ratedetail freightdetail=null;
		List<Ratedetail> detailList=null;
		List<QuotaBusModel> quotaList=new ArrayList<QuotaBusModel>();	
		HashMap currMap=new HashMap();
		HashMap currCurrMap=new HashMap();		
		
		
		BigDecimal totalPrice=new BigDecimal(0);
		if(param.getVolume()==null)
		{
			throw new QuotaParamException("体积不允许为空");
		}
		if(param.getWeight()==null)
		{
			throw new QuotaParamException("重量不允许为空");
		}
		/*Map<String,Object> chargenameparam=new HashMap<String,Object>();
    	chargenameparam.put("code", param.getTruckType());
    	Chargename chargename=chargenameService.getByExample(chargenameparam);*/
    	/**
    	 * 卡车类型根据 crossweight 和 volume 计算出 卡车类型
    	 */
		
		//整车价格
		Chargename chargename=null;
		if(ContainerType.FCL.toString().equals(param.getFcllcl()))
	    	chargename=chargenameService.selectConformTruck(param.getWeight(), param.getVolume(),"FCL");
		else
    		chargename=chargenameService.selectConformTruck(param.getWeight(), param.getVolume(),"LCL");  //		//拼箱价格	
		
    	if(chargename==null)
	    {
    		throw new QuotaParamException("根据重量体积找不到对应的报价明细");
    		//基本运费
    	 }
    	
    	
    	
    	
    	//此处只找到相应的运价条目， 根据运价条目是否能够找到，得到报价是否能够找到
    	//但后续计算是要加上运费附加费的，即FreightType=1
        Map<String,Object>modelSearch=new HashMap<String,Object>();
	   	    modelSearch.put("baseChargenameId", chargename.getId());
	   	    modelSearch.put("rRateMasterId", master.getId());
	   	    modelSearch.put("freighttype", Datadict.FREIGHTTYPE_FREIGHT);
	   	    modelSearch.put("accessString", " flag = 0 and CONVERT(char(19), getdate(), 120) >=CONVERT(char(19), effectbgndate, 120) and  CONVERT(char(19), effectenddate, 120) >= CONVERT(char(19), getdate(), 120)  ");
	      	freightdetail= ratedetailService.getByExample(modelSearch);
	 	    if(freightdetail==null)
	 	    {
	 		    throw new DataNotFondException("找不到报价");   	 		 
	 	    }

	      modelSearch=new HashMap<String,Object>();
	      modelSearch.put("rRateMasterId", master.getId());
	      //运费附加费
	      modelSearch.put("freighttype", Datadict.FREIGHTTYPE_ADDITIONAL);		
	      modelSearch.put("accessString", " flag = 0 and CONVERT(char(19), getdate(), 120) >=CONVERT(char(19), effectbgndate, 120) and  CONVERT(char(19), effectenddate, 120) >= CONVERT(char(19), getdate(), 120)  ");
	      detailList= ratedetailService.selectByExample(modelSearch);   
	   	  detailList.add(freightdetail);

		for (int i=0;i<detailList.size();i++)
		{
			Ratedetail objDetail=(Ratedetail)detailList.get(i);
			BigDecimal price=null;
			BigDecimal unitNumber=null;
			//计算出金额
			//需要区分整箱和拼箱，如果是拼箱是需要计算计费重的

			//判断是运费还是运费附加费
			if (Datadict.FREIGHTTYPE_FREIGHT==objDetail.getFreighttype().intValue())
			{
			   //计算计费重	
				if(ContainerType.FCL.toString().equals(param.getFcllcl()))
					unitNumber=new BigDecimal(1);
				else 
				{
					
					unitNumber=getUnitNumber(param,objDetail.getBaseUnitId());
				}
				   
				
			}
			else  //运费附加费
			{
				/*
				 * PerContainer		PerShipment  PerCustom  PerGW  PerVolume  PerCHW  Per20  Per40
 				   Per40HC  Per45
				 * */
				unitNumber=getUnitNumber(param,objDetail.getBaseUnitId());
			}
			
			//当前的价格
			price=objDetail.getPrice().multiply(unitNumber);
			//把最大值，最小值保存进去
			price=ratedetailService.caculatePrice(price, objDetail.getMinvalue(), objDetail.getMaxvalue());
			//如果是浮动的
			if(Datadict.BEFIXED_FLOAT.equals(objDetail.getBefixed()))
			{
				//下面找到相应的浮动规则
				Map<String,Object> nameaccountsparam=new HashMap<String,Object>();
				nameaccountsparam.put("code", param.getUser().getUsername());
				Nameaccounts nameaccounts=nameaccountsService.getByExample(nameaccountsparam);
				Ratemarkup ratemarkup=ratemarkupService.getRatemarkup(nameaccounts.getId(), master.getId(), objDetail.getBaseChargenameId(),objDetail.getFreighttype());
				if (ratemarkup!=null)
				{
				   price=getMarkupPrice(price,ratemarkup);	
				}
		 	 }
			
		   	Currency currency= currencyService.selectByPrimaryKey(objDetail.getBaseCurrencyId());
			if (currMap.containsKey(currency.getCode()))
			{
				BigDecimal currPrice=(BigDecimal)currMap.get(currency.getCode());
				currPrice=currPrice.add(price);
				currMap.put(currency.getCode(), currPrice);
				
				totalPrice=totalPrice.add(price.multiply(currency.getRate()));
			}
			else
			{
				currMap.put(currency.getCode(), price);
				currCurrMap.put(currency.getCode(), currency);
				totalPrice=totalPrice.add(price.multiply(currency.getRate()));				
			}
		}
		
		
		Iterator currIt=currMap.keySet().iterator();
		while (currIt.hasNext())
		{
		  String keyStr=(String)currIt.next();
		  BigDecimal keyValue=(BigDecimal)currMap.get(keyStr);
		  Currency currencyValue=(Currency)currCurrMap.get(keyStr);
		  
		  QuotaBusModel qbm=new QuotaBusModel();
		  qbm.setCurrency(keyStr);
		  qbm.setCurr(currencyValue);
		  qbm.setPrice(keyValue);
		  quotaList.add(qbm);
		}
			
	   	TotalQuota tq=new TotalQuota(master.getId(),quotaList);
		return tq;	
		
		
	}
	
	
	private BigDecimal getMarkupPrice(BigDecimal price,Ratemarkup ratemarkup)
	{
		if(ratemarkup.getMarkuptype()==0)
		{
			return price.multiply(new BigDecimal(1).add(ratemarkup.getMarkupamount()));
		}
		else
		{
			return price.add(ratemarkup.getMarkupamount());
		}
	}
	
	
	
	private BigDecimal getUnitNumber(QuotaBusParam param,Integer unitId)
	{
		/*
		 * PerShipment PerCustom PerGW PerVolume PerCHW 
		 * */
    	Unit objUnit=unitService.selectByPrimaryKey(unitId);
    	if (objUnit==null)
    		return new BigDecimal(0);
		
		String unitType=objUnit.getCode();
		
		if (unitType.equalsIgnoreCase("PerShipment"))
		{
			return new BigDecimal(1);
		}		
		else if (unitType.equalsIgnoreCase("PerCar"))
		{
			return new BigDecimal(param.getQuantity());
		}
		else if (unitType.equalsIgnoreCase("PerGW"))
		{
			return param.getWeight();
		}
		else if (unitType.equalsIgnoreCase("PerVolume"))
		{
			return param.getVolume();
		}else if (unitType.equalsIgnoreCase("PerWeight"))
		{
			return param.getWeight().divide(new BigDecimal(1000));   //转换为吨
		}else if (unitType.equalsIgnoreCase("PerSize"))
		{
			return param.getVolume();
		}
		
		else
			return new BigDecimal(0);
			
		
			
	}

	



}
