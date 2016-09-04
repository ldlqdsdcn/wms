package com.delmar.system.web.displaytag.decorator;

import com.delmar.core.web.bean.SystemContextHelper;
import com.delmar.sys.model.Client;
import com.delmar.sys.service.ClientService;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import javax.servlet.jsp.PageContext;

/**
 * Created by admin on 2016/8/30.
 */
public class ClientDecorator implements DisplaytagColumnDecorator {
    private ClientService clientService= SystemContextHelper.getBean("clientService", ClientService.class);
    /* (non-Javadoc)
     * @see org.displaytag.decorator.DisplaytagColumnDecorator#decorate(java.lang.Object, javax.servlet.jsp.PageContext, org.displaytag.properties.MediaTypeEnum)
     */
    @Override
    public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
            throws DecoratorException {

        if(arg0!=null)
        {
            Integer value=(Integer)arg0;
            Client client=clientService.selectByPrimaryKey(value);
            if(client!=null)
            {
                return client.getName();
            }
            else
            {
                return null;
            }
        }


        return null;
    }
}
