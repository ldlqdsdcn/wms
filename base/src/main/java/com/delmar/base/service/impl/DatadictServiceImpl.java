/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.DatadictDao;
import com.delmar.base.dao.DatadictTrlDao;
import com.delmar.base.dao.DatadictTypeDao;
import com.delmar.base.enumdef.DataDictPublicType;
import com.delmar.base.model.Datadict;
import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.exception.ParamMissingException;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22014-12-22 13:26:54
 */
@Service("datadictService")
public class DatadictServiceImpl extends CoreServiceImpl<Datadict> implements
		DatadictService {
	@Autowired
	private DatadictDao datadictDao;
	@Autowired
	private DatadictTypeDao datadictTypeDao;
	@Autowired
	private DatadictTrlDao datadictTrlDao;
	
	
	
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Datadict> getCoreDao() {
		return datadictDao;
	}
	/* (non-Javadoc)
	 * @see com.delmar.base.service.DatadictService#deleteDatadictList(java.lang.Integer[])
	 */
	public void deleteDatadictList(Integer[] ids) {
		if(ids!=null)
		{
			for(Integer id:ids)
			{
				deleteByPrimaryKey(id);
				
			}
		}
		
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.service.impl.CoreServiceImpl#deleteByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public Integer deleteByPrimaryKey(Integer id) {
		Map map=new HashMap();
		map.put("datadictId", id);
		this.datadictTrlDao.deleteByExample(map);
		return super.deleteByPrimaryKey(id);
	}
	/* (non-Javadoc)
	 * @see com.delmar.base.service.DatadictService#getDatadictListByTypeId(java.lang.Integer)
	 */
	public List<Datadict> getDatadictListByTypeId(Integer typeId,Integer clientId) {

		return datadictDao.getDatadictListByTypeId(typeId,clientId);
	}
	
	public List<Datadict> getDatadictListByTypeIdAndDate(Integer typeId,String requestDate,Integer clientId) {
		Map map=new HashMap();
		map.put("accessString", " datadict_type_id="+typeId+" and client_id="+clientId+" and updated>=convert(datetime,'"+requestDate+"') ");
		return datadictDao.selectByExample(map);
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.base.service.DatadictService#getDatadictListByTypeValue(java.lang.String)
	 */
	
	public List<Datadict> getDatadictListByTypeValue(String value) {
		Map<String,Object>param=new HashMap<String,Object>();
		param.put("value", value);
		DatadictType dt=	datadictTypeDao.getByExample(param);
		
		if (dt.getBePublic().intValue()==DataDictPublicType.PRIVATE.getType())   //私有
			throw new ParamMissingException(getText("public.exception.parammissingexception.noclient","参数缺少,没有client参数"));
		
		return datadictDao.getDatadictListByTypeId(dt.getId(),null);
	}
	
	
	public List<Datadict> getDatadictListByTypeValue(String value,Integer clientId) {
		Map<String,Object>param=new HashMap<String,Object>();
		param.put("value", value);
		DatadictType dt=	datadictTypeDao.getByExample(param);
		
		if (dt.getBePublic().intValue()==DataDictPublicType.PUBLIC.getType())   //公有的
			clientId=null;
		
		return datadictDao.getDatadictListByTypeId(dt.getId(),clientId);
	}
	/* (non-Javadoc)
	 * @see com.delmar.base.service.DatadictService#saveDatadict(com.delmar.base.model.Datadict, java.util.List)
	 */
	public void saveDatadict(Datadict datadict,
			List<DatadictTrl> datadictTrlList) {
		this.save(datadict);
		if(datadictTrlList!=null)
		{
			for(DatadictTrl trl:datadictTrlList)
			{
				trl.setDatadictId(datadict.getId());
				trl.setIndexOrder(datadict.getIndexOrder());
				this.datadictTrlDao.save(trl);
			}
		}
		
	}
	/* (non-Javadoc)
	 * @see com.delmar.base.service.DatadictService#getDatadictTrlByTypeId(java.lang.Integer)
	 */
	public List<DatadictTrl> getDatadictTrlByTypeId(Integer typeId,String language,Integer clientId) {
		List<DatadictTrl> datadictTrlList=datadictTrlDao.getDatadictTrlByTypeId(typeId,language,clientId);
		if(datadictTrlList==null||datadictTrlList.size()==0)
		{
			datadictTrlList=new ArrayList<DatadictTrl>();
			List<Datadict> datadictList=this.getDatadictListByTypeId(typeId,clientId);
			for(Datadict dict:datadictList)
			{
				DatadictTrl trl=new DatadictTrl();
				trl.setDatadictId(dict.getId());
				trl.setName(dict.getName());
				trl.setLanguage(language);
				trl.setValue(dict.getValue());
				datadictTrlList.add(trl);
			}
		}
		return datadictTrlList;
	}
	
	public List<DatadictTrl> getDatadictTrlByTypeIdAndDate(Integer typeId,String requestDate,String language,Integer clientId) {
		List<DatadictTrl> datadictTrlList=datadictTrlDao.getDatadictTrlByTypeIdAndDate(typeId,requestDate,language,clientId);
		if(datadictTrlList==null||datadictTrlList.size()==0)
		{
			datadictTrlList=new ArrayList<DatadictTrl>();
			List<Datadict> datadictList=this.getDatadictListByTypeIdAndDate(typeId,requestDate,clientId);
			for(Datadict dict:datadictList)
			{
				DatadictTrl trl=new DatadictTrl();
				trl.setDatadictId(dict.getId());
				trl.setName(dict.getName());
				trl.setLanguage(language);
				trl.setValue(dict.getValue());
				datadictTrlList.add(trl);
			}
		}
		return datadictTrlList;
	}	
	/* (non-Javadoc)
	 * @see com.delmar.base.service.DatadictService#getDatadictTrlByValue(java.lang.String)
	 */
	
	public List<DatadictTrl> getDatadictTrlByValue(String value,String language) {
		Map<String,Object>param=new HashMap<String,Object>();
		param.put("value", value);
		DatadictType dt=	datadictTypeDao.getByExample(param);
		
		if (dt.getBePublic().intValue()==1)   //私有
			throw new ParamMissingException(getText("public.exception.parammissingexception.noclient","参数缺少,没有client参数"));
		
		
		return getDatadictTrlByTypeId(dt.getId(),language,null);
	}
	
	
	public List<DatadictTrl> getDatadictTrlByValue(String value,String language,Integer clientId) {
		Map<String,Object>param=new HashMap<String,Object>();
		param.put("value", value);

		DatadictType dt=	datadictTypeDao.getByExample(param);
		
		if (dt.getBePublic().intValue()==0)   //私有
			clientId=null;
			
		return getDatadictTrlByTypeId(dt.getId(),language,clientId);
	}
	
	/** 
	 * @Title:        getTrlByDataId 
	 * @Description:  TODO
	 * @param:        @param dataId
	 * @param:        @param language
	 * @param:        @return    
	 * @return:       DatadictTrl    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年5月29日 下午4:22:06 
	 */
	public DatadictTrl getTrlByDataId(Integer dataId,String language)
	{
		Map<String,Object>param=new HashMap<String,Object>();		
		param.put("datadictId", dataId);
	    param.put("language",language);
		
		DatadictTrl dataTrl=datadictTrlDao.getByExample(param);
		if(dataTrl==null)
		{
			Datadict datadict=this.selectByPrimaryKey(dataId);
			if (datadict!=null)
			{
				dataTrl=new DatadictTrl();
				dataTrl.setLanguage(language);
				dataTrl.setDatadict(datadict);
				dataTrl.setDatadictId(datadict.getId());
				dataTrl.setName(datadict.getName());
				dataTrl.setValue(datadict.getValue());
			}
		};
		
		
		return	dataTrl;
				
	}
	
	
	public String getTransName(Integer dataId,String language)
	{
		DatadictTrl datadict=this.getTrlByDataId(dataId, language);
		if (datadict==null)
			return "";
		else
			return datadict.getName();
	}
	
	
	public void saveIndexOrder(Integer newIndexOrder,Integer newId,Integer otherIndexOrder,Integer otherId)
	{
	    
	    this.datadictDao.updateIndexOrder(newIndexOrder, newId);
	    this.datadictTrlDao.updateIndexOrder(newIndexOrder, newId);
	    
	    this.datadictDao.updateIndexOrder(otherIndexOrder, otherId);
	    this.datadictTrlDao.updateIndexOrder(otherIndexOrder, otherId);
				
		
	}

	
}
