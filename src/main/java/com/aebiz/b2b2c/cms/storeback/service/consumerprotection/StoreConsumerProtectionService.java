package com.aebiz.b2b2c.cms.storeback.service.consumerprotection;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionModel;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionQueryModel;
import com.aebiz.b2b2c.cms.storeback.web.consumerprotection.vo.ConsumerProductWebModel;

/**
 * 商户平台消费者权益保障服务
 * @author XiaoY
 *
 */
public interface StoreConsumerProtectionService extends
		BaseService<ConsumerProtectionModel, ConsumerProtectionQueryModel> {
	
	/**
	 * 通過商户id获取没有参与的服务保障项
	 * @param storeUuid	商户id
	 * @return
	 */
	public List<ConsumerProtectionModel> getNonparticipant(String storeUuid);
	
	/**
	 * 通過商户id获取参与的服务保障项
	 * @param storeUuid	商户id
	 * @return
	 */
	public List<ConsumerProtectionModel> getAttain(String storeUuid);

	/**
	 * 获取该商户下所有商品的总数
	 * @param storeUuid 商户id
	 * @param protectionUuid 权益的id
	 * @param qm
	 * @return 商品总数
	 */
	public int getProductCount(ConsumerProtectionQueryModel qm, String storeUuid,String protectionUuid);
	
	/**
	 * 通过商户的id和权益id获取所有参与的商品
	 * @param qm
	 * @param storeUuid	商户的id
	 * @param protectionUuid	权益id
	 * @param start	起始面
	 * @param page	页面大小
	 * @return	List&ltConsumerProductWebModel&gt
	 */
	public List<ConsumerProductWebModel> getProductDels(ConsumerProtectionQueryModel qm,String storeUuid, String protectionUuid, int start,int page);
	
	/**
	 * 通过商户的id和权益id获取所有没有参与保障权益的商品
	 * @param qm
	 * @param storeUuid	商户的id
	 * @param protectionUuid	权益id
	 * @param start	起始面
	 * @param page	页面大小
	 * @return	List&ltConsumerProductWebModel&gt
	 */
	public List<ConsumerProductWebModel> getProductAdds(ConsumerProtectionQueryModel qm,String storeUuid, String protectionUuid,int start,int page);
	
	/**
	 * 通过商户的id和权益id获取所有参与保障权益的商品数量
	 * @param qm
	 * @param storeUuid	商户的id
	 * @param protectionUuid	权益id
	 * @return	List&ltConsumerProductWebModel&gt
	 */
	public int getCountProducts(ConsumerProtectionQueryModel qm,String storeUuid, String protectionUuid);
}
