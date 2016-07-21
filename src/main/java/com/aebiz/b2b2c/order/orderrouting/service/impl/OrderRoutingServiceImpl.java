package com.aebiz.b2b2c.order.orderrouting.service.impl;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderStatusEnum;
import com.aebiz.b2b2c.order.orderrouting.dao.OrderRoutingDAO;
import com.aebiz.b2b2c.order.orderrouting.service.OrderRoutingService;
import com.aebiz.b2b2c.order.orderrouting.vo.OrderRoutingModel;
import com.aebiz.b2b2c.order.orderrouting.vo.OrderRoutingQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;

@Service
@Transactional
public class OrderRoutingServiceImpl extends BaseServiceImpl<OrderRoutingModel,OrderRoutingQueryModel> implements OrderRoutingService {
	public Integer START = 1;
	public Integer DAYLENGTH = 10; 
	public Integer MONTHLENGTH = 7; 
	public Integer YEARLENGTH = 4; 
	private OrderRoutingDAO myDao = null;
	@Autowired
	private UuidService us;
	
	@Autowired
	public void setMyDao(OrderRoutingDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}
	
	/*注入订单的Service*/
	@Autowired
	private OrderMainService orderService;
	@Autowired
	private ServicestaffService servicestaffService;
	
	@Override
	public String create(OrderRoutingModel m) {
		m.setUuid(us.getNextUuid("orderrouting",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(OrderRoutingModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(OrderRoutingModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 根据订单id获取所有的分账数据
	 */
	@Override
	public List<OrderRoutingModel> searchAllRoutings(String orderMainUuid) {
		return myDao.getModelByOrderId(orderMainUuid);
	}
	/**
	 * 查询所有分账记录的总数量
	 */
	@Override
	public int getAllChargeCount(OrderRoutingQueryModel qm) {
		return myDao.getCount(qm);
	}
	
	/**
	 * 查询家政人员一天的收入
	 * @param day 传入距离查询日期的天数，例如：如果查询昨天的收入，则day=1，今天的day=0.以此类推
	 * @param Id  家政员Id 
	 * @return 所查询日期的收入
	 */
	public Double getTodayIncome(int day,String id) 
	{
		//定义一个变量用来存放累加结果，并赋值0.0
		Double incomeSum = 0.0; 
		List<Double> list = getIncomes(day,id);
		//
		if(list !=null && list.size()>0){
			for(Double d : list){
				incomeSum = incomeSum + d;
			}
		}
	
		return format(incomeSum);
	}
	/**
	 * 查询某个家政员在所查日期内的每一笔收入
	 * @param day 查询日期距离当前日期的天数差
	 * @param id 家政员ID
	 * @return 家政员一天的收入集合
	 */
	public List<Double> getIncomes(int day,String id)
	{
		List<Double> list = new ArrayList<Double>();
		String date = getDay(new Date(), day);
		//String id,int start,int length,String date
		//通过家政员分账信息对象获取家政员的每一笔收入，并逐一放到list中
		List<OrderRoutingModel> orderRoutingModels = myDao.getAppAll(id,START,DAYLENGTH,date);
		if(orderRoutingModels !=null && orderRoutingModels.size()>0){
			for(OrderRoutingModel model : orderRoutingModels){
				list.add(model.getRoutPrice());
			}
		}
		return list;		
	}
	/**
	 * 获得当前年份
	 * @return
	 */
	public  String getYear()
	{ 
		StringBuffer year = new StringBuffer(DateFormatHelper.getNowTimeStr());	
	    return year.substring(0,4);
	}
	/**
	 * 获得月份
	 * @return
	 */
	public  String getMonth()
	{ 
		StringBuffer date = new StringBuffer(DateFormatHelper.getNowTimeStr());
		//截取date字符串中的月份
		String month = date.substring(5,7);
		return month;
		
	}
	/**
	 * 传入一个util.Date类型的时间和距离传入时间的天数day，返回一个String类型的”年月日“时间："yyyy-MM-dd"
	 * @param date util.Date对象
	 * @param day 查询日期距离当前日期的天数差，例如：如果查询昨天的收入，则day=1，今天的day=0.以此类推
	 * @return String类型的”年月日“时间："yyyy-MM-dd"
	 */
	public String getDay(Date date,int day)
	{
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(5, now.get(5) - day);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
	    return df.format(now.getTime());
	}

	@Override
	/**
	 * 查询家政员本年收入
	 * @param id 家政员ID
	 * @return
	 */
	public Double getYearSumIncome(String id) {
		//定义一个变量用来存放累加结果，并赋值0.0
		Double incomeSum = 0.0; 
		//
		List<Double> yearIncomes = getYearIncomes(id);
		if(yearIncomes!=null && yearIncomes.size()>0)
		{
			for(Double d : yearIncomes)
			{
				if(d!=null)
				{
					incomeSum = incomeSum + d;
				}
				else
				{
					return incomeSum;
				}
			}
		}
		
		
		return format(incomeSum);
	}
	/**
	 * 获取本年度所有订单
	 * @param id
	 * @return
	 */
	@Override
	public List<Double> getYearIncomes(String id){
		List<Double> list = new ArrayList<Double>();
		String date = getYear();
		//String id,int start,int length,String date
		//通过家政员分账信息对象获取家政员的每一笔收入，并逐一放到list中
		List<OrderRoutingModel> listAll =myDao.getAppAll(id,START,YEARLENGTH,date);
		if(listAll!=null&&listAll.size()>0){
			for(OrderRoutingModel model : listAll)
			{
				//将所有分账信息的分账金额放入list中
				list.add(model.getRoutPrice());
			}
		}
		
		return list;	
	}
	/**
	 * 查询家政员在某月份内的总收入
	 * @param id 家政员ID
	 * @param month 查询月份   格式为：01,02,12
	 * @return 某月份内的总收入
	 */
	@Override
	public Double getMonthSumIncome(String id,String month) {
		//定义一个变量用来存放累加结果，并赋值0.0
		Double incomeSum = 0.0; 
		//获取家政员一个月内每一笔订单收入
		List<Double> list = getMonthIncomes(id,month);
		if(list!=null&&list.size()>0)
		{
			for(Double d : list)
			{
				if(d!=null)
				{
					incomeSum = incomeSum + d;
				}
			}
		}
		
		//月度收入总和
		return format(incomeSum);
	}
	@Override
	/**
	 * 获得本年度内十二个月份的收入
	 * @return
	 */
	public Double[] getAllMonthIncomes(String id) {
		Double[] mIncome = new Double[12];	
		
		for(int i=0;i<12;i++){
			StringBuffer month = new StringBuffer("0");
			int j = i +1 ;
			if(j<10){
				//利用getMonthSumIncome查询出对应月份j月份的月收入
				month.append(j);
				String month1 = month.toString();
				mIncome[i] = format(getMonthSumIncome(id,month1));
			}else{
				mIncome[i] = format(getMonthSumIncome(id, String.valueOf((j))));
			}
		}
		return mIncome;
	}
	/**
	 * 查询家政员在某月份的每一笔收入
	 * @param id 家政员ID
	 * @param month 查询月份
	 * @return 某月份的每一笔收入
	 */
	@Override
	public List<Double> getMonthIncomes(String id, String month) {
		List<Double> list = new ArrayList<Double>();
		String date = getYear() + "-"+month;
		//String id,int start,int length,String date
		//通过家政员分账信息对象获取家政员的每一笔收入，并逐一放到list中
		List<OrderRoutingModel> orderRoutingModels = myDao.getAppAll(id,START,MONTHLENGTH,date);
		if(orderRoutingModels !=null && orderRoutingModels.size()>0){
			for(OrderRoutingModel model : orderRoutingModels){
				list.add(model.getRoutPrice());
			}
		}
		return list;	
	}
	/**
	 * 获取当前月份收入
	 * @param id 家政员ID
	 * @return 当月收入
	 */
	@Override
	public Double getCurrentMonthIncomes(String id) {
		//获取当前月份
		String current = getMonth();
		//获取当前月份收入
		Double currentMonthIncomes = getMonthSumIncome(id, current);
		if(currentMonthIncomes==null)
		{
			currentMonthIncomes = 0.0;
		}
		return format(currentMonthIncomes);
	}

	/**
	 * 根据家政员编号和分账时间获取总的分账金额等
	 */
	@Override
	public double getTotalMoneyBystaffId(String strName,
			String serviceStaffUuid, String routTime) {
		return myDao.getTotalMoneyBystaffId(strName, serviceStaffUuid, routTime);
	}
	
	/**
	 * 查询家政员在本月内的订单数量
	 * @param id 家政员ID
	 * @return
	 */
	@Override
	public int getStaffMonthOrderCount(String id) {
		//获取当前月份
		String month = getMonth();
		String year = getYear();
		String date = year+"-"+month; 
		int count = myDao.getOrderCount(id, MONTHLENGTH, date);
		
		return count;

	}
	@Override
	/**
	 * 查询家政员在一定日期内的订单数量
	 * @param date 查询日期
	 * @param id 家政员ID
	 * @return
	 */
	public int getStaffOrderCount(String date, String id) {
		int count = myDao.getOrderCount(id,DAYLENGTH, date);
		return count;
	}
	
	/**
	 * 创建分账信息
	 * 
	 * @param serviceStaffUuid 家政员编号
	 * @param orderMainUuid 订单编号
	 * @param routPrice 分账金额
	 * @param routType 分账类型 
	 */
	@Override
	public void createOrderRout(String serviceStaffUuid, String orderMainUuid,
			double routPrice, String routType) {
		OrderRoutingModel m = new OrderRoutingModel();
		m.setServiceStaffUuid(serviceStaffUuid);
		m.setOrderMainUuid(orderMainUuid);
		m.setRoutPrice(routPrice);
		m.setRoutType(routType);
		m.setRoutTime(DateFormatHelper.getNowTimeStr());
		this.create(m);
	}
	
	
	/**
	 * 根据家政员id查询在一定时间内的分账记录
	 */
	@Override
	public List<Object> getOrderRoutingList(String serviceStaffUuid,
			String timeType, int pageNo, int pageCount) {
		return myDao.getOrderRoutingList(serviceStaffUuid, timeType, pageNo, pageCount);
	}
	
	/**
	 * 获取家政员的总分账金额
	 */
	@Override
	public Double getStaffTotalRouting(String serviceStaffUuid) {
		return myDao.getTotalRoutingMoneyBystaffId(serviceStaffUuid);
	}

	/**
	 * 根据家政员id和分账时间查询对应的记录
	 */
	@Override
	public List<OrderRoutingModel> getWagesByserviceStaffUuid(
			String serviceStaffUuid, String routTime) {
		return myDao.getWagesByserviceStaffUuid(serviceStaffUuid, routTime);
	}
	/**
	 * 获取某一个月份所有家政员分账总和
	 */
	@Override
	public double getTotalRoutingMoneyByTime(String routTime,String routType) {
		return myDao.getTotalRoutingMoneyByTime(routTime,routType);
	}
	@Override
	public double format(double price) {
		DecimalFormat formatter = new DecimalFormat("0.00");
		return Double.parseDouble(formatter.format(price));
	}
	/**
	 * 获取家政员分账列表
	 * @param serviceStaffUuid
	 * @return
	 * @2015-4-20
	 * @author :SZH
	 */
	@Override
	public List<OrderRoutingModel> getOrderRoutingsByServiceStaffId(
			String serviceStaffUuid, int pageCount, int pageNo) {
		return myDao.getOrderRoutingsByServiceStaffId(serviceStaffUuid,pageCount,pageNo);
	}

	@Override
	public Double getStaffTotalRouting(String orderMainUuid, String routeType) {
		return myDao.getStaffTotalRouting(orderMainUuid, routeType);
	}

	/**
	 * 得到医生的不重复的UuidList
	 * @return
	 */
	@Override
	public List<String> getDoctorUuids() {
		return myDao.getDoctorUuids() ;
	}
	
	/**
	 * 得到医生的不重复的UuidList
	 * @return
	 */
	@Override
	public List<String> getUuids() {
		return myDao.getDoctorUuids() ;
	}
	/**
	 * 根据医生Uuid得到List
	 * @return
	 */
	@Override
	public List<OrderRoutingModel> getByDoctorUuid(String doctorUuid) {
		return myDao.getByDoctorUuid(doctorUuid);
	}
	/**
     * 获取平台收益的总钱数
     * @return
     */
    @Override
    public Number getAllPlatformIncome() {
        return myDao.getAllPlatformIncome();
    }
    /**
     * 根据医生的id和类型获取医生的收入
     * @param doctorUuid
     * @param type
     * @return
     */
    @Override
    public Number getDoctorAllIncomeByIdAndType(String doctorUuid, String type) {
        return myDao.getDoctorAllIncomeByIdAndType(doctorUuid,type);
    }
    
    /**
     * 订单分账
     */
	@Override
	public void orderFz() {
		//获取可以分账的订单列表
		List<OrderMainModel> list = orderService.getCanFzOrders();
		if(list !=null && list.size()>0){
			for(OrderMainModel order :list){
				// 如果订单为空 或订单状态不是已到货，则不计算分账
		
				//计算订单分账的金额 ： 订单支付金额 
				Double orderAmount = 0.0;
				orderAmount = order.getPayMoney();
				String orderType=order.getOrderType();
				// 如果需要分账的金额小于或等于0，则返回
				if (orderAmount <= 0) {
					order.setNote("订单实际金额为" + orderAmount + "，小于0，所以不计算分账");
					return;
				}
				// 获取订单分账比率
				double splitRate = 0.7;
				String fzSpit= MessageHelper.getMessage("fzSplit");
				if(!StringUtil.isEmpty(fzSpit)){
					splitRate = Double.parseDouble(fzSpit);
				}
				
				// 定义医生分账金额
				Double doctorFz = 0.00;
				// 定义平台分账金额
				Double shopFz = 0.00;
				// 订单分账注释
				StringBuffer fzNote = new StringBuffer("订单计算分账金额: 分账比率=");
				fzNote.append(splitRate).append("%;订单总额=").append(orderAmount).append(";");
		
				//店铺分账金额  = 订单需要分账的金额 * (分账比率/100)
				doctorFz = orderAmount * (splitRate / 1);
				fzNote.append("订单分账金额=").append(doctorFz).append(";");
				// 平台分账金额= 订单需要分账的金额 - 平台分账的金额
				shopFz = orderAmount - doctorFz;
				fzNote.append("平台分账金额=").append(shopFz).append(";");
				// 将店铺分账金额set到订单中
				order.setNote(fzNote.toString());
				order.setAccountState("1");
				orderService.update(order);
				
				//生成分账信息到 分账信息表中
				String incomeType="";
				if(!StringUtil.isEmpty(orderType)&&"1".equals(orderType)){
					incomeType = OrderRoutingModel.incomeType_tel;
				}else{
					incomeType = OrderRoutingModel.incomeType_pes;
				}
				
				/*********************分账信息****************************/
				//获取医生信息，将分账收入计入医生收入总额
				ServicestaffModel servicestaffModel = servicestaffService.getByUuid(order.getDoctorUuid());
				//医生分账信息
				if(doctorFz>0){
					OrderRoutingModel orm = new OrderRoutingModel();
					orm.setOrderMainUuid(order.getUuid());
					orm.setServiceStaffUuid(order.getDoctorUuid());
					orm.setRoutPrice(doctorFz);
					orm.setRoutTime(DateFormatHelper.getNowTimeStr());
					orm.setRoutType(OrderRoutingModel.routType_doctor);
					orm.setIncomeType(incomeType);
					String succ = this.create(orm);
					
					//分账成功后将分账收入计入医生收入总额
					if (!StringUtil.isEmpty(succ) && servicestaffModel!=null) {
						double totalPrice = servicestaffModel.getTotalPrice();
						servicestaffModel.setTotalPrice(totalPrice + doctorFz);
						servicestaffService.update(servicestaffModel);
					}
					
					
				}
				//生成分账信息到 分账信息表中 --平台分账信息
				if(shopFz >0){
					OrderRoutingModel orm = new OrderRoutingModel();
					orm.setOrderMainUuid(order.getUuid());
					orm.setServiceStaffUuid(order.getDoctorUuid());
					orm.setRoutPrice(shopFz);
					orm.setRoutTime(DateFormatHelper.getNowTimeStr());
					orm.setRoutType(OrderRoutingModel.routType_sys);
					orm.setIncomeType(incomeType);
					this.create(orm);
				}
				
			}
		}
	}
}