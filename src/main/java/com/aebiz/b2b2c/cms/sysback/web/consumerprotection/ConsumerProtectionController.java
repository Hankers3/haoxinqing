package com.aebiz.b2b2c.cms.sysback.web.consumerprotection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.consumerprotection.service.ConsumerProtectionService;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionModel;
import com.aebiz.b2b2c.cms.consumerprotection.vo.ConsumerProtectionQueryModel;

@Controller
@RequestMapping("/sysback/consumerprotection")
public class ConsumerProtectionController extends BaseController<ConsumerProtectionModel,ConsumerProtectionQueryModel>{
	
	private ConsumerProtectionService myService;
	
	@Autowired
	public void  setMyService(ConsumerProtectionService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	
	public ConsumerProtectionController(){
		super("cms/sysback/consumerprotection","ConsumerProtection",ConsumerProtectionController.class);
	}

//	/*名字的长度*/
//	private static int NAMELENGTH = 100;
//	
//	/*检查协议的长度*/
//	private static int PROTOCOLCONTENT = 1000;
//	
//	/*备注的长度*/
//	private static int NOTE = 200;
	
	/**
	 * 带上传的添加操作
	 * @param files	上传的文件
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/adds", method = RequestMethod.POST)
	public String add(@RequestParam("files") MultipartFile[] files, Model model, @ModelAttribute("m") ConsumerProtectionModel m, HttpServletRequest request) {
		myService.uploadImage(m, files);
		return super.add(model, m, request);
	}
	
	/**
	 * 添加检查
	 */
	@Override
	protected boolean checkAdd(Model model, ConsumerProtectionModel m, HttpServletRequest request) {
		//检查名字是否为空
		checkFieldNameIsEmpty("name", m.getName());
		
		//检查名字长度
		checkFieldNameLength("name",m.getName(),ConsumerProtectionModel.NAMELENGTH);
		
		//检查上传文件是否为空
		checkFieldNameIsEmpty("files", m.getIconUrl());
	
		//检查协议的长度
		checkFieldNameLength("protocolContent", m.getProtocolContent(),ConsumerProtectionModel.PROTOCOLCONTENT);
		
		//检查备注的长度
		checkFieldNameLength("note", m.getNote(), ConsumerProtectionModel.NOTE);
		
		// 跳转到页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			request.setAttribute(ERROR_BACK_URL,"cms/sysback/consumerprotection/ConsumerProtectionAdd");
			return false;
		}
		return true;
	}
	
	/**
	 * 校验字段长度
	 * 
	 * @param name
	 *            字段名
	 * @param value
	 *            字段值
	 * @param maxLength
	 *            最大长度
	 */
	private void checkFieldNameLength(String name, String value, int maxLength){
		if(value.length() > maxLength){
			this.putErrorMsg(name, MessageHelper.getMessage("protection." + name + ".maxLength") + maxLength);
		}
	}
	
	/**
	 * 校验字段是否为空
	 * 
	 * @param name
	 *            字段名
	 * @param value
	 *            字段值
	 */
	private void checkFieldNameIsEmpty(String name, String value){
		if(StringUtil.isEmpty(value)){
			this.putErrorMsg(name, MessageHelper.getMessage("protection." + name + ".isEmpty"));
		}
	}
	
	/**
	 * 带上传的更新操作
	 * @param files	上传的文件
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/updates"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	 public String update(@RequestParam("files") MultipartFile[] files,Model model, @ModelAttribute("m") ConsumerProtectionModel m, HttpServletRequest request){
		ConsumerProtectionModel cm = myService.getByUuid(m.getUuid());
		cm.setName(m.getName());
		cm.setIcon(m.getIcon());
		cm.setNeedChooseProduct(m.getNeedChooseProduct());
		cm.setNeedAgreeProtocol(m.getNeedAgreeProtocol());
		cm.setProtocolContent(m.getProtocolContent());
		cm.setNote(m.getNote());
		cm.setState(m.getState());
		myService.uploadImage(cm, files);
		return super.update(model, cm, request);
	}
	
	/**
	 * 更新检查
	 */
	@Override
	protected boolean checkUpdate(Model model, ConsumerProtectionModel m, HttpServletRequest request) {
		//检查名字是否为空
		checkFieldNameIsEmpty("name", m.getName());
		
		//检查名字长度
		checkFieldNameLength("name",m.getName(),ConsumerProtectionModel.NAMELENGTH);
		
		//检查上传文件是否为空
		checkFieldNameIsEmpty("files", m.getIconUrl());
	
		//检查协议的长度
		checkFieldNameLength("protocolContent", m.getProtocolContent(),ConsumerProtectionModel.PROTOCOLCONTENT);
		
		//检查备注的长度
		checkFieldNameLength("note", m.getNote(), ConsumerProtectionModel.NOTE);
		
		// 跳转到页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			request.setAttribute(ERROR_BACK_URL,"cms/sysback/consumerprotection/ConsumerProtectionUpdate");
			return false;
		}
		return true;
	}
}