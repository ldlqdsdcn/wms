/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.web.displaytag.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

/**
 * @author 刘大磊 2015年1月20日 上午11:53:08
 */
public class ChangeLogOperateTypeDecorator implements DisplaytagColumnDecorator{
	@Override
	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		if("D".equals(arg0))
		{
			return  "删除";
		}
		else if("I".equals(arg0))
		{
			return "新建";
		}
		else if("U".equals(arg0))
		{
			return "更新";
		}
		return null;
	}
}
