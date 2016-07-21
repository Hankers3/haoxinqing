package com.aebiz.b2b2c.cms.storeback.web.storetags;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.storetags.service.StoreTagsService;
import com.aebiz.b2b2c.cms.storetags.vo.StoreTagsModel;
import com.aebiz.b2b2c.cms.storetags.vo.StoreTagsQueryModel;

@Controller("StoreTagsController")
@RequestMapping("/store/storetags")
public class StoreTagsController extends BaseController<StoreTagsModel,StoreTagsQueryModel>{
	private StoreTagsService myService;
	@Autowired
	public void  setMyService(StoreTagsService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	public StoreTagsController(){
		super("cms/storeback/storetags","StoreTags",StoreTagsController.class);
	}
	
	/**
	 * 商户标签添加操作 对标签名称、类型数据进行验证
	 */
	@Override
	public boolean checkAdd(Model model, StoreTagsModel m, HttpServletRequest request){
		if(StringUtil.isEmpty(m.getTagName())){
			this.putErrorMsg("tagName", MessageHelper.getMessage("storetags.tagName.isEmpty"));
		}else{
			if(m.getTagName().length()>40){
				this.putErrorMsg("tagName", MessageHelper.getMessage("storetags.tagName.lengthError"));
			}
		}
		if(StringUtil.isEmpty(m.getTagType())){
			this.putErrorMsg("tagType", MessageHelper.getMessage("storetags.tagType.isEmpty"));
		}
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			model.addAttribute("m", m);
			request.setAttribute(ERROR_BACK_URL,
					"cms/storeback/storetags/StoreTagsAdd");
			return false;
		}
		return true;
	}
	
	/**
	 * 商户标签更新操作 对标签名称、类型数据进行验证
	 */
	@Override
	public boolean checkUpdate(Model model, StoreTagsModel m, HttpServletRequest request){
		if(StringUtil.isEmpty(m.getTagName())){
			this.putErrorMsg("tagName", MessageHelper.getMessage("storetags.tagName.isEmpty"));
		}else{
			if(m.getTagName().length()>40){
				this.putErrorMsg("tagName", MessageHelper.getMessage("storetags.tagName.lengthError"));
			}
		}
		if(StringUtil.isEmpty(m.getTagType())){
			this.putErrorMsg("tagType", MessageHelper.getMessage("storetags.tagType.isEmpty"));
		}
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			model.addAttribute("m", m);
			request.setAttribute(ERROR_BACK_URL,
					"cms/storeback/storetags/StoreTagsUpdate");
			return false;
		}
		return true;
	}
}