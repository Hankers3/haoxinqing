package com.aebiz.b2b2c.customermgr.jobinterface;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;

import com.aebiz.b2b2c.baseframework.servicelocator.ServiceLocator;
import com.aebiz.b2b2c.order.orderrouting.service.OrderRoutingService;
import com.aebiz.b2b2c.order.orderrouting.service.impl.OrderRoutingServiceImpl;

/**
 * 订单自动分账
 * 
 * @author xl
 * 
 */
public class AutoOrderFzJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			OrderRoutingService ors = (OrderRoutingServiceImpl) ServiceLocator
					.getInstance().getCtx().getBean("orderRoutingServiceImpl");
			ors.orderFz();
		} catch (BeansException e) {
			e.printStackTrace();
		}

	}

}
