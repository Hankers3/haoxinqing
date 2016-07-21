package com.aebiz.b2b2c.userfront.platimagecategory.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.userfront.common.UserFrontCacheConstant;
import com.aebiz.b2b2c.userfront.platimagecategory.vo.PlatImageCategoryModel;
import com.aebiz.b2b2c.userfront.platimagecategory.vo.PlatImageCategoryQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class PlatImageCategoryCacheImpl
		extends
		BaseMemcachedCache<PlatImageCategoryModel, PlatImageCategoryQueryModel>
		implements
			PlatImageCategoryDAO {
	@Resource(name = UserFrontCacheConstant.USERFRONT_CLIENT_NAME)
	private MemCachedClient mcc;
	
	@Autowired
	private PlatImageCategoryDAO dao;
	
	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}
	
	public PlatImageCategoryCacheImpl() {
		super(UserFrontCacheConstant.USERFRONT_PLATIMAGECATEGORY_KEY);
	}
	
	@Override
	public void update(PlatImageCategoryModel m) {
		dao.update(m);
		this.mcc.delete(UserFrontCacheConstant.USERFRONT_PLATIMAGECATEGORY_KEY+m.getUuid());
	}
	
	/**
	 * 根据图片父分类uuid查询图片子分类 该功能用于分类树展开时查询子分类
	 * 
	 * @param parentUuid
	 *            父分类uuid
	 * @return
	 */
	public List<PlatImageCategoryModel> getSubPlatImageCategoryByParentUuid(
			String parentUuid) {
		List<PlatImageCategoryModel> list = new ArrayList<PlatImageCategoryModel>();
		//从缓存中去的所有的分类
		List<PlatImageCategoryModel> all = this.getByCondition(new PlatImageCategoryQueryModel());
		if(all != null && all.size() > 0){
			for (PlatImageCategoryModel platImageCategoryModel : all) {
				if(parentUuid.equals(platImageCategoryModel.getParentUuid())){
					list.add(platImageCategoryModel);
				}
			}
		}
		return list;
	}
	
	/**
	 * 获取父分类下的所有分类的uuid集合
	 * @param parentUuid
	 * @return 
	 * List<String>
	 */
	public List<String> getSubCategory(String parentUuid){
		List<String> list = new ArrayList<String>();
		//从缓存中去的所有的分类
		List<PlatImageCategoryModel> all = this.getByCondition(new PlatImageCategoryQueryModel());
		if(all != null && all.size() > 0){
			for (PlatImageCategoryModel platImageCategoryModel : all) {
				if(parentUuid.equals(platImageCategoryModel.getParentUuid())){
					list.add(platImageCategoryModel.getUuid());
				}
			}
		}
		return list;
	}
}
