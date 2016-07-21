package com.aebiz.b2b2c.order.interactive.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.order.interactive.vo.OrderInteractiveModel;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderStatusEnum;
import com.aebiz.b2b2c.order.ordermainaddress.vo.OrderMainAddressModel;

@Repository
public class OrderInteractiveH4Impl extends
		BaseH4Impl<OrderMainModel, OrderMainQueryModel> implements
		OrderInteractiveDAO {

	protected String getMultiSelect() {
		return " , orderAddress";
	}

	protected String getMultiModel() {
		return " , OrderMainAddressModel orderAddress";
	}

	protected String getAppendHql(OrderMainQueryModel qm) {

		StringBuffer appendHql = new StringBuffer("");

		if (qm != null) {
			return "order by o." + qm.getSortName() + " " + qm.getSortType();
		}
		return "order by o.opeTime desc ";
	}

	/**
	 * 查询订单列表的总数量
	 * 
	 * 1.目前可由发货系统调用待发货订单 <br />
	 * 
	 * @param qm
	 */
	public int getOrderCount(OrderMainQueryModel qm) {
		StringBuffer hql = new StringBuffer("select o ")
				.append(getMultiSelect()).append(" from OrderMainModel o ")
				.append(getMultiModel()).append(" where 1=1 ");

		hql.append(prepareHql(qm));
		hql.append(getAppendHql(qm));

		Query q = getH4Session().createQuery(hql.toString());
		setValue(qm, q);
		setAppendHqlValue(qm, q);

		List list = q.list();
		if (list != null && list.size() > 0) {
			return list.size();
		}

		return 0;
	}

	/**
	 * 查询订单列表
	 * 
	 * 1.目前可由发货系统调用待发货订单 <br />
	 * 
	 * @param paramQM
	 * @param paramInt1
	 * @param paramInt2
	 * 
	 * @return 返回的是一个复合对象，包含了订单明细的对象和订单收货地址的信息
	 */
	public List<OrderInteractiveModel> getOrderListByCondition(
			OrderMainQueryModel qm, int start, int pageShow) {
		StringBuffer hql = new StringBuffer("select o ")
				.append(getMultiSelect()).append(" from OrderMainModel o ")
				.append(getMultiModel()).append(" where 1=1 ");

		hql.append(prepareHql(qm));
		hql.append(getAppendHql(qm));

		Query q = getH4Session().createQuery(hql.toString());
		setValue(qm, q);
		setAppendHqlValue(qm, q);

		q.setFirstResult(start);
		q.setMaxResults(pageShow);

		if ((getMultiSelect() != null)
				&& (getMultiSelect().trim().length() > 0)) {
			List tempList = q.list();
			return exeInteractiveResultList(tempList);
		}

		List retList = q.list();
		return retList;
	}

	/**
	 * 
	 * 通过订单编号获得订单明细列表
	 * 
	 * @param orderUuid
	 * @return
	 */
	public List<OrderDetailModel> getOrderDetailListByOrderUuid(String orderUuid) {

		StringBuffer hql = new StringBuffer(
				"from OrderDetailModel od where 1=1 ");
		hql.append(" and od.orderMainUuid=:orderMainUuid");

		Query q = getH4Session().createQuery(hql.toString());
		q.setString("orderMainUuid", orderUuid);

		return q.list();
	}

	/**
	 * 
	 * **************************************
	 * 
	 */
	private List<OrderInteractiveModel> exeInteractiveResultList(
			List<Object[]> tempList) {
		List<OrderInteractiveModel> list = new ArrayList<OrderInteractiveModel>();
		if (tempList != null && tempList.size() > 0) {
			for (Object[] obj : tempList) {

				// 拼接StoreBreakContractModel
				OrderMainModel omm = (OrderMainModel) obj[0];
				OrderMainAddressModel orderAddress = (OrderMainAddressModel) obj[1];

				OrderInteractiveModel orderInteractive = new OrderInteractiveModel();
				orderInteractive.setOrderMain(omm);
				orderInteractive.setOrderAddress(orderAddress);

				list.add(orderInteractive);
			}
		}
		return list;
	}
}
