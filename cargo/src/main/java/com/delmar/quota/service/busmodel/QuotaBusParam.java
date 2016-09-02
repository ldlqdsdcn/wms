/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.quota.service.busmodel;

import java.math.BigDecimal;

import com.delmar.base.model.Port;
import com.delmar.sys.model.Client;
import com.delmar.sys.model.User;

/**
 * @author 刘大磊 2014年12月23日 下午4:33:02
 */
public class QuotaBusParam {
	
	/**
	 * 
	 */
	public QuotaBusParam( User user,String pol,String poa,String cargotype,BigDecimal weight,BigDecimal  volume
			,String fclunit,String truckType, String fcllcl,String mode,Integer quantity) {
			this.user=user;
			this.poa=poa;
			this.pol=pol;
			this.cargotype=cargotype;
			this.weight=weight;
			this.volume=volume;
			this.fclunit=fclunit;
			this.truckType=truckType;
			this.fcllcl=fcllcl;
			this.mode=mode;
			this.quantity=quantity;
	}
	private User user;
	/**
	 * 发货港
	 */
	private String pol;
	/**
	 * 目的港
	 */
	private String poa;
	/**
	 * 货物类型
	 */
	private String cargotype;
	/**
	 * 重量
	 */
	private BigDecimal weight;
	/**
	 * 体积
	 */
	private BigDecimal  volume;
	
	
	private String fclunit;
	
	private String truckType;

	/**
	 * 整箱散箱
	 */
	private String fcllcl;
	/**
	 * 货运类型
	 */
	private String mode;
	
	/**
	 * 整箱数量
	 */
	private Integer quantity;
	
	/**
	 * 提供者
	 */
	private Client provider;
	
	private Port polPort;
	
	private Port poaPort;
	
	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the fcllcl
	 */
	public String getFcllcl() {
		return fcllcl;
	}
	/**
	 * @param fcllcl the fcllcl to set
	 */
	public void setFcllcl(String fcllcl) {
		this.fcllcl = fcllcl;
	}
	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}
	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the pol
	 */
	public String getPol() {
		return pol;
	}
	/**
	 * @param pol the pol to set
	 */
	public void setPol(String pol) {
		this.pol = pol;
	}
	/**
	 * @return the poa
	 */
	public String getPoa() {
		return poa;
	}
	/**
	 * @param poa the poa to set
	 */
	public void setPoa(String poa) {
		this.poa = poa;
	}
	/**
	 * @return the cargotype
	 */
	public String getCargotype() {
		return cargotype;
	}
	/**
	 * @param cargotype the cargotype to set
	 */
	public void setCargotype(String cargotype) {
		this.cargotype = cargotype;
	}
	/**
	 * @return the weight
	 */
	public BigDecimal getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	/**
	 * @return the volume
	 */
	public BigDecimal getVolume() {
		return volume;
	}
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	/**
	 * @return the fclunit
	 */
	public String getFclunit() {
		return fclunit;
	}
	/**
	 * @param fclunit the fclunit to set
	 */
	public void setFclunit(String fclunit) {
		this.fclunit = fclunit;
	}
	public String getTruckType() {
		return truckType;
	}
	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}
	/**
	 * @return the provider
	 */
	public Client getProvider() {
		return provider;
	}
	/**
	 * @param provider the provider to set
	 */
	public void setProvider(Client provider) {
		this.provider = provider;
	}
	/**
	 * @return the polPort
	 */
	public Port getPolPort() {
		return polPort;
	}
	/**
	 * @param polPort the polPort to set
	 */
	public void setPolPort(Port polPort) {
		this.polPort = polPort;
	}
	/**
	 * @return the poaPort
	 */
	public Port getPoaPort() {
		return poaPort;
	}
	/**
	 * @param poaPort the poaPort to set
	 */
	public void setPoaPort(Port poaPort) {
		this.poaPort = poaPort;
	}
	
	
	

}
