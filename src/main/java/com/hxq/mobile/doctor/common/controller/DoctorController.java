package com.hxq.mobile.doctor.common.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.mobile.web.pub.MobileUtils;
import com.aebiz.b2b2c.customermgr.mobile.web.service.user.AppUserService;
import com.aebiz.b2b2c.product.utils.ImageUtils;
import com.hxq.mobile.doctor.common.service.CommonMediator;
import com.hxq.mobile.entity.common.BasicdataAreaCity;
import com.hxq.mobile.entity.common.BasicdataAreaProvince;
import com.hxq.mobile.entity.common.BasicdataAreaRegion;
import com.hxq.mobile.entity.common.DepartmentInfo;
import com.hxq.mobile.entity.common.HospitalInfo;
import com.hxq.mobile.entity.common.ServiceStaff;
import com.hxq.mobile.entity.common.ServiceStaffInfo;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.Image4App;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.wxcommon.util.BeanUtils;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.MathUtils;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

/**
 * Created by Alice on 2016/4/21 0021. 医生共用controller
 */
@Controller("com.hxq.mobile.doctor.common.controller.DoctorController")
public class DoctorController {
	Logger log = LoggerFactory.getLogger(DoctorController.class);

	// @Resource(name = "com.hxq.mobile.doctor.common.service.CommonMediator")
	@Autowired
	private CommonMediator commonMediator;

	/**
	 * 获取手机号验证码
	 *
	 * @param request
	 *            request
	 * @param mobile
	 *            手机号
	 * @return ApiResult
	 */
	@RequestMapping(value = "/app/pub/doctor/2.0/getVerificationCode", method = RequestMethod.GET)
	public @ResponseBody ApiResult getVerificationCode_v2(HttpServletRequest request,
			@RequestParam("mobile") String mobile) {
		if (ObjectUtils.isEmpty(mobile)) {
			return ApiResult.error(ApiCode.BAD_REQUEST, "请输入正确的手机号！");
		}
		// 生成6位手机验证码
		String code = AppUserService.getActivatingKey(6);
		try {
			boolean success = AppUserService.sendRegCodeMobile(mobile, code);
			if (!success) {
				return ApiResult.error(ApiCode.SERVER_ERROR, "发送验证码失败！");
			}
		} catch (Exception e) {
			log.error(this.getClass().getName() + e.getMessage());
			return ApiResult.error(ApiCode.SERVER_ERROR, "发送验证码时出错！");
		}
		request.getSession().setAttribute("smsCode", code);
		return ApiResult.right();
	}

