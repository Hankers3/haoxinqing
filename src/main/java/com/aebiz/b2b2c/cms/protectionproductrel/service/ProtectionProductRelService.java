package com.aebiz.b2b2c.cms.protectionproductrel.service;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.cms.protectionproductrel.vo.ProtectionProductRelModel;
import com.aebiz.b2b2c.cms.protectionproductrel.vo.ProtectionProductRelQueryModel;

/**
 * 
 * 消费者权益保障服务和商户商品的参与<br/>
 * 
 * 比如商户参加七天退换货<br/>
 * 但是可以选择部分商品参与，比如生鲜商品不参与等形式<br/>
 * 
 * @author XiaoY
 *
 */
public interface ProtectionProductRelService extends BaseService<ProtectionProductRelModel,ProtectionProductRelQueryModel>{

}
