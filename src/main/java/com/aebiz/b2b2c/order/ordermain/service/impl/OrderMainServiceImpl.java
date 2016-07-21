package com.aebiz.b2b2c.order.ordermain.service.impl;

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
import com.aebiz.b2b2c.basicdata.city.service.CityService;
import com.aebiz.b2b2c.order.accountkey.service.AccountKeyService;
import com.aebiz.b2b2c.order.accountkey.vo.AccountKeyModel;
import com.aebiz.b2b2c.order.orderclose.service.OrderCloseService;
import com.aebiz.b2b2c.order.orderdetail.service.OrderDetailService;
import com.aebiz.b2b2c.order.orderdetail.vo.OrderDetailModel;
import com.aebiz.b2b2c.order.ordermain.dao.OrderMainDAO;
import com.aebiz.b2b2c.order.ordermain.service.OrderMainService;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainModel;
import com.aebiz.b2b2c.order.ordermain.vo.OrderMainQueryModel;
import com.aebiz.b2b2c.order.ordermainaddress.service.OrderMainAddressService;
import com.aebiz.b2b2c.order.ordermainlog.service.OrderMainLogService;
import com.aebiz.b2b2c.order.orderstamp.service.OrderStampService;
import com.aebiz.b2b2c.product.productmain.service.ProductMainService;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.staffloginstatus.service.StaffLoginStatusService;

