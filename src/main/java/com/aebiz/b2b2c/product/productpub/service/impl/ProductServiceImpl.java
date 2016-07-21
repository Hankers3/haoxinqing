package com.aebiz.b2b2c.product.productpub.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.product.productmain.service.ProductMainService;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.product.productmainbaseprice.service.ProductMainBasePriceService;
import com.aebiz.b2b2c.product.productmainbaseprice.vo.ProductMainBasePriceModel;
import com.aebiz.b2b2c.product.productmodifylog.service.ProductModifyLogService;
import com.aebiz.b2b2c.product.productpub.dao.ProductDAO;
import com.aebiz.b2b2c.product.productpub.service.ProductService;
import com.aebiz.b2b2c.product.productpub.vo.ProductModel;
import com.aebiz.b2b2c.product.productpub.vo.ProductQueryModel;

@Service
@Transactional
public class ProductServiceImpl extends BaseServiceImpl<ProductModel,ProductQueryModel> implements ProductService {
	private ProductDAO myDao = null;
	
	@Autowired
	private ProductMainService mainService;
	
	@Autowired
	private ProductMainBasePriceService priceService;
	
	@Autowired
	private ProductModifyLogService logService;
	
	
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ProductDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}
 
	public List<ProductModel> getProductListByCondition(ProductQueryModel paramQM, int paramInt1, int paramInt2){
		return myDao.getProductListByCondition(true, paramQM, paramInt1, paramInt2);
	}
	
	public int getCount(ProductQueryModel qm) {
		return myDao.getCount(qm);
	}
	
	public ProductModel getByUuid(String uuid) {
		ProductModel model=new ProductModel();
		//获取商品信息
		ProductMainBasePriceModel productPrice = priceService.getProductMainBasePriceModelByProductUuid(uuid);
		ProductMainModel productMain=mainService.getByUuid(uuid);
		//设置商品信息
		model.setProductMain(productMain);
	
		return model;
	}
	
	/**
	 * 删除商品
	 */
	@Override
	public void deletes(List<String> needDeleteUuids) {
		// 删除价格信息
		priceService.deletesByProductUuids(needDeleteUuids);
	
		mainService.deletes(needDeleteUuids);
	
	}
	
	public void update(ProductModel m,String type){
		// 获取商品主要信息
		ProductMainModel productMain=m.getProductMain();
		
		
		mainService.update(productMain);
		
		
	}
	
	/**
	 * 发布商品
	 * @param m
	 */
	public String save(ProductModel m,String type) {
		// 获取商品主要信息
		ProductMainModel productMain=m.getProductMain();
		String uuid =mainService.create(productMain);
		// 设置创建时间
		productMain.setCreateTime(DateFormatHelper.getNowTimeStr());
		return uuid;
		
	}
	
	/**
	 * 设置商品上架,根据所选productUuid集合
	 * @param productUuids 商品productUuid集合
	 * 
	 */
	public List<String> updateStateShelves(List<String> productUuids){
		List<String> errorList=new ArrayList<String>();
		for(int i=0;i<productUuids.size();i++){
			String productUuid=productUuids.get(i);
			if(StringUtil.isEmpty(productUuid)){
				continue;
			}
			ProductMainModel productMain = mainService.getProductMainModelByProductUuid(productUuid);
			//判断该商品是否存在
			if(productMain==null){
				//TODO 记录错误信息
				errorList.add(MessageHelper.getMessage("productmain.qm.productNo")
						+ productMain.getProductNo()
						+ MessageHelper.getMessage("productList.error.noProduct"));
				continue;
			}
			
			//如果商品审核状态为不通过，则不能上架该商品
			if(productMain.NO_PASS.equals(productMain.getAuditState())){
				//TODO 记录错误信息
				errorList.add(MessageHelper.getMessage("productmain.qm.productNo")
						+ productMain.getProductNo()
						+ MessageHelper.getMessage("productList.error.productState"));
				continue;
			}
			
			mainService.updateStateShelves(productMain.getUuid());
			//记录日志
			ProductMainModel newProductMain = mainService.getProductMainModelByProductUuid(productUuid);
			logService.createLog(productMain, newProductMain, 0, 0);
		}
		
		return errorList;
	}
	
	/**
	 * 设置商品下架,根据所选productUuid集合
	 * @param productUuids 商品productUuid集合
	 * 
	 */
	public List<String> updateStateUndercarriadge(List<String> productUuids){
		List<String> errorList=new ArrayList<String>();
		for(int i=0;i<productUuids.size();i++){
			String productUuid=productUuids.get(i);
			if(StringUtil.isEmpty(productUuid)){
				continue;
			}
			ProductMainModel productMain = mainService.getProductMainModelByProductUuid(productUuid);
			//判断该商品是否存在
			if(productMain==null){
				//TODO 记录错误信息
				errorList.add(MessageHelper.getMessage("productmain.qm.productNo")
						+ productMain.getProductNo()
						+ MessageHelper.getMessage("productList.error.noProduct"));
				continue;
			}
			
			mainService.updateStateUndercarriadge(productMain.getUuid());
			//记录日志
			ProductMainModel newProductMain = mainService.getProductMainModelByProductUuid(productUuid);
			logService.createLog(productMain, newProductMain, 0, 0);
		}
		
		return errorList;
 
	}

}