package com.aebiz.b2b2c.finance.sysback.web.customerbankrel;

/**
 * 会员提出绑定申请,有平台给申请记录中的银行卡打一定的金额,<br>
 * 
 * 然后后台更新这个金额,并且把验证状态修改为待验证状态,<br>
 * 
 * 这时会员可以根据收到的金额去和会员中心验证,如果错误3次那么就不能再次验证了,需要联系平台
 *
 * @author tangyunkai
 *
 * @date 2014年12月20日 下午12:26:57 
 *
 */
import java.io.PrintWriter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.service.CustomerInteractive;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerQueryModel;
import com.aebiz.b2b2c.finance.customerbankrel.service.CustomerBankRelService;
import com.aebiz.b2b2c.finance.customerbankrel.vo.CustomerBankRelModel;
import com.aebiz.b2b2c.finance.customerbankrel.vo.CustomerBankRelQueryModel;
import com.aebiz.b2b2c.finance.customerbankrel.vo.ValidateState;
import com.aebiz.b2b2c.finance.sysback.web.customerbankrel.vo.CustomerBankRelWebModel;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/sysback/customerbankrel")
public class CustomerBankRelController extends BaseController<CustomerBankRelModel,CustomerBankRelQueryModel>{
	private CustomerBankRelService myService;
	@Autowired
	public void  setMyService(CustomerBankRelService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public CustomerBankRelController(){
		super("finance/sysback/customerbankrel","CustomerBankRel",CustomerBankRelController.class);
	}
	
	//注入会员接口
	@Autowired
	private CustomerInteractive customerInteractive;
	
	/**
	 * 把验证状态通过枚举的方式发送到页面
	 */
	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {
		List<DataTablesPageParam> validateStateList = new ArrayList<DataTablesPageParam>();
		
		//遍历验证状态的类型枚举
		for(ValidateState state :ValidateState.values()){
			DataTablesPageParam pageParam = new DataTablesPageParam();
			
			pageParam.setName(state.getName());
			pageParam.setValue(state.getValue());
			
			validateStateList.add(pageParam);
		}
		
		//把验证状态发送到页面
		CustomerBankRelWebModel webModel = new CustomerBankRelWebModel();
		webModel.setValidateStateList(validateStateList);
		
		model.addAttribute("webModel", webModel);
	}
	/**
	 * 更新验证金额
	 * @param uuId
	 * @param vilidateMount
	 * @param model
	 * @param m
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/updateVilidateMount" }, method = {RequestMethod.GET })
	@ResponseBody
	public String updateVilidateMount(@RequestParam("uuId") String uuId,
			@RequestParam("vilidateMount") String vilidateMount, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		//修改验证的金额和验证状态
		boolean flag = myService.updateVilidateMount(uuId, vilidateMount);
		
		if(flag){
			return "success";
		}else{
			return "fail";
		}

	}
	
	/**
	 * 会员银行卡绑定查看
	 */
	 @RequestMapping(value={"/toInfo/{uuid}"}, method={RequestMethod.GET})
	  public String toInfo(@PathVariable("uuid") String uuid, Model model, HttpServletRequest request)
	  {
		 CustomerBankRelModel m = (CustomerBankRelModel) this.bs.getByUuid(uuid);

	    model.addAttribute("m", m);

	    preparedInfoData(model, request);

	    return "finance/sysback/customerbankrel/CustomerBankRelInfo";
	  }
	 
	 /**
	  * 因为要根据会员编号来查询数据,并且还带着分页参数,<br>
	  * 
	  * 所以必须重写这个方法,把分页的参数传给会员系统的接口才能获取到结果
	  */
	@RequestMapping({"/queryList"})
	public String queryList(HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		List<CustomerBankRelModel> showList = new ArrayList();

		Map<String, Object> pageParamMap = parsePageParam(request);

		CustomerBankRelQueryModel qm = parseQueryModel(request);

		qm = preparedQMFixValue(qm);

		int iDisplayStart = ((Integer) pageParamMap.get("iDisplayStart"))
				.intValue();

		int iDisplayLength = ((Integer) pageParamMap.get("iDisplayLength"))
				.intValue();
		
		//因为要根据会员编号来查询数据,并且还带着分页参数,所以加入这个方法来实现
		qm = getPreparedQmByPager(qm,iDisplayStart,iDisplayLength);
		
		int iSortCol_0 = ((Integer) pageParamMap.get("iSortCol_0")).intValue();

		String sortFieldName = (String) pageParamMap.get("mDataProp_"
				+ iSortCol_0);

		String sortTypeString = (String) pageParamMap.get("sSortDir_0");

		Boolean needSort = (Boolean) pageParamMap
				.get("bSortable_" + iSortCol_0);

		if (needSort.booleanValue()) {
			qm.setSortName(sortFieldName);
			qm.setSortType(sortTypeString);
		}

		List<CustomerBankRelModel> listData = myService.getByCondition(qm, iDisplayStart,
				iDisplayLength);

		int totalCount = myService.getCount(qm);

		for (int i = 0; i < listData.size(); i++) {
			CustomerBankRelModel m = (CustomerBankRelModel) listData.get(i);
			showList.add(m);
		}

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
	  * 查询时,把会员的编号获取去会员系统查是否存在该会员,<br>
	  * 
	  * 存在需要把uuid 放到qm里,不存在,也需要set进一个不存的值,保证list页面不能查出数据<br>
	  * 
	  * 这里需要加入分页参数,分页参数是根据当前请求的分页
	  */
	protected CustomerBankRelQueryModel getPreparedQmByPager(CustomerBankRelQueryModel qm,int startPage,int pageShow) {
		if(!StringUtil.isEmpty(qm.getCustomerUuid())){
			CustomerQueryModel paramCustomerQueryModel = new CustomerQueryModel();
			paramCustomerQueryModel.setCustomerId(qm.getCustomerUuid());
			List<CustomerModel> customerModels = customerInteractive.getCustomerModelListByCondition(paramCustomerQueryModel, startPage, pageShow);
			if(customerModels != null && customerModels.size() >0){
				StringBuffer customerUuids = new StringBuffer();
				//获取所有的匹配的会员的uuid,如果有匹配的则拼成字符串set重新把customerUuid设置成新的值,否则需要把customer设置成空
				for (int i = 0; i < customerModels.size(); i++) {
					CustomerModel customerModel = customerModels.get(i);
					if(i == customerModels.size()-1){
						customerUuids.append(customerModel.getUuid());
					}else{
						customerUuids.append(customerModel.getUuid()+",");
					}
				}
				//将符合条件的会员的uuid放到qm里
				qm.setCustomerUuid(customerUuids.toString());	
			}
			qm.getMapCondition().put("customerUuid", ConditionOpTypeEnum.IN.getCode());
			
		}
		return qm;
	}

}