package com.hxq.mobile.support.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.productmain.service.ProductMainService;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.product.productmaindescription.service.ProductMainDescriptionService;
import com.aebiz.b2b2c.product.productmaindescription.vo.ProductMainDescriptionModel;
import com.hxq.mobile.entity.common.ImgUpload;
import com.hxq.mobile.entity.common.ImgUploadResponse;
import com.hxq.mobile.entity.common.ImgUploadResponse.FileMeta;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.util.BoolResult;
import com.wxcommon.util.JsonUtil;
import com.wxcommon.util.StringUtils;

@Controller
public class CommonController {
	Logger log = LoggerFactory.getLogger(CommonController.class);

	/* 药品描述的service */
	@Autowired
	private ProductMainDescriptionService productMainDescriptionService;
	/*
	 * 药品service
	 */
	@Autowired
	private ProductMainService productMainService;

	@Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
	private ImgUploadService imgUploadService;

	/**
	 * 上传图片
	 */
	@RequestMapping(value = "/app/support/common/uploadimg")
	public @ResponseBody List<FileMeta> uploadFile(NativeWebRequest request,
			@RequestParam(value = "thumbnail", required = false) String thumbnail,
			@RequestParam(value = "files", required = false) MultipartFile[] files) {
		ImgUploadResponse result = null;
		boolean needThumbnail = StringUtils.trimToEmpty(thumbnail).toLowerCase().contains("true");
		try {
			result = imgUploadService.updateForUpload(needThumbnail, files);
		} catch (Exception e) {
			log.error("", e);
		}
		return result != null ? result.getFiles() : null;
	}

	/**
	 * 删除图片
	 */
    @RequestMapping(value="/app/support/common/deleteimg", method=RequestMethod.POST)
	public @ResponseBody ApiResult delete(@RequestParam("imgid") String[] imgid) {
		try {
			ImgUpload img = new ImgUpload();
			for (String id : imgid) {
				img.setId(id);
				imgUploadService.delete(img);
			}
			return ApiResult.right();
		} catch (Exception e) {
			log.error(String.valueOf(imgid), e);
			return ApiResult.error(ApiCode.SERVER_ERROR, "删除出错，请稍后再试！");
		}
	}
 
    /**
	 * 根据药品名查药品 (药品名可不填) 12/19
	 * 
	 * @param request
	 * @param response
	 * @param doctorid
	 * @return
	 */
    //根据关键字获取药品列表
    /**
     * @RequestMapping(value = "/app/customer/user/gotoLogin", method = RequestMethod.POST)
	public ResponseEntity<String>  gotoLogin(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("mobile") String mobile, @RequestParam("password") String password) {
     * @param request
     * @param response
     * @return
     */
    
	@RequestMapping(value = "/app/supports/getProductMainList", method = RequestMethod.POST)
	public ResponseEntity<String>  getProductMainListByName(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="medicineName",required=false ) String medicineName) {//@ResponseBody BoolResult
		
		BoolResult boolResult =null;
		// 得到药品名
	//	String medicineName = request.getParameter("medicineName");
		if (StringUtil.isEmpty(medicineName)) {
			medicineName = "";
		}
		List<ProductMainModel> productMainList = productMainService.getByProductName(medicineName);//根据药品名获得list
		List<Map<Object, Object>> relist = new ArrayList<Map<Object, Object>>();
		if (productMainList != null && productMainList.size() > 0) {
			for (ProductMainModel productMainModel : productMainList) {
				Map<Object, Object> noticeMap = new HashMap<Object, Object>();
				
				noticeMap.put("productName", productMainModel.getProductName());//药品名称(通用名)
				noticeMap.put("productUuid", productMainModel.getUuid());// 药品id
				noticeMap.put("imageUrl", productMainModel.getImageUrl());//图片url
				noticeMap.put("productEnglishName", productMainModel.getProductEnglishName());//英文名称 
				relist.add(noticeMap);
			}
		}
		// 消息字段 返回参数见 com.aebiz.b2b2c.product.productmain.vo.ProductMainModel
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("relist", relist);
		//return BoolResult.right(JsonUtil.toJSONString(jsonMap, false, false));
		boolResult = BoolResult.right(JsonUtil.toJSON(jsonMap));
		return JsonUtil.ResponseJson(JsonUtil.toJSONString(boolResult));
	}

	/**
	 * 查看药品详情 根据药品id查药品 (药品名可不填) 12/19
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @param doctorid
	 * @return
	 */
	@RequestMapping(value = "/app/supports/getProductMain", method = RequestMethod.GET)
	public ResponseEntity<String>  getProductMainByid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("productUuid") String productUuid) {
		BoolResult boolResult = null;
		// 判断药品id不能为空
		if (StringUtil.isEmpty(productUuid)) {	 
			 return JsonUtil.ResponseJson(JsonUtil.toJSONString(BoolResult.error("药品id不能为空")));
		}
		// 通过药品id得到药品moel
		ProductMainModel productMainModel = productMainService.getByUuid(productUuid);
		if (productMainModel == null) {
			return JsonUtil.ResponseJson(JsonUtil.toJSONString(BoolResult.error("根据药品id没有查到对象")));
		}
		String uuid = productMainDescriptionService.getProductMainDescriptionUuidByProductUuid(productUuid);//根据商品UUID获取查询描述信息的UUID
		if (!StringUtil.isEmpty(uuid)) {
			ProductMainDescriptionModel pdm = productMainDescriptionService.getByUuid(uuid);
			if (pdm != null) {
				productMainModel.setLaboratorExamination(pdm.getLaboratorExamination());//应该做的化验检查
				productMainModel.setAttention(pdm.getAttention());//注意事项
				productMainModel.setDrugInteractio(pdm.getDrugInteractio());//药物相互作用
				
				productMainModel.getProductEnglishName();
				productMainModel.getDrugInteractio();
				productMainModel.getAddiction();
			}
		}
		Map<String, Object> jsonMap = new HashMap<String ,Object>();
		// 消息字段 返回参数见 com.aebiz.b2b2c.product.productmain.vo.ProductMainModel
		jsonMap.put("productMainModel", productMainModel);
		//return BoolResult.right(JsonUtil.toJSONString(jsonMap, false, false));
		boolResult = BoolResult.right(JsonUtil.toJSON(jsonMap));
		return JsonUtil.ResponseJson(JsonUtil.toJSONString(boolResult));
	}
    
}
