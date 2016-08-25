/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.devs;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

import java.io.File;
import java.io.IOException;

/**
 * @author 刘大磊 2014年12月22日 下午1:01:54
 * 
 * 生成dao层 和Service 层代码
 */
public class GenerateCode {
/*	public static String[] modelList={"Carrier","Chargename","Commdity","Currency","Datadict","Nameaccounts",
		"Port","Strategy","Unit"};*/
	//public static String[] modelList={"Client","Org","User"};,"Module","ModuleJavabean","ModuleMenu","ModulePage","ModuleRole","Operator","Org","Page","PageMenu","Privilege","Role","UserRole","UserorgAccess"
	public static String[] modelList={"Window","Page","Field"};
	
	public  static String modulename="core";
	public static String genmodelpath="d:/IdeaProjects/MyHome/";
	public static Configuration config;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File filepath=new File(GenerateDaoMain.class.getResource("/").getFile());
		   config = new Configuration();
		  try {
			config.setDirectoryForTemplateLoading(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}    
		  config.setObjectWrapper(new DefaultObjectWrapper());
		  
		  GenerateDaoMain gdm=new GenerateDaoMain(config,modulename,genmodelpath,modelList);
		  gdm.generateInterface();
		  gdm.generatedaoclass();
		  
		  GenerateServiceMain gsm=new GenerateServiceMain(config,modulename,genmodelpath,modelList);
		  gsm.generateInterface();
		  gsm.generateServiceclass();

	}

}
