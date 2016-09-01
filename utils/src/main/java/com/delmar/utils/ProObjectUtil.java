package com.delmar.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年6月9日 下午5:07:24 
 * 类说明 
 */
public class ProObjectUtil {
	
	 public static void copyPropertiesExclude(Object from, Object to, String[] excludeArray) throws Exception {
	        List<String> excludesList = null;  
	        if(excludeArray != null && excludeArray.length > 0) {
	            excludesList = Arrays.asList(excludeArray); //构造列表对象
	        } 
	        
	        Method[] fromMethods = from.getClass().getDeclaredMethods();  
	        Method[] toMethods = to.getClass().getDeclaredMethods();  
	        Method fromMethod, toMethod;
	        String fromMethodName, toMethodName;
		 for (Method fromMethod1 : fromMethods) {
			 fromMethod = fromMethod1;
			 fromMethodName = fromMethod.getName();
			 if (!fromMethodName.contains("get"))
				 continue;
			 //排除列表检测
			 if (excludesList != null && excludesList.contains(fromMethodName.substring(3).toLowerCase())) {
				 continue;
			 }
			 toMethodName = "set" + fromMethodName.substring(3);
			 toMethod = findMethodByName(toMethods, toMethodName);
			 if (toMethod == null)
				 continue;
			 Object value = fromMethod.invoke(from);
			 if (value == null)
				 continue;
			 //集合类判空处理
			 if (value instanceof Collection) {
				 Collection newValue = (Collection) value;
				 if (newValue.size() <= 0)
					 continue;
			 }
			 toMethod.invoke(to, value);
		 }
	    }  
	 
	 
	   /** 
	     * 对象属性值复制，仅复制指定名称的属性值 
	     * @param from 
	     * @param to 
	     * @param includeArray
	     * @throws Exception 
	     */  
	    @SuppressWarnings("unchecked")  
	    public static void copyPropertiesInclude(Object from, Object to, String[] includeArray) throws Exception {
	        List<String> includesList;
	        if(includeArray != null && includeArray.length > 0) {
	            includesList = Arrays.asList(includeArray); //构造列表对象
	        } else {  
	            return;  
	        }  
	        Method[] fromMethods = from.getClass().getDeclaredMethods();  
	        Method[] toMethods = to.getClass().getDeclaredMethods();  
	        Method fromMethod, toMethod;
	        String fromMethodName, toMethodName;
			for (Method fromMethod1 : fromMethods) {
				fromMethod = fromMethod1;
				fromMethodName = fromMethod.getName();
				if (!fromMethodName.contains("get"))
					continue;
				//排除列表检测
				String str = fromMethodName.substring(3);
				if (!includesList.contains(str.substring(0, 1).toLowerCase() + str.substring(1))) {
					continue;
				}
				toMethodName = "set" + fromMethodName.substring(3);
				toMethod = findMethodByName(toMethods, toMethodName);
				if (toMethod == null)
					continue;
				Object value = fromMethod.invoke(from);
				if (value == null)
					continue;
				//集合类判空处理
				if (value instanceof Collection) {
					Collection newValue = (Collection) value;
					if (newValue.size() <= 0)
						continue;
				}
				toMethod.invoke(to, value);
			}
	    }  
	      	 
	 
	  
	    /** 
	     * 从方法数组中获取指定名称的方法 
	     *  
	     * @param methods 
	     * @param name 
	     * @return 
	     */  
	    public static Method findMethodByName(Method[] methods, String name) {
			for (Method method : methods) {
				if (method.getName().equals(name))
					return method;
			}
	        return null;  
	    }  	
	    
	    
	    public static List<String> retrieveFieldList(List originList,String fieldName) throws Exception 
	    {
	    	List<String> stringList= new ArrayList<>();
	    	
 
	        Method fromMethod;
	        String fromMethodName;

	    	for (Object obj:originList)
	    	{
	    		  Method[] fromMethods = obj.getClass().getDeclaredMethods();
				for (Method fromMethod1 : fromMethods) {
					fromMethod = fromMethod1;
					fromMethodName = fromMethod.getName();
					if (!fromMethodName.contains("get"))
						continue;
					//排除列表检测
					String str = fromMethodName.substring(3);
					if (fieldName.equalsIgnoreCase(str)) {
						Object value = fromMethod.invoke(obj);
						if (value == null) {
							break;
						}

						if (value instanceof String) {
							stringList.add((String) value);
							break;
						}
					}

				}
	    	}
	    	
	    	
	    	return stringList;
	    }

}
