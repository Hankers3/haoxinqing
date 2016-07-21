package com.aebiz.b2b2c.userfront.plattimagelibrary.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.userfront.common.UserFrontCacheConstant;
import com.aebiz.b2b2c.userfront.plattimagelibrary.vo.PlattImageLibraryModel;
import com.aebiz.b2b2c.userfront.plattimagelibrary.vo.PlattImageLibraryQueryModel;
import com.danga.MemCached.MemCachedClient;

@Primary
@Repository
public class PlattImageLibraryCacheImpl
		extends
		BaseMemcachedCache<PlattImageLibraryModel, PlattImageLibraryQueryModel>
		implements
			PlattImageLibraryDAO {
	
	@Resource(name = UserFrontCacheConstant.USERFRONT_CLIENT_NAME)
	private MemCachedClient mcc;
	
	@Autowired
	private PlattImageLibraryDAO dao;
	
	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}
	
	public PlattImageLibraryCacheImpl() {
		super(UserFrontCacheConstant.USERFRONT_PLATIMAGE_KEY);
	}
	
	@Override
	public void update(PlattImageLibraryModel m) {
		dao.update(m);
		this.mcc.delete(UserFrontCacheConstant.USERFRONT_PLATIMAGE_KEY+m.getUuid());
	}
	/**
	 * 根据分类的uuid获取该分类下的所有图片
	 * 
	 * @param categoryUuid
	 * @return List<PlattImageLibraryModel>
	 */
	public List<PlattImageLibraryModel> getPlattImageLibraryModelsByCategoryUuid(
			String categoryUuid,int nowPage ,int pageShow){
			return dao.getPlattImageLibraryModelsByCategoryUuid(categoryUuid, nowPage, pageShow);
	}
	
	/**
	 * 根据分类的uuid获取该分类下的所有图片总数
	 * 
	 * @param categoryUuid
	 * @return List<PlattImageLibraryModel>
	 */
	public int getPlattImageLibraryModelsByCategoryUuid(
			String categoryUuid){
		//从缓存中获取所有的图片对象
		List<PlattImageLibraryModel> list =  this.getByCondition(new PlattImageLibraryQueryModel());
		int count = 0;
		if(list != null && list.size() > 0){
			for (PlattImageLibraryModel plattImageLibraryModel : list) {
				if(categoryUuid.equals(plattImageLibraryModel.getCategoryUuid())){
					count = count + 1;
				}
			}
		}
		return count;
	}

	@Override
	public List<PlattImageLibraryModel> getPlattImageLibraryModelListByCategoryUuid(
			String categoryUuid) {
		
		return dao.getPlattImageLibraryModelListByCategoryUuid(categoryUuid);
	}
}
