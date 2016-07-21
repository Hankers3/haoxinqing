package com.hxq.mobile.doctor.content.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.hxq.mobile.doctor.content.service.UseTheManualService;
import com.hxq.mobile.entity.common.ProductMain;
import com.hxq.mobile.entity.common.ProductMainDescription;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.Image4App;
import com.wxcommon.util.ObjectUtils;

@Controller("com.hxq.mobile.doctor.content.controller.UseTheManualController")
public class UseTheManualController {
	Logger log = LoggerFactory.getLogger(UseTheManualController.class);
	
	@Resource(name = "com.hxq.mobile.doctor.content.service.UseTheManualService")
	private UseTheManualService useTheManualService;
	
    @Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
    private ImgUploadService imgUploadService;
    
    @Autowired
    private FileService fileService;
	
	 //根据关键字获取药品列表
    /**
     * @param request
     * @param response
     * @return
     */
    
	@RequestMapping(value = "/app/supports/1.0/getProductMainList", method = RequestMethod.POST)
	public String  getProductMainListByName(HttpServletRequest request, HttpServletResponse response,
			   @RequestParam(value="medicineName",required=false ) String medicineName,
			   @RequestParam(value ="pageCount",required = false) Integer pageCount,
			   @RequestParam(value = "pageNo",required = false) Integer pageNo) {
		
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = CompatibleTools.getParam(request, response, breakOut, new String[] { "callback,false" });
		// 设置返回信息
		Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		
		// 得到药品名
		if (StringUtil.isEmpty(medicineName)) {
			medicineName = "";
		}
		
		List<Map<String, Object>> productMainList = useTheManualService.selectProductMainListByName(medicineName,pageCount,pageNo);//根据药品名获得list
		List<Map<String, Object>> relist = new ArrayList<Map<String, Object>>();
		Map<String, Object> noticeMap;
		if (productMainList != null && productMainList.size() > 0) {
			for (Map<String, Object> productMainModel : productMainList) {
				noticeMap = new HashMap<String, Object>();
				
				noticeMap.put("productName", productMainModel.get("productName"));//药品名称(通用名)
				noticeMap.put("productUuid", productMainModel.get("uuid"));// 药品id
				
				Image4App[] url = CompatibleTools.getImages(imgUploadService,fileService, (String) productMainModel.get("image"));
				if (!ObjectUtils.isEmpty(url))
				noticeMap.put("imageUrl", url[0]);//图片url
				
				noticeMap.put("productEnglishName", productMainModel.get("productEnglishName"));//英文名称 
				relist.add(noticeMap);
			}
		}
		if(!ObjectUtils.isEmpty(pageCount)){//分页条数20
			jsonMap.put("pageCount", pageCount);
		}else{
			jsonMap.put("pageCount", "20");
		}
		if(!ObjectUtils.isEmpty(pageNo)){//分页页数20
			jsonMap.put("pageNo", pageNo);
		}else{
			jsonMap.put("pageNo", "1");
		}
		// 消息字段 返回参数见 com.aebiz.b2b2c.product.productmain.vo.ProductMainModel
		jsonMap.put("relist", relist);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 查看药品详情 根据药品id查药品 (药品名可不填) 12/19
	 * 
	 * @author 
	 * @param request
	 * @param response
	 * @param doctorid
	 * @return
	 */
	@RequestMapping(value = "/app/supports/1.0/getProductMain", method = RequestMethod.GET)
	public String  getProductMainByid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("productUuid") String productUuid) {
		
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> maps = CompatibleTools.getParam(request, response, breakOut,
				new String[] { "callback,false", "productUuid,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = CompatibleTools.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = maps.get("callback");
				
		// 判断药品id不能为空
		if (StringUtil.isEmpty(productUuid)) {	 
			HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "药品id不能为空"), callback);
			return null;
		}
		
		ProductMain productMain = new ProductMain();
		ProductMainDescription pdm = new ProductMainDescription();
		try {
			// 通过药品id得到药品moel
			productMain = (ProductMain) useTheManualService.select(new ProductMain(productUuid));
			if (productMain == null) {
				HttpServletUtils.outJsonCallBack(response, CompatibleTools.getFailJsonMap(SysConst.FAIL, "根据药品id没有查到对象"), callback);
				return null;
			}
			Map<String, Object> map = useTheManualService.selectProductMainDescriptionByid(productUuid);//根据商品UUID获取查询描述信息的UUID
			if(!ObjectUtils.isEmpty(map.get("uuid"))){
				String uuid = (String) map.get("uuid");
				pdm = (ProductMainDescription) useTheManualService.select(new ProductMainDescription(uuid));
				if (pdm != null) {
					productMain.setLaboratorExamination(pdm.getLaboratorExamination());//应该做的化验检查
					productMain.setAttention(pdm.getAttention());//注意事项
					productMain.setDrugInteractio(pdm.getDrugInteractio());//药物相互作用
					
					productMain.getProductEnglishName();
					productMain.getDrugInteractio();
					productMain.getAddiction();
				}
			}
		} catch (Exception e) {
			log.error("", e);
		}
		
		jsonMap.put("productMainModel", productMain);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}
}