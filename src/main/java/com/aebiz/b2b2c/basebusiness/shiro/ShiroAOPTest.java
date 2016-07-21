package com.aebiz.b2b2c.basebusiness.shiro;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.service.SysManagerService;
import com.aebiz.b2b2c.basebusiness.systembackmgr.sysmanager.vo.SysManagerModel;

public class ShiroAOPTest {

	public static void main(String[] args) {
		ApplicationContext act = new ClassPathXmlApplicationContext(
				"applicationContext.xml", "applicationContext-shiro.xml",
				"applicationContext-shiro-authentication.xml");

		SysManagerService t = (SysManagerService) act
				.getBean("sysManagerServiceImpl");

		List<SysManagerModel> list = t.getAll(1, 1);
		System.out.println("list==" + list);
	}
}