@Service
@Transactional
public class OrderMainServiceImpl extends
		BaseServiceImpl<OrderMainModel, OrderMainQueryModel> implements
		OrderMainService {
	private OrderMainDAO myDao = null;

	// 编号类型订单
	public static final String NOTYPE_ORDER = "order";
	// 时间参数：年或者月份
	public static final String YEAR_CURTIME = "year";
	public static final String MONTH_CURTIME = "month";
	// 0为拒单2为未接单 3为接单失败
	public static final String RECEIVESTATUS_ONE = "0";
	// 1为接单
	public static final String RECEIVESTATUS_TWO = "1";
	public static final String RECEIVESTATUS_THREE = "2";
	public static final String RECEIVESTATUS_FOUR = "3";
	@Autowired
	private UuidService us;
	@Autowired
	private OrderDetailService detailService;
	@Autowired
	private OrderMainLogService orderMainLogService;

	@Autowired
	private OrderCloseService orderCloseService;

	@Autowired
	private OrderStampService orderStampService;

	@Autowired
	private AccountKeyService accountKeyService;

	@Autowired
	private StaffLoginStatusService staffLoginStatusService;

	@Autowired
	private OrderMainAddressService orderMainAddressService;

	@Autowired
	private ServicestaffService servicestaffService;

	@Autowired
	private ProductMainService productMainService;

	@Autowired
	private CityService cityService;

	@Autowired
	public void setMyDao(OrderMainDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(OrderMainModel m) {
		m.setUuid(us.getNextUuid("Order", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(OrderMainModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(OrderMainModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 订单超时未支付，关闭订单
	 * 
	 * @param omm
	 */
	/*
	 * public void closeOrderForOvertime(OrderMainModel omm) { // 如果订单不存在 if
	 * (omm == null) { throw new AppException("order.not.existed"); }
	 * 
	 * if (OrderPayTypeEnum.PAYTYPENOCOD.getValue().equals(omm.getPayType())) {
	 * // 如果是货到付款,只有确认状态能够取消 if
	 * (!OrderStatusEnum.WAITCONFIRM.getValue().equals(omm.getState())) { throw
	 * new AppException("order.state.notequal"); } } else { //
	 * 如果是非货到付款，如果订单不是未支付状态,提示状态不匹配 if
	 * (!OrderStatusEnum.WAITPAY.getValue().equals(omm.getState())) { throw new
	 * AppException("order.state.notequal"); } }
	 * 
	 * // 更新订单的状态为关闭 omm.setState(OrderStatusEnum.ORDERFAIL.getValue());
	 * this.myDao.update(omm);
	 * 
	 * // 记录日志-订单超时未支付，自动关闭 String note =
	 * MessageHelper.getMessage("order.opernote.closebysys");
	 * orderMainLogService.createOrderLog(omm.getUuid(), note,
	 * OrderLogOperateEnum.CANCELBYSYSTEM.getValue(),
	 * LoginUserHelper.getLoginUserUuid(),
	 * PlatFormRoleTypeEnum.PLATFORM.getValue());
	 * 
	 * // 记录订单的取消表 orderCloseService.createOrderClose(omm.getUuid(),
	 * OrderCloseModel.CANCELBYSYSTEM, note); }
	 */

	/**
	 * 订单查询列表，重新定义获得查询总数
	 * 
	 * @param qm
	 */
	public int getOrderCount(OrderMainQueryModel qm) {
		return myDao.getOrderCount(qm);
	}

	/**
	 * *************************************************************************
	 * 
	 * @param tempList
	 * @return
	 */
	private List<OrderMainModel> exeResultList(List<OrderMainModel> tempList) {
		List<OrderMainModel> ommList = new ArrayList<OrderMainModel>();
		for (OrderMainModel omm : tempList) {
			if (omm != null) {
				// 获得订单的订单明细列表
				List<OrderDetailModel> detailList = detailService
						.getOrderDetailModelByOrderId(omm.getUuid());
				omm.setDetailList(detailList);

			}

			ommList.add(omm);
		}
		return ommList;
	}

	/**
	 * 根据用户id来获取其订单列表
	 * 
	 * @param customerUuid
	 */
	@Override
	public List<String> getOrderListByCustomerUuid(String customerUuid,
			String type, int pageCount, int pageNo) {
		return myDao.getOrderListByCustomerUuid(customerUuid, type, pageCount,
				pageNo);
	}

	/**
	 * 根据订单id获取订单
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public OrderMainModel getOrderMainModelByOrderId(String orderId) {
		return myDao.getOrderMainModelByOrderId(orderId);
	}

	/**
	 * 通过订单编号获得这个主订单对象
	 * 
	 * @param orderUuid
	 * @return
	 */
	@Override
	public OrderMainModel getOrderMainByUuid(String orderUuid) {

		return this.myDao.getOrderMainByUuid(orderUuid);
	}
	/**
	 * 创建生成订单流水号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createOrderNo() {
		String returnNo = "";
		synchronized (NOTYPE_ORDER) {
			// 日期
			String dateStr = DateFormatHelper.getNowTimeStr().substring(0, 10);
			String date = dateStr.replace("-", "");
			int maxcount = getMaxCount();

			if (maxcount == 1000000)
				maxcount = 1;

			// 保存订单流水号
			AccountKeyModel accountKey = accountKeyService
					.getAccountKeyModelById(NOTYPE_ORDER);

			if (accountKey == null) {
				accountKey = new AccountKeyModel();
				accountKey.setId(NOTYPE_ORDER);
				accountKey.setValue("1");
				accountKeyService.create(accountKey);
			} else {
				accountKey.setValue(maxcount + "");
				accountKeyService.update(accountKey);
			}

			// 生成订单号
			String orderno = getId(maxcount + "", 6);
			returnNo = date + orderno;

			return returnNo + "8";
		}

	}

	/**
	 * 返回最大数
	 * 
	 */
	private int getMaxCount() {
		try {
			AccountKeyModel key = accountKeyService
					.getAccountKeyModelById(NOTYPE_ORDER);
			if (key != null) {
				return Integer.parseInt(key.getValue()) + 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 1;
	}

	/**
	 * 获取定长流水号
	 * 
	 * @param sno
	 * @param length
	 * @return
	 */
	public static String getId(String sno, int length) {
		int zeronum = length - ((sno + "").length());

		String returnSno = "";

		for (int i = 0; i < zeronum; i++) {
			returnSno = returnSno + "0";
		}

		returnSno = returnSno + sno;

		return returnSno;
	}

	/**
	 * 按照分账日期查询对应家政员所有的订单
	 */
	@Override
	public List<OrderMainModel> getOrderListByServiceStaffUuid(
			String serviceStaffUuid, String routTime) {
		return myDao.getOrderListByServiceStaffUuid(serviceStaffUuid, routTime);
	}

	/**
	 * 根据家政员id和分账时间获取相关分账数据总额
	 */
	@Override
	public double getTotalMoneyByStaffId(String strName,
			String serviceStaffUuid, String routTime) {
		return myDao
				.getTotalMoneyByStaffId(strName, serviceStaffUuid, routTime);
	}

	/**
	 * 统计订单某个月份收入管理
	 */
	@Override
	public List<Object> getOrderListByReceiveTime(String receiveTime,
			int pageCount, int pageNo) {
		return myDao.getOrderListByReceiveTime(receiveTime, pageCount, pageNo);
	}


	/**
	 * 查询某一个月所有已完成的订单
	 */
	@Override
	public int getCountOrderListByReceiveTime(String receiveTime) {
		return myDao.getCountOrderListByReceiveTime(receiveTime);
	}

	/**
	 * 根据确认时间查询每天的支付宝支付或者微信支付的金额总和
	 */
	@Override
	public double getPayMoneyByReceiveTime(String receiveTime, String payType) {
		return myDao.getPayMoneyByReceiveTime(receiveTime, payType);
	}

	/**
	 * 获取某一段时间的已完成订单收入金额总和
	 */
	@Override
	public double getPaidMoneyByReceiveTime(String receiveTime) {
		return myDao.getPaidMoneyByReceiveTime(receiveTime);
	}

	/**
	 * 根据订单uuid获取id
	 */
	@Override
	public String getOrderIdByUuid(String uuid) {
		return myDao.getOrderIdByUuid(uuid);
	}

	/**
	 * 根据会员id和订单类型获取所有订单
	 */
	@Override
	public List<OrderMainModel> getOrderListByCustomerId(
			OrderMainQueryModel qm, String customerId, String state,
			int pageCount, int pageNo) {
		return myDao.getOrderListByCustomerId(qm, customerId, state, pageCount,
				pageNo);
	}

	/**
	 * 根据会员id和订单类型查询总数量
	 */
	@Override
	public int getOrderCountByCustomerId(OrderMainQueryModel qm,
			String customerId, String state) {
		return myDao.getOrderCountByCustomerId(qm, customerId, state);
	}

	/**
	 * 根据会员ID查询评价列表
	 * 
	 * @param customerUuid
	 *            会员ID
	 * @param pageCount
	 * @param pageNo
	 * @return
	 */
	@Override
	public List<OrderMainModel> getOrdermainCommentList(String customerUuid,
			int pageCount, int pageNo) {
		return myDao
				.getOrderListByCustomerUuid(customerUuid, pageCount, pageNo);
	}

	/**
	 * 查詢訂單的對應數量
	 * 
	 * @param serachType
	 * @return
	 */

	@Override
	public int getOrderCount(String serachType) {

		return myDao.getOrderCount(serachType);
	}

	/**
	 * 根据家政员ID当月的订单数量
	 * 
	 * @param serviceStaffUuid
	 * @return
	 * @2015-3-31
	 * @author :SZH
	 */
	public int getCurMonthReceiveOrderCount(String serviceStaffUuid) {
		String curTime = MONTH_CURTIME;
		String receiveTime = DateFormatHelper.getNowTimeStr().substring(0, 7);
		// 接单
		String receiveStatus = RECEIVESTATUS_TWO;
		return myDao.getReceiveOrderCount(serviceStaffUuid, curTime,
				receiveTime, receiveStatus);
	}

	/**
	 * 根据家政员ID当年的订单数量
	 * 
	 * @param serviceStaffUuid
	 * @return
	 * @2015-3-31
	 * @author : SZH
	 */
	public int getCurYearReceiveOrderCount(String serviceStaffUuid) {
		String curTime = YEAR_CURTIME;
		String receiveTime = DateFormatHelper.getNowTimeStr().substring(0, 4);
		// 接单
		String receiveStatus = RECEIVESTATUS_TWO;
		return myDao.getReceiveOrderCount(serviceStaffUuid, curTime,
				receiveTime, receiveStatus);
	}


	/**
	 * 查询当前年的接单总额
	 * 
	 * @param serviceStaffUuid
	 * @return
	 * @2015-3-31
	 * @author :JoeyShee
	 */
	@Override
	public int getReceiveOrderYearSum(String serviceStaffUuid) {
		String curTime = YEAR_CURTIME;
		String receiveTime = DateFormatHelper.getNowTimeStr().substring(0, 4);
		String receiveStatus = RECEIVESTATUS_TWO;

		return myDao.getReceiveOrderSum(serviceStaffUuid, curTime, receiveTime,
				receiveStatus);
	}

	/**
	 * 查询当前月的接单总额
	 * 
	 * @param serviceStaffUuid
	 * @return
	 * @2015-3-31
	 * @author :JoeyShee
	 */
	@Override
	public int getReceiveOrderMonthSum(String serviceStaffUuid) {
		String curTime = MONTH_CURTIME;
		String receiveTime = DateFormatHelper.getNowTimeStr().substring(0, 7);
		String receiveStatus = RECEIVESTATUS_TWO;
		return myDao.getReceiveOrderSum(serviceStaffUuid, curTime, receiveTime,
				receiveStatus);

	}

	/**
	 * 获得订单的数量根据订单对应的状态
	 * 
	 * @param staffUuid
	 * @param state
	 * @param commentState
	 * @return
	 */
	@Override
	public int getCountOrderNum(String staffUuid, String state,
			String commentState) {

		return myDao.getCountOrderNum(staffUuid, state, commentState);
	}


	/**
	 * 根据订单组编号获取子订单集合
	 * 
	 * @param orderGroupUuid
	 * @return
	 */
	public List<OrderMainModel> getByOrderGroupUuid(String orderGroupUuid) {
		return this.myDao.getByOrderGroupUuid(orderGroupUuid);
	}

	/**
	 * 根据订单金额计算需要多少家政员
	 * 
	 * @param orderAmount
	 * @return
	 */
	public int getOrderNeedNumbersByOrderAmount(double orderAmount) {
		double number = orderAmount / 80;
		int num = Integer.parseInt(new java.text.DecimalFormat("0")
				.format(number));
		return num;
	}

	/**
	 * 30秒倒计时
	 */
	public void thirtySecond() {
		long time;
		// 第一次推送完系统时间
		long onePushTime = System.currentTimeMillis();
		time = onePushTime;
		while (true) {
			onePushTime = System.currentTimeMillis();
			long d = onePushTime - time;
			if (d > 10000) {
				System.out.println("倒计时结束！");
				break;
			}
		}
	}

	/**
	 * 返回错误信息调用的组装jsonMap
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	public Map<Object, Object> getFailJsonMap(String code, String message) {
		Map<Object, Object> jsonMap = new HashMap<Object, Object>();
		Map<Object, Object> runMap = new HashMap<Object, Object>();
		runMap.put("runNumber", code);
		runMap.put("message", message);
		jsonMap.put("query", runMap);
		return jsonMap;
	}

	@Override
	public List<OrderMainModel> getOrderList(String orderType,
			String customerUuid, int pageCount, int pageNo) {
		return myDao.getOrderList(orderType, customerUuid, pageCount, pageNo);
	}

	@Override
	public List<OrderMainModel> getOrderList(String customerUuid,
			int pageCount, int pageNo) {
		return myDao.getOrderList(customerUuid, pageCount, pageNo);
	}

	/**
	 * 获取医生电话咨询的数量
	 */
	@Override
	public int getTotalTelNumByDoctorId(String doctorId) {
		return myDao.getTotalTelNumByDoctorId(doctorId);
	}
	/**
     * 根据患者的id和订单的状态查询患者所有的订单
     * @param state
     * @param customerUuid
     * @param pageCount
     * @param pageNo
     * @return
     */
    @Override
    public List<OrderMainModel> getCustomerOrderList(String state, String customerUuid, int pageCount,
            int pageNo,String orderType) {
        return myDao.getCustomerOrderList( state,  customerUuid,  pageCount, pageNo,orderType) ;
    }
    
 	/**
 	 * 根据患者的id和订单的状态查询患者所有的订单
 	 * @param state
 	 * @param customerUuid
 	 * @param pageCount
 	 * @param pageNo
 	 * @return
 	 */
    public List<OrderMainModel> getOrderList(String state, String doctorUuid, int pageCount, int pageNo,String orderType){
      return myDao.getOrderList( state,  doctorUuid,  pageCount, pageNo,orderType) ;
    }
    
    
 	/**
 	 * 根据患者的id和订单的状态查询患者所有编号
 	 * @param state
 	 * @param customerUuid
 	 * @param pageCount
 	 * @param pageNo
 	 * @return
 	 */
    public List<String> getCustomerUuids(String state, String doctorUuid, int pageCount, int pageNo,String orderType){
      return myDao.getCustomerUuids( state,  doctorUuid,  pageCount, pageNo,orderType) ;
    }


	/**
	 * 获取医生id
	 * 
	 * @param orderMainUuid
	 * @return
	 */
	@Override
	public String getDoctorUuidByUuid(String orderMainUuid) {
		return myDao.getDoctorUuidByUuid(orderMainUuid);
	}

	/**
	 * 获取患者id
	 * 
	 * @param orderMainUuid
	 * @return
	 */
	@Override
	public String getCustomerUuidByUuid(String orderMainUuid) {
		return myDao.getCustomerUuidByUuid(orderMainUuid);
	}
	
	/**
	 * 通过会议Id获取订单对象
	 * @param confId
	 * @return
	 */
	@Override
	public OrderMainModel getOrderMainByConfId(String confId) {
	
		return myDao.getOrderMainByConfId(confId);
	}
	/**
	 * 根据医生编号 通话时长、预约日期 
	 * by add xl 20160115
	 * @param doctorUuid
	 * @param consultDuration
	 * @param bookTime
	 * @return
	 */
	@Override
	public List<OrderMainModel> getOrderMains(String doctorUuid,
			String consultDuration, String bookTime) {
		
		return myDao.getOrderMains(doctorUuid, consultDuration, bookTime);
	}
	
	/**
	 * 获取可以分账的订单信息
	 * by add xl 20160128
	 * @return
	 */
	@Override
	public List<OrderMainModel> getCanFzOrders() {
		return myDao.getCanFzOrders();
	}

}