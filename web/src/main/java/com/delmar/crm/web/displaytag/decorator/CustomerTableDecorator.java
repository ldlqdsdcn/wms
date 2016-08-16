package com.delmar.crm.web.displaytag.decorator;

import java.util.Date;

import org.displaytag.decorator.TableDecorator;

import com.delmar.crm.model.Customer;
import com.delmar.crm.model.CustomerExtra;

public class CustomerTableDecorator extends TableDecorator {
	
    public String addRowClass()
    {
       if (((Customer)getCurrentRowObject()).getCustomerExtra().getNextLinkTime()==null)
        	return null;
    	
       float daydiff=(((Customer)getCurrentRowObject()).getCustomerExtra().getNextLinkTime().getTime()-(new Date()).getTime())/1000/60/60/24 ;
       if (daydiff >= 0 && daydiff < 2 )
    	 return "d-highlight-red";
       if ( daydiff < 0 ) 
         return "d-highlight-gray";
       else
          return null;  
    }

}
