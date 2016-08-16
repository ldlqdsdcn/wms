/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.corebus.model;

/**
 * @author 刘大磊 2015年3月24日 下午5:39:46
 */
public enum EBusinessStatus {
	DRAFT(100),COMPLETE(101);	
    private int state;
    private EBusinessStatus(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
