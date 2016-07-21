package com.aebiz.b2b2c.vipclub.sysback.web.giftpackage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.vo.ConditionOpTypeEnum;
import com.aebiz.b2b2c.baseframework.basecrud.web.BaseController;
import com.aebiz.b2b2c.baseframework.filemgr.helper.FileUploadHelper;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.promotion.coupontype.vo.CouponTypeModel;
import com.aebiz.b2b2c.vipclub.giftpackage.service.GiftPackageService;
import com.aebiz.b2b2c.vipclub.giftpackage.vo.GiftPackageModel;
import com.aebiz.b2b2c.vipclub.giftpackage.vo.GiftPackageQueryModel;
import com.aebiz.b2b2c.vipclub.giftpackagecouponrel.service.GiftPackageCouponRelService;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/sysback/giftpackage")
public class GiftPackageController extends
		BaseController<GiftPackageModel, GiftPackageQueryModel> {
	private GiftPackageService myService;

	@Autowired
	private FileUploadHelper fileUploadHelper;

	@Autowired
	private GiftPackageCouponRelService gpcrService;

	@Autowired
	public void setMyService(GiftPackageService bs) {
		this.myService = bs;
		super.setBs(bs);
	}

	public GiftPackageController() {
		super("vipclub/sysback/giftpackage", "GiftPackage",
				GiftPackageController.class);
	}

	@Override
	protected GiftPackageQueryModel preparedQMFixValue(GiftPackageQueryModel qm) {
		qm.setPackageCount(1);
		qm.getMapCondition().put("packageCount",
				Integer.valueOf(ConditionOpTypeEnum.GE.getCode()));
		return qm;
	}

	/**
	 * 
	 * 会员大礼包添加操作
	 * 
	 */
	@RequestMapping(value = { "/adds" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String adds(Model model,
			@RequestParam("files") MultipartFile[] files,
			@ModelAttribute("m") GiftPackageModel m, HttpServletRequest request) {
		String[] obj = request.getParameterValues("prizeTypeS");
		String prizeType = "";
		if (obj != null) {
			for (int n = 0; n < obj.length; n++) {
				prizeType += (String) obj[n] + ",";
			}
		}
		m.setPrizeType(prizeType);

		if (!checkAdds(model, m, request, files, prizeType)) {
			request.setAttribute("ShowMsgs", this.getMapErrorMsg());
			return (String) request.getAttribute("ERROR_BACK_URL");
		}

		// 上传礼包图片
		uploadImage(m, files);

		cleanQuerySession(request);
		this.myService.create(m);

		return "vipclub/sysback/giftpackage/GiftPackageSuccess";
	}

	/**
	 * 
	 * 会员大礼包更新操作
	 * 
	 */
	@RequestMapping(value = { "/updates" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String updates(Model model,
			@RequestParam("files") MultipartFile[] files,
			@ModelAttribute("m") GiftPackageModel m, HttpServletRequest request) {
		String[] obj = request.getParameterValues("prizeTypeS");
		String prizeType = "";
		if (obj != null) {
			for (int n = 0; n < obj.length; n++) {
				prizeType += (String) obj[n] + ",";
			}
		}
		m.setPrizeType(prizeType);

		if (!checkUpdates(model, m, request, files, prizeType)) {
			request.setAttribute("ShowMsgs", this.getMapErrorMsg());
			return (String) request.getAttribute("ERROR_BACK_URL");
		}

		// 更新礼包图片
		uploadImage(m, files);

		cleanQuerySession(request);
		this.myService.update(m);

		return "vipclub/sysback/giftpackage/GiftPackageSuccess";
	}

	/**
	 * 会员大礼包添加数据验证
	 * 
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 */
	public boolean checkAdds(Model model, GiftPackageModel m,
			HttpServletRequest request, MultipartFile[] files, String prizeType) {
		if (!StringUtil.isEmpty(m.getPackageName())) {
			if (m.getPackageName().length() > 40) {
				this.putErrorMsg("packageName", MessageHelper
						.getMessage("giftpackage.packageName.lengthError"));
			}
		} else {
			this.putErrorMsg("packageName",
					MessageHelper.getMessage("giftpackage.packageName.isEmpty"));
		}
		if (StringUtil.isEmpty(prizeType)) {
			this.putErrorMsg("prizeTypeS",
					MessageHelper.getMessage("giftpackage.prizeTypeS.isEmpty"));
		}
		if (files == null || files.length <= 0) {
			this.putErrorMsg("files",
					MessageHelper.getMessage("giftpackage.files.isEmpty"));
		}
		if (!StringUtil.isEmpty(m.getNote()) && m.getNote().length() > 250) {
			this.putErrorMsg("note",
					MessageHelper.getMessage("giftpackage.note.lengthError"));
		}
		// 跳转到页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			request.setAttribute(ERROR_BACK_URL,
					"vipclub/sysback/giftpackage/GiftPackageAdd");
			return false;
		}
		return true;
	}

	/**
	 * 会员大礼包更新数据验证
	 * 
	 * @param model
	 * @param m
	 * @param request
	 * @return
	 */
	public boolean checkUpdates(Model model, GiftPackageModel m,
			HttpServletRequest request, MultipartFile[] files, String prizeType) {
		if (!StringUtil.isEmpty(m.getPackageName())) {
			if (m.getPackageName().length() > 40) {
				this.putErrorMsg("packageName", MessageHelper
						.getMessage("giftpackage.packageName.lengthError"));
			}
		} else {
			this.putErrorMsg("packageName",
					MessageHelper.getMessage("giftpackage.packageName.isEmpty"));
		}
		if (StringUtil.isEmpty(prizeType)) {
			this.putErrorMsg("prizeTypeS",
					MessageHelper.getMessage("giftpackage.prizeTypeS.isEmpty"));
		}
		if (files == null || files.length <= 0) {
			this.putErrorMsg("files",
					MessageHelper.getMessage("giftpackage.files.isEmpty"));
		}
		if (!StringUtil.isEmpty(m.getNote()) && m.getNote().length() > 250) {
			this.putErrorMsg("note",
					MessageHelper.getMessage("giftpackage.note.lengthError"));
		}
		// 跳转到页面，并将错误返回
		if (this.getMapErrorMsg() != null && this.getMapErrorMsg().size() > 0) {
			request.setAttribute(ERROR_BACK_URL,
					"vipclub/sysback/giftpackage/GiftPackageUpdate");
			return false;
		}
		return true;
	}

	/**
	 * 上传图片
	 * 
	 * @param m
	 * @param files
	 * @return
	 */
	public GiftPackageModel uploadImage(GiftPackageModel m,
			MultipartFile[] files) {
		List<String> fileNameList = new ArrayList<String>();
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();

		try {
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					String filePrefix = "giftPackage"
							+ new SimpleDateFormat("yyyyMMddHHmmss")
									.format(new Date());

					// 如果文件大小不为0
					if (files[i].getSize() <= 0) {
						continue;
					}

					String oldName = fileUploadHelper.getFileName(files[i]);
					String fileSuffix = this.getFileSuffix(oldName);

					String newName = filePrefix + fileSuffix;
					m.setPackageImage(newName);

					fileNameList.add(newName);
					fileList.add(files[i]);
				}
			} else {
				m.setPackageImage("");
			}
		} catch (Exception ex) {
			m.setPackageImage("");
		}
		if (fileList != null && fileList.size() > 0) {
			MultipartFile[] newFiles = new MultipartFile[fileList.size()];
			String[] newFileNames = new String[fileList.size()];
			for (int i = 0; i < fileList.size(); i++) {
				newFileNames[i] = fileNameList.get(i);
				newFiles[i] = fileList.get(i);
			}
			fileUploadHelper.uploadFiles(newFiles, newFileNames);
		}
		return m;
	}

	/**
	 * 得到文件后缀
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public String getFileSuffix(String fileName) {
		if (StringUtil.isEmpty(fileName)) {
			return "";
		}
		return fileName.substring(fileName.indexOf("."), fileName.length());
	}

	/**
	 * to优惠券关联页面
	 * 
	 * @return
	 */
	@RequestMapping("/toRelateCoupon/{uuid}")
	public String toRelateCoupon(@PathVariable("uuid") String uuid, Model model) {
		GiftPackageModel m = this.myService.getByUuid(uuid);
		model.addAttribute("m", m);
		return "vipclub/sysback/giftpackage/GiftPackageRetaleCouponList";
	}

	/**
	 * 查询礼包已关联的优惠券
	 * 
	 * @param uuid
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryCouponList/{uuid}")
	public Map<String, Object> queryCouponList(
			@PathVariable("uuid") String uuid, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Map<String, Object> pageParamMap = parsePageParam(request);
		GiftPackageQueryModel qm = parseQueryModel(request);
		int iDisplayStart = ((Integer) pageParamMap.get("iDisplayStart"))
				.intValue();
		int iDisplayLength = ((Integer) pageParamMap.get("iDisplayLength"))
				.intValue();
		int iSortCol_0 = ((Integer) pageParamMap.get("iSortCol_0")).intValue();
		String sortFieldName = (String) pageParamMap.get("mDataProp_"
				+ iSortCol_0);
		String sortTypeString = (String) pageParamMap.get("sSortDir_0");
		Boolean needSort = (Boolean) pageParamMap
				.get("bSortable_" + iSortCol_0);
		if (needSort.booleanValue()) {
			qm.setSortName(sortFieldName);
			qm.setSortType(sortTypeString);
		}
		qm.setUuid(uuid);
		List<CouponTypeModel> showList = this.myService
				.getCouponListByGiftPackageUuid(qm, iDisplayStart,
						iDisplayLength);
		int totalCount = this.myService.getCouponListCountByGiftPackageUuid(qm);
		Map<String, Object> jsonMap = new HashMap();
		jsonMap.put("sEcho", pageParamMap.get("sEcho"));
		jsonMap.put("iTotalRecords", Integer.valueOf(totalCount));
		jsonMap.put("iTotalDisplayRecords", Integer.valueOf(totalCount));
		jsonMap.put("aaData", showList);
		return jsonMap;
	}

	/**
	 * 搜索礼包可以关联的优惠券<br/>
	 * 
	 * 需要把已关联的排除掉<br/>
	 * 
	 * @param uuid
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/searchCouponList/{uuid}")
	public Map<String, Object> searchCouponList(@PathVariable String uuid,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		Map<String, Object> pageParamMap = parsePageParam(request);
		GiftPackageQueryModel qm = parseQueryModel(request);
		int iDisplayStart = ((Integer) pageParamMap.get("iDisplayStart"))
				.intValue();
		int iDisplayLength = ((Integer) pageParamMap.get("iDisplayLength"))
				.intValue();
		int iSortCol_0 = ((Integer) pageParamMap.get("iSortCol_0")).intValue();
		String sortFieldName = (String) pageParamMap.get("mDataProp_"
				+ iSortCol_0);
		String sortTypeString = (String) pageParamMap.get("sSortDir_0");
		Boolean needSort = (Boolean) pageParamMap
				.get("bSortable_" + iSortCol_0);
		if (needSort.booleanValue()) {
			qm.setSortName(sortFieldName);
			qm.setSortType(sortTypeString);
		}
		qm.setUuid(uuid);
		List<CouponTypeModel> showList = this.myService.searchCouponList(qm,
				iDisplayStart, iDisplayLength);
		int totalCount = this.myService.searchCouponListCount(qm);
		Map<String, Object> jsonMap = new HashMap();
		jsonMap.put("sEcho", pageParamMap.get("sEcho"));
		jsonMap.put("iTotalRecords", Integer.valueOf(totalCount));
		jsonMap.put("iTotalDisplayRecords", Integer.valueOf(totalCount));
		jsonMap.put("aaData", showList);
		return jsonMap;
	}

	/**
	 * 保存礼包关联优惠券
	 * 
	 * @param uuid
	 *            礼包id
	 * @param checkIds
	 *            优惠券id集合
	 * @return
	 */
	@RequestMapping("/saveChooseCoupon")
	@ResponseBody
	public String saveChooseCoupon(@RequestParam("uuid") String uuid,
			@RequestParam("checkIds") String checkIds) {
		if (StringUtil.isEmpty(checkIds)) {
			return JSON.toJSONString("fail");
		}
		List<String> list = Arrays.asList(checkIds.split(","));
		this.gpcrService.saveChooseCoupon(uuid, list);
		return JSON.toJSONString("success");
	}

	/**
	 * 删除礼包关联优惠券
	 * 
	 * @param uuid
	 *            礼包id
	 * @param checkIds
	 *            优惠券id集合
	 * @return
	 */
	@RequestMapping("/removeRelate")
	@ResponseBody
	public String removeRelate(@RequestParam("uuid") String uuid,
			@RequestParam("selectOne") String checkIds) {
		if (StringUtil.isEmpty(checkIds)) {
			return JSON.toJSONString("fail");
		}
		List<String> list = Arrays.asList(checkIds.split(","));
		this.gpcrService.removeRelate(uuid, list);
		return JSON.toJSONString("success");
	}

}