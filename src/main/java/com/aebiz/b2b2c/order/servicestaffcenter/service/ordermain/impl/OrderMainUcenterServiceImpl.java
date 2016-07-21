package com.aebiz.b2b2c.order.servicestaffcenter.service.ordermain.impl;

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
import com.aebiz.b2b2c.cms.consumerprotection.service.ConsumerProtectionService;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.MobileUtils;
import com.aebiz.b2b2c.finance.customeraccount.service.CustomerAccountInteractive;
import com.aebiz.b2b2c.order.orderclose.service.OrderCloseService;
import com.aebiz.b2b2c.order.orderdetail.service.OrderDetailService;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;
import com.aebiz.b2b2c.order.ordermainaddress.service.OrderMainAddressService;
import com.aebiz.b2b2c.order.ordermaindiscount.service.OrderMainDiscountService;
import com.aebiz.b2b2c.order.ordermainlog.service.OrderMainLogService;
import com.aebiz.b2b2c.order.orderrouting.service.OrderRoutingService;
import com.aebiz.b2b2c.order.orderstamp.service.OrderStampService;
import com.aebiz.b2b2c.order.orderstamp.vo.OrderStampModel;
import com.aebiz.b2b2c.order.servicestaffcenter.dao.ordermain.OrderMainUcenterDAO;
import com.aebiz.b2b2c.order.servicestaffcenter.service.ordermain.OrderMainUcenterService;
import com.aebiz.b2b2c.product.interactive.product.dao.ProductInteractiveDAO;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;

