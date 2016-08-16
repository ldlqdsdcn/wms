package com.delmar.common.model;

import com.delmar.core.model.MailInfo;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年8月27日 下午4:24:44 
 * 类说明 
 */
public class ObjMailInfo extends MailInfo {
	
	private StringBuffer contentBuf;
	private StringBuffer currentContentBuf;
	private int xhNo=1;
	
	
	public ObjMailInfo()
	{
		contentBuf=new StringBuffer();
		currentContentBuf=new StringBuffer();
	}


	public StringBuffer getContentBuf() {
		return contentBuf;
	}


	public void setContentBuf(StringBuffer contentBuf) {
		this.contentBuf = contentBuf;
	}


	public StringBuffer getCurrentContentBuf() {
		return currentContentBuf;
	}


	public void setCurrentContentBuf(StringBuffer currentContentBuf) {
		this.currentContentBuf = currentContentBuf;
	}


	public int getXhNo() {
		return xhNo;
	}


	public void setXhNo(int xhNo) {
		this.xhNo = xhNo;
	}
	
	public void addXhNo()
	{
		this.xhNo++;
	}
	
	
	

}
