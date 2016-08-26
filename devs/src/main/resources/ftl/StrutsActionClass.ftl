/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package ${packagename};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.web.action.CoreEditPrivAction;

import ${modelpackage}.${modelname};

import ${servicepackage}.${modelname}Service;


/**
 * @author 刘大磊 ${datetime}
 */
public class ${modelname}Action extends CoreEditPrivAction {
	private ${modelname}  ${modelObjname};
	
	@Autowired
	private ${modelname}Service ${modelObjname}Service;
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "${modelObjname}";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		${modelObjname}Service.deleteByPrimaryKey(${modelObjname}.getId());
		return list();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		${modelObjname}Service.delete${modelname}List(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return ${modelObjname}.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		 ${modelObjname}= ${modelObjname}Service.selectByPrimaryKey(id);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		return ${modelObjname}Service.selectByExample(null);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		${modelObjname}=new ${modelname}();
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {

		${modelObjname}Service.save(${modelObjname});
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public ${modelname} get${modelname}() {
		return ${modelObjname};
	}

	/**
	 * @param ${modelObjname} the ${modelObjname} to set
	 */
	public void set${modelname}(${modelname} ${modelObjname}) {
		this.${modelObjname} = ${modelObjname};
	}

}
