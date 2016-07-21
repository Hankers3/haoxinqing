package com.aebiz.b2b2c.finance.sysback.web.orderreturnmoneymain;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.finance.orderreturnmoneymain.service.OrderReturnMoneyMainService;
import com.aebiz.b2b2c.finance.orderreturnmoneymain.vo.OrderReturnMoneyMainModel;
import com.aebiz.b2b2c.finance.orderreturnmoneymain.vo.OrderReturnMoneyMainQueryModel;
import com.aebiz.b2b2c.orderaftersaleservice.orderaftersaleservicemain.vo.OrderAfterMoneyBackTypeEnum;
import com.aebiz.b2b2c.orderaftersaleservice.orderaftersaleservicemain.vo.OrderAfterSaleBackMoneyStatusEnum;

@Controller
@RequestMapping("/sysback/orderreturnmoneymain")
public class OrderReturnMoneyMainController extends BaseController<OrderReturnMoneyMainModel,OrderReturnMoneyMainQueryModel>{
	
	/*注入退款单service*/
	private OrderReturnMoneyMainService myService;
	
	@Autowired
	public void  setMyService(OrderReturnMoneyMainService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	
	public OrderReturnMoneyMainController(){
		super("finance/sysback/orderreturnmoneymain","OrderReturnMoneyMain",OrderReturnMoneyMainController.class);
	}
	
	/**
	 * 跳转退款单列表页面，向页面添加退款单状态，退款类型
	 */
	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {
		
		//循环退款单状态，退款类型枚举类型添加到页面
		Map<String, String> orderreturnmoneymainStates=new HashMap<String, String>();
		Map<String, String> orderreturnmoneymainReturnTypes=new HashMap<String, String>();
		
		for (OrderAfterMoneyBackTypeEnum q : OrderAfterMoneyBackTypeEnum.values()) {
			orderreturnmoneymainReturnTypes.put(q.getValue(), q.getName());
		}
		for (OrderAfterSaleBackMoneyStatusEnum q : OrderAfterSaleBackMoneyStatusEnum.values()) {
			orderreturnmoneymainStates.put(q.getValue(), q.getName());
		}
		
		model.addAttribute("orderreturnmoneymainState", orderreturnmoneymainStates);
		model.addAttribute("orderreturnmoneymainReturnType", orderreturnmoneymainReturnTypes);
	}
	
	/**
	 * ajax提交，确认退款完成
	 * @param model
	 * @param request
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = "/returnSuccess/{uuid}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String , Object> returnSucess(Model model, HttpServletRequest request,@PathVariable("uuid") String uuid,@RequestParam("remarks") String remarks){
		
		Map<String , Object> jsonMap=new HashMap<String, Object>();
		OrderReturnMoneyMainModel orderReturnMoneyMainModel=myService.getByUuid(uuid);
		orderReturnMoneyMainModel.setRemarks(remarks);
		
		//设置退款单状态为退款成功，并更新
		orderReturnMoneyMainModel.setState(OrderAfterSaleBackMoneyStatusEnum.BACKMONEYSUCCESS.getValue());
		myService.update(orderReturnMoneyMainModel);
		
		//返回操作成功信息
		jsonMap.put("message", Boolean.valueOf(true));
		return jsonMap;
	}
}