package com.delmar.cargo.model;

import java.math.BigDecimal;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月26日 上午11:20:38 
 * 类说明 
 */
public class BusinessInvoice extends BusinessForwarder {
	

	private String invoiceNo;
	private String cusCode;
	private String cusCodeName;
	private String currencyType;
	private BigDecimal currencyRate;
	private Integer receDeal;
	private BigDecimal transDebit;
	private BigDecimal transCredit;
	private BigDecimal transProfit;
	private BigDecimal cwAcount;
	private BigDecimal balance;
	private String confirmDate;
	private String operatorName;
	private String operateDate;	
	private String billDate;
	private String billNo;	
	private String receiveDate;
	private String cwFinishDate;
	private String balanceType;
	private String creditDebit;
	
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getCusCode() {
		return cusCode;
	}
	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}
	public String getCusCodeName() {
		return cusCodeName;
	}
	public void setCusCodeName(String cusCodeName) {
		this.cusCodeName = cusCodeName;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public BigDecimal getCurrencyRate() {
		return currencyRate;
	}
	public void setCurrencyRate(BigDecimal currencyRate) {
		this.currencyRate = currencyRate;
	}
	public Integer getReceDeal() {
		return receDeal;
	}
	public void setReceDeal(Integer receDeal) {
		this.receDeal = receDeal;
		if (receDeal.intValue()==1)
			creditDebit="Debit";
		else
			creditDebit="Credit";			
	}
	public BigDecimal getTransDebit() {
		return transDebit;
	}
	public void setTransDebit(BigDecimal transDebit) {
		this.transDebit = transDebit;
	}
	public BigDecimal getTransCredit() {
		return transCredit;
	}
	public void setTransCredit(BigDecimal transCredit) {
		this.transCredit = transCredit;
	}
	public BigDecimal getTransProfit() {
		return transProfit;
	}
	public void setTransProfit(BigDecimal transProfit) {
		this.transProfit = transProfit;
	}
	public BigDecimal getCwAcount() {
		return cwAcount;
	}
	public void setCwAcount(BigDecimal cwAcount) {
		this.cwAcount = cwAcount;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getBillNo() {
		if (billNo==null)
			billNo="";
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getCwFinishDate() {
		return cwFinishDate;
	}
	public void setCwFinishDate(String cwFinishDate) {
		this.cwFinishDate = cwFinishDate;
	}
	public String getBalanceType() {
		return balanceType;
	}
	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}
	public String getCreditDebit() {
		return creditDebit;
	}
	public void setCreditDebit(String creditDebit) {
		this.creditDebit = creditDebit;
	}
	
	
	

}
