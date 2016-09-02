/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.rate.busmodel;

import java.math.BigDecimal;

/**
 * @author 刘大磊 2014年12月25日 上午9:25:51
 */
public class KgVolume {
	public static final int UNIT_KG=1;
	public static final int UNIT_VO=2;
	private KgVolume()
	{
		
	}
	public static KgVolume buildKgVolume(BigDecimal quantity,int unit)
	{
		KgVolume kv=new KgVolume();
		kv.quantity=quantity;
		kv.unit=unit;
		return kv;
	}
	public static KgVolume buildKgVolume()
	{
		KgVolume kv=new KgVolume();
	
		return kv;
	}
	
	
	private BigDecimal quantity;
	private int unit;
	/**
	 * @return the quantity
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the unit
	 */
	public int getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(int unit) {
		this.unit = unit;
	}
}
