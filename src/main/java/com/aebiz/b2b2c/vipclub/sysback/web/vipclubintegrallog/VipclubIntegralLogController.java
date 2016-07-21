package com.aebiz.b2b2c.vipclub.sysback.web.vipclubintegrallog;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.VipclubIntegralLogService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.IntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogQueryModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.service.VipclubIntegralStatService;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.StatIntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatModel;

/**
 * 会员积分日志controller
 * 
 * @author huyingying
 * 
 */
@Controller
@RequestMapping("/sysback/vipclubintegrallog")
public class VipclubIntegralLogController extends BaseController<VipclubIntegralLogModel, VipclubIntegralLogQueryModel> {
	
	/* 会员积分日志service */
	private VipclubIntegralLogService myService;

	/* 会员积分统计service */
	@Autowired
	private VipclubIntegralStatService statService;

	@Autowired
	public void setMyService(VipclubIntegralLogService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public VipclubIntegralLogController() {
		super("vipclub/sysback/vipclubintegrallog", "VipclubIntegralLog",VipclubIntegralLogController.class);
	}

	/**
	 * 跳转会员日志列表页面
	 * @param model ,request
	 * @return Model
	 */
	@RequestMapping(value = "/toLogList/{customerUuid}", method = RequestMethod.GET)
	public String toQueryCustomerList(Model model, HttpServletRequest request,@PathVariable String customerUuid) {
		
		//页面添加会员Uuid信息
		model.addAttribute("customerUuid", customerUuid);
		
		return super.toList(model, request);
	}

	/**
	 * 组织会员积分类型发送到页面进行选择
	 * @param model,request
	 * @return Model
	 */
	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {
		
		List<DataTablesPageParam> types = new ArrayList<DataTablesPageParam>();
		
		for (IntegralType q : IntegralType.values()) {
			DataTablesPageParam data = new DataTablesPageParam();
			data.setValue(q.getValue());
			data.setName(q.getName());
			types.add(data);
		}
		
		model.addAttribute("integralType", types);
	}

	/**
	 * 平台后台可以手动增减会员积分<br />
	 * 跳转至积分调整页面进行调整<br />
	 * @param model
	 * @param request
	 * @param customerUuid
	 * @return 调整会员积分日志页面
	 */
	@RequestMapping(value = "/toAdapt/{customerUuid}", method = RequestMethod.GET)
	public String toAdapt(Model model, HttpServletRequest request,@PathVariable("customerUuid") String customerUuid) {
		
		// 查询会员统计表的会员名和对应的可用积分
		VipclubIntegralStatModel integralStatModel = statService.getByCustomerUuidAndIntegralType(customerUuid,StatIntegralType.USABLE.getValue());
		
		//向调整会员积分日志页面添加integralStatModel
		model.addAttribute("integralStatModel", integralStatModel);
		//跳转调整页面
		return "vipclub/sysback/vipclubintegrallog/VipclubIntegralLogAdapt";
	}

	/**
	 * 调整会员积分
	 * @param model
	 * @param request
	 * @param customerUuid
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String toSave(Model model, HttpServletRequest request,
			@RequestParam("customerUuid") String customerUuid,
			@RequestParam("adaptType") String adaptType,
			@RequestParam("changgeIntegralNum") int changgeIntegralNum,
			@RequestParam("description") String description,
			@RequestParam("userType") String userType) {
		
		//保存一条会员积分日志操作记录，更新会员积分统计表
		if("add".equals(adaptType)){
			myService.saveVipIntegralLog(customerUuid, adaptType, changgeIntegralNum,IntegralType.GET_BY_PLATEFORM.getValue(),userType,description,"");
		}else{
			myService.saveVipIntegralLog(customerUuid, adaptType, changgeIntegralNum,IntegralType.REDUCE_BY_PLATEFORM.getValue(),userType,description,"");
		}
		
		//页面增加会员Uuid信息
		model.addAttribute("customerUuid", customerUuid);
		model.addAttribute("userType", userType);
		//跳转保存成功页面
		return "vipclub/sysback/vipclubintegrallog/VipclubIntegralLogSuccess";
	}

}