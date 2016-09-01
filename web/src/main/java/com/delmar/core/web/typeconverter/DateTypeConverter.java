package com.delmar.core.web.typeconverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.util.StrutsTypeConverter;


public class DateTypeConverter extends StrutsTypeConverter {  
    
   /** 
    * 内存中的日期格式 
    */  
   private String shortFormat;
   private String longFormat;
   private String longlongFormat;
    
   /** 
    * 可以从数据库中加载日期格式 
    */  
   @PostConstruct  
   public void loadFormat() {  
       shortFormat = "yyyy-MM-dd";
       longFormat="yyyy-MM-dd HH:mm";
       longlongFormat="yyyy-MM-dd HH:mm:ss";
   }  
    
   @SuppressWarnings("rawtypes")  
   @Override  
   public Object convertFromString(Map context,String[] values, Class toClass) {  
   // Pre-validate  
       if (ArrayUtils.isEmpty(values) ||StringUtils.isEmpty(values[0])) {     
           return null;     
       }  
        
       Date date = null;  
       try {
    	   if (values[0].length()<=10)
             date = new SimpleDateFormat(shortFormat).parse(values[0]);
    	   else  if (values[0].length()<=16)
    		   date = new SimpleDateFormat(longFormat).parse(values[0]);
    	   else 
    		   date = new SimpleDateFormat(longlongFormat).parse(values[0]);    		   
       } catch (ParseException e) {       
           date = null;       
       }  
       return date;  
   }  
  
   @SuppressWarnings("rawtypes")  
   public String convertToString(Map context, Object o) {  
       if (o instanceof Date) {    
           return new SimpleDateFormat(longlongFormat).format((Date) o);    
       }  
       return StringUtils.EMPTY;     
}  
  
/** 
    * 更改日期格式（可以同时更新到数据库） 
    * @param shortFormat
    */  
   public void changeShortFormat(String shortFormat) {  
       this.shortFormat = shortFormat;  
   }  
   
   public void changeLongFormat(String longFormat) {  
       this.longFormat = longFormat;  
   }     
}  