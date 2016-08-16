package com.delmar.cargo.model;

import java.math.BigDecimal;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年6月29日 下午5:01:38 
 * 类说明 
 */
public class BusinessClient {

	private String clientCode;
	private String clientName;
	private Integer inumber;
	private BigDecimal cargoVolume;
	private BigDecimal profit;
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Integer getInumber() {
		return inumber;
	}
	public void setInumber(Integer inumber) {
		this.inumber = inumber;
	}
	public BigDecimal getCargoVolume() {
		return cargoVolume;
	}
	public void setCargoVolume(BigDecimal cargoVolume) {
		this.cargoVolume = cargoVolume;
	}
	public BigDecimal getProfit() {
		return profit;
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	
	
	
}
