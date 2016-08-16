package com.delmar.crm.web.model;

import java.math.BigDecimal;
import java.util.ArrayList;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年7月2日 下午11:28:15 
 * 类说明 
 */
public class ObjStastics {
	
	private ArrayList groupList;
	private Integer count;
	private BigDecimal profit;
	private BigDecimal oceanTeu;
	private BigDecimal airVolume;
	
	
	
	public ObjStastics()
	{
		groupList=new ArrayList();
		profit=new BigDecimal(0);
		oceanTeu=new BigDecimal(0);
		airVolume=new BigDecimal(0);
	}

	public ArrayList getGroupList() {
		return groupList;
	}

	public void setGroupList(ArrayList groupList) {
		this.groupList = groupList;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public void addCount()
	{
		this.count++;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	
	public void addProfit(BigDecimal profitValue)
	{
		if (profitValue!=null)		
		this.profit=this.profit.add(profitValue);
	}

	public BigDecimal getOceanTeu() {
		return oceanTeu;
	}

	public void setOceanTeu(BigDecimal oceanTeu) {
		this.oceanTeu = oceanTeu;
	}
	
	public void addOceanTeu(BigDecimal oceanTeuValue)
	{
		if (oceanTeuValue!=null)
		this.oceanTeu=this.oceanTeu.add(oceanTeuValue);
	}

	public BigDecimal getAirVolume() {
		return airVolume;
	}

	public void setAirVolume(BigDecimal airVolume) {
		if (airVolume==null)
			airVolume=new BigDecimal(0);
		this.airVolume = airVolume;
	}
	
	public void addAirVolume(BigDecimal airVolumeValue)
	{
		if (airVolumeValue!=null)
		  this.airVolume=this.airVolume.add(airVolumeValue);
	}	
	
	
	
	

}
