package com.aebiz.b2b2c.userfront.sysback.web.platadimagerel.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.userfront.platadimagerel.service.PlatAdImageRelService;
import com.aebiz.b2b2c.userfront.platadimagerel.vo.PlatAdImageRelModel;
import com.aebiz.b2b2c.userfront.platadimagerel.vo.PlatAdImageRelQueryModel;

@Controller
@RequestMapping("/sysback/platadimagerel")
public class PlatAdImageRelController extends BaseController<PlatAdImageRelModel,PlatAdImageRelQueryModel>{
	private PlatAdImageRelService myService;
	@Autowired
	public void  setMyService(PlatAdImageRelService bs){
		this.myService = bs;
		super.setBs(bs);
	}
	
	public PlatAdImageRelController(){
		super("userfront/sysback/platadimagerel","PlatAdImageRel",PlatAdImageRelController.class);
	}
	
	/**
	 * 跳转到去关联图片页面<br>
	 * 
	 * 需要获取已经关联的图片的list集合发送到页面
	 * 
	 * @param uuid
	 * @param model
	 * @param request
	 * @return 
	 * String
	 */
	@RequestMapping(value = {"/toRelImage/{uuid}"}, method = {RequestMethod.GET})
	public String toRelImage(@PathVariable("uuid") String uuid, Model model,
			HttpServletRequest request) {
		model.addAttribute("adUuid", uuid);
		
		//获取已经关联的图片
		List<PlatAdImageRelModel> adImageRelModels = myService.getPlatAdImageRelModelsByAdUuid(uuid);
		model.addAttribute("adImageRelModels", adImageRelModels);

		return "userfront/sysback/platadimagerel/RelImage";
	}
	
	
	/**
	 * 保存广告和图片的关联关系
	 */
	@RequestMapping(value = {"/saveRel"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
	public String saveRel(Model model, @ModelAttribute("m") PlatAdImageRelModel m,
			HttpServletRequest request) {
		//获取每个图片对应的链接,没有链接则寸空字符串
		String[] uuids = m.getUuids();
		if(uuids != null && uuids.length > 0){
			String[] urls = new String[uuids.length];
			for (int i = 0; i < uuids.length; i++) {
			        String uuid= uuids[i];
				String url = request.getParameter(uuid+"urls");
				if(url == null){
					urls[i] = "";
				}else{
					urls[i] = url;
				}
			}
			
			m.setUrls(urls);
		}
		
		myService.create(m);
		return "userfront/sysback/platadimagerel/PlatAdImageRelSuccess";
	}
	
	
}