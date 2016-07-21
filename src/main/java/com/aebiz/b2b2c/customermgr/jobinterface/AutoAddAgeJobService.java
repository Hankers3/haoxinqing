package com.aebiz.b2b2c.customermgr.jobinterface;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;

import com.aebiz.b2b2c.baseframework.servicelocator.ServiceLocator;
import com.aebiz.b2b2c.customermgr.customerinfo.service.CustomerInfoService;
import com.aebiz.b2b2c.customermgr.customerinfo.service.impl.CustomerInfoServiceImpl;

/**
 * 成功订单自动送积分的定时任务
 * 
 * @author lej
 * 
 */
public class AutoAddAgeJobService implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			CustomerInfoService addIntegralService = (CustomerInfoServiceImpl) ServiceLocator
					.getInstance().getCtx().getBean("customerInfoServiceImpl");
			addIntegralService.updateAgeByBirthday();

		} catch (BeansException e) {
			e.printStackTrace();
		}

	}

}
