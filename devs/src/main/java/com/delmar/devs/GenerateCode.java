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
	private static String user="刘大磊";
	public static String[] modelList={"Window","Page","Field"};
	public static String[] titleList={"窗体","页面","字段"};
	public  static String modulename="core";
	public static String genmodelpath="d:/IdeaProjects/MyHome/";
	public static Configuration config;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File filepath=new File(GenerateDaoMain.class.getResource("/").getFile());
		   config = new Configuration();
			config.setDefaultEncoding("utf-8");
		  try {
			config.setDirectoryForTemplateLoading(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}    
		  config.setObjectWrapper(new DefaultObjectWrapper());
		  //生成dao
		  GenerateDaoMain gdm=new GenerateDaoMain(modulename,modelList);
		  gdm.generateInterface();
		  gdm.generatedaoclass();
		  //生成 service
		  GenerateServiceMain gsm=new GenerateServiceMain(modulename,modelList);
		  gsm.generateInterface();
		  gsm.generateServiceclass();
		//生成struts Configuration config, String modulename, String genmodelpath, String[] modelList
		GenerateActionMain generateActionMain=new GenerateActionMain(modulename,modelList);
		generateActionMain.generateActionclass();
		//生成struts xml
		GenerateStrutsConfigMain gam=new GenerateStrutsConfigMain("core"
				,modelList);
		gam.generateStrutsConfig();

		//Configuration config,String namespace,String[] modeList,String[] modeNameList,String user,String genmodelpath
		GenerateJspPageMain generateJspPageMain=new GenerateJspPageMain(modelList,titleList,user,modulename,null);
		generateJspPageMain.generateJspPage();


	}

}
