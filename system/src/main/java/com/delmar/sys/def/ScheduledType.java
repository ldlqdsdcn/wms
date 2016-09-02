/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.sys.def;

/**
 * @author 刘大磊 2015年8月27日 上午11:52:52
 */
public enum ScheduledType {
  Delay(0),Time(1);
  private ScheduledType(int type)
  {
	  this.type=type;
  }
  
  private int type;

  public int getType()
  {
	  return type;
  }
}
