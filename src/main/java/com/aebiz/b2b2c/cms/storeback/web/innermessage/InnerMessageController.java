package com.aebiz.b2b2c.cms.storeback.web.innermessage;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageQueryModel;
import com.aebiz.b2b2c.cms.storeback.service.innermessage.StoreInnerMessageService;
import com.alibaba.fastjson.JSON;

@Controller("StoreBackInnerMessageController")
@RequestMapping("/store/innermessage")
public class InnerMessageController extends
		BaseController<InnerMessageModel, InnerMessageQueryModel> {
	private StoreInnerMessageService innerMessageService;

	@Autowired
	public void setMyService(StoreInnerMessageService bs) {
		this.innerMessageService = bs;
		super.setBs(bs);
	}

	public InnerMessageController() {
		super("cms/storeback/innermessage", "InnerMessage",
				InnerMessageController.class);
	}

	/**
	 * 我的消息列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/list" }, method = { RequestMethod.POST })
	public String list(@ModelAttribute("wm") BaseWebModel wm, Model model,
			@RequestParam("type") String type,
			@RequestParam("messageType") String messageType,
			@RequestParam("readStatus") String readStatus,
			@RequestParam("accountType") String accountType,
			@RequestParam("nowPage") String nowPage,
			@RequestParam("pageShow") String pageShow,
			HttpServletRequest request) {
		
		if (!StringUtil.isEmpty(nowPage)) {
			wm.setNowPage(Integer.parseInt(nowPage));
		}
		if (!StringUtil.isEmpty(pageShow)) {
			wm.setPageShow(Integer.parseInt(pageShow));
		}
		
		InnerMessageQueryModel qm = (InnerMessageQueryModel)getQueryModel();
		String init = request.getParameter("init");
	    if (!"true".equalsIgnoreCase(init))
	    {
	      Object obj = SecurityUtils.getSubject().getSession().getAttribute("InnerMessageIsQuery");
	      if ((obj != null) && (obj.toString().equals("true"))) {
	        qm = (InnerMessageQueryModel)SecurityUtils.getSubject().getSession().getAttribute("OrderMainQueryModel");
	      }
	    }
	    qm.setDelFlag("1");
	    qm.getMapCondition().put("delFlag", Integer.valueOf(ConditionOpTypeEnum.EQ.getCode()));
		
		// 收信人/发信人
	    String accountUuid = "Store0000000001";
	    //String accountUuid = LoginUserHelper.getStoreLoginUserUuid();
		if (type.equals("sx")) {
			qm.setReceiveUser(accountUuid);
			qm.getMapCondition().put("receiveUser", Integer.valueOf(ConditionOpTypeEnum.EQ.getCode()));
		} else {
			qm.setSendUser(accountUuid);
			qm.getMapCondition().put("sendUser", Integer.valueOf(ConditionOpTypeEnum.EQ.getCode()));
		}
		
		// 消息类型
		if (!StringUtil.isEmpty(messageType)) {
			qm.setMessageType(messageType);
			qm.getMapCondition().put("messageType", Integer.valueOf(ConditionOpTypeEnum.EQ.getCode()));
		}
		
		// 读取状态
		if(!StringUtil.isEmpty(readStatus)) {
			qm.setReadStatus(readStatus);
			qm.getMapCondition().put("readStatus", Integer.valueOf(ConditionOpTypeEnum.EQ.getCode()));
		}
		
		// 收件人类别
		if(!StringUtil.isEmpty(accountType)) {
			qm.setAccountType(accountType);
			qm.getMapCondition().put("accountType", Integer.valueOf(ConditionOpTypeEnum.EQ.getCode()));
		}
		
		// 收件人的删除状态为未删除
		if (type.equals("sx")) {
			qm.setSjDelStatus("0");
			qm.getMapCondition().put("sjDelStatus", Integer.valueOf(ConditionOpTypeEnum.EQ.getCode()));
		// 发件人的删除状态为未删除
		} else if(type.equals("fx")) {
			qm.setFjDelStatus("0");
			qm.getMapCondition().put("fjDelStatus", Integer.valueOf(ConditionOpTypeEnum.EQ.getCode()));
		}
		
		// 按照发送时间降序
		qm.setSortName("sendTime");
		qm.setSortType("desc");
		
		
		int count = this.innerMessageService.getCount(qm);
		wm.setTotalNum(count);
		
		List<InnerMessageModel> list = this.innerMessageService.getByCondition(
				qm, wm.getFromNum(), wm.getPageShow());
		
		wm.setRows(list);
		
		if (type.equals("sx")) {
			return "cms/storeback/innermessage/ShowReceiveMessageList";
		} else {
			return "cms/storeback/innermessage/ShowSendMessageList";
		}
	}
	
	/**
	 * 会员发送站内信
	 * 
	 * @param receiveUser
	 * @param title
	 * @param content
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/send" }, method = { RequestMethod.POST })
	@ResponseBody
	public String send(@RequestParam("receiveUser") String receiveUser,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			HttpServletResponse response) {
		
		this.innerMessageService.saveInnerMessage(receiveUser, title, content);
		
		return "true";
	}
	
	/**
	 * 跳转到站内信查看页面
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toView/{uuid}", method = RequestMethod.GET)
	public String toView(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		InnerMessageModel innerMessage = this.innerMessageService.getByUuid(uuid);

		model.addAttribute("m", innerMessage);
		
		if (innerMessage.getReadStatus().equals(InnerMessageModel.READ_STATUS_UNREAD)) {
			this.innerMessageService.update(innerMessage);
		}

		return "cms/storeback/innermessage/InnerMessageView";
	}
	
	/**
	 * 删除站内信
	 * 
	 * @param needDeleteUuids
	 * @param type
	 * @param wm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/removes" }, method = { RequestMethod.POST })
	public String removes(
			@RequestParam("selectOne") List<String> needDeleteUuids,
			@RequestParam("type") String type,
			BaseWebModel wm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		this.innerMessageService.deleteInnerMessage(needDeleteUuids, type);

		Map<String, Object> jsonMap = new HashMap();

		jsonMap.put("rsp", Boolean.valueOf(true));
		PrintWriter out = response.getWriter();
		out.print(JSON.toJSONString(jsonMap));

		return null;
	}

}