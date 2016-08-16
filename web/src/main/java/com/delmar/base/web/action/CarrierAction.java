/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.base.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.Carrier;
import com.delmar.base.model.CarrierTrl;
import com.delmar.base.service.CarrierService;
import com.delmar.base.service.CarrierTrlService;
import com.delmar.common.model.DelmarFile;
import com.delmar.common.model.FileRelation;
import com.delmar.common.model.FileSetting;
import com.delmar.common.service.FileRelationService;
import com.delmar.common.service.FileSettingService;
import com.delmar.core.model.Language;
import com.delmar.core.service.LanguageService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.action.PrivilegeOperator;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.Module;
import com.delmar.utils.ResourceMessage;


/**
 * @author 刘大磊 2015-02-05 16:28:29
 */
public class CarrierAction extends CoreEditPrivAction {
	private Carrier  carrier;
	private List<CarrierTrl> carrierTrlList=null;
	private List<FileRelation> fileRelationList=new ArrayList<FileRelation>();
	@Autowired
	private FileSettingService fileSettingService;
	@Autowired
	private FileRelationService fileRelationService;
	@Autowired
	private CarrierService carrierService;
	@Autowired
	private CarrierTrlService carrierTrlService;
	@Autowired
	private LanguageService languageService;
	
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "Carrier维护");
	}

	
	private void init()
	{
		
		Module module=PrivilegeOperator.getModule();
		FacesUtils.setValueInHashtableOfSession("module", module);
		Map<String,Object> param1=new HashMap<String,Object>();
		param1.put("moduleId", module.getId());
		FileSetting fs=fileSettingService.getByExample(param1);
		FacesUtils.setValueInHashtableOfSession("fileSetting", fs);
		
		
		if(carrier.getId()!=null)
		{
			Map  param=new HashMap();
			param.put("carrierId", carrier.getId());
			carrierTrlList=	carrierTrlService.selectByExample(param);
			
			List<Language> list=languageService.selectByExample(null);
			List<Language> noList=new ArrayList<Language>();
			if(carrierTrlList==null||carrierTrlList.size()==0)
			{
				carrierTrlList=new ArrayList<CarrierTrl>();
				noList=list;
			}
			else
			{
				
				for(Language lang:list)
				{
					boolean has=false;
					for(CarrierTrl trl:carrierTrlList)
					{
						if(trl.getLanguage().equals(lang.getCode()))
						{
							has=true;
							break;
						}
					}
					if(!has)
					{
						noList.add(lang);
					}
				}
			}
			for(Language lang:noList)
			{
				CarrierTrl trl=new CarrierTrl();
				trl.setLanguage(lang.getCode());
				trl.setCarrierId(carrier.getId());
				carrierTrlList.add(trl);
			}
			 param=new HashMap<String,Object>();
			 param.put("tableName", "base_carrier");
			 param.put("tableId", carrier.getId());
			fileRelationList=this.fileRelationService.selectByExample(param);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "carrier";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		carrierService.deleteByPrimaryKey(carrier.getId());
		return "list";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		carrierService.deleteCarrierList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return carrier.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		 carrier= carrierService.selectByPrimaryKey(id);
		 init();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		return carrierService.selectByExample(null);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		carrier=new Carrier();
		carrierTrlList=null;
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
		Date now=new Date();
		for(FileRelation fr:fileRelationList)
		{
			fr.setCreated(now);
			fr.setTableName("base_carrier");
			
		}
		carrierService.saveCarrier(carrier,this.carrierTrlList,fileRelationList);
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public Carrier getCarrier() {
		return carrier;
	}

	/**
	 * @param usergroup the usergroup to set
	 */
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	/**
	 * @return the carrierTrlList
	 */
	public List<CarrierTrl> getCarrierTrlList() {
		return carrierTrlList;
	}

	/**
	 * @param carrierTrlList the carrierTrlList to set
	 */
	public void setCarrierTrlList(List<CarrierTrl> carrierTrlList) {
		this.carrierTrlList = carrierTrlList;
	}

	/**
	 * @return the fileRelationList
	 */
	public List<FileRelation> getFileRelationList() {
		return fileRelationList;
	}

	/**
	 * @param fileRelationList the fileRelationList to set
	 */
	public void setFileRelationList(List<FileRelation> fileRelationList) {
		this.fileRelationList = fileRelationList;
	}
	public String addFile()
	{
		FileRelation fr=new FileRelation();
		DelmarFile df=new DelmarFile();
		fr.setDelmarFile(df);
		fileRelationList.add(fr);
		return "edit";
	}
	public String deleteFiles()
	{
		String[] ids=ServletActionContext.getRequest().getParameterValues("ids");
		List<String> idList=new ArrayList<String>();
		
		//
		Integer[] intids=new Integer[ids.length];
		
		for(int i=0;i<ids.length;i++)
		{
			idList.add(ids[i]);
			Integer index=Integer.parseInt(ids[i]);
			FileRelation column=fileRelationList.get(index);
			if(column.getId()!=null&&column.getId()!=0)
			{
				intids[i]=column.getId();
				this.fileRelationService.deleteFileRelation(column);
			}
		}
		java.util.Collections.sort(idList);
		System.out.println("columns.size="+fileRelationList.size());
		for(int i=idList.size()-1;i>=0;i--)
		{
			
			this.fileRelationList.remove(Integer.parseInt(idList.get(i)));
		}
		System.out.println("columns.size="+fileRelationList.size());
		return "edit";
	}
}
