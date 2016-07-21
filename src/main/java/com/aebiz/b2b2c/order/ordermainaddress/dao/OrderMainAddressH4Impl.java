package com.aebiz.b2b2c.order.ordermainaddress.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;

import com.aebiz.b2b2c.order.ordermainaddress.vo.OrderMainAddressModel;
import com.aebiz.b2b2c.order.ordermainaddress.vo.OrderMainAddressQueryModel;

@Repository
public class OrderMainAddressH4Impl extends
		BaseH4Impl<OrderMainAddressModel, OrderMainAddressQueryModel> implements
		OrderMainAddressDAO {

	/**
	 * 通过订单编号获得这个订单对应的收货地址对象
	 * 
	 * @param orderUuid
	 * @return
	 */
	public OrderMainAddressModel getOrderMainAddressModelByUuid(String orderUuid) {
		StringBuffer sb = new StringBuffer(
				"from OrderMainAddressModel omam where 1=1");
		sb.append(" and omam.orderMainUuid=:orderMainUuid ");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("orderMainUuid", orderUuid);

		List<OrderMainAddressModel> omamList = q.list();
		if (omamList != null && omamList.size() > 0) {
			return omamList.get(0);
		}

		return null;
	}

	

	/**
	 * 通过主订单编号获取客户手机
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	@Override
	public String getOrderMainAddresscustomerMobile(String orderMainUuid) {
		StringBuffer hql = new StringBuffer(
				"from OrderMainAddressModel where orderMainUuid=:orderMainUuid");


		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("orderMainUuid", orderMainUuid);
		if (query.list() != null && query.list().size() > 0) {
			OrderMainAddressModel model = (OrderMainAddressModel) query.list().get(0);
			return model.getMobile();
		}
		
		return "";
	}



	/**
	 * 通过主订单编号获取客户服务地址
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	@Override
	public String getOrderMainAddressaddress(String orderMainUuid) {
		StringBuffer hql = new StringBuffer(
				"from OrderMainAddressModel where orderMainUuid=:orderMainUuid");


		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("orderMainUuid", orderMainUuid);
		if (query.list() != null && query.list().size() > 0) {
			OrderMainAddressModel model = (OrderMainAddressModel) query.list().get(0);
			return model.getAddress();
		}
		
		return "";
	}



	
	/**
	 * 通过主订单编号获取客户服务时间
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	@Override
	public String getOrderMainAddressserviceTime(String orderMainUuid) {
		StringBuffer hql = new StringBuffer(
				"from OrderMainAddressModel where orderMainUuid=:orderMainUuid");


		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("orderMainUuid", orderMainUuid);
		if (query.list() != null && query.list().size() > 0) {
			OrderMainAddressModel model = (OrderMainAddressModel) query.list().get(0);
			return model.getServiceTime();
		}
		
		return "";
	}



	
	/**
	 * 通过主订单编号获取客户名称
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	@Override
	public String getOrderMainAddressname(String orderMainUuid) {
		StringBuffer hql = new StringBuffer(
				"from OrderMainAddressModel where orderMainUuid=:orderMainUuid");


		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("orderMainUuid", orderMainUuid);
		if (query.list() != null && query.list().size() > 0) {
			OrderMainAddressModel model = (OrderMainAddressModel) query.list().get(0);
			return model.getName();
		}
		
		return "";
	}


	/**
	 * 通过家政员编号和日期获得获取对应的订单编号
	 * @param orderUuid
	 * @param date
	 * @return
	 */
	@Override
	public List getOrderMainAddressModelByServiceUuid(
			String serviceUuid, String date) {
		//当前时间
		String serviceTime1 = DateFormatHelper.getNowTimeStr();
		StringBuffer sb = new StringBuffer(
				"select omam.orderMainUuid from OrderMainAddressModel as omam,OrderStaffModel as os,OrderMainModel as omm where 1=1");
		sb.append(" and omam.orderMainUuid=os.orderMainUuid and omm.uuid=os.orderMainUuid ");
		//待上门状态
		sb.append(" and omm.state !=:state1 and omm.state !=:state2 and omm.state !=:state3 and omm.state !=:state4 and omm.state !=:state5");
		//家政员编号
		sb.append(" and os.serviceStaffUuid= :serviceStaffUuid ");
		//服务时间大于当前时间
		sb.append(" and omam.serviceTime > :serviceTime1 ");
		//家政员接单状态
		sb.append(" and os.receiveStatus =:receiveStatus ");
		//服务时间为某一天的
		sb.append(" and SUBSTRING(omam.serviceTime,1,10) = :serviceTime");
		sb.append(" order by omam.serviceTime ");
		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("serviceTime", date);
		q.setString("serviceStaffUuid", serviceUuid);
		q.setString("state1", "1");
		q.setString("state3", "3");
		q.setString("state2", "2");
		q.setString("state4", "11");
		q.setString("state5", "12");
		//家政员接单状态
		q.setString("receiveStatus", "1");
		//当前时间
		q.setString("serviceTime1", serviceTime1);
		List orderList = q.list();

		if (orderList != null && orderList.size() > 0) {
			return orderList;
		}
		return null;

		
	}
	/**
	 * 通过日期获得该日期的订单号
	 * @param date
	 * @return
	 */
	public List<String> getOrderMainUuidByDate(String date,String type,String userId ) {
		
		//String serviceTime1 = DateFormatHelper.getNowTimeStr();
		StringBuffer sb = new StringBuffer(
				"select omam.orderMainUuid from OrderMainAddressModel omam,OrderMainModel as omm,OrderStaffModel as os where 1=1");
		//服务时间为某一天的
		sb.append(" and SUBSTRING(omam.serviceTime,1,10) = :serviceTime");
		sb.append(" and os.orderMainUuid = omm.uuid and omm.uuid = omam.orderMainUuid");
		//家政员编号
		sb.append(" and os.serviceStaffUuid =:serviceStaffUuid ");
		//待上门
		if("0".equals(type)){
			//订单为待上门状态     
			sb.append(" and omm.state !=:state1 and omm.state !=:state2 and omm.state !=:state3 and omm.state !=:state4 and omm.state !=:state5");
			//服务时间大于当前时间
			//sb.append(" and omam.serviceTime > :serviceTime1 ");
			//家政员接单状态
			sb.append(" and os.receiveStatus =:receiveStatus ");
		}else if("1".equals(type)){
			//sb.append(" and (omm.state =:state1 or omm.state =:state2 ) and omam.serviceTime < :serviceTime1 ");
			sb.append(" and (omm.state =:state1 or omm.state =:state2 or omm.state =:state5) ");
		}
		sb.append(" order by omam.serviceTime ");
		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("serviceStaffUuid", userId);
		q.setString("serviceTime", date);
		//待上门
		if("0".equals(type)){
			q.setString("state1", "1");
			q.setString("state3", "3");
			q.setString("state2", "2");
			q.setString("state4", "11");
			q.setString("state5", "12");
			//q.setString("serviceTime1", serviceTime1);
			//家政员接单状态
			q.setString("receiveStatus", "1");
		}else if("1".equals(type)){
			q.setString("state1", "2");
			q.setString("state2", "11");
			q.setString("state5", "12");
			//q.setString("serviceTime1", serviceTime1);
		}
		List omamList = q.list();
		if (omamList != null && omamList.size() > 0) {
			return omamList;
		}
		return null;
	}




	/**
	 * 通过手机号获得这个订单对应的收货地址对象
	 * hedongfei
	 * @param orderUuid
	 * @return
	 */
	public OrderMainAddressModel getOrderMainAddressModelByMobile(String Mobile) {
		StringBuffer sb = new StringBuffer(
				"from OrderMainAddressModel omam where 1=1");
		sb.append(" and omam.mobile like:mobile ");

		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("mobile",Mobile);

		List<OrderMainAddressModel> omamList = q.list();
		if (omamList != null && omamList.size() > 0) {
			return omamList.get(0);
		}

		return null;
	}



	
	/**
	 * 根据主订单编号得到上门间隔时长的方法
	 * hedongfei
	 * @param orderUuid
	 * @return
	 */
	@Override
	public int getdoorinBetweenTimeByordId(List orderMainUuids) {
		int sum=0;
		if(orderMainUuids!=null&&orderMainUuids.size()>0){
			for(int i=0;i<orderMainUuids.size(); i++) {
				StringBuffer sb = new StringBuffer(
						"SELECT SUM(TIMESTAMPDIFF(MINUTE,omd.dropInTime,omd.doorinEndtime)) FROM order_main_address  AS omd ,order_staff AS os WHERE  omd.orderMainUuid=os.orderMainUuid ");
				sb.append("and omd.orderMainUuid=:orderMainUuid");
		
				Query q = this.getH4Session().createSQLQuery(sb.toString());
				
				String orderMainUuid=(String) orderMainUuids.get(i);
				q.setString("orderMainUuid",orderMainUuid);
		
				int nun =0;
				List<Object> omamList = q.list();
				if (omamList.get(0) != null && omamList.size() > 0) {
					 nun = ((Number) q.uniqueResult()).intValue();
				}else{
					nun=0;
				}
				
				//int nun = ((Number) q.uniqueResult()).intValue();

				sum=sum+nun;
				

			}
					
		}
		
		return sum;
		
	}


	
	/**
	 * 通过主订单编号获取对应的收货地址对象
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	@Override
	public OrderMainAddressModel getOrderMainAddressModelbyOrdId(
			String orderMainUuid) {
		StringBuffer hql = new StringBuffer(
				"from OrderMainAddressModel where orderMainUuid=:orderMainUuid");


		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("orderMainUuid", orderMainUuid);
		if (query.list() != null && query.list().size() > 0) {
			OrderMainAddressModel model = (OrderMainAddressModel) query.list().get(0);
			return model;
		}
		
		return null;
	}



	/**
	 * 根据时间得到时间内的已丈量的总订单量
	 * hedongfei
	 * @param orderUuid
	 * @return
	 */
	@Override
	public int getMeasuredOrders(String time,String searchType) {
		StringBuffer hql = new StringBuffer(
				"SELECT  COUNT(*) FROM order_main_address WHERE  measureTime IS NOT NULL");
	
		//hql.append(" AND  SUBSTR(measureTime,1,10) =:time ");	
		if(searchType.equals("0")){		
			hql.append(" and SUBSTR(measureTime,1,10)=:time");
		}else if(searchType.equals("1")){
			hql.append(" and SUBSTR(measureTime,1,7)=:time");
		}else{
			hql.append(" and SUBSTR(measureTime,1,4)=:time");
		}
		
		Query q = this.getH4Session().createSQLQuery(hql.toString());
		
		
		//判断所传时间是否为空
		if(time.equals("")){
			if(searchType.equals("0")){		
				q.setString("time",DateFormatHelper.getNowTimeStr().substring(0, 10));
			}else if(searchType.equals("1")){
				q.setString("time",DateFormatHelper.getNowTimeStr().substring(0, 7));
			}else{
				q.setString("time",DateFormatHelper.getNowTimeStr().substring(0, 4));
			}
		}else{
			q.setString("time",time);
			}
		
		List<Object> result =q.list();
		
		if(result.get(0)!=null && result.size()>0){
			return ((Number) q.uniqueResult()).intValue();
		}else{
			
			return 0;
		}
	}


	/**
	 * 根据时间得到时间内的已丈量订单丈量时间及服务时间偏差的总和
	 * hedongfei
	 * @param orderUuid
	 * @return
	 */
	@Override
	public int getMeasuredOrdersTimeWarp(String time,String searchType) {
		StringBuffer hql = new StringBuffer(
				"SELECT  SUM(TIMESTAMPDIFF(MINUTE,measureTime,serviceTime)) FROM order_main_address WHERE   measureTime IS NOT NULL");
	
		//hql.append(" AND  SUBSTR(measureTime,1,10) =:time ");	
		if(searchType.equals("0")){		
			hql.append(" and SUBSTR(measureTime,1,10)=:time");
		}else if(searchType.equals("1")){
			hql.append(" and SUBSTR(measureTime,1,7)=:time");
		}else{
			hql.append(" and SUBSTR(measureTime,1,4)=:time");
		}
		
		Query q = this.getH4Session().createSQLQuery(hql.toString());
		
		
		//判断所传时间是否为空
		if(time.equals("")){
			if(searchType.equals("0")){		
				q.setString("time",DateFormatHelper.getNowTimeStr().substring(0, 10));
			}else if(searchType.equals("1")){
				q.setString("time",DateFormatHelper.getNowTimeStr().substring(0, 7));
			}else{
				q.setString("time",DateFormatHelper.getNowTimeStr().substring(0, 4));
			}
		}else{
			q.setString("time",time);
			}
		
		List<Object> result =q.list();
		
		if(result.get(0)!=null && result.size()>0){
			return ((Number) q.uniqueResult()).intValue();
		}else{
			
			return 0;
		}
	}

	/**
	 * 通过手机号获得这个订单对应的收货地址对象
	 * 
	 * @param Mobile
	 * @param city
	 * @return
	 */
	@Override
	public OrderMainAddressModel getOrderMainAddressByMobileAndCity(
			String Mobile, String city) {
		StringBuffer sb = new StringBuffer(
				"from OrderMainAddressModel omam where 1=1");
		sb.append(" and omam.mobile like:mobile ");
		sb.append(" and omam.city =:city ");
		
		Query q = this.getH4Session().createQuery(sb.toString());
		q.setString("mobile",Mobile);
		q.setString("city",city);
		
		List<OrderMainAddressModel> omamList = q.list();
		if (omamList != null && omamList.size() > 0) {
			return omamList.get(0);
		}
		return null;
	}


	

}
