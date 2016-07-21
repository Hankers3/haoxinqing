package com.aebiz.b2b2c.customermgr.jobinterface;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;

import com.aebiz.b2b2c.baseframework.servicelocator.ServiceLocator;
import com.aebiz.b2b2c.visitprecept.visitpreceptpush.service.VisitPreceptPushService;
import com.aebiz.b2b2c.visitprecept.visitpreceptpush.service.impl.VisitPreceptPushServiceImpl;

/**
 * 随访周期消息推送  按随访周期 进行消息提醒推送 用来提醒患者添加随访表单
 * @author xueli
 *
 */
public class AutoVisitMessagePush  implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			VisitPreceptPushService visitPreceptPushService = (VisitPreceptPushServiceImpl) ServiceLocator
					.getInstance().getCtx().getBean("visitPreceptPushServiceImpl");
			visitPreceptPushService.pushVisitMeaasge();
		} catch (BeansException e) {
			e.printStackTrace();
		}
		
	}

}
