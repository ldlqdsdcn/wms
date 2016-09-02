/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.sys.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.delmar.sys.def.ScheduledType;
import com.delmar.sys.service.ScheduledLogService;
import com.delmar.sys.service.ScheduledService;
import com.delmar.utils.DateTimeDecorator;
import com.delmar.utils.DmLog;
import com.delmar.utils.StringUtil;

/**
 * @author 刘大磊 2015年8月24日 下午4:51:36
 */
@Component
public class DelmarTask implements BeanFactoryAware {
	private static final DmLog logger = DmLog.getLogger(DelmarTask.class);
	private BeanFactory beanFactory;
	@Autowired
	private ScheduledService scheduledService;
	@Autowired
	private ScheduledLogService scheduledLogService;


	public void runTask() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("accessString", "isactive='Y'");

		List<com.delmar.sys.model.Scheduled> list = scheduledService
				.selectByExample(param);

		for (com.delmar.sys.model.Scheduled s : list) {
			taskRun(s);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.BeanFactoryAware#setBeanFactory(org
	 * .springframework.beans.factory.BeanFactory)
	 */
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;

	}

	private void taskRun(com.delmar.sys.model.Scheduled s) {
		Date now = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(now);
		if (s.getRunType() == ScheduledType.Time.getType()) {
			String year = s.getTimeYear();
			String week = s.getTimeWeek();
			String month = s.getTimeMonth();
			String day = s.getTimeDay();
			String time = s.getTimeTime();
			if (StringUtil.isNotEmpty(year)) {
				int nowyear = gc.get(Calendar.YEAR);
				if (nowyear != Integer.parseInt(year)) {
					return;
				}
			}
			if (StringUtil.isNotEmpty(week)) {
				int dayOfWeek = gc.get(Calendar.DAY_OF_WEEK);
				int intweek = Integer.parseInt(week);
				if (intweek == 7) {
					intweek = 0;
				} else {
					intweek = intweek + 1;
				}
				if (dayOfWeek != intweek) {
					return;
				}
			}
			if (StringUtil.isNotEmpty(month)) {
				int currentMonth = gc.get(Calendar.MONTH) + 1;
				if (Integer.parseInt(month) != currentMonth) {
					return;
				}
			}
			if (StringUtil.isNotEmpty(day)) {
				int currentDay = gc.get(Calendar.DAY_OF_MONTH);
				if (Integer.parseInt(day) != currentDay) {
					return;
				}
			}
			if (StringUtil.isNotEmpty(time)) {
				String timePatten = DateTimeDecorator.getTimeFormat(now);
				if (!timePatten.equals(time)) {
					return;
				}
			}
			if (System.currentTimeMillis() > s.getNextRunTime()) {
				runAvailableTask(s);
			}
		} else {
			if (System.currentTimeMillis() > s.getNextRunTime()) {
				runAvailableTask(s);
			}
		}

		// DateTimeDecorator
	}

	private void runAvailableTask(com.delmar.sys.model.Scheduled s) {
		try {
			Runnable runnable = (Runnable) beanFactory.getBean(Class.forName(s
					.getClassName()));
			Thread tr = new Thread(runnable);
			tr.start();
		} catch (ClassNotFoundException e) {
			logger.error(e, e);
		}
	}

	public static void main(String[] args) {
		Date now = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		System.out.println("gc.get(Calendar.DAY_OF_WEEK)="
				+ gc.get(Calendar.DAY_OF_WEEK));
		System.out.println("gc.get(Calendar.MONTH)=" + gc.get(Calendar.MONTH));
		System.out.println("gc.get(Calendar.DAY_OF_MONTH)="
				+ gc.get(Calendar.DAY_OF_MONTH));
	}
}
