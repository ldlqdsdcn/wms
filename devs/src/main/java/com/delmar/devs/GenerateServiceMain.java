/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.devs;

import com.delmar.devs.ftl.FreeMarkerHelper;
import com.delmar.utils.DateTimeDecorator;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘大磊 2014年12月22日 下午12:44:44
 */
public class GenerateServiceMain {
	String[] modelList;

	private String modulename;

	public GenerateServiceMain( String modulename,String[] modelList) {
		this.modulename=modulename;
		this.modelList=modelList;
	}
	
	
	public  void generateInterface()
	{
	   Map root = new HashMap();
		Date date=new Date();
		String interfacepackage="com.delmar."+modulename+".service";
		String modelpackage="com.delmar."+modulename+".model";
		String datetime=DateTimeDecorator.dateToLongString(date);
		root.put("packagename", interfacepackage);
		root.put("modelpackage", modelpackage);
		root.put("datetime", datetime);
		//gc.setTime(date);
		try
		{
			for(String model:modelList)
			{

				root.put("modelname", model);
				FreeMarkerHelper.getInstance().outFile("serviceInterface.ftl",root,"src/main/java/"+interfacepackage.replace(".", "/")+"/"+model+"Service.java");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public   void generateServiceclass()
	{
		Map root = new HashMap();
		Date date=new Date();
		String classpackage="com.delmar."+modulename+".service.impl";
		String modelpackage="com.delmar."+modulename+".model";
		String datetime=DateTimeDecorator.dateToLongString(date);
		root.put("packagename", "com.delmar."+modulename+".service.impl");
	
		root.put("modelpackage", modelpackage);
		root.put("datetime", datetime);
		//gc.setTime(date);
		try
		{
			for(String model:modelList)
			{
				root.put("interfacefulldaoname", "com.delmar."+modulename+".dao."+model+"Dao");
				//toUpperCase
				root.put("interfacefullservicename", "com.delmar."+modulename+".service."+model+"Service");

				root.put("modelname", model);
				String bgnChar=model.substring(0,1);
				
				root.put("repositoryname", bgnChar.toLowerCase()+model.substring(1)+"Dao");
				root.put("mappername", "com.delmar."+modulename+".mybatis.sql."+model+"Mapper");
				String modelname2=model.substring(0,1).toLowerCase()+model.substring(1);
				System.out.println("modelname2="+modelname2);
				root.put("serviceName", "@Service(\""+modelname2+"Service\")");

				FreeMarkerHelper.getInstance().outFile("serviceClass.ftl",root,"src/main/java/"+classpackage.replace(".", "/")+"/"+model+"ServiceImpl.java");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
