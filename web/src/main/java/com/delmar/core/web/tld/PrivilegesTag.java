/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.web.tld;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import com.delmar.core.DelmarConst;
import com.delmar.core.web.action.PrivilegeOperatorComon;

/**
 * @author 刘大磊 2015年1月30日 上午8:53:05
 * 操作权限控制标签
 */
public class PrivilegesTag extends BodyTagSupport {
	private static final Logger logger=Logger.getLogger(PrivilegesTag.class);
	boolean hasoperator=false;
	private Integer idpk=-1;
	private String operator;
	private String path;
	

/* (non-Javadoc)
 * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
 */
@Override
public int doStartTag() throws JspException {
	HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
	if(DelmarConst.OPERATOR_CREATE.equals(operator))
	{
			if(idpk>0)
			{
				hasoperator=false;
			}
			else
			if(PrivilegeOperatorComon.isCreate(path,request))
			{
				hasoperator=true;
			}

	}
	else if(DelmarConst.OPERATOR_UPDATE.equals(operator))
	{
		if(idpk==null||idpk==0)
		{
			hasoperator=false;
		}
		else
		if(PrivilegeOperatorComon.isUpdate(path,request))
		{
			hasoperator=true;
		}
	}
	else if(DelmarConst.OPERATOR_VIEW.equals(operator))
	{
		if(PrivilegeOperatorComon.isView(path,request))
		{
			hasoperator=true;
		}
	}
	else if(DelmarConst.OPERATOR_DELETE.equals(operator))
	{
		if(idpk==null||idpk==0)
		{
			hasoperator=false;
		}
		else
		if(PrivilegeOperatorComon.isDelete(path,request))
		{
			hasoperator=true;
		}
	}
	return super.doStartTag();
}
/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		if(hasoperator)
		{
			  try {
				pageContext.getOut().write(bodyContent.getString().trim());
			} catch (IOException e) {
	
				logger.error("权限标签输出错误",e);
			}
		}
		return super.doEndTag();
	}
	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * @return the idpk
	 */
	public Integer getIdpk() {
		return idpk;
	}
	/**
	 * @param idpk the idpk to set
	 */
	public void setIdpk(Integer idpk) {
		this.idpk = idpk;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
}
