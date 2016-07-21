package com.aebiz.b2b2c.finance.sysback.web.invoice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.finance.invoice.service.InvoiceService;
import com.aebiz.b2b2c.finance.invoice.vo.InvoiceModel;
import com.aebiz.b2b2c.finance.invoice.vo.InvoiceQueryModel;
import com.aebiz.b2b2c.finance.invoice.vo.InvoiceState;
import com.aebiz.b2b2c.finance.invoice.vo.SaleTypeInvoice;

import com.aebiz.b2b2c.websiteoperation.websiteoperate.service.WebsiteOperateInteractive;

@Controller()
@RequestMapping("/sysback/invoice")
public class InvoiceController extends BaseController<InvoiceModel,InvoiceQueryModel>{

	/*注入发票service*/
	private InvoiceService myService;


	
	//注入运营系统接口,为了获取发票的设置信息
	@Autowired
	public WebsiteOperateInteractive websiteOperateInteractive;


	@Autowired
	public void  setMyService(InvoiceService bs){
		this.myService = bs;
		super.setBs(bs);
	}

	public InvoiceController(){
		super("finance/sysback/invoice","Invoice",InvoiceController.class);
	}

	/**
	 * 跳转发票列表页面，添加发票种类，发票类型，发票状态到页面
	 */
	@Override
	protected void preparedListData(Model model, HttpServletRequest request) {

		//发票状态map集合
		Map<String, String> invoiceStates=new HashMap<String, String>();

		String invoiceType=websiteOperateInteractive.getValueByKey("finance_invoice_type");
		String invoiceCateType=websiteOperateInteractive.getValueByKey("finance_invoice_head");	

		//从发票设置信息中取出发票类型，发票种类数组集合
		String[] invoiceTypeArry=invoiceType.split(";");
		String[] invoiceCateTypeArry=invoiceCateType.split(";");	

		//从枚举类型中获取发票状态map集合
		for (InvoiceState q : InvoiceState.values()) {
			invoiceStates.put(q.getName(), q.getName());
		}

		//添加到list页面
		model.addAttribute("invoiceCateType", invoiceCateTypeArry);
		model.addAttribute("invoiceType", invoiceTypeArry);
		model.addAttribute("invoiceState", invoiceStates);
	}

	/**
	 * 跳转商户开票页面向页面添加发票内容信息
	 */
	@Override
	protected void preparedUpdateData(Model model, HttpServletRequest request) {

		String invoiceContent=websiteOperateInteractive.getValueByKey("finance_invoice_content");
		String[] invoiceContentArry=invoiceContent.split(";");
		model.addAttribute("invoiceContent", invoiceContentArry);
	}

	/**
	 * 平台后台，账单结算，礼品卡购买，续缴服务费三种交易类型发票管理
	 */
	@Override
	protected InvoiceQueryModel preparedQMFixValue(InvoiceQueryModel qm) {

		if(StringUtil.isEmpty(qm.getNeedInvoice())){

			//设置交易类型为账单结算，礼品卡购买，续缴服务费的发票
			qm.setNeedInvoice(SaleTypeInvoice.BILL_GIFT_SERVICEFEE.getValue());
		}	
		return super.preparedQMFixValue(qm);
	}

	/**
	 * 跳转添加页面，添加选择发票类型
	 */
	@Override
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(Model model, HttpServletRequest request) {

		//向页面添加发票种类，类型，交易类型的各种类型
		this.preparedListData(model, request);
		this.preparedUpdateData(model, request);
		return "finance/sysback/invoice/InvoiceAdd";
	}

	/**
	 * 保存一条发票记录
	 * @param model
	 * @param request
	 * @param invoiceCate
	 * @param invoiceType
	 * @param invoiceNO
	 * @param invoiceContent
	 * @return
	 */
	@RequestMapping(value = "/toSave", method = RequestMethod.POST)
	public String toSave(Model model, HttpServletRequest request,@RequestParam("invoiceCate") String invoiceCate,@RequestParam("invoiceType") String invoiceType,@RequestParam("invoiceNO") String invoiceNO,@RequestParam("invoiceContent") String[] invoiceContents){

		myService.save(invoiceCate, invoiceType, invoiceNO, invoiceContents);
		return "finance/sysback/invoice/InvoiceAdd";
	}

	/**
	 * 跳转发票开票页面，查询出要更新的记录
	 * @param model
	 * @param request
	 * @param invoiceUuid
	 * @return
	 */
	@RequestMapping(value = "/toInvoice/{invoiceUuid}", method = RequestMethod.GET)
	public String toInvoice(Model model, HttpServletRequest request,@PathVariable("invoiceUuid") String invoiceUuid){

		InvoiceModel m=myService.getByInvoiceUuid(invoiceUuid);

		//通过发票内容向页面传送发票内容数组集合
		m.setInvoiceContents(m.getInvoiceContent().split(","));
		model.addAttribute("m", m);
		this.preparedListData(model, request);
		this.preparedUpdateData(model, request);
		return "finance/sysback/invoice/InvoiceUpdate";
	}

	/**
	 * 保存修改记录
	 * @param model
	 * @param request
	 * @param invoiceModel
	 * @return
	 */
	@RequestMapping(value = "/invoice", method = RequestMethod.POST)
	public String invoice(Model model, HttpServletRequest request,@ModelAttribute("m") InvoiceModel invoiceModel){

		//更新发票记录
		invoiceModel.setInvoiceState(InvoiceState.INVOICED.getName());
		invoiceModel.setInvoiceContent(myService.arrayToString(invoiceModel.getInvoiceContents()));
		invoiceModel.setInvoiceTime(DateFormatHelper.getNowTimeStr());
		myService.updateInvoiceModel(invoiceModel);

		return "finance/sysback/invoice/InvoiceSuccess";
	}
}