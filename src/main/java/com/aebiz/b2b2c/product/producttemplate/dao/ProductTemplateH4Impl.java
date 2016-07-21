package com.aebiz.b2b2c.product.producttemplate.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.producttemplate.vo.ProductTemplateModel;
import com.aebiz.b2b2c.product.producttemplate.vo.ProductTemplateQueryModel;
/**
* 商品模板数据库操作hibernate实现
* 在发布商品时，不同种类的商品它的模板所不同，比如一件衣服他有 颜色：黄色、绿色和尺寸：XXL、XL 之分</br>
* 此时可以做个服装模板然后关联颜色和尺寸属性，属性性再关联属性值
* @author huangpinpin
*
*/
@Repository
public class ProductTemplateH4Impl extends BaseH4Impl<ProductTemplateModel,ProductTemplateQueryModel> implements ProductTemplateDAO {
	/**
	 * 根据集合uuid查询模板集合
	 * @param uuids
	 * @return
	 */
	public List<ProductTemplateModel> getProductTemplateByUuids(List<String> uuids){
		if(uuids==null||uuids.size()==0){
			return null;
		}
		StringBuffer hql=new StringBuffer("select o from ProductTemplateModel o where  o.uuid in (:uuids)");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setParameterList("uuids", uuids.toArray());
		
		List<ProductTemplateModel> list = query.list();
		return list;
	}
	
	
	@Override
	protected String getAppendHql(ProductTemplateQueryModel qm) {
		StringBuffer hql = new StringBuffer(" ");
		
		if(qm!=null && qm.getTemplateUuids() != null && qm.getTemplateUuids().size()>0){
			List<String> existTemplateUuids = qm.getTemplateUuids();
			hql.append(" and o.uuid not in (");
			for(int i=0;i<existTemplateUuids.size();i++){
				hql.append("'"+existTemplateUuids.get(i)+"'");
				if(i!=existTemplateUuids.size()-1){
					hql.append(",");
				}
			}
			hql.append(") ");
		}
		hql.append(super.getAppendHql(qm));
		return hql.toString();
	}
	
	/**
	 * 根据模板uuid查询模板名称
	 * @param uuid
	 * @return
	 */
	public String getTemplateNameByUuids(String uuid){
		if(StringUtil.isEmpty(uuid)){
			return null;
		}
		StringBuffer hql=new StringBuffer("select o.name from ProductTemplateModel o where  o.uuid=:uuid ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", uuid);
		
		Object obj= query.uniqueResult();
		if(obj!=null){
			return (String)obj;
		}
		
		return "";
	}
}
