package com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogModel;
import com.aebiz.b2b2c.virtualaccount.virtualaccountcustomerlog.vo.VirtualAccountCustomerLogQueryModel;

@Repository
public class VirtualAccountCustomerLogH4Impl
		extends
		BaseH4Impl<VirtualAccountCustomerLogModel, VirtualAccountCustomerLogQueryModel>
		implements VirtualAccountCustomerLogDAO {

	/**
	 * 查询会员收支纪录的总数<br>
	 * 
	 * @param customerUuid
	 * @param operType
	 *            为空时,查全部 0:查收入的 1:查支出的
	 * @return int
	 */
	public int getCustomerAccountLogCount(String customerUuid, String operType) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from VirtualAccountCustomerLogModel v where 1=1 ");
		hql.append(" and v.customerUuid =:customerUuid");
		if (!StringUtil.isEmpty(operType)) {
			hql.append(" and v.operType =:operType");
		}

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);
		if (!StringUtil.isEmpty(operType)) {
			query.setString("operType", operType);
		}
		List list = query.list();
		if (list != null && list.size() > 0) {
			return list.size();
		}
		return 0;
	}

	/**
	 * 查询会员收支明细的list,查询的是会员虚拟账户日志表
	 * 
	 * @param customerUuid
	 * @param operType
	 *            为空时,查全部 0:查收入的 1:查支出的
	 * @param fromNum
	 * @param pageShow
	 * @return List<VirtualAccountCustomerLogModel>
	 */
	public List<VirtualAccountCustomerLogModel> searchCustomerAccountLog(
			String customerUuid, String operType, int fromNum, int pageShow) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from VirtualAccountCustomerLogModel v where 1=1 ");
		hql.append(" order by v.opeTime desc ");

		Query query = this.getH4Session().createQuery(hql.toString());

		query.setFirstResult(fromNum);
		query.setMaxResults(pageShow);

		return (List<VirtualAccountCustomerLogModel>) query.list();
	}

	/**
	 * 根据订单id获取对象
	 * 
	 * @param orderUuid
	 * @return
	 */
	public VirtualAccountCustomerLogModel getVirtualAccountCustomerLogModelByOrderUuid(
			String orderUuid) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from VirtualAccountCustomerLogModel v where 1=1 ");
		hql.append(" and v.orderMainUuid =:orderMainUuid");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("orderMainUuid", orderUuid);
		List list = query.list();
		if (list != null && list.size() > 0) {
			VirtualAccountCustomerLogModel virtualAccountCustomerLogModel = (VirtualAccountCustomerLogModel) list
					.get(0);
			return virtualAccountCustomerLogModel;
		} else {
			return new VirtualAccountCustomerLogModel();
		}
	}

	@Override
	public int getCount(VirtualAccountCustomerLogQueryModel qm) {
		qm.setSearchType("1");
		String hql = "select count(distinct o.uuid) from VirtualAccountCustomerLogModel o "
				+ getMultiModel() + " where 1=1 ";
		hql = hql + getAppendHql(qm);
		Query q = getH4Session().createQuery(hql);
		setValue(qm, q);
		setAppendHqlValue(qm, q);
		Number unm = (Number) q.uniqueResult();
		return unm.intValue();
	}

	@Override
	protected String getMultiModel() {
		return " , CustomerInfoModel as cim , CustomerModel as cm ";
	}

	/**
	 * 拼接搜索时需要的字段
	 */
	@Override
	protected String getAppendHql(VirtualAccountCustomerLogQueryModel qm) {
		StringBuffer buff = new StringBuffer("  ");
		buff.append(" and cim.customerUuid =cm.uuid and o.customerUuid =cm.uuid ");
		
		if (!StringUtil.isEmpty(qm.getRealName())) {
			buff.append(" and cim.realName Like:realName ");
		}
		if (!StringUtil.isEmpty(qm.getNickName())) {
			buff.append(" and cim.nickName Like:nickName ");
		}
		if (!StringUtil.isEmpty(qm.getCustomerMobile())) {
			buff.append(" and cm.mobile Like:customerMobile ");
		}
		if (!StringUtil.isEmpty(qm.getOrderType())) {
			buff.append(" and o.orderType =:orderType ");
		}

		if (!qm.getSearchType().equals("1")) {
			buff.append(" group by o.uuid ");
		}
		if(!StringUtil.isEmpty(qm.getSortName()) && !"uuid".equals(qm.getSortName())){
			buff.append(" order by o." + qm.getSortName() + " " + qm.getSortType());
		}else{
			buff.append(" order by o.orderTime desc");
		}
		return buff.toString();
	}

	/**
	 * 为搜索查询赋值
	 */
	@Override
	protected void setAppendHqlValue(VirtualAccountCustomerLogQueryModel qm,
			Query q) {
		if (!StringUtil.isEmpty(qm.getRealName())) {
			q.setString("realName", "%" + qm.getRealName() + "%");
		}
		if (!StringUtil.isEmpty(qm.getNickName())) {
			q.setString("nickName", "%" + qm.getNickName() + "%");
		}

		if (!StringUtil.isEmpty(qm.getCustomerMobile())) {
			q.setString("customerMobile", "%" + qm.getCustomerMobile() + "%");
		}
		if (!StringUtil.isEmpty(qm.getOrderType())) {
			q.setString("orderType", qm.getOrderType());
		}

	}

	/**
	 * 根据消费记录的Uuid查询患者的uuid
	 */
	@Override
	public String getCustomerUuidByLogsUuid(String uuid) {
		StringBuffer hql = new StringBuffer();
		hql.append("select customerUuid from VirtualAccountCustomerLogModel v where 1=1 ");
		if (!StringUtil.isEmpty(uuid)) {
			hql.append(" and v.uuid =:uuid");
		}
		Query query = this.getH4Session().createQuery(hql.toString());
		if (!StringUtil.isEmpty(uuid)) {
			query.setString("uuid", uuid);
		}
		List<String> list = query.list();

		if (list != null && list.size() > 0) {

			return list.get(0);
		}

		return "";
	}
        /**
         *  根据患者的id获取患者消费记录的列表
         */
        @Override
        public List<VirtualAccountCustomerLogModel> getVirtualAccountCustomerLogListByCustomerUuid(
                String customerUuid) {
                StringBuffer hql = new StringBuffer();
                hql.append(" from VirtualAccountCustomerLogModel v where  v.customerUuid =:customerUuid ");
                Query query = this.getH4Session().createQuery(hql.toString());
                
                if (!StringUtil.isEmpty(customerUuid)) {
                        query.setString("customerUuid", customerUuid);
                }
                List<VirtualAccountCustomerLogModel> list = query.list();
    
                if (list != null && list.size() > 0) {
                        return list;
                }
                return null;
        }

}
