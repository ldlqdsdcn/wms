/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.CoreService;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.sys.dao.JavabeanDao;
import com.delmar.sys.dao.OperatorDao;
import com.delmar.sys.dao.PageDao;
import com.delmar.sys.dao.UserDao;
import com.delmar.sys.dao.UserRoleDao;
import com.delmar.sys.dao.UsergroupAccessDao;
import com.delmar.sys.dao.UserorgAccessDao;
import com.delmar.sys.model.Javabean;
import com.delmar.sys.model.Operator;
import com.delmar.sys.model.Page;
import com.delmar.sys.model.User;
import com.delmar.sys.model.UserContent;
import com.delmar.sys.model.UserRole;
import com.delmar.sys.model.UsergroupAccess;
import com.delmar.sys.model.UserorgAccess;
import com.delmar.sys.service.UserService;

/**
 * @author 刘大磊 22014-12-23 14:47:25
 */
@Service("userService")
public class UserServiceImpl extends CoreServiceImpl<User> implements
		UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private OperatorDao operatorDao;
	@Autowired
	private PageDao pageDao;
	@Autowired
	private JavabeanDao javaBeanDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private UserorgAccessDao userorgAccessDao;
	@Autowired
	private UsergroupAccessDao usergroupAccessDao;

	@Override
	protected CoreDao<User> getCoreDao() {
		return userDao;
	}
	/** (non-Javadoc)
	 * @see com.delmar.sys.service.UserService#getUserByUsername(String)
	 */
	public User getUserByUsername(String username) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("username", username);
		List<User> users=userDao.getUsers(map);
		if(users==null||users.size()==0)
		{
			return null;
		}
		return 	users.get(0);
	}
	/** (non-Javadoc)
	 * @see com.delmar.sys.service.UserService#getUserPrivileges(Integer)
	 */
	public UserContent getUserPrivileges(Integer userId) {
		UserContent uc=new UserContent();
		
		List<Operator> operatorList=operatorDao.selectByExample(null);
		for(Operator oper:operatorList)
		{
			List<Page> pageList=pageDao.selectPageByPrivileges(userId, oper.getValue());
			List<Javabean>javabeanList=javaBeanDao.selectJavabeanByPrivileges(userId, oper.getValue());
			uc.JAVABEANPRIVILEGES.put( oper.getValue(), javabeanList);
			uc.PAGEPRIVILEGES.put(oper.getValue(), pageList);
		}
		return uc;
	}
	/** (non-Javadoc)
	 * @see com.delmar.sys.service.UserService#validateUsernameExist(String, Integer)
	 */
	public boolean validateUsernameExist(String username, Integer id) {
		Map param=new HashMap();
		param.put("username", username);
		param.put("id", id);
		
		Integer  count=userDao.validateUsername(username, id);
		if(count>0)return true;
		return false;
	}

	/** (non-Javadoc)
	 * @see com.delmar.sys.service.UserService# saveUser(com.delmar.sys.model.User, java.util.List, java.util.List, java.util.List)
	 */
	public Integer saveUser(User user, List<Integer> roles,
			String[] orgList,String[] saveGroupList) {
		boolean isnew=user.isnew();
		
		if(isnew)
		{
			/*Setting setting=new Setting();
			setting.setUserId(userId);
			setting.setLanguage(1);//zh_CN en_US
			setting.setApproveWarn("Y");
			setting.setRejectWarn("Y");
			setting.setListRows(20);
			setting.setStyleId(1);
			setting.setReceiveWarn("Y");
			settingDao.insert(setting);*/
		
			
		}
		
		this.userDao.save(user);
		Integer userId=user.getId();

		Map<String,Object> param=new HashMap<String,Object>();
		param.put("userId", userId);
		//this.userDepartmentDao.deleteByExample(param);
		this.userRoleDao.deleteByExample(param);
		this.userorgAccessDao.deleteByExample(param);
		this.usergroupAccessDao.deleteByExample(param);
		for(Integer id:roles)
		{
			UserRole ur=new UserRole();
			ur.setRoleId(id);
			ur.setUserId(userId);
			this.userRoleDao.save(ur);
		}
		if(orgList!=null)
		for(String orgId:orgList)
		{
			UserorgAccess ua=new UserorgAccess();
			ua.setOrgId(Integer.parseInt(orgId));
			ua.setUserId(userId);
			//uo.setUserId(userId);
			this.userorgAccessDao.save(ua);
		}
		if(saveGroupList!=null)
		for(String groupId:saveGroupList)
		{
			UsergroupAccess ua=new UsergroupAccess();
			ua.setUsergroupId(Integer.parseInt(groupId));
			ua.setUserId(userId);
			usergroupAccessDao.save(ua);
		}
		
		return userId;
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.UserService#getUsers(java.util.Map)
	 */

	public List getUsers(Map<String, Object> param) {
		return userDao.getUsers(param);
	}

	public User getUserById(Integer userId)
	{
		return userDao.getUserById(userId);
	}
	
	public List getUserOrgByRoleId(Integer roleId)
	{
		return userDao.getUserOrgByRoleId(roleId);
	}
	
	public List getNonUserOrgByRoleId(Integer roleId)
	{
		return userDao.getNonUserOrgByRoleId(roleId);
	}
	
	public List getUserSubstituteById(Integer userId)
	{
		return userDao.getUserSubstituteById(userId);
	}	
	
	public List getNonUserSubstituteById(Integer userId)
	{
		return userDao.getNonUserSubstituteById(userId);
	}	
	
	
	public void deleteById(Integer id)
	{
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.UserService#getNonUserOrgByUsergroupId(java.lang.Integer)
	 */
	public List<User> getNonUserOrgByUsergroupId(Integer usergroupId,Integer orgId) {
		return userDao.getNonUserOrgByUsergroupId(usergroupId,orgId);
	}
}
