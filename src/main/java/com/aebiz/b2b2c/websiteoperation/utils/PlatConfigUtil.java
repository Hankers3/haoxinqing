package com.aebiz.b2b2c.websiteoperation.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.websiteoperation.paltcongfig.service.PaltCongfigService;
import com.aebiz.b2b2c.websiteoperation.paltcongfig.vo.PaltCongfigModel;
import com.aebiz.b2b2c.websiteoperation.paltcongfig.vo.PaltCongfigQueryModel;

@Component
public class PlatConfigUtil {
	
	private static PaltCongfigService congfigService ;
	@Autowired
	public void setCongfigService(PaltCongfigService congfigService) {
		this.congfigService = congfigService;
	}

	public static  String getConfig(String codeName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		List<PaltCongfigModel> list = congfigService
				.getByCondition(new PaltCongfigQueryModel());
		PaltCongfigModel congfigModel = null;
		if (list != null && list.size() > 0) {
			congfigModel = list.get(0);
		}

		if (congfigModel == null) {
			congfigModel = congfigService
					.createPlatConfig(new PaltCongfigModel());
		}
		
		Method mth;
		try {
			mth = congfigModel.getClass().getDeclaredMethod(
					"get" + codeName.substring(0, 1).toUpperCase()
							+ codeName.substring(1), new Class[0]);
			Object object =  mth.invoke(congfigModel, new Object[0]);
			return (String)object;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "" ;
	}
	
}
