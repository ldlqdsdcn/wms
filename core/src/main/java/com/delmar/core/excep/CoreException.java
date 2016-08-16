package com.delmar.core.excep;
/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年7月15日 上午9:54:31 
 * 类说明 
 */
public class CoreException extends RuntimeException {
	
	public CoreException(String msg)
	{
		super(msg);
		this.message=msg;
	}
	
	public CoreException()
	{
		
	}
	
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	private String message;

}
