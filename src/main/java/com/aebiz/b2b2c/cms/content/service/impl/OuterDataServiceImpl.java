package com.aebiz.b2b2c.cms.content.service.impl;

import com.aebiz.b2b2c.cms.content.outerdata.OuterDataSource;
import com.aebiz.b2b2c.cms.content.service.ContentService;
import com.aebiz.b2b2c.cms.content.service.OuterDataService;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 孙超 on 15/12/23 and { 医脉通接口实现 }
 */
@Service
public class OuterDataServiceImpl implements OuterDataService {

	/*
	 * 外部数据源 *
	 */
	private List<OuterDataSource> sources = new ArrayList<>();

	@Autowired
	private ContentService contentService;

	@Override
	public void updateData() {
		Iterator<OuterDataSource> iter = sources.iterator();
		while (iter.hasNext()) {
			OuterDataSource out = iter.next();
			List<ContentModel> models = out.pullOuterData();
			System.out.println("==================models==========="+models.size());
			for (int i = 0; i < models.size(); i++) {
				// TODO INSERT TO DB
				contentService.create(models.get(i));
			}
		}
	}

	@Override
	public void addOuterSource(OuterDataSource source) {
		this.sources.add(source);
	}
}
