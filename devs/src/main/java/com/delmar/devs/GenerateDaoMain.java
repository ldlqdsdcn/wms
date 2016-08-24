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
 * @author 刘大磊 2014年12月22日 上午10:28:48
 */
public class GenerateDaoMain {
	String[] modelList;
	private Configuration config;
	private String modulename;
	private String genmodelpath;
	
	
	/**
	 * 
	 */
	public GenerateDaoMain( Configuration config,String modulename,String genmodelpath,String[] modelList) {
		this.config=config;
		this.modulename=modulename;
		this.genmodelpath=genmodelpath;
		this.modelList=modelList;
	}
	
	public void generateInterface()
	{
		Template template =null;
		 try {
			 template= config.getTemplate("daoInterface.flt","UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
		File filepath=new File(GenerateDaoMain.class.getResource("/").getFile());
		   Map root = new HashMap();  
		
		Date date=new Date();
		String interfacepackage="com.kudan."+modulename+".dao";
		String modelpackage="com.kudan."+modulename+".model";
		String datetime=DateTimeDecorator.dateToLongString(date);
		root.put("packagename", interfacepackage);
		root.put("modelpackage", modelpackage);
		root.put("datetime", datetime);
		//gc.setTime(date);
		try
		{
			for(String model:modelList)
			{
				System.out.println(genmodelpath+"src/main/java/"+interfacepackage.replace(".", "/")+"/"+model+"Dao.java");
				 File file = new File(genmodelpath+"src/main/java/"+interfacepackage.replace(".", "/")+"/"+model+"Dao.java"); 
				root.put("modelname", model);
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
	public   void generatedaoclass()
	{
		Template template =null;
		 try {
			 template= config.getTemplate("daoclass.flt","UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
		File filepath=new File(GenerateDaoMain.class.getResource("/").getFile());
		   Map root = new HashMap();  
		
		Date date=new Date();
		String classpackage="com.kudan."+modulename+".dao.mybatis";
		String modelpackage="com.kudan."+modulename+".model";
		String datetime=DateTimeDecorator.dateToLongString(date);
		root.put("packagename", classpackage);
	
		root.put("modelpackage", modelpackage);
		root.put("datetime", datetime);
		//gc.setTime(date);
		try
		{
			for(String model:modelList)
			{
				//toUpperCase
				root.put("interfacefullname", "com.kudan."+modulename+".dao."+model+"Dao");
				System.out.println(genmodelpath+"src/main/java/"+classpackage.replace(".", "/")+"/"+model+"DaoMybatis.java");
				 File file = new File(genmodelpath+"src/main/java/"+classpackage.replace(".", "/")+"/"+model+"DaoMybatis.java"); 
				root.put("modelname", model);
				String bgnChar=model.substring(0,1);
				
				root.put("repositoryname", bgnChar.toLowerCase()+model.substring(1)+"Dao");
				root.put("mappername", "com.kudan."+modulename+".mybatis.sql."+model+"Mapper");
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
