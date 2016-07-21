package com.aebiz.b2b2c.order.ordermaincomment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermaincomment.dao.OrderMainCommentDAO;
import com.aebiz.b2b2c.order.ordermaincomment.service.OrderMainCommentService;
import com.aebiz.b2b2c.order.ordermaincomment.vo.OrderMainCommentModel;
import com.aebiz.b2b2c.order.ordermaincomment.vo.OrderMainCommentQueryModel;

@Service
@Transactional
public class OrderMainCommentServiceImpl extends BaseServiceImpl<OrderMainCommentModel,OrderMainCommentQueryModel> implements OrderMainCommentService {
	private OrderMainCommentDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	private OrderMainService orderMainService;
	@Autowired
	public void setMyDao(OrderMainCommentDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}
	@Override
	public String create(OrderMainCommentModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(OrderMainCommentModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(OrderMainCommentModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	/**
	 * 根据id修改处理意见
	 * hedongfei
	 * @param 
	 */
	@Override
	public void updateconductIdea(String uuid, String conductIdea) {
		this.myDao.updateconductIdea(uuid, conductIdea);
		
	}
	/**
	 * 通过主订单编号获取订单评价信息表的服务态度评分
	 * hedongfei
	 * @param uuid
	 * @return
	 */
	@Override
	public String getOrderMainCommentServiceScoreByorderMainUuid(
			String orderMainUuid) {
		return myDao.getOrderMainCommentServiceScoreByorderMainUuid(orderMainUuid);
	}
	/**
	 * 通过主订单号来获取订单信息及评价
	 * @param customerUuid 
	 * @param pageCount 
	 * @param pageNo 
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getOrderMainCommentAndOthers(
		String customerUuid, int pageCount, int pageNo) {
		//定义一个存储所有map的list集合
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		//查到所有的订单对象
		List<OrderMainModel> list = orderMainService.getOrderList(customerUuid, pageCount, pageNo);
		if(list!=null&&list.size()>0)
		{
			for(OrderMainModel o : list)			
			{				//定义一个map接收
				Map<String, Object> map = new HashMap<String, Object>();
				//获取orderMainUuid
				String orderMainUuid = o.getUuid();
				map.put("orderMainUuid", orderMainUuid);

				//下单时间--上午/下午
				String amOrPm = getAMOrPM(o.getOrderTime());
				map.put("amOrPm", amOrPm);
				//下单时间——时间
				String time = getHours(o.getOrderTime());
				map.put("time", time);
				//下单时间--日期
				String date = getDate(o.getOrderTime());
				map.put("date", date);
				//预约单号
				String orderId = o.getOrderId();
				map.put("orderId", orderId);
				//商品信息
				 String info = getInfo(o.getDetailList());
				 map.put("info", info);
				 //总价
				double totalMoney = o.getTotalMoney();
				map.put("totalMoney",totalMoney);
				//获取订单对应的评价
				OrderMainCommentModel orderMainCommentModel = o.getOrderMainCommentModel();
				//初始化评分和评价内容为空串
				int orderMainCommentJudge = 0;
				if(orderMainCommentModel!=null && !"".equals(orderMainCommentModel.getUuid()))
				{
					//评分
					orderMainCommentJudge = 1;
					String content = orderMainCommentModel.getContent();
					String serviceScore = orderMainCommentModel.getServiceScore();
					//返回评价内容
					map.put("content",content);
					//返回服务评分
					map.put("serviceScore",serviceScore);
				}
				map.put("orderMainCommentJudge",orderMainCommentJudge);
				//把map放到list中
				maps.add(map);
			}
		}
		//循环list
			
		
		return maps;
	}
	/**
	 * 根据传入的时间（精确到时分秒），得到日期
	 * @param time
	 * @return
	 */
	public String getDate(String time)
	{
		return time.substring(0,10);
	}
	/**
	 * 根据传入的时间（精确到时分秒），判断是上午还是下午
	 * @param time
	 * @return
	 */
	public String getAMOrPM(String time)
	{
		Integer judge = Integer.valueOf(time.substring(11,13));
		if(judge>12){
			return "下午";
		}
		else{
			return "上午";
		}
	}
	/**
	 * 根据传入的时间（精确到时分秒），得到时分秒
	 * @param time
	 * @return
	 */
	public String getHours(String time)
	{
		return time.substring(11,19);
	}
	/**
	 * 传入同一个订单的OrderDetailModel集合，返回类似：”厨房：5㎡、卫生间：2㎡“的字符串
	 * @param orderDetailModels
	 * @return
	 */
	public String getInfo(List<OrderDetailModel> orderDetailModels)
	{
		String info = null;
		for(OrderDetailModel o :orderDetailModels)
		{
			info = o.getProductName();
		}
		return info;
	}
	
	/**
	 * 根据订单id获取评价列表
	 * @param orderMainUuuid
	 * @author zdx
	 * @return
	 */
	@Override
	public List<OrderMainCommentModel> getCommentByOrderId(String orderMainUuid) {
		return myDao.getCommentByOrderId(orderMainUuid);
	}
	/**
	 * 通过主订单号来获取订单评价
	 * @param orderMainUuid
	 * @return 
	 * @2015-3-27:2015-3-27
	 * @author : SZH
	 */
	@Override
	public OrderMainCommentModel getOrderMainCommentByOrderMainUuid(
			String orderMainUuid) {
		return myDao.getOrderMainCommentByOrderMainUuid(orderMainUuid);
	}
}