package com.aebiz.b2b2c.cms.content.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.aebiz.b2b2c.baseframework.servicelocator.ServiceLocator;
import com.aebiz.b2b2c.cms.content.outerdata.OuterDataSource;
import com.aebiz.b2b2c.cms.content.outerdata.impl.YmtDataSource;
import com.aebiz.b2b2c.cms.content.service.OuterDataService;
import com.aebiz.b2b2c.cms.content.service.impl.OuterDataServiceImpl;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;

/**
 * Created by sunchao on 15/12/24 and { 外部资源导入调度任务 }
 */
public class OuterDataJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
       
    	OuterDataService nouterDataService = (OuterDataServiceImpl) ServiceLocator
				.getInstance().getCtx().getBean("outerDataServiceImpl");
    	
     	OuterDataSource<ContentModel> source = new YmtDataSource();
     	nouterDataService.addOuterSource(source);
     	nouterDataService.updateData();
        
        
    }
}
