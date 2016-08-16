package com.delmar.crm.web.displaytag.decorator;

import java.util.Date;

import org.displaytag.decorator.TableDecorator;

import com.delmar.crm.model.Linkman;

public class LinkmanTableDecorator extends TableDecorator {
	
    public String addRowClass()
    {
       if (((Linkman)getCurrentRowObject()).getNextLinkTime()==null)
        	return null;
    	
       float daydiff=(((Linkman)getCurrentRowObject()).getNextLinkTime().getTime()-(new Date()).getTime())/1000/60/60/24 ;
       if (daydiff >= 0 && daydiff < 2 )
    	 return "d-highlight-red";
       if ( daydiff < 0 ) 
         return "d-highlight-gray";
       else
          return null;  
    }

}
