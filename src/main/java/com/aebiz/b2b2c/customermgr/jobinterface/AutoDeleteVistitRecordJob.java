package com.aebiz.b2b2c.customermgr.jobinterface;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;

import com.aebiz.b2b2c.baseframework.servicelocator.ServiceLocator;
import com.aebiz.b2b2c.visitprecept.visitrecord.service.VisitRecordService;
import com.aebiz.b2b2c.visitprecept.visitrecord.service.impl.VisitRecordServiceImpl;

/**
 * 
 * @Description: (定时删除无用的随访表单)    
 * @author xl
 * @date 2016-1-26 下午1:38:39
 */
public class AutoDeleteVistitRecordJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			VisitRecordService visitService = (VisitRecordServiceImpl) ServiceLocator
					.getInstance().getCtx().getBean("visitRecordServiceImpl");
			visitService.deleteVisit();

		} catch (BeansException e) {
			e.printStackTrace();
		}
	}

}
