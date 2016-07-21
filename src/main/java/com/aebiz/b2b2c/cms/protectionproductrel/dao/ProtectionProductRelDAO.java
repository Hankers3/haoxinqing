package com.aebiz.b2b2c.cms.protectionproductrel.dao;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.cms.protectionproductrel.vo.ProtectionProductRelModel;
import com.aebiz.b2b2c.cms.protectionproductrel.vo.ProtectionProductRelQueryModel;

/**
 * 
 * 消费者权益保障服务和商户商品的参与<br/>
 * 商户选择权益保障服务时，有的需要选择商品。此类就是对该权益下的商品进行添加和删除的操作
 * 
 * @author XiaoY
 *
 */
public interface ProtectionProductRelDAO extends BaseDAO<ProtectionProductRelModel,ProtectionProductRelQueryModel>{

}