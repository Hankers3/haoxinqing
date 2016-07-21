package com.aebiz.b2b2c.customermgr.jobinterface;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;

import com.aebiz.b2b2c.baseframework.servicelocator.ServiceLocator;
import com.aebiz.b2b2c.order.accountkey.service.AccountKeyService;
import com.aebiz.b2b2c.order.accountkey.service.impl.AccountKeyServiceImpl;

/**
 * 
 * @Description: (定时修改account_key里面的值的定时任务)    
 * @author XP  
 * @date 2016-1-26 下午1:38:39
 */
public class AutoUpdateAccountValueJobService implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
		        AccountKeyService addIntegralService = (AccountKeyServiceImpl) ServiceLocator
					.getInstance().getCtx().getBean("accountKeyServiceImpl");
			addIntegralService.updateAccountVlue();

		} catch (BeansException e) {
			e.printStackTrace();
		}

	}

}
