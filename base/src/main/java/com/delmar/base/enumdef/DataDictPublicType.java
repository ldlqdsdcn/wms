package com.delmar.base.enumdef;
/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年7月24日 下午5:16:42 
 * 类说明 
 */
public enum DataDictPublicType {
	PUBLIC(0),PRIVATE(1);
	
	private int type;
	
	DataDictPublicType(int type)
	{
		this.type=type;
	}

	public int getType() {
		return type;
	}
	
}