@Service
@Transactional
public class OrderMainUcenterServiceImpl extends
		BaseServiceImpl<OrderMainModel, OrderMainQueryModel> implements
		OrderMainUcenterService {
	private OrderMainUcenterDAO myDao = null;
	@Autowired
	private ServicestaffService servicestaffService;
	@Autowired
	private UuidService us;
	@Autowired
	private OrderDetailService detailService;
	@Autowired
	private OrderMainDiscountService orderMainDiscountService;
	@Autowired
	private OrderMainService orderMainService;
	@Autowired
	private OrderMainLogService orderMainLogService;
	@Autowired
	private OrderRoutingService orderRoutingService;
	@Autowired
	private OrderMainAddressService orderMainAddressService;
	@Autowired
	private OrderStampService orderStampService;

	@Autowired
	private ConsumerProtectionService consumerProtectionSercice;

	@Autowired
	private OrderCloseService orderCloseService;

	/* 财务系统中，调用会员账户接口 */
	@Autowired
	private CustomerAccountInteractive customerInteractive;
	
	@Autowired
	private ProductInteractiveDAO productInteractive;

	@Autowired
	public void setMyDao(OrderMainUcenterDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public void update(OrderMainModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}



	/**
	 * 会员中心查询订单列表
	 * 
	 * @param paramQM
	 * @param paramInt1
	 * @param paramInt2
	 * @return
	 */
	public List<OrderMainModel> getOrderListByCondition(
			OrderMainQueryModel paramQM, int paramInt1, int paramInt2) {
		List<String> orderUuids = myDao.getOrderListByCondition(paramQM,
				paramInt1, paramInt2);

		List<OrderMainModel> ommList = new ArrayList<OrderMainModel>();
		for (String uuid : orderUuids) {
			OrderMainModel omm = this.getByUuid(uuid);
			// 查询订单的标记
			if (omm != null) {
				OrderStampModel osm = orderStampService
						.getOrderStampModelByOrderUuid(omm.getUuid());
				omm.setOrderStamp(osm);

			}
			ommList.add(omm);
		}

		return ommList;
	}
	
	/**
	 * 会员中心订单查询列表，重新定义获得查询总数
	 * 
	 * @param qm
	 */
	public int getOrderCount(OrderMainQueryModel qm) {
		return myDao.getOrderCount(qm);
	}

	/**
	 * 根据会员id和订单类型查询总数量
	 * @param OrderMainQueryModel
	 * @param state 订单状态
	 * @return
	 */
	@Override
	public int getOrderCountByCustomerId(OrderMainQueryModel qm,
			String serviceStaffUuid, String state) {
		
		return myDao.getOrderCountByCustomerId(qm, serviceStaffUuid, state);
	}
	@Override
	public Map<String, Object> getServiceIncome(String id) {
		ServicestaffModel servicestaffModel = servicestaffService.getServicestaffModelByUuid(id);
		Map<String,Object> myIncome = new HashMap<String,Object>();
		double accountAmount= 0.0;
		double availableAmount= 0.0;
		double todayIncome= 0.0;
		double yesterdayIncome= 0.0;
		double beforeYesterdayIncome= 0.0;
		double monthIncome= 0.0;
		double yearIncome= 0.0;
		int todayOrderCount= 0;
		int yesterdayOrderCount= 0;
		int beforeYesterdayOrderCount= 0;
		int monthOrderCount= 0;
		int incomeRanked= 0;
		String todayDate= "";
		String  yesterdayDate= "";
		String  beforeYesterdayDate= "";
		if(servicestaffModel!=null)
		{
			accountAmount = servicestaffModel.getAccountAmount();
			availableAmount = servicestaffModel.getAvailableAmount();
			//在OrderRoutingService中获取今天的收入、昨天的收入、前天的收入
			todayIncome = orderRoutingService.getTodayIncome(0, id);
			yesterdayIncome = orderRoutingService.getTodayIncome(1, id);
			beforeYesterdayIncome = orderRoutingService.getTodayIncome(2, id);
			//在OrderRoutingService中获取月收入、年收入
			monthIncome = orderRoutingService.getCurrentMonthIncomes(id);
			yearIncome= orderRoutingService.getYearSumIncome(id);
			//返回今天、昨天、明天星期和日期，格式为：星期四 2015-03-05
			todayDate = MobileUtils.getWeekAndDate(0);
			yesterdayDate = MobileUtils.getWeekAndDate(1);
			beforeYesterdayDate = MobileUtils.getWeekAndDate(2);
			//今日订单数量根据家政员ID从 OrderStaff、OrderMainAddress订单服务信息中获取今天的订单数量、昨天的订单数量、前天的订单数量
			todayOrderCount = orderMainAddressService.getStaffOrderCount(id,0);
			yesterdayOrderCount = orderMainAddressService.getStaffOrderCount(id,1);
			beforeYesterdayOrderCount = orderMainAddressService.getStaffOrderCount(id,2);
			//本月订单数
			monthOrderCount = orderMainAddressService.getStaffOrderMonthCount(id);
		}
		//家政员排名
		//ygs20160309 no method incomeRanked = servicestaffService.getStaffRanked(id);
		myIncome.put("accountAmount", accountAmount);
		myIncome.put("availableAmount", availableAmount);
		myIncome.put("todayIncome", todayIncome);
		myIncome.put("yesterdayIncome", yesterdayIncome);
		myIncome.put("beforeYesterdayIncome", beforeYesterdayIncome);
		myIncome.put("monthIncome", monthIncome);
		myIncome.put("yearIncome", yearIncome);
		myIncome.put("todayOrderCount", todayOrderCount);
		myIncome.put("yesterdayOrderCount", yesterdayOrderCount);
		myIncome.put("beforeYesterdayOrderCount", beforeYesterdayOrderCount);
		myIncome.put("monthOrderCount", monthOrderCount);
		myIncome.put("incomeRanked", incomeRanked);
		myIncome.put("todayDate", todayDate);
		myIncome.put("yesterdayDate", yesterdayDate);
		myIncome.put("beforeYesterdayDate", beforeYesterdayDate);
		return myIncome;
	}

	@Override
	public Map<String, Object> getALLMonthIncome(String id) {
		//返回1-12月份的收入
		Double[] allMonthIncomes = orderRoutingService.getAllMonthIncomes(id);
		ServicestaffModel servicestaffModel = servicestaffService.getServicestaffModelByUuid(id);
		Map<String,Object> monthsIncome = new HashMap<String,Object>();
		
		if(servicestaffModel==null){
			monthsIncome.put("januaryIncome", 0.0);
			monthsIncome.put("februaryIncome", 0.0);
			monthsIncome.put("marchIncome", 0.0);
			monthsIncome.put("aprilIncome",0.0);
			monthsIncome.put("mayIncome",0.0);
			monthsIncome.put("juneIncome",0.0);
			monthsIncome.put("julyIncome",0.0);
			monthsIncome.put("augustIncome",0.0);
			monthsIncome.put("septemberIncome",0.0);
			monthsIncome.put("octoberIncome",0.0);
			monthsIncome.put("novemberIncome",0.0);
			monthsIncome.put("decemberIncome",0.0);
			return monthsIncome;
		}
		monthsIncome.put("januaryIncome", allMonthIncomes[0]);
		monthsIncome.put("februaryIncome", allMonthIncomes[1]);
		monthsIncome.put("marchIncome", allMonthIncomes[2]);
		monthsIncome.put("aprilIncome", allMonthIncomes[3]);
		monthsIncome.put("mayIncome", allMonthIncomes[4]);
		monthsIncome.put("juneIncome", allMonthIncomes[5]);
		monthsIncome.put("julyIncome", allMonthIncomes[6]);
		monthsIncome.put("augustIncome", allMonthIncomes[7]);
		monthsIncome.put("septemberIncome", allMonthIncomes[8]);
		monthsIncome.put("octoberIncome", allMonthIncomes[9]);
		monthsIncome.put("novemberIncome", allMonthIncomes[10]);
		monthsIncome.put("decemberIncome", allMonthIncomes[11]);
		return monthsIncome;
	}
	
	
}