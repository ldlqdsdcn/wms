/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.devs;

import com.delmar.util.DateTimeDecorator;
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
	private Configuration config;
	private String modulename;
	private String genmodelpath;

	public GenerateServiceMain( Configuration config,String modulename,String genmodelpath,String[] modelList) {
		this.config=config;
		this.modulename=modulename;
		this.genmodelpath=genmodelpath;
		this.modelList=modelList;
	}
	
	
	public  void generateInterface()
	{
		Template template =null;
		 try {
			 template= config.getTemplate("serviceInterface.flt","UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
		File filepath=new File(GenerateDaoMain.class.getResource("/").getFile());
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
				System.out.println(genmodelpath+"src/main/java/"+interfacepackage.replace(".", "/")+"/"+model+"Service.java");
				 File file = new File(genmodelpath+"src/main/java/"+interfacepackage.replace(".", "/")+"/"+model+"Service.java"); 
				root.put("modelname", model);
				
			
				if(!file.exists()){    
					if(!file.getParentFile().exists())
					{
						file.getParentFile().mkdirs();
					}
	                //System.out.println("file exist");    
	                file.createNewFile();    
	            }    
	            Writer out = new BufferedWriter(new FileWriter(file));    
	            template.process(root, out);    
	            out.flush();   
	            out.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public   void generateServiceclass()
	{
		Template template =null;
		 try {
			 template= config.getTemplate("serviceClass.flt","UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
		File filepath=new File(GenerateDaoMain.class.getResource("/").getFile());
		   Map root = new HashMap();  
		
		Date date=new Date();
		String classpackage="com.kudan."+modulename+".service.impl";
		String modelpackage="com.kudan."+modulename+".model";
		String datetime=DateTimeDecorator.dateToLongString(date);
		root.put("packagename", "com.kudan."+modulename+".service.impl");
	
		root.put("modelpackage", modelpackage);
		root.put("datetime", datetime);
		//gc.setTime(date);
		try
		{
			for(String model:modelList)
			{
				root.put("interfacefulldaoname", "com.kudan."+modulename+".dao."+model+"Dao");
				//toUpperCase
				root.put("interfacefullservicename", "com.kudan."+modulename+".service."+model+"Service");
				System.out.println(genmodelpath+"src/main/java/"+classpackage.replace(".", "/")+"/"+model+"ServiceImpl.java");
				 File file = new File(genmodelpath+"src/main/java/"+classpackage.replace(".", "/")+"/"+model+"ServiceImpl.java"); 
				root.put("modelname", model);
				String bgnChar=model.substring(0,1);
				
				root.put("repositoryname", bgnChar.toLowerCase()+model.substring(1)+"Dao");
				root.put("mappername", "com.kudan."+modulename+".mybatis.sql."+model+"Mapper");
				String modelname2=model.substring(0,1).toLowerCase()+model.substring(1);
				System.out.println("modelname2="+modelname2);
				root.put("serviceName", "@Service(\""+modelname2+"Service\")");
				if(!file.exists()){    
	                //System.out.println("file exist");    
					if(!file.getParentFile().exists())
					{
						file.getParentFile().mkdirs();
					}
	                file.createNewFile();    
	            }    
	            Writer out = new BufferedWriter(new FileWriter(file));    
	            template.process(root, out);    
	            out.flush();   
	            out.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
