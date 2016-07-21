package com.aebiz.b2b2c.product.productbrand.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.product.productbrand.dao.ProductBrandDAO;
import com.aebiz.b2b2c.product.productbrand.service.ProductBrandService;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandModel;
import com.aebiz.b2b2c.product.productbrand.vo.ProductBrandQueryModel;

/**
 * 商品品牌管理业务实现 商品品牌是用来发布商品时，所要选择的和前台列表页用来筛选的 现在的品牌有子品牌的概念，比如可口可乐，下面有雪碧、果粒橙
 * 该类主要实现增删查改的业务
 * 
 * @author huangpinpin
 * 
 */
@Service
@Transactional
public class ProductBrandServiceImpl extends
		BaseServiceImpl<ProductBrandModel, ProductBrandQueryModel> implements
		ProductBrandService {
	private ProductBrandDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	private FileService fileService;

	@Autowired
	public void setMyDao(ProductBrandDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProductBrandModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(ProductBrandModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(ProductBrandModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据文件名得到文件路径
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public String getUrlByFileName(String fileName) {
		if (StringUtil.isEmpty(fileName)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(fileName);
		if (file != null) {
			return file.getRemotePaths();
		}
		return "";
	}

	/**
	 * 通过父分类uuid查询所属子分类集合
	 * 
	 * @param productBrandUuid
	 * @return
	 */
	public List<ProductBrandQueryModel> getSubProductBrandsByUuid(
			String productBrandUuid) {
		return myDao.getSubProductBrandsByUuid(productBrandUuid);
	}

	/**
	 * 根据品牌uuid,获取品牌名称
	 * 
	 * @param uuid
	 * @return
	 */
	public String getBrandNameByUuid(String uuid) {
		return myDao.getBrandNameByUuid(uuid);
	}
	
	/**
	 * 根据品牌uuid集合,获取品牌名称集合
	 * @param uuid
	 * @return
	 */
	public List<String> getBrandNamesByUuids(List<String> uuids){
		return myDao.getBrandNamesByUuids(uuids);
	}
	
	
	/**
	 * 根据品牌uuid集合,获取品牌集合
	 * @param uuid
	 * @return
	 */
	public List<ProductBrandModel> getByUuids(List<String> uuids){
		return myDao.getByUuids(uuids);
	}

}