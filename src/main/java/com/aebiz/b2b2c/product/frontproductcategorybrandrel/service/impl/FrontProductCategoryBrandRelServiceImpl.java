package com.aebiz.b2b2c.product.frontproductcategorybrandrel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.product.frontproductcategorybrandrel.dao.FrontProductCategoryBrandRelDAO;
import com.aebiz.b2b2c.product.frontproductcategorybrandrel.service.FrontProductCategoryBrandRelService;
import com.aebiz.b2b2c.product.frontproductcategorybrandrel.vo.FrontProductCategoryBrandRelModel;
import com.aebiz.b2b2c.product.frontproductcategorybrandrel.vo.FrontProductCategoryBrandRelQueryModel;

@Service
@Transactional
public class FrontProductCategoryBrandRelServiceImpl
		extends
		BaseServiceImpl<FrontProductCategoryBrandRelModel, FrontProductCategoryBrandRelQueryModel>
		implements FrontProductCategoryBrandRelService {
	private FrontProductCategoryBrandRelDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(FrontProductCategoryBrandRelDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(FrontProductCategoryBrandRelModel m) {
		m.setUuid(us.getNextUuid("ProductCategoryBrandRel", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(FrontProductCategoryBrandRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(FrontProductCategoryBrandRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 保存分类和品牌的关联关系
	 * 
	 * @param categoryUuid
	 * @param brands
	 */
	public void saveRelate(String categoryUuid, List<String> brands) {
		if (brands != null && brands.size() > 0) {
			for (String brandUuid : brands) {
				FrontProductCategoryBrandRelModel productCategoryBrandRelModel = new FrontProductCategoryBrandRelModel();
				productCategoryBrandRelModel.setCategoryUuid(categoryUuid);
				productCategoryBrandRelModel.setBrandUuid(brandUuid);
				this.create(productCategoryBrandRelModel);
			}
		}
	}

	/**
	 * 根据前台分类id获取分类关联的品牌id集合
	 * 
	 * @param cateUuid
	 * @return
	 */
	public List<String> getBrandIdsByCateUuid(String cateUuid) {
		return this.myDao.getBrandIdsByCateUuid(cateUuid);
	}

	/**
	 * 根据分类id删除分类和品牌的关联关系
	 * 
	 * @param cateUuid
	 */
	public void deleteByCateUuid(String cateUuid) {
		this.myDao.deleteByCateUuid(cateUuid);
	}

	/**
	 * 根据品牌uuid集合和分类uuid,删除关系数据
	 * 
	 * @param brandUuids
	 *            品牌uuid集合
	 * @param categoryUuid
	 *            分类uuid
	 */
	public void deleteByBrandUuidsAndCategoryUuid(List<String> brandUuids,
			String categoryUuid) {
		myDao.deleteByBrandUuidsAndCategoryUuid(brandUuids, categoryUuid);
	}
}