package com.aebiz.b2b2c.vipclub.sysback.web.vipclubintegralstat;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerInteractive;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffQueryModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.service.VipclubIntegralStatService;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.CutomerIntegralStatModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.DoctorIntegralStatModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.VipclubIntegralStatQueryModel;
import com.alibaba.fastjson.JSON;

/**
 * 会员积分统计表controller
 * 
 * @author huyingying
 * 
 */
@Controller
@RequestMapping("/sysback/vipclubintegralstat")
public class VipclubIntegralStatController extends BaseController<VipclubIntegralStatModel, VipclubIntegralStatQueryModel> {
	
	/* 会员积分统计表service */
	private VipclubIntegralStatService myService;
	
	/* 会员系统接口service */
	@Autowired
	private CustomerInteractive customerInteractive;
	/* 会员系统接口service */
	@Autowired
	private ServicestaffService staffService;
	
	@Autowired
	public void setMyService(VipclubIntegralStatService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public VipclubIntegralStatController() {
		super("vipclub/sysback/vipclubintegralstat", "VipclubIntegralStat",VipclubIntegralStatController.class);
	}

	/**
	 * 跳转到医生积分统计列表
	 * 
	 * @param model
	 * @param request
	 * @param customerUuid
	 */
	@RequestMapping(value = "/toDcList", method = RequestMethod.GET)
	public String toDcList(Model model, HttpServletRequest request) {
		
		//跳转调整页面
		return "vipclub/sysback/vipclubintegralstat/VipclubIntegralStatDcList";
	}
	
	/**
	 * 修改调整积分信息
	 */
	@Override
	@RequestMapping(value = "/toUpdate/{uuid}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		VipclubIntegralStatModel m = this.myService.getByUuid(uuid);
		model.addAttribute("m", m);
		return "vipclub/sysback/vipclubintegralstat/VipclubIntegralStatUpdate";
	}
	

	/**
	 * 重写queryList方法
	 * 
	 * @return 返回json数据
	 */
	@Override
	@RequestMapping("/queryList")
	public String queryList(HttpServletResponse response,HttpServletRequest request) throws Exception {
		Map<String, Object> pageParamMap = parsePageParam(request);
		
		VipclubIntegralStatQueryModel qm = parseQueryModel(request);
		
		qm = preparedQMFixValue(qm);
		
		CustomerQueryModel qm1 = new CustomerQueryModel();
		qm1.setCustomerName(qm.getCustomerName());
		qm1.getMapCondition().put("customerName", ConditionOpTypeEnum.Like.getCode());
		
		qm1.setSortName(qm.getSortName());
		qm1.setSortType(qm.getSortType());
		int iDisplayStart = ((Integer) pageParamMap.get("iDisplayStart")).intValue();

		int iDisplayLength = ((Integer) pageParamMap.get("iDisplayLength")).intValue();

		int iSortCol_0 = ((Integer) pageParamMap.get("iSortCol_0")).intValue();

		String sortFieldName = (String) pageParamMap.get("mDataProp_"+ iSortCol_0);

		String sortTypeString = (String) pageParamMap.get("sSortDir_0");

		Boolean needSort = (Boolean) pageParamMap.get("bSortable_" + iSortCol_0);

		if (needSort.booleanValue()) {
			qm1.setSortName(sortFieldName);
			qm1.setSortType(sortTypeString);
		}
		
		//获取会员 或者医生的积分信息 
		List<CustomerModel> listData =null;
		int totalCount = 0;
	
		listData = customerInteractive.getCustomerModelListByCondition(qm1, iDisplayStart,iDisplayLength);
		totalCount = this.customerInteractive.getCountByCondition(qm1);

		List<CutomerIntegralStatModel> showList=myService.makeCutomerIntegralStatModel(listData);
		
		Map<String, Object> jsonMap = new HashMap();
		
		jsonMap.put("sEcho", pageParamMap.get("sEcho"));
		
		jsonMap.put("iTotalRecords", Integer.valueOf(totalCount));
		
		jsonMap.put("iTotalDisplayRecords", Integer.valueOf(totalCount));
		
		jsonMap.put("aaData", showList);
		
		response.setContentType("charset=UTF-8");
		
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.print(JSON.toJSONString(jsonMap));
		
		return null;
	}
	
	
	
	/**
	 * 重写queryList方法
	 * 
	 * @return 返回json数据
	 */
	@RequestMapping("/queryDList")
	public String queryDList(HttpServletResponse response,HttpServletRequest request) throws Exception {
		Map<String, Object> pageParamMap = parsePageParam(request);
		
		VipclubIntegralStatQueryModel qm = parseQueryModel(request);
		
		qm = preparedQMFixValue(qm);
		
		//拼接查询语句使用
		ServicestaffQueryModel qm1 = new ServicestaffQueryModel();
		qm1.setRealName(qm.getCustomerName());
		qm1.getMapCondition().put("realName", ConditionOpTypeEnum.Like.getCode());

		int iDisplayStart = ((Integer) pageParamMap.get("iDisplayStart")).intValue();

		int iDisplayLength = ((Integer) pageParamMap.get("iDisplayLength")).intValue();

		int iSortCol_0 = ((Integer) pageParamMap.get("iSortCol_0")).intValue();

		String sortFieldName = (String) pageParamMap.get("mDataProp_"+ iSortCol_0);

		String sortTypeString = (String) pageParamMap.get("sSortDir_0");

		Boolean needSort = (Boolean) pageParamMap.get("bSortable_" + iSortCol_0);

		if (needSort.booleanValue()) {
			qm.setSortName(sortFieldName);
			qm.setSortType(sortTypeString);
		}
		
		//获取会员 或者医生的积分信息 
		List<ServicestaffModel> listData =null;
		int totalCount = 0;
		
		listData = staffService.getServicestaffModelListByCondition(qm1, iDisplayStart,iDisplayLength);
		totalCount = staffService.getCountByCondition(qm1);

		List<DoctorIntegralStatModel> showList=myService.makeDoctorIntegralStatModel(listData);
		
		Map<String, Object> jsonMap = new HashMap();
		
		jsonMap.put("sEcho", pageParamMap.get("sEcho"));
		
		jsonMap.put("iTotalRecords", Integer.valueOf(totalCount));
		
		jsonMap.put("iTotalDisplayRecords", Integer.valueOf(totalCount));
		
		jsonMap.put("aaData", showList);
		
		response.setContentType("charset=UTF-8");
		
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.print(JSON.toJSONString(jsonMap));
		
		return null;
	}
}