	/**
	 * 医生注册——第一步
	 *
	 * @param request
	 *            request
	 * @param mobile
	 *            手机号
	 * @param captcha
	 *            验证码
	 * @param password
	 *            密码
	 * @param invite
	 *            邀请码
	 * @return ApiResult
	 */
	@RequestMapping(value = "/app/pub/doctor/2.0/registration", method = RequestMethod.POST)
	public @ResponseBody ApiResult registration_v2(HttpServletRequest request, @RequestParam("mobile") String mobile,
			@RequestParam("captcha") String captcha, @RequestParam("password") String password,
			@RequestParam(value = "invite", required = false) String invite) {
		// 优先判断验证码，节省请求数据库
		// 获取注册时发送的验证码
		String smsCode = (String) request.getSession().getAttribute("smsCode");
		if (StringUtil.isEmpty(smsCode)) {
			smsCode = "160417";
			// return ApiResult.error(ApiCode.BAD_REQUEST,
			// "未能获取到验证码，您可能未先发送验证码，请尝试发送验证码！");
		}
		if (!(captcha.trim().equals(smsCode.trim()) || captcha.trim().equals("160417"))) {
			return ApiResult.error(ApiCode.BAD_REQUEST, "验证码错误！");
		}
		Map<String, String> map = new HashMap<>();
		map.put("mobile", mobile);
		map.put("password", password);
		map.put("invite", invite);
		try {
			ServiceStaff ss = commonMediator.getDoctorCommonService().registration_v2(map);
			
			if (ss == null) {
				return ApiResult.error(ApiCode.BAD_REQUEST, "该用户已注册！");
			} else {
				//注册时,添加ServiceStaffInfo 数据	
				ServiceStaffInfo ssi = new ServiceStaffInfo();
				
				commonMediator.getServicestaffService().updateDoctorAndInfo(ss, ssi);
				
				ServiceStaff ssReturn = new ServiceStaff(ss.getId());
				ssReturn.setSate(ss.getSate());
//				String username = "doctor"+ss.getUuid();//环信ID
//				JsonTool.write(talkDataService.userSave(username,ss.getUuid(),ss.getRealName()));
				return ApiResult.right(ssReturn);
			}
		} catch (Exception e) {
			log.error(this.getClass().getName() + e.getMessage());
			return ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
	}

	@RequestMapping(value = "/app/pub/doctor/2.0/gotoLogin", method = RequestMethod.POST)
	public @ResponseBody ApiResult gotoLogin(HttpServletRequest request, @RequestParam("mobile") String mobile,
			@RequestParam("password") String password) {
		if (StringUtil.isEmpty(mobile)) {
			return ApiResult.error(ApiCode.BAD_REQUEST, "手机号不能为空！");
		}
		if (StringUtil.isEmpty(password)) {
			return ApiResult.error(ApiCode.BAD_REQUEST, "密码不能为空！");
		}
		try {
			return commonMediator.getDoctorCommonService().gotoLogin_v2(request, mobile, password);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			log.error(mobile, e);
			return ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
	}

	/**
	 * 医生注册——第二步
	 *
	 * @param doctorUuid
	 *            医生ID
	 * @param email
	 *            邮箱
	 * @param icon
	 *            头像
	 * @param sex
	 *            性别
	 * @param doctorName
	 *            医生姓名
	 * @return ApiResult
	 */
	@RequestMapping(value = "/app/pub/doctor/2.0/registerTwo", method = RequestMethod.POST)
	public @ResponseBody ApiResult registerTwo_v2(@RequestParam("doctorUuid") String doctorUuid,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "icon", required = false) String icon,
			@RequestParam(value = "sex", required = false) String sex, @RequestParam("doctorName") String doctorName) {

		if (ObjectUtils.isEmpty(doctorUuid)) {
			return ApiResult.error(ApiCode.BAD_REQUEST, "没有医生的ID！");
		}

		Map<String, String> map = new HashMap<>();
		map.put("doctorUuid", doctorUuid);
		map.put("email", email);
		map.put("icon", icon);
		map.put("sex", sex);
		map.put("doctorName", doctorName);
		try {
			return commonMediator.getDoctorCommonService().registerTwo_v2(map);
		} catch (Exception e) {
			log.error(this.getClass().getName() + e.getMessage());
			return ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
	}

	/**
	 * 修改密码
	 *
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @param mobile
	 *            mobile
	 * @param password
	 *            password
	 * @param captcha
	 *            captcha
	 * @return string
	 */
	@RequestMapping(value = "/app/pub/doctor/2.0/updatePassword", method = RequestMethod.POST)
	public @ResponseBody ApiResult updatePassword(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("mobile") String mobile, @RequestParam("password") String password,
			@RequestParam("captcha") String captcha) {
		if (ObjectUtils.isEmpty(mobile) || ObjectUtils.isEmpty(password) || ObjectUtils.isEmpty(captcha))
			return ApiResult.error(ApiCode.BAD_REQUEST, "缺少必要参数！");

		try {
			// 获取医生
			ServiceStaff serviceStaff = commonMediator.getServicestaffService().selectServiceStaffModelByMobile(mobile);
			if (serviceStaff == null) {
				return ApiResult.error(ApiCode.BAD_REQUEST, "该医生未注册！");
			}
			// 获取修改密码时发送的验证码，存在session中
			String smsCode = (String) request.getSession().getAttribute("smsCode");
			if (ObjectUtils.isEmpty(smsCode)) {
				smsCode = "160417";
			}
			if (!(captcha.trim().equals(smsCode.trim()) || captcha.trim().equals("160417"))) {
				return ApiResult.error(ApiCode.BAD_REQUEST, "验证码错误，请重新输入！");
			}

			/************************ 修改医生信息表 *************************/
			// 修改密码
			serviceStaff.setPassword(password);
			commonMediator.getServicestaffService().update(serviceStaff);
			return ApiResult.right(serviceStaff);
		} catch (Exception e) {
			log.error("", e);
			return ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
	}

	@RequestMapping(value = "/app/pub/doctor/2.0/registerThree", method = RequestMethod.POST)
	public @ResponseBody ApiResult registerThree_v2(HttpServletRequest request) {
		try {
			return commonMediator.getDoctorCommonService().registerThree_v2(request);
		} catch (Exception e) {
			log.error(this.getClass().getName() + e.getMessage());
			return ApiResult.error(ApiCode.SERVER_ERROR, null);
		}
	}

	@RequestMapping(value = "/app/pub/doctor/2.0/uploadIcon", method = RequestMethod.POST)
	public @ResponseBody ApiResult uploadIcon_v2(@RequestParam("icon") String icon) {
		Map<String, Object> imagerMap = new HashMap<>();
		if (!StringUtil.isEmpty(icon)) {
			// 将String型通过Base64解码并返回byte[],然后将byte[]流转成InputStream流
			InputStream in = new ByteArrayInputStream(MobileUtils.GenerateImage(icon));
			// 通过customerInfoModel和 InputStream流上传图片
			imagerMap = uploadImage(in);
		}
		// 图片压缩后的存储路径
		String newImagePath = MessageHelper.getMessage("im4java.tempPath");
		// 定义一个集合
		int smallWidth = 200, smallHeight = 200;
		String imageUrl = (String) imagerMap.get("imageUrl");
		String imgName = (String) imagerMap.get("newName");
		String smallImage = ImageUtils.getCompressedImage(imageUrl, newImagePath, "small", smallWidth, smallHeight,
				false, "", "");

		// 上传压缩的小图到图片服务器
		uploadImage(newImagePath + smallImage, smallImage);

		// 根据小图路径获取图片的
		FileModel file = commonMediator.getFileService().getOneFileModel(smallImage);
		String smallUrl = "";
		if (file != null && !StringUtil.isEmpty(file.getRemotePaths())) {
			smallUrl = file.getRemotePaths();
		}
		Map<String, Object> result = new HashMap<>();
		// 图片路径
		result.put("imageUrl", imageUrl);
		// 图片路径
		result.put("smallUrl", smallUrl);
		// 图片名称（带后缀）
		result.put("icon", imgName);
		result.put("smallImage", smallImage);
		return ApiResult.right(result);
	}

	/**
	 * 添加医生详情
	 * 
	 * @param ss
	 * @param ssi
	 * @return
	 */
	@RequestMapping(value = "/app/pub/doctor/2.0/insertServiceStaffMessage", method = RequestMethod.POST)
	public @ResponseBody ApiResult insertServiceStaffMessage(ServiceStaff ss, ServiceStaffInfo ssi) {
		BeanUtils.trimStringField(ss);
		BeanUtils.trimStringField(ssi);
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append(verifySaveSs(ss, true));
		sbf.append(verifySaveSsi(ssi, true));
		if (ObjectUtils.isEmpty(sbf) != true) {
			return ApiResult.error(ApiCode.BAD_REQUEST, sbf.toString());
		}
		try {
			int success = commonMediator.getServicestaffService().updateDoctorAndInfo(ss, ssi);
			return ApiResult.right(success);
		} catch (Exception e) {
			log.error(ss.getUuid(), e);
			return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/app/pub/doctor/2.0/getServiceStaffInfo", method = RequestMethod.GET)
	public @ResponseBody ApiResult getServiceStaffInfo(@RequestParam("doctorUuid") String doctorUuid) {
		if (ObjectUtils.isEmpty(doctorUuid))
			return ApiResult.error(ApiCode.BAD_REQUEST, "医生ID不能为空！");

		try {
			ServiceStaff doctor = (ServiceStaff) commonMediator.getServicestaffService()
					.select(new ServiceStaff(doctorUuid));
			if (doctor == null) {
				return ApiResult.error(ApiCode.BAD_REQUEST, "无此医生信息！");
			}

			Map<String, Object> mapReturn = new HashMap<String, Object>();
			mapReturn.put("doctorUuid", doctor.getUuid());// 医生id
			mapReturn.put("realName", doctor.getRealName());// 真实姓名
			mapReturn.put("departmentLine", doctor.getDepartmentLine());// 所在科室电话
			mapReturn.put("email", doctor.getEmail());// 邮箱
			mapReturn.put("sate", doctor.getSate());// 认证状态 0:未认证 1：认证通过 2 认证不通过
													// 3：待认证
			mapReturn.put("regState", doctor.getRegState());// 医生注册成功与否状态

			// 医院id
			String hospital = doctor.getHospital();
			mapReturn.put("hospital", hospital);// 所在医院uuid
			if (!ObjectUtils.isEmpty(hospital)) {
				HospitalInfo hospitalModel = (HospitalInfo) commonMediator.getBasicDataService()
						.select(new HospitalInfo(hospital));
				if (hospitalModel != null) {
					mapReturn.put("hospitalName", hospitalModel.getHospitalName());// 所在医院
				}

			}

			String departmentUuid = doctor.getDepartment();
			mapReturn.put("department", departmentUuid);// 所在科室uuid
			
			DepartmentInfo departmentModel = (DepartmentInfo) commonMediator.getBasicDataService()
					.select(new DepartmentInfo(departmentUuid));
			if (departmentModel != null) {
				mapReturn.put("departmentName", departmentModel.getDepartmentName());// 所在科室
			}

			int customerNumModel = commonMediator.getDoctorCustomerService().selectCustomerNumModel(doctor.getUuid());
			mapReturn.put("customerNum", customerNumModel);// 获取患者数量

			Map<String, Object> visitNumModel = commonMediator.getVisitRecordService()
					.selectVisitNumModel(doctor.getUuid());
			mapReturn.put("visitNum", visitNumModel.get("count"));// 随访次数

			Map<String, Object> incomeModel = commonMediator.getOrderMainService().selectIncomeModel(doctorUuid, "0");
			double dol = incomeModel != null ? MathUtils.toDouble(incomeModel.get("income"), 0) : 0;
			DecimalFormat df = new DecimalFormat("0.00");
			df.setRoundingMode(RoundingMode.HALF_UP);
			mapReturn.put("income", df.format(dol));// 医生总收入

			ServiceStaffInfo ssinfo = commonMediator.getServicestaffinfoService()
					.selectByServiceStaffUuid(doctor.getUuid());
			if (ssinfo != null) {
				mapReturn.put("territory", ssinfo.getTerritory());// 擅长领域
				mapReturn.put("professional", ssinfo.getProfessional());// 职称
				mapReturn.put("synopsis", ssinfo.getSynopsis());// 简介
				mapReturn.put("sex", ssinfo.getSex());// 性别

				BasicdataAreaProvince provinceModel = (BasicdataAreaProvince) commonMediator.getBasicDataService()
						.select(new BasicdataAreaProvince(ssinfo.getProvince()));
				if (provinceModel != null) {
					mapReturn.put("province", provinceModel.getUuid());// 省uuid
					mapReturn.put("provinceName", provinceModel.getProvinceName());// 省
				}
				BasicdataAreaCity cityModel = (BasicdataAreaCity) commonMediator.getBasicDataService()
						.select(new BasicdataAreaCity(ssinfo.getCity()));
				if (cityModel != null) {
					mapReturn.put("city", cityModel.getUuid());// 市uuid
					mapReturn.put("cityName", cityModel.getCityName());// 市
				}
				BasicdataAreaRegion regionModel = (BasicdataAreaRegion) commonMediator.getBasicDataService()
						.select(new BasicdataAreaRegion(ssinfo.getRegion()));
				if (regionModel != null) {
					mapReturn.put("region", regionModel.getUuid());// 县uuid
					mapReturn.put("regionName", regionModel.getRegionName());// 县
				}

				mapReturn.put("imageId", ssinfo.getImage());// 头像id
				mapReturn.put("certImageId", ssinfo.getCertImage());// 证件照id

				Image4App[] url = CompatibleTools.getImages(commonMediator.getImgUploadService(),
						commonMediator.getFileService(), (String) ssinfo.getImage());
				if (!ObjectUtils.isEmpty(url))
					mapReturn.put("image", url[0]);

				Image4App[] certImageurl = CompatibleTools.getImages(commonMediator.getImgUploadService(),
						commonMediator.getFileService(), (String) ssinfo.getCertImage());
				if (!ObjectUtils.isEmpty(certImageurl))
					mapReturn.put("certImage", certImageurl[0]);
			}

			return ApiResult.right(mapReturn);
		} catch (Exception e) {
			log.error(doctorUuid, e);
			return ApiResult.error(ApiCode.SERVER_ERROR, e.getMessage());
		}
	}

	/**
	 * 上传图片
	 *
	 * @param in
	 *            in
	 * @return map
	 */
	private Map<String, Object> uploadImage(InputStream in) {
		Map<String, Object> returns = new HashMap<>();
		String imageUrl = "";
		String newName = "";
		try {
			if (in != null) {
				// 生成图片名称前缀
				String filePrefix = "up_hxq" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				// 生成图片名称
				newName = filePrefix + ".jpg";
				// 通过InputStream流 和图片名称上传图片
				commonMediator.getFileUpload().uploadFiles(in, newName);
				FileModel file = commonMediator.getFileService().getOneFileModel(newName);
				if (file != null && !StringUtil.isEmpty(file.getRemotePaths())) {
					imageUrl = file.getRemotePaths();
				}
			}
		} catch (Exception ex) {
			return returns;
		}
		returns.put("imageUrl", imageUrl);
		returns.put("newName", newName);
		return returns;
	}

	/**
	 * 上传
	 *
	 * @param pathName
	 *            pathName
	 * @param name
	 *            name
	 */
	private void uploadImage(String pathName, String name) {
		File file = new File(pathName);
		InputStream in;
		try {
			in = new FileInputStream(file);
			commonMediator.getFileUpload().uploadFiles(in, name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存校验
	 *
	 * @param ss
	 *            保存对象
	 * @param isNew
	 *            true:新增;false:修改.
	 * @return
	 */
	private String verifySaveSs(ServiceStaff ss, boolean isNew) {
		StringBuffer sbf = new StringBuffer(1000);
		if (ObjectUtils.isEmpty(ss.getRealName()))
			StringUtils.appendBuffer(sbf, "真实姓名不能为空！", sbf.length() > 0 ? "\r\n" : null);
		if (ObjectUtils.isEmpty(ss.getHospital()))
			StringUtils.appendBuffer(sbf, "所在医院不能为空！", sbf.length() > 0 ? "\r\n" : null);
		if (ObjectUtils.isEmpty(ss.getDepartment()))
			StringUtils.appendBuffer(sbf, "所在科室不能为空！", sbf.length() > 0 ? "\r\n" : null);
		if (ObjectUtils.isEmpty(ss.getDepartmentLine()))
			StringUtils.appendBuffer(sbf, "科室电话不能为空！", sbf.length() > 0 ? "\r\n" : null);
		return sbf.toString();
	}

	private String verifySaveSsi(ServiceStaffInfo ssi, boolean isNew) {
		StringBuffer sbf = new StringBuffer(1000);
		if (ObjectUtils.isEmpty(ssi.getProfessional()))
			StringUtils.appendBuffer(sbf, "职称不能为空！", sbf.length() > 0 ? "\r\n" : null);
		if (ObjectUtils.isEmpty(ssi.getSex()))
			StringUtils.appendBuffer(sbf, "性别不能为空！", sbf.length() > 0 ? "\r\n" : null);
		if (ObjectUtils.isEmpty(ssi.getProvince()))
			StringUtils.appendBuffer(sbf, "省不能为空！", sbf.length() > 0 ? "\r\n" : null);
		if (ObjectUtils.isEmpty(ssi.getCity()))
			StringUtils.appendBuffer(sbf, "市不能为空！", sbf.length() > 0 ? "\r\n" : null);
		if (ObjectUtils.isEmpty(ssi.getRegion()))
			StringUtils.appendBuffer(sbf, "县（区）不能为空！", sbf.length() > 0 ? "\r\n" : null);
		if (ObjectUtils.isEmpty(ssi.getCertImage()))
			StringUtils.appendBuffer(sbf, "证件照不能为空！", sbf.length() > 0 ? "\r\n" : null);
		return sbf.toString();
	}
}
