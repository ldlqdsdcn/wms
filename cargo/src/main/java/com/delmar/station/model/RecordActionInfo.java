package com.delmar.station.model;

import com.delmar.core.model.CoreModel;

/**
 * <p>功能描述:日志记录<p>
 * <p/>
 * 创建日期  2009-11-18 10:43:11<br>
 *
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @see
 * @since 1.0.0
 */
public class RecordActionInfo extends CoreModel{
	private String rid;
	private String actionType;
	private String originCode;
	private String actionCode;
	private String actionName;
	private String responseContext;
	private String responseOperator;
	private String responseOperatorName;
	private String responseDate;
	private String companyID;
	private String operator;
	private String operatorName;
	private String createDate;
	private int zfbz;
	private String remark;
	private String toBranch;


	public String getActionType() {
		return actionType;
	}


	public void setActionType(String actionType) {
		this.actionType = actionType;
	}


	public String getOriginCode() {
		return originCode;
	}


	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}


	public String getActionCode() {
		return actionCode;
	}


	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}


	public String getActionName() {
		return actionName;
	}


	public void setActionName(String actionName) {
		this.actionName = actionName;
	}


	public String getResponseContext() {
		return responseContext;
	}


	public void setResponseContext(String responseContext) {
		this.responseContext = responseContext;
	}


	public String getResponseOperator() {
		return responseOperator;
	}


	public void setResponseOperator(String responseOperator) {
		this.responseOperator = responseOperator;
	}


	public String getResponseOperatorName() {
		return responseOperatorName;
	}


	public void setResponseOperatorName(String responseOperatorName) {
		this.responseOperatorName = responseOperatorName;
	}


	public String getResponseDate() {
		if (responseDate==null)
			responseDate="";
		return responseDate;
	}


	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}


	public String getToBranch() {
		return toBranch;
	}


	public void setToBranch(String toBranch) {
		this.toBranch = toBranch;
	}


	public String getCompanyID() {
		return companyID;
	}


	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}


	public String getOperator() {
		return operator;
	}


	public void setOperator(String operator) {
		this.operator = operator;
	}


	public String getOperatorName() {
		return operatorName;
	}


	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}


	public String getCreateDate() {
		return createDate;
	}


	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public int getZfbz() {
		return zfbz;
	}


	public void setZfbz(int zfbz) {
		this.zfbz = zfbz;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRid() {
		return rid;
	}


	public void setRid(String rid) {
		this.rid = rid;
	}



	
	
	

}
