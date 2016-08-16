/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.base.model;

/**
 * @author 刘大磊 2015年3月27日 上午10:46:04
 */
public enum EventTypeStatus {
    BGN_STATUS(1), MID_STATUS(2),END_STATUS(3);
    private int state;
    private EventTypeStatus(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
