package com.delmar.officeTest.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.officeTest.dao.OfficeTestExamDetailDao;
import com.delmar.officeTest.model.OfficeTestBank;
import com.delmar.officeTest.model.OfficeTestExamDetail;
import com.delmar.officeTest.service.OfficeTestExamDetailService;

@Service("OfficeTestExamDetailService")
public class OfficeTestExamDetailServiceImpl extends CoreServiceImpl<OfficeTestExamDetail> implements OfficeTestExamDetailService{

	@Autowired
	private OfficeTestExamDetailDao officeTestExamDetailDao;
	
	@Override
	protected CoreDao<OfficeTestExamDetail> getCoreDao() {
		return officeTestExamDetailDao;
	}

	public OfficeTestExamDetail saveOrUpdate(OfficeTestExamDetail officeTestExamDetail) {
		
		if (officeTestExamDetail.getId() == null) {
			officeTestExamDetailDao.insert(officeTestExamDetail);
    	} else {
    		officeTestExamDetailDao.update(officeTestExamDetail);
    	}
		
		return officeTestExamDetail;
	}

	public List<OfficeTestExamDetail> searchQuestionTestDetail(Map<String, Object> parm) {
		return officeTestExamDetailDao.searchQuestionTestDetail(parm);
	}

	public List<OfficeTestExamDetail> selectByParm(Map<String, Object> param) {
		return officeTestExamDetailDao.selectByParm(param);
	}
	

}
