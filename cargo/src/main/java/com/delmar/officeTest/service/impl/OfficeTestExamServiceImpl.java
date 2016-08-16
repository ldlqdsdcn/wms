package com.delmar.officeTest.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.crm.dao.CustomerDao;
import com.delmar.crm.model.Customer;
import com.delmar.officeTest.dao.OfficeTestExamDao;
import com.delmar.officeTest.model.OfficeTestBank;
import com.delmar.officeTest.model.OfficeTestExam;
import com.delmar.officeTest.model.OfficeTestExamDetail;
import com.delmar.officeTest.service.OfficeTestExamService;

@Service("OfficeTestExamService")
public class OfficeTestExamServiceImpl extends CoreServiceImpl<OfficeTestExam> implements OfficeTestExamService{

	@Autowired
	private OfficeTestExamDao officeTestExamDao;
	
	@Override
	protected CoreDao<OfficeTestExam> getCoreDao() {
		return officeTestExamDao;
	}
	
	public OfficeTestExam saveOrUpdate(OfficeTestExam officeTestExam) {
		
		if (officeTestExam.getId() == null) {
			officeTestExamDao.insert(officeTestExam);
    	} else {
    		officeTestExamDao.update(officeTestExam);
    	}
		
		return officeTestExam;
	}

	public List<OfficeTestExam> selectByCustom(Map<String, Object> param) {
		return officeTestExamDao.selectByCustom(param);
	}

}
