package com.aebiz.b2b2c.cms.storeback.web.messageremind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindModel;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindQueryModel;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageType;
import com.aebiz.b2b2c.cms.storeback.service.messageremind.StoreMessageRemindService;
import com.aebiz.b2b2c.cms.storeback.web.messageremind.vo.MessageRemindWebModel;
import com.aebiz.b2b2c.cms.storeback.web.messageremind.vo.StoreMessageRemind;

@Controller
@RequestMapping("storeback/messageremind")
public class StoreMessageRemindController extends BaseController<MessageRemindModel,MessageRemindQueryModel>{
	
	/*注入商户消息service*/
	private StoreMessageRemindService myService;
	@Autowired
	public void  setMyService(StoreMessageRemindService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public StoreMessageRemindController(){
		super("cms/storeback/messageremind","MessageRemind",StoreMessageRemindController.class);
	}
	
	/**
	 * 向页面添加商户所有消息，卖家提醒，促销提醒，退款提醒和该商家对应的设置集合
	 * @param webModel
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showMessageRemindList", method = RequestMethod.GET)
	public String showMessageRemindList(@ModelAttribute("wm") MessageRemindWebModel webModel, Model model, HttpServletRequest request) {
		
		String uuid=LoginUserHelper.getStoreLoginUserUuid();
		
		//目前使用静态商户uuid,得到卖家提醒的消息集合
		List<StoreMessageRemind> storerMessageRemindsSeller=myService.getStoreMessageWebModelList(uuid, MessageType.SELLERREMIND.getValue());
		
		//得到退款提醒的消息集合
		List<StoreMessageRemind> storerMessageRemindsRefund=myService.getStoreMessageWebModelList(uuid, MessageType.TEFUNDREMIND.getValue());
		
		//得到促销提醒的消息集合
		List<StoreMessageRemind> storerMessageRemindsPromotion=myService.getStoreMessageWebModelList(uuid, MessageType.PROMOTIONREMIND.getValue());
		
		//添加到map集合中
		Map<String, List<StoreMessageRemind>> map=new HashMap<String, List<StoreMessageRemind>>();
		map.put("seller", storerMessageRemindsSeller);
		map.put("refund", storerMessageRemindsRefund);
		map.put("promotion", storerMessageRemindsPromotion);
		webModel.setMap(map);
		return "cms/storeback/messageremind/StoreMessageRemindList";
	}
}