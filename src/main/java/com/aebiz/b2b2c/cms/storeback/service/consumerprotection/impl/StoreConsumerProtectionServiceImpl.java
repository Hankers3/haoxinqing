package com.aebiz.b2b2c.cms.storeback.service.consumerprotection.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionModel;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionQueryModel;
import com.aebiz.b2b2c.cms.protectionproductrel.service.ProtectionProductRelService;
import com.aebiz.b2b2c.cms.protectionproductrel.vo.ProtectionProductRelModel;
import com.aebiz.b2b2c.cms.protectionproductrel.vo.ProtectionProductRelQueryModel;
import com.aebiz.b2b2c.cms.storeback.dao.consumerprotection.StoreConsumerProtectionDAO;
import com.aebiz.b2b2c.cms.storeback.service.consumerprotection.StoreConsumerProtectionService;
import com.aebiz.b2b2c.cms.storeback.service.protectionproductrel.StoreProtectionProductRelService;
import com.aebiz.b2b2c.cms.storeback.web.consumerprotection.vo.ConsumerProductWebModel;
import com.aebiz.b2b2c.product.interactive.product.service.ProductInteractiveService;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveModel;
import com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveQueryModel;
import com.aebiz.b2b2c.product.productmainbaseprice.service.ProductMainBasePriceService;

