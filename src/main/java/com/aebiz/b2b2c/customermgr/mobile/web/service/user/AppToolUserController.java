package com.aebiz.b2b2c.customermgr.mobile.web.service.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.basicdata.userequipment.service.UserEquipmentService;
import com.aebiz.b2b2c.basicdata.userequipment.vo.UserEquipmentModel;
import com.aebiz.b2b2c.cms.content.service.ContentService;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.cms.contentcategory.service.ContentCategoryService;
import com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryModel;
import com.aebiz.b2b2c.cms.doccuscon.service.DocCusConService;
import com.aebiz.b2b2c.cms.doccuscon.vo.DocCusConModel;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.AppBaseController;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.HttpServletUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.SysConst;
import com.aebiz.b2b2c.customermgr.mobile.web.push.PushConst;
import com.aebiz.b2b2c.product.productmain.service.ProductMainService;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.product.productmaindescription.service.ProductMainDescriptionService;
import com.aebiz.b2b2c.product.productmaindescription.vo.ProductMainDescriptionModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.service.ServicestaffService;
import com.aebiz.b2b2c.userfront.plattimagelibrary.service.PlattImageLibraryService;
import com.aebiz.b2b2c.userfront.plattimagelibrary.vo.PlattImageLibraryModel;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.service.VipclubIntegralLogService;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.IntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogModel;
import com.aebiz.b2b2c.websiteoperation.customerservice.service.CustomerServiceService;
import com.aebiz.b2b2c.websiteoperation.customerservice.vo.CustomerServiceModel;
import com.aebiz.b2b2c.websiteoperation.versionupdate.service.VersionUpdateService;
import com.aebiz.b2b2c.websiteoperation.versionupdate.vo.VersionUpdateModel;

/**
 * 医生端工具箱服务接口控制类
 * 
 * @author szr
 * 
 */
@Controller
@RequestMapping("/app/service/tools")
public class AppToolUserController extends AppBaseController {
	/**
	 * 构造方法
	 */
	public AppToolUserController() {
		super("", "", AppToolUserController.class);
	}

	@Autowired
	private ServicestaffService servicestaffService;
	@Autowired
	private InnerMessageService innerMessageService;
	/*
	 * 用户设备表service
	 */
	@Autowired
	private UserEquipmentService userEquipmentService;
	/*
	 * 文章service
	 */
	@Autowired
	private ContentService contentService;
	/*
	 * 图片service
	 */
	@Autowired
	private PlattImageLibraryService plattImageLibraryService;

	/*
	 * 患教类型service
	 */
	@Autowired
	private ContentCategoryService contentCategoryService;

	/* 药品描述的service */
	@Autowired
	private ProductMainDescriptionService productMainDescriptionService;

	/*
	 * 医生患者文章关联service
	 */
	@Autowired
	private DocCusConService docCusConService;
	/*
	 * 药品service
	 */
	@Autowired
	private ProductMainService productMainService;

	/*
	 * 客服信息service
	 */
	@Autowired
	private CustomerServiceService customerServiceService;
	
	@Autowired
	private VipclubIntegralLogService vipclubIntegralLogService;
	
	@Autowired
	private VersionUpdateService  versionService;

