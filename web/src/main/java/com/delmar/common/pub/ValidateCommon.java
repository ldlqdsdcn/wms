package com.delmar.common.pub;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.delmar.common.exception.ValidateException;
import com.delmar.core.web.bean.UserResource;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年8月17日 下午4:12:17 
 * 类说明 
 */
public class ValidateCommon {
	
	private static String getText(String aTextName,HttpServletRequest request) {
		
		UserResource ur=(UserResource)request.getSession().getAttribute("resource");
		String resource=ur.get(aTextName);
		if(resource==null)
		  resource=aTextName;
			 
			 
	   	 return resource;
		 
		
	}
	public static String validateData(Object checkObj,String[][] checkColumnA,String formName,HttpServletRequest request)  throws ValidateException
	{
		try
		{
		 String[] columnNameA=checkColumnA[0];
		 String[] columnTypeA=checkColumnA[1];	 
		 
		 
		 StringBuilder errorBuilder=new StringBuilder();		
         List<String> columnNameList = Arrays.asList(columnNameA); //构造列表对象
         List<String> columnTypeList = Arrays.asList(columnTypeA); //构造列表对象
         
         
         
          Method[] checkMethods = checkObj.getClass().getDeclaredMethods();  
           Method checkMethod = null;  
	       String checkMethodName = null;  
	       for (int i = 0; i < checkMethods.length; i++) {  
	            checkMethod = checkMethods[i];  
	            checkMethodName = checkMethod.getName();  
	            if (!checkMethodName.contains("get"))  
	                continue;  
	            //排除列表检测
	            
	            String columnName=checkMethodName.substring(3).toLowerCase();
	            if(columnNameList != null && !columnNameList.contains(columnName)) {  
	                continue;  
	            }  
	            Object value = checkMethod.invoke(checkObj);
	            
            	String resource=formName+".column."+columnName;
            	String valueResource=getText(resource,request);
            	if(valueResource==null)
            		valueResource=checkMethodName.substring(3).toLowerCase();
	            
	            if(value == null)
	            {
	               errorBuilder.append(valueResource+getText("public.validate.isempty",request));
	               errorBuilder.append("<br>");
	               continue;
	            }
	           
	            if(value instanceof String) {  
                   if (columnTypeList.get(columnNameList.indexOf(columnName)).equals("mail"))
                   {
                	 if (isEmail((String)value)==false)  
                	 {
      	               errorBuilder.append(valueResource+getText("public.validate.emailtype",request));
    	               errorBuilder.append("<br>");                		 
                	 }
                   }
	            }  
	            
	            if(value instanceof Integer) {  
	                   if (columnTypeList.get(columnNameList.indexOf(columnName)).equals("integer"))
	                   {
	                	 if (isInteger((String)value)==false)  
	                	 {
	      	               errorBuilder.append(valueResource+getText("public.validate.integertype",request));
	    	               errorBuilder.append("<br>");                		 
	                	 }
	                   }
		            }  
	            
	            if(value instanceof BigDecimal) {  
	                   if (columnTypeList.get(columnNameList.indexOf(columnName)).equals("decimal"))
	                   {
	                	 if (isDecimal((String)value)==false)  
	                	 {
	      	               errorBuilder.append(valueResource+getText("public.validate.decimaltype",request));
	    	               errorBuilder.append("<br>");                		 
	                	 }
	                   }
		            }  	            
	            

	        } 
	       
	       
	       return errorBuilder.toString();
		} catch (Exception ex)
		{
			throw new ValidateException(ex.getMessage());
		}
	        
 		
	}

	
    private static boolean isEmail(String email){  
        if (null==email || "".equals(email)) return false;    
//      Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配  
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配  
        Matcher m = p.matcher(email);  
        return m.matches();  
       }  
    
    private static boolean isInteger(String intValue){  
        if (null==intValue || "".equals(intValue)) return false;    
        Pattern p =  Pattern.compile("^-?[0-9]\\d*$");//复杂匹配  
        Matcher m = p.matcher(intValue);  
        return m.matches();  
       }      
    
    private static boolean isDecimal(String decimalValue){  
        if (null==decimalValue || "".equals(decimalValue)) return false;    
        Pattern p =  Pattern.compile("^-?[0-9]+[.]?\\d*$");//复杂匹配  
        Matcher m = p.matcher(decimalValue);  
        return m.matches();  
       }      
}
