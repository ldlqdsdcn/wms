package com.delmar.cargo.web.pub;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.delmar.cargo.model.BusinessPerformance;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月27日 下午3:59:02 
 * 类说明 
 */
public class ObjPerformance extends BusinessPerformance {
	
	private ArrayList groupList;
	private Integer yyInumber;
	private BigDecimal yyProfit;
	private Integer ssInumber;
	private BigDecimal ssProfit;
	private Integer yourPos;
	private String topToYou;
	private String topToYouProfit;
	
	private double yyPercent;  //同比
	private double ssPercent;  //环比
	
	
	public ObjPerformance()
	{
		groupList=new ArrayList();	
	}
	
	public ArrayList getGroupList() {
		return groupList;
	}
	public void setGroupList(ArrayList groupList) {
		this.groupList = groupList;
	}

	public double getYyPercent() {
		if (super.getProfit()==null)
		{
			yyPercent=0;
			return yyPercent;			
			   
		}
		
		if (yyProfit==null)
		{
			yyPercent=0;
			return yyPercent;
		}
		
		BigDecimal chazhi=super.getProfit().subtract(yyProfit);
		if (yyProfit.doubleValue()==0)
			yyPercent=100;
		else
		    yyPercent=chazhi.divide(yyProfit,4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).doubleValue();
		return yyPercent;
	}
	public void setYyPercent(double yyPercent) {
		this.yyPercent = yyPercent;
	}
	public double getSsPercent() {
		if (super.getProfit()==null)
		{
			ssPercent=0;
			return ssPercent;
		}
		
		if (ssProfit==null)
		{
			ssPercent=0;
			return ssPercent;

		}

		BigDecimal chazhi=super.getProfit().subtract(ssProfit);
		if (ssProfit.doubleValue()==0)
			ssPercent=100;
		else
    		ssPercent=chazhi.divide(ssProfit,4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).doubleValue();
		
		return ssPercent;
	}
	public void setSsPercent(double ssPercent) {
		this.ssPercent = ssPercent;
	}

	public Integer getYyInumber() {
		return yyInumber;
	}

	public void setYyInumber(Integer yyInumber) {
		this.yyInumber = yyInumber;
	}

	public BigDecimal getYyProfit() {
		return yyProfit;
	}

	public void setYyProfit(BigDecimal yyProfit) {
		this.yyProfit = yyProfit;
	}

	public Integer getSsInumber() {
		return ssInumber;
	}

	public void setSsInumber(Integer ssInumber) {
		this.ssInumber = ssInumber;
	}

	public BigDecimal getSsProfit() {
		return ssProfit;
	}

	public void setSsProfit(BigDecimal ssProfit) {
		this.ssProfit = ssProfit;
		

	}

	public Integer getYourPos() {
		return yourPos;
	}

	public void setYourPos(Integer yourPos) {
		this.yourPos = yourPos;
	}

	public String getTopToYou() {
		return topToYou;
	}

	public void setTopToYou(String topToYou) {
		this.topToYou = topToYou;
	}

	public String getTopToYouProfit() {
		return topToYouProfit;
	}

	public void setTopToYouProfit(String topToYouProfit) {
		this.topToYouProfit = topToYouProfit;
	}



	
	
	

}