	/**
	 * 根据分类id获取文章列表
	 */
	@RequestMapping(value = "/getContentList", method = RequestMethod.GET)
	public String getCustomerListByDoctorUuidAndGroupId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("contentCategoryUuid") String contentCategoryUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志 以及必需要传的参数
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "contentCategoryUuid,true" });

		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");

		// 判断分类id不能为空
		if (StringUtil.isEmpty(contentCategoryUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "分类id不能为空"), callback);
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 根据分类id获得文章列表 内容分类uuid
		List<ContentModel> contentModelList = contentService.getByContentCategoryUuid(contentCategoryUuid);
		// 定义返回List
		List relist = new ArrayList();
		if (contentModelList != null && contentModelList.size() > 0) {
			for (ContentModel cm : contentModelList) {
				Map<String, Object> save = new HashMap<String, Object>();
				// 文章图片
				save.put("img", cm.getImgUrl());
				// 文章标题
				save.put("contentTitle", cm.getContentTitle());
				// 推荐时间
				save.put("createTime", cm.getCreateTime());
				// 作者
				save.put("author", cm.getAuthor());
				// 出处
				save.put("provenance", cm.getProvenance());
				// 文章Uuid
				save.put("contextUuid", cm.getUuid());
				relist.add(save);
			}
		}

		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 根据文章id获取文章信息
	 */
	@RequestMapping(value = "/getContent", method = RequestMethod.GET)
	public String getContentByUuid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("contentUuid") String contentUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志 以及必需要传的参数
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "contentUuid,true" });
		String doctorUuid = request.getParameter("doctorUuid");
		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");

		// 判断分类id不能为空
		if (StringUtil.isEmpty(contentUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "文章id不能为空"), callback);
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 根据id获得文章信息
		ContentModel contentModel = contentService.getByUuid(contentUuid);

		// 判空
		if (contentModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "文章为空"), callback);
			return null;
		}
		
		System.out.println("==========doctorUuid==========="+doctorUuid);
		if(!StringUtil.isEmpty(doctorUuid)){
			vipclubIntegralLogService.saveVipIntegralLog(doctorUuid, "add", 0, IntegralType.ADD_SHARE_BY_ARTICLE.getValue(),
					VipclubIntegralLogModel.VIPCLUB_USERTYPE_DOC, "分享"+contentModel.getContentTitle()+"文章成功，积分加0","");
		}

		// 文章分类名称
		jsonMap.put("contentCategoryName", contentModel.getCategoryName());
		// 文章标题
		jsonMap.put("contentTitle", contentModel.getContentTitle());
		// 作者
		jsonMap.put("author", contentModel.getAuthor());
		// 出处
		jsonMap.put("provenance", contentModel.getProvenance());
		// 内容(简介)
		jsonMap.put("contentNote", contentModel.getContentNote());
		// 介绍
		jsonMap.put("introduction", contentModel.getIntroduction());
		// 文章Uuid
		jsonMap.put("contentId", contentModel.getUuid());
		// 图片地址
		jsonMap.put("img", contentModel.getImgUrl());

		jsonMap.put("createTime", contentModel.getCreateTime());

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 根据图片库分类id获取图片list， 分类id：1表示美学医图，
	 */
	@RequestMapping(value = "/getMedicalImg", method = RequestMethod.GET)
	public String getMedicalImg(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("categoryUuid") String categoryUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志 以及必需要传的参数
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "categoryUuid,true" });

		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");

		// 判断分类id不能为空
		if (StringUtil.isEmpty(categoryUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "分类id不能为空"), callback);
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 根据分类id获得美图list
		List<PlattImageLibraryModel> plattImageLibraryList = plattImageLibraryService
				.getPlattImageLibraryModelListByCategoryUuid(categoryUuid);

		// 判空
		if (plattImageLibraryList == null || plattImageLibraryList.size() < 0) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "图片列表为空"), callback);
			return null;
		}

		// 定义返回List
		List relist = new ArrayList();
		if (plattImageLibraryList != null && plattImageLibraryList.size() > 0) {
			for (PlattImageLibraryModel pilm : plattImageLibraryList) {
				Map<String, Object> save = new HashMap<String, Object>();
				// 图片
				save.put("img", pilm.getImagePath());

				// 图片Uuid
				save.put("imgUuid", pilm.getUuid());
				relist.add(save);
			}
		}

		jsonMap.put("relist", relist);// 消息字段

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 获取患教分类
	 */
	@RequestMapping(value = "/getCustomerTeachType", method = RequestMethod.GET)
	public String getCustomerTeachType(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志 以及必需要传的参数
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });

		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		String categoryUuid = request.getParameter("categoryUuid");
		List<ContentCategoryModel> contentCategoryList = null;
		if (!StringUtil.isEmpty(categoryUuid)) {
			contentCategoryList = contentCategoryService.getSubContentCategoryByParentUuid(categoryUuid);
		} else {
			// 获得患教类型list
			contentCategoryList = contentCategoryService.getSubContentCategoryByParentUuid("HJ");
		}
		// 定义返回List
		List relist = new ArrayList();
		if (contentCategoryList != null && contentCategoryList.size() > 0) {
			for (ContentCategoryModel ccm : contentCategoryList) {
				Map<String, Object> save = new HashMap<String, Object>();
				// 患教分类名
				save.put("category", ccm.getCategoryName());
				// 患教分类id
				save.put("categoryId", ccm.getUuid());
				relist.add(save);
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 根据患教分类获取患教信息
	 */
	@RequestMapping(value = "/getCustomerTeachList", method = RequestMethod.GET)
	public String getCustomerTeachList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("categoryId") String categoryId) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志 以及必需要传的参数
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });

		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");

		// 判断患教分类id不能为空
		if (StringUtil.isEmpty(categoryId)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "分类id不能为空"), callback);
			return null;
		}
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 获得患教list根据分类id
		List<ContentModel> contentModelList = contentService.getCustomerTeachList(categoryId);
		// 定义返回List
		List relist = new ArrayList();
		if (contentModelList != null && contentModelList.size() > 0) {
			for (ContentModel cm : contentModelList) {
				Map<String, Object> save = new HashMap<String, Object>();
				// 患教标题
				save.put("title", cm.getContentTitle());
				// 患教id
				save.put("contentUuid", cm.getUuid());
				// 启用状态
				save.put("state", cm.getState());
				relist.add(save);
			}
		}
		jsonMap.put("relist", relist);// 消息字段
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 根据患教id获取患教文章信息
	 */
	@RequestMapping(value = "/getCustomerTeach", method = RequestMethod.GET)
	public String getCustomerTeach(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("contentUuid") String contentUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志 以及必需要传的参数
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "contentUuid,true" });

		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");

		// 判断id不能为空
		if (StringUtil.isEmpty(contentUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患教id不能为空"), callback);
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 根据id获得患教信息
		ContentModel contentModel = contentService.getByUuid(contentUuid);
		// 判空
		if (contentModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患教文章为空"), callback);
			return null;
		}
		// 图片
		jsonMap.put("img", contentModel.getImgUrl());
		// 文章分类名称
		jsonMap.put("contentCategoryName", contentModel.getCategoryName());
		// 文章标题
		jsonMap.put("contentTitle", contentModel.getContentTitle());
		// 作者
		jsonMap.put("author", contentModel.getAuthor());
		// 出处
		jsonMap.put("provenance", contentModel.getProvenance());
		// 内容(简介)
		jsonMap.put("contentNote", contentModel.getContentNote());
		// 介绍
		jsonMap.put("introduction", contentModel.getIntroduction());
		// 文章Uuid
		jsonMap.put("contentId", contentModel.getUuid());

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 医生推荐文章给患者
	 */
	@RequestMapping(value = "/createDocCusCon", method = RequestMethod.POST)
	public String createDocCusCon(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("contentUuid") String contentUuid, @RequestParam("doctorUuid") String doctorUuid,
			@RequestParam("customerUuids") List<String> customerUuids) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志 以及必需要传的参数
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "contentUuid,true", "doctorUuid,true", "customerUuids,true" });

		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");

		// 判断id不能为空
		if (StringUtil.isEmpty(contentUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "患教id不能为空"), callback);
			return null;
		}
		if (StringUtil.isEmpty(doctorUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "医生id不能为空"), callback);
			return null;
		}

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 创建新的DocCusConModel
		DocCusConModel docCusConModel = new DocCusConModel();
		// 赋值
		docCusConModel.setDoctorUuid(doctorUuid);
		docCusConModel.setContentUuid(contentUuid);
		// 循环
		for (String customerUuid : customerUuids) {
			docCusConModel.setContentUuid(customerUuid);
			docCusConService.create(docCusConModel);
			String doctorName = servicestaffService.getRealNameByUuid(doctorUuid);

			// szr 推送消息 点击“发送给我的患者” 患者收到“XX医生向您发送了一篇患教文章”似的消息
			String content = doctorName + MessageHelper.getMessage("message.doctorPushCustomerArticle");
			innerMessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, content,
					InnerMessageModel.ACCOUNT_TYPE_STORE, InnerMessageModel.ACCOUNT_TYPE_CUSTOMER,
					PushConst.push_client_customer, "contentUuid", contentUuid, "");

		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 根据药品名查药品 (药品名可不填) 12/19
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @param doctorid
	 * @return
	 */

	@RequestMapping(value = "/getProductMainListByName", method = RequestMethod.GET)
	public String getProductMainListByName(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		// 得到药品名
		String medicineName = request.getParameter("medicineName");
		if (StringUtil.isEmpty(medicineName)) {
			medicineName = "";
		}
		List<ProductMainModel> productMainList = productMainService.getByProductName(medicineName);
		List relist = new ArrayList();
		if (productMainList != null && productMainList.size() > 0) {
			for (ProductMainModel productMainModel : productMainList) {
				Map<Object, Object> noticeMap = new HashMap<Object, Object>();
				// 药品名
				noticeMap.put("productName", productMainModel.getProductName());
				// 药品id
				noticeMap.put("productUuid", productMainModel.getUuid());
				noticeMap.put("imageUrl", productMainModel.getImageUrl());
				noticeMap.put("productEnName", productMainModel.getProductEnglishName());
				relist.add(noticeMap);
			}
		}
		// 消息字段 返回参数见 com.aebiz.b2b2c.product.productmain.vo.ProductMainModel
		jsonMap.put("relist", relist);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
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
	@RequestMapping(value = "/getProductMainByid", method = RequestMethod.GET)
	public String getProductMainByid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("productUuid") String productUuid) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut,
				new String[] { "callback,true", "productUuid,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");

		// 判断药品id不能为空
		if (StringUtil.isEmpty(productUuid)) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "药品id不能为空"), callback);
			return null;
		}

		// 通过药品id得到药品moel
		ProductMainModel productMainModel = productMainService.getByUuid(productUuid);
		if (productMainModel == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "根据药品id没有查到对象"), callback);
			return null;
		}
		String uuid = productMainDescriptionService.getProductMainDescriptionUuidByProductUuid(productUuid);
		if (!StringUtil.isEmpty(uuid)) {
			ProductMainDescriptionModel pdm = productMainDescriptionService.getByUuid(uuid);
			if (pdm != null) {
				productMainModel.setLaboratorExamination(pdm.getLaboratorExamination());
				productMainModel.setAttention(pdm.getAttention());
				productMainModel.setDrugInteractio(pdm.getDrugInteractio());
			}
		}

		// 消息字段 返回参数见 com.aebiz.b2b2c.product.productmain.vo.ProductMainModel
		jsonMap.put("productMainModel", productMainModel);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 查看手机的设备号 16/01/12
	 * 
	 * @author szr
	 * @param request
	 * @param response
	 * @param doctorid
	 * @return
	 */
	@RequestMapping(value = "/getUserEquipmentByUserId", method = RequestMethod.GET)
	public String getUserEquipmentByUserUuid(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		// 获取回调的名
		Object callback = map.get("callback");
		String userUuid = request.getParameter("userId");
		String userType = request.getParameter("userType");
		String deviceNumber = request.getParameter("deviceNumber");
		String mobileType = request.getParameter("mobileType");

		UserEquipmentModel userEquipmentModel = null;
		// 通过用户id得到model对象
		if (!StringUtil.isEmpty(userUuid) && !StringUtil.isEmpty(deviceNumber)) {
			userEquipmentModel = userEquipmentService.getByUserUuid(userUuid, deviceNumber);
		}
		// 如果为空创建
		if (userEquipmentModel == null) {
			userEquipmentModel = new UserEquipmentModel();
			userEquipmentModel.setUserUuid(userUuid);
			userEquipmentModel.setUserType(userType);
			userEquipmentModel.setDeviceNumber(deviceNumber);
			userEquipmentModel.setMobileType(mobileType);
			userEquipmentService.create(userEquipmentModel);
		}
		// 返回字段
		Map<String, Object> reMap = new HashMap<String, Object>();
		// 最后操作时间
		reMap.put("opeTime", userEquipmentModel.getOpeTime());
		// 创建时间
		reMap.put("createTime", userEquipmentModel.getCreateTime());
		// 用户类型  1：医生 2:患者
		reMap.put("userType", userEquipmentModel.getUserType());
		// 手机类型 0:ios 1：安卓
		reMap.put("mobileType", userEquipmentModel.getMobileType());
		// 设备号
		reMap.put("deviceNumber", userEquipmentModel.getDeviceNumber());
		// 消息字段
		jsonMap.put("reMap", reMap);
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;
	}

	/**
	 * 查看并返回最新一条的客服信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getCustomerServiceInfo", method = RequestMethod.GET)
	public String getCustomerServiceInfo(HttpServletRequest request, HttpServletResponse response) {

		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取参数值并且进行非空判断
		boolean breakOut = false;// return 标志
		// 标志 以及必需要传的参数
		Map<String, String> map = getParam(request, response, breakOut, new String[] { "callback,true" });

		if ("true".equals(map.get("breakOut"))) {
			return null;
		}
		Object callback = map.get("callback");

		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);

		// 根据电话获得客服信息
		List<CustomerServiceModel> customerServicelist = customerServiceService.getAll();

		// 判空
		if (customerServicelist == null) {
			HttpServletUtils.outJsonCallBack(response, this.getFailJsonMap(SysConst.FAIL, "客户信息为空"), callback);
			return null;
		}

		if (customerServicelist != null && customerServicelist.size() > 0) {
			CustomerServiceModel customerServiceModel = customerServicelist.get(0);

			jsonMap.put("customerServiceName", customerServiceModel.getCustomerServiceName());
			jsonMap.put("state", customerServiceModel.getState());
			jsonMap.put("customerServiceMobile", customerServiceModel.getCustomerServiceMobile());
		}

		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);
		return null;

	}
	
	
	/**
	 * 获取app版本和链接地址 by  xl 2016-03-08
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getAppVersion", method = RequestMethod.GET)
	public String  getAppVersion(HttpServletRequest request, HttpServletResponse response) {
		// 设置返回数据编码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Object callback = request.getParameter("callback");
		//发布端 1：患者端 0：医生端
		String  versionType = request.getParameter("versionType");
		// 设置返回信息
		Map<Object, Object> jsonMap = this.getSucJsonMap(SysConst.SUCCESS, SysConst.SUCCESSMESSAGE);
		//调用service 来获取最新发布的版本
		VersionUpdateModel version= versionService.getVersionUpdateModel(versionType);
		if(version !=null){
			jsonMap.put("versionNo", version.getVersionNo());
			jsonMap.put("versionUrl", version.getVersionUrl());
		}else{
			jsonMap.put("versionNo", "");
			jsonMap.put("versionUrl", "");
		}
		
		HttpServletUtils.outJsonCallBack(response, jsonMap, callback);

		return null;
	
	}
	
}
