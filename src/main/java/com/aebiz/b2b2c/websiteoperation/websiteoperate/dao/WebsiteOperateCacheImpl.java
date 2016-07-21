package com.aebiz.b2b2c.websiteoperation.websiteoperate.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.websiteoperation.common.WebsiteOperateCacheConstant;
import com.aebiz.b2b2c.websiteoperation.quizresultdocrel.vo.QuizResultDocRelModel;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.vo.WebsiteOperateModel;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.vo.WebsiteOperateQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class WebsiteOperateCacheImpl extends
		BaseMemcachedCache<WebsiteOperateModel, WebsiteOperateQueryModel>
		implements WebsiteOperateDAO {

	@Resource(name = WebsiteOperateCacheConstant.WEBSITE_OPERATE_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private WebsiteOperateDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public WebsiteOperateCacheImpl() {
		super(WebsiteOperateCacheConstant.WEBSITE_OPERATE_WB);
	}
	
	@Override
	public void update(WebsiteOperateModel m) {
		this.dao.update(m);
		this.mcc.delete(WebsiteOperateCacheConstant.WEBSITE_QUIZRESULTDOCREL_KEY + m.getUuid());
	}
	
	
	public WebsiteOperateModel getByResourceKey(String resourceKey) {
		// 1：查缓存，如果有就从缓存取值并返回
		Object obj = mcc
				.get(WebsiteOperateCacheConstant.WEBSITE_OPERATE_RESOURCE_KEY
						+ resourceKey);

		if (obj != null) {
			return (WebsiteOperateModel) obj;
		}

		// 2：缓存没有，从db中取
		WebsiteOperateModel m = (WebsiteOperateModel) dao
				.getByResourceKey(resourceKey);
		// 3：把这个数据设置到缓存中
		mcc.set(WebsiteOperateCacheConstant.WEBSITE_OPERATE_RESOURCE_KEY
				+ resourceKey, m);

		return m;
	}

	/**
	 * 校验编号是否重复
	 */
	@Override
	public boolean checkKeyExist(String resourceKey) {
		WebsiteOperateModel wm = this.getByResourceKey(resourceKey);

		if (wm != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 通过运营参数key值获取value值
	 */
	@Override
	public String getValueByKey(String resourceKey) {

		WebsiteOperateModel wm = this.getByResourceKey(resourceKey);

		if (wm != null) {
			return wm.getParamValue();
		}

		return "";
	}

	/**
	 * 通过运营参数key更新value值
	 */
	@Override
	public void updateValueByKey(String resourceKey, String value) {
		dao.updateValueByKey(resourceKey, value);

		// 通过key获得对象
		WebsiteOperateModel wm = this.getByResourceKey(resourceKey);

		if (wm != null) {
			// 更新uuid缓存
			mcc.delete(WebsiteOperateCacheConstant.WEBSITE_OPERATE_WB
					+ wm.getUuid());
		}

		// 更新resourceKey
		mcc.delete(WebsiteOperateCacheConstant.WEBSITE_OPERATE_RESOURCE_KEY
				+ resourceKey);

	}

	/**
	 * 通过模块名称获取参数列表
	 */
	@Override
	public List<WebsiteOperateModel> getWebsiteOperateModelListByModuleName(
			String moduleName) {
		return dao.getWebsiteOperateModelListByModuleName(moduleName);
	}
}
