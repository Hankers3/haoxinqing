package com.aebiz.b2b2c.vipclub.usercenter.web.vipclubintegrallog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.vipclub.usercenter.service.vipclubintegrallog.UCVipclubIntegralLogService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.VipclubIntegralLogService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.QueryTimeType;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogQueryModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.service.VipclubIntegralStatService;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.StatIntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatModel;

/**
 * 会员中心我的积分controller
 * 
 * @author huyingying
 * 
 */
@Controller
@RequestMapping("/usercenter/vipclubintegrallog")
public class UCVipclubIntegralLogController extends
		BaseController<VipclubIntegralLogModel, VipclubIntegralLogQueryModel> {

	/* 会员积分日志service */
	private VipclubIntegralLogService myService;

	/* 会员积分统计表service */
	@Autowired
	private VipclubIntegralStatService statService;

	/* 用户中心会员积分service */
	@Autowired
	private UCVipclubIntegralLogService ucService;


	@Autowired
	public void setMyService(VipclubIntegralLogService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public UCVipclubIntegralLogController() {
		super("vipclub/usercenter/vipclubintegrallog", "VipclubIntegralLog",
				UCVipclubIntegralLogController.class);
	}

	/**
	 * 重新toList方法，添加分页信息，向页面添加数据
	 */

	@Override
	@RequestMapping(value = "/toList/{nowPage}/{pageShow}", method = RequestMethod.GET)
	public String toList(@ModelAttribute("wm") BaseWebModel wm, Model model,
			HttpServletRequest request) {
		VipclubIntegralLogQueryModel qm = getQueryModel();
		String init = request.getParameter("init");
		if (!"true".equalsIgnoreCase(init)) {
			Object obj = SecurityUtils.getSubject().getSession()
					.getAttribute("VipclubIntegralLog" + "IsQuery");
			if ((obj != null) && (obj.toString().equals("true"))) {
				qm = (VipclubIntegralLogQueryModel) SecurityUtils.getSubject()
						.getSession()
						.getAttribute("VipclubIntegralLog" + "QueryModel");
			}
		}

		// 判断查询时间类型是否为空
		if (!StringUtil.isEmpty(qm.getQueryTimeType())) {

			// 查询三个月以内
			if (qm.getQueryTimeType().equals(
					QueryTimeType.IN_THREE_MONTH.getValue())) {

				// 设置查询时间为三个月之内
				qm.setQueryTime(this.getResultDate(3));
			}

			// 查询三个月以前
			if (qm.getQueryTimeType().equals(
					QueryTimeType.OUT_THREE_MONTH.getValue())) {

				// 设置查询时间为三个月之前
				qm.setQueryTime(this.getResultDate(3));
			}

			// 查询一年以内
			if (qm.getQueryTimeType().equals(
					QueryTimeType.IN_ONE_YEAR.getValue())) {

				// 设置查询时间为一年以内
				qm.setQueryTime(this.getResultDate(12));
			}
		}
		qm.setDelFlag("1");
		qm.getMapCondition().put("delFlag",
				Integer.valueOf(ConditionOpTypeEnum.EQ.getCode()));
		qm = preparedQMFixValue(qm);

		// 目前开发使用静态的customerUuid，后期从session中取
		qm.setCustomerUuid("4110279fe2204e64b936917976596fad");

		// 新建一个查询结果的会员积分日志记录集合
		List<VipclubIntegralLogModel> list = new ArrayList<VipclubIntegralLogModel>();

		// 查询所有积分日志记录
		wm.setTotalNum(ucService.getCount(qm));

		list = ucService.getByCondition(qm, wm.getFromNum(), wm.getPageShow());

		// 查询该类型会员积分总数，显示到页面
		int totalIntegral = ucService.getTypeIntegralSumByConditon(qm);
		request.setAttribute("totalIntegral", totalIntegral);

		// 将List添加到webmodel中
		wm.setRows(list);

		// 通过会员Uuid和积分类型查询统计表，向页面添加会员积分信息
		VipclubIntegralStatModel integralStatModel = statService
				.getByCustomerUuidAndIntegralType(
						"4110279fe2204e64b936917976596fad",
						StatIntegralType.USABLE.getValue());

		// 向页面添加会员查询model
		request.setAttribute("qm", qm);

		// 向页面添加会员积分统计表记录
		request.setAttribute("integralStatModel", integralStatModel);

		// 跳转会员积分日志列表页面
		return "vipclub/usercenter/vipclubintegrallog/VipclubIntegralLogList";
	}

	/**
	 * 从当前时间按月推算到之前的时间点方法
	 * 
	 * @param time
	 *            时间间隔，按月计算
	 * @return
	 */
	public String getResultDate(int time) {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -time);// 月份减一
		return DateFormatHelper.getTimeStr(calendar.getTime());
	}
}