@Service
@Transactional
public class StoreConsumerProtectionServiceImpl extends
		BaseServiceImpl<ConsumerProtectionModel, ConsumerProtectionQueryModel>
		implements StoreConsumerProtectionService {

	private StoreConsumerProtectionDAO myDao = null;

	//消费者权益保障服务和商户商品的参与
	@Autowired
	private ProtectionProductRelService pprsService;

	//用于权益保障服务与商品的操作
	@Autowired
	private StoreProtectionProductRelService storpprsService;

	//商品信息接口
	@Autowired
	private ProductInteractiveService productInteractiveService;

	
	@Autowired
	private ProductMainBasePriceService pmbpService;

	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(StoreConsumerProtectionDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

//	/* 需要选择商品的标志 */
//	public static String NEEDCHOOSEPRODUCT = "1";

	public String create(ConsumerProtectionModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(ConsumerProtectionModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(ConsumerProtectionModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 通過商户id获取没有参与的服务保障项
	 * 
	 * @param storeUuid
	 *            商户id
	 * @return
	 */
	public List<ConsumerProtectionModel> getNonparticipant(String storeUuid) {
		List<ConsumerProtectionModel> list = this.myDao.getAttainById(storeUuid);
		List<String> ids = new ArrayList<String>();
		if (list != null && list.size() > 0) {
			for (ConsumerProtectionModel m : list) {
				ids.add(m.getUuid());
			}
		}
		/* 商户没有参与的服务保障项 */
		List<ConsumerProtectionModel> noList = this.myDao.getNonparticipantById(storeUuid, ids);
		return noList;
	}

	/**
	 * 通過商户id获取参与的服务保障项,并且获得参与的商品的数量
	 * 
	 * @param storeUuid
	 *            商户id
	 * @return List&ltConsumerProtectionModel&gt
	 */
	public List<ConsumerProtectionModel> getAttain(String storeUuid) {
		/* 商户参与的服务保障 */
		List<ConsumerProtectionModel> list = this.myDao.getAttainById(storeUuid);
		if (list != null && list.size() > 0) {
			for (ConsumerProtectionModel c : list) {
				//需要选择商品的
				if (ConsumerProtectionModel.NEEDCHOOSEPRODUCT.equals(c.getNeedChooseProduct())) {
					ProtectionProductRelQueryModel pprm = new ProtectionProductRelQueryModel();
					pprm.setProtectionUuid(c.getUuid());
					pprm.getMapCondition().put("protectionUuid", ConditionOpTypeEnum.EQ.getCode());
					pprm.setStoreUuid(storeUuid);
					pprm.getMapCondition().put("storeUuid", ConditionOpTypeEnum.EQ.getCode());
					
					// 通过权益id和商户id统计该商户在该权益下的商品数量
					c.setCountProductrel(pprsService.getCount(pprm));
				}
			}
		}
		return list;
	}

	/**
	 * 通过商户的id和权益id获取所有没有参与保障权益的商品，用于添加商品关联
	 * 
	 * @param qm
	 * @param storeUuid
	 *            商户的id
	 * @param protectionUuid
	 *            权益id
	 * @param start
	 *            起始面
	 * @param page
	 *            页面大小
	 * @return List&ltConsumerProductWebModel&gt
	 */
	public List<ConsumerProductWebModel> getProductAdds(
			ConsumerProtectionQueryModel qm, String storeUuid,
			String protectionUuid, int start, int page) {

		List<ConsumerProductWebModel> cp = new ArrayList<ConsumerProductWebModel>();

		/* 通过商户的id和权益id获取所有商品 */
		List<ProtectionProductRelModel> ppr = storpprsService.getProducts(storeUuid, protectionUuid);

		/* 商品id的集合 */
		List<String> ids = null;

		/* 当前用户下，所有没有参与保障权益的集合 */
		List<ProductInteractiveModel> list = null;

		if (ppr != null && ppr.size() > 0) {
			// 如果不为空，查出所有所有可以添加关联的商品
			ids = new ArrayList<String>();
			for (ProtectionProductRelModel p : ppr) {
				ids.add(p.getProductUuid());
			}
			ProductInteractiveQueryModel pQueryModel = new ProductInteractiveQueryModel();
			pQueryModel.setProductUuids(ids.toArray(new String[ids.size()]));
			pQueryModel.setStoreUuid(storeUuid);
			
			pQueryModel.setProductNo(qm.getProductNo());
			pQueryModel.setProductName(qm.getProductName());

			list = this.productInteractiveService.searchProduct(pQueryModel,
					start, page);
		} else {
			// 为空时，查出所有可以添加关联的商品
			ProductInteractiveQueryModel pQueryModel = new ProductInteractiveQueryModel();
			pQueryModel.setStoreUuid(storeUuid);
			
			pQueryModel.setProductNo(qm.getProductNo());
			pQueryModel.setProductName(qm.getProductName());
			
			list = this.productInteractiveService.searchProduct(pQueryModel,
					start, page);
		}
		if (list != null && list.size() > 0) {
			for (ProductInteractiveModel p : list) {
				ConsumerProductWebModel conpw = new ConsumerProductWebModel();
				conpw.setProductUuid(p.getProductMain().getUuid());
				conpw.setProductNo(p.getProductMain().getProductNo());
				conpw.setProductName(p.getProductMain().getProductName());
				conpw.setShopPrice(p.getProductPrice().getShopPrice());
				cp.add(conpw);
			}
		}
		return cp;
	}

	/**
	 * 获取该商户下所有商品的总数
	 * @param storeUuid 商户id
	 * @param protectionUuid 权益的id
	 * @param qm
	 * @return 商品总数
	 */
	public int getProductCount(ConsumerProtectionQueryModel qm, String storeUuid,String protectionUuid) {
		List<ProtectionProductRelModel> list = storpprsService.getProducts(storeUuid, protectionUuid);
		String[] ids = null;
		ProductInteractiveQueryModel piqm = new ProductInteractiveQueryModel();
		if(list.size() > 0 && list != null){
			ids = new String[list.size()];
			for(int i = 0; i < list.size(); i++){
				ids[i] = list.get(i).getProductUuid();
			}
		}
		piqm.setProductUuids(ids);
		piqm.setStoreUuid(storeUuid);
		piqm.setProductNo(qm.getProductNo());
		piqm.setProductName(qm.getProductName());
		
		return this.productInteractiveService.getCountByProduct(piqm);
	}

	/**
	 * 通过商户的id和权益id获取所有参与的商品，用于移除关联
	 * 
	 * @param qm
	 * @param storeUuid
	 *            商户id
	 * @param start
	 *            起始面
	 * @param page
	 *            页面大小
	 * @return List<ConsumerProductWebModel>
	 */
	public List<ConsumerProductWebModel> getProductDels( ConsumerProtectionQueryModel qm, String storeUuid, String protectionUuid, int start, int page) {
		
		List<ConsumerProductWebModel> cp = new ArrayList<ConsumerProductWebModel>();
		
		/* 通过商户的id和权益id获取所有商品 */
		List<ProtectionProductRelModel> protectionProductRels = storpprsService.getProducts(storeUuid, protectionUuid, start, page);
		
		ProductInteractiveQueryModel piqm = new ProductInteractiveQueryModel();
		
		String[] pIds  = null;
		
		if(protectionProductRels != null && protectionProductRels.size() > 0){
			pIds = new String[protectionProductRels.size()];
			for(int i = 0; i < protectionProductRels.size(); i++){
				pIds[i] = protectionProductRels.get(i).getProductUuid();
				protectionProductRels.get(i).getUuid();
			}
			piqm.setInProductUuids(pIds);
			piqm.setProductNo(qm.getProductNo());
			piqm.setProductName(qm.getProductName());
		}
		
		List<ProductInteractiveModel> proModel = this.productInteractiveService.searchProduct(piqm, 0, 0);
		
		if(proModel != null && proModel.size() > 0){
			for(int i = 0; i < proModel.size(); i++){
				
				ConsumerProductWebModel conpw = new ConsumerProductWebModel();
				
				conpw.setStoreUuid(storeUuid);
				conpw.setProtectionUuid(protectionUuid);
				conpw.setProductUuid(proModel.get(i).getProductMain().getUuid());
				
				conpw.setProductNo(proModel.get(i).getProductMain().getProductNo());
				conpw.setProductName(proModel.get(i).getProductMain().getProductName());
				conpw.setShopPrice(proModel.get(i).getProductPrice().getShopPrice());
				cp.add(conpw);
			}
		}
		return cp;
	}

	/**
	 * 通过商户的id和权益id获取所有参与保障权益的商品数量
	 * @param qm
	 * @param storeUuid	商户的id
	 * @param protectionUuid	权益id
	 * @return	商品数量
	 */
	public int getCountProducts(ConsumerProtectionQueryModel qm,String storeUuid, String protectionUuid) {
		List<ProtectionProductRelModel> protectionProductRels = storpprsService.getProducts(storeUuid, protectionUuid);
		String[] pIds  = null;
		ProductInteractiveQueryModel piqm = new ProductInteractiveQueryModel();
		if(protectionProductRels != null && protectionProductRels.size() > 0){
			pIds = new String[protectionProductRels.size()];
			for(int i = 0; i < protectionProductRels.size(); i++){
				pIds[i] = protectionProductRels.get(i).getProductUuid();
				protectionProductRels.get(i).getUuid();
			}
			piqm.setInProductUuids(pIds);
			piqm.setProductNo(qm.getProductNo());
			piqm.setProductName(qm.getProductName());
		}
		return this.productInteractiveService.getCountByProduct(piqm);
	}
}
