package com.hxq.mobile.doctor.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.hxq.mobile.common.mapper.DepartmentInfoMapper;
import com.hxq.mobile.common.mapper.HospitalInfoMapper;
import com.hxq.mobile.common.service.BasicDataService;
import com.hxq.mobile.doctor.common.mapper.DoctorRightMapper;
import com.hxq.mobile.doctor.common.mapper.ServiceStaffInfoMapper;
import com.hxq.mobile.doctor.common.mapper.ServiceStaffMapper;
import com.hxq.mobile.doctor.common.mapper.StaffLoginStatusMapper;
import com.hxq.mobile.doctor.common.mapper.VipclubIntegralLogMapper;
import com.hxq.mobile.doctor.common.service.DoctorCommonService;
import com.hxq.mobile.doctor.visit.service.DoctorCustomerService;
import com.hxq.mobile.doctor.visit.service.VisitRecordService;
import com.hxq.mobile.entity.common.BasicdataAreaCity;
import com.hxq.mobile.entity.common.BasicdataAreaProvince;
import com.hxq.mobile.entity.common.BasicdataAreaRegion;
import com.hxq.mobile.entity.common.DepartmentInfo;
import com.hxq.mobile.entity.common.DoctorRight;
import com.hxq.mobile.entity.common.HospitalInfo;
import com.hxq.mobile.entity.common.ServiceStaff;
import com.hxq.mobile.entity.common.ServiceStaffInfo;
import com.hxq.mobile.entity.common.StaffLoginStatus;
import com.hxq.mobile.entity.common.VipclubIntegralLog;
import com.hxq.mobile.support.service.ImgUploadService;
import com.hxq.mobile.util.CompatibleTools;
import com.hxq.mobile.util.Image4App;
import com.hxq.mobile.util.api.ApiCode;
import com.hxq.mobile.util.api.ApiResult;
import com.hxq.mobile.util.repository.MybatisSimpleEntityService;
import com.hxq.mobile.util.repository.SimpleEntityDao;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;

/**
 * Created by Alice on 2016/4/26 0026.
 */
@Service("com.hxq.mobile.doctor.common.service.impl.DoctorCommonServiceImpl")
public class DoctorCommonServiceImpl extends MybatisSimpleEntityService implements DoctorCommonService {
    @Override
    protected SimpleEntityDao getDao() {
        return null;
    }

    @Override
    protected String getCacheName() {return "DoctorCommonService";}

    @Autowired
    private ServiceStaffMapper serviceStaffMapper;
    @Autowired
    private ServiceStaffInfoMapper serviceStaffInfoMapper;
    @Autowired
    private VipclubIntegralLogMapper vipclubIntegralLogMapper;
    @Autowired
    private DoctorRightMapper doctorRightMapper;
    @Autowired
    private StaffLoginStatusMapper staffLoginStatusMapper;
    @Autowired
    private HospitalInfoMapper hospitalInfoMapper;
    @Autowired
    private DepartmentInfoMapper departmentInfoMapper;
    @Resource(name = "com.hxq.mobile.support.service.ImgUploadService")
    private ImgUploadService imgUploadService;
    @Autowired
    private FileService fileService;
    @Resource(name = "com.hxq.mobile.doctor.visit.service.DoctorCustomerService")
    private DoctorCustomerService doctorCustomerService;
    @Resource(name = "com.hxq.mobile.common.service.BasicDataService")
    private BasicDataService basicDataService;
    @Resource(name = "com.hxq.mobile.doctor.visit.service.VisitRecordService")
    private VisitRecordService visitRecordService;

    @Override
    public ServiceStaff registration_v2(Map<String, String> map) throws Exception {
        ServiceStaff ss = serviceStaffMapper.selectByMobile(map.get("mobile"));
        if (!ObjectUtils.isEmpty(ss)) return null;

        //不存在或者注册状态为0，创建新用户
        ss = new ServiceStaff(IdentityHelper.uuid2());
        ss.setMobile(map.get("mobile"));// 手机号码
        ss.setPassword(map.get("password"));// 密码
        ss.setOpeTime(DateFormatHelper.getNowTimeStr());// 创建时间
        ss.setCreateTime(DateFormatHelper.getNowTimeStr());
        ss.setFrozenSate("0");// 冻结状态
        ss.setAccountAmount(0.0d);
        ss.setAvailableAmount(0.0d);
        //注册时,姓名设置为空
        ss.setRealName("");// 设置姓名为手机号
        ss.setActiveCode(map.get("invite"));
        ss.setCreateTime(DateFormatHelper.getNowTimeStr());
        ss.setOpeTime(DateFormatHelper.getNowTimeStr());
        ss.setSate("0");// 审核认证未审核
        ss.setRegState("1");
        ss.setDoctorType("2"); //普通医生 ,1 名医风采
        int success = serviceStaffMapper.insert(ss);
        return success < 0 ? null : ss;
    }

    @Override
    public ApiResult gotoLogin_v2(HttpServletRequest request, String mobile, String password) throws Exception {

       	ServiceStaff ss = serviceStaffMapper.selectByMobileAndPassword(mobile, password);
        if (ObjectUtils.isEmpty(ss)) {
            return ApiResult.error(ApiCode.BAD_REQUEST, "用户不存在或密码错误！");
        }
        if ("1".equals(ss.getFrozenSate())) {
            return ApiResult.error(ApiCode.BAD_REQUEST, "医生已被冻结，登录失败！");
        }
        if ("0".equals(ss.getRegState())) {
            return ApiResult.error(ApiCode.BAD_REQUEST, "医生注册未成功，登录失败！");
        }
        request.getSession().setAttribute("servicestaffModel", ss);

        Map<Object, Object> mapSS = new HashMap<Object, Object>();// 组装返回到客户端的医生信息
        // 医生状态 审核状态：0待审核/未认证； 1审核通过/已认证； 2未通过审核/未通过认证；3认证中
        mapSS.put("sate", ss.getSate());
        int customerNumModel = doctorCustomerService.selectCustomerNumModel(ss.getUuid());// 获取患者数量
        Map<String, Object> visitNumModel = visitRecordService.countVisitRecordByDoctorUuid(ss.getUuid());// 随访次数
        mapSS.put("doctorUuid", ss.getUuid());// 医生uuid
        mapSS.put("doctorName", ss.getRealName());// 医生名称
        mapSS.put("income", ss.getAccountAmount());// 医生总收入
        HospitalInfo hospitalInfo = hospitalInfoMapper.selectByPrimaryKey(ss.getHospital());// 医院名称
        mapSS.put("hospital", hospitalInfo != null ? hospitalInfo.getHospitalName() : null);
        DepartmentInfo departmentInfo = departmentInfoMapper.selectByPrimaryKey(ss.getDepartment());// 科室名称
        mapSS.put("department", departmentInfo != null ? departmentInfo.getDepartmentName() : null);
        mapSS.put("departmentLine", ss.getDepartmentLine());// 科室电话
        mapSS.put("visitNum", visitNumModel.get("visitNum"));// 医生随访次数
        mapSS.put("customerNum", customerNumModel);// 患者数

        // 获取医生基础信息
        ServiceStaffInfo ssinfo = serviceStaffInfoMapper.selectByDoctorUuid(ss.getUuid());
        if(ssinfo != null) {
	        mapSS.put("sex", ssinfo.getSex());
	        mapSS.put("professional", ssinfo.getProfessional());// 主治医师 职称
	        // 医生头像
	        Image4App[] urls = CompatibleTools.getImages(imgUploadService, fileService, ssinfo.getImage());
	        if(!ObjectUtils.isEmpty(urls)) mapSS.put("doctorIcon", urls[0]);
	        mapSS.put("synopsis", ssinfo.getSynopsis());// 医生的简介
	        mapSS.put("address", ssinfo.getAddress());

	        BasicdataAreaProvince provinceModel = (BasicdataAreaProvince) basicDataService.select(new BasicdataAreaProvince(ssinfo.getProvince()));
	        mapSS.put("province", provinceModel != null ? provinceModel.getProvinceName() : null);// 省
	        BasicdataAreaCity cityModel = (BasicdataAreaCity) basicDataService.select(new BasicdataAreaCity(ssinfo.getCity()));
	        mapSS.put("city", cityModel != null ? cityModel.getCityName() : null);//市
	        BasicdataAreaRegion regionModel = (BasicdataAreaRegion) basicDataService.select(new BasicdataAreaRegion(ssinfo.getRegion()));
	        mapSS.put("region", regionModel != null ? regionModel.getRegionName() : null);//县（区）
	        mapSS.put("territory", ssinfo.getTerritory());// 擅长疾病 擅长领域
	        mapSS.put("synopsis", ssinfo.getSynopsis());// 个人简介
        }

        // 通过医生编号获取该医生的是否登录信息
        StaffLoginStatus staffLoginStatus = staffLoginStatusMapper.selectByDoctorUuid(ss.getUuid());

        // 如果是第一次登录，没有该医生的登录信息，则创建登录信息
        if (staffLoginStatus == null) {
            staffLoginStatus = new StaffLoginStatus();
            staffLoginStatus.setUuid(IdentityHelper.uuid2());
            staffLoginStatus.setServiceStaffUuid(ss.getUuid());// 医生编号
            staffLoginStatus.setCreateTime(DateFormatHelper.getNowTimeStr());// 创建时间
            staffLoginStatus.setLastLoginTime(DateFormatHelper.getNowTimeStr());// 上次登录时间，因为第一次登录，此时间是本次登录时间
            staffLoginStatus.setTheLoginTime(DateFormatHelper.getNowTimeStr());// 本次登录时间
            staffLoginStatus.setStatus("1");// 登录状态
            staffLoginStatusMapper.insertSelective(staffLoginStatus);
        } else {
            // 本次登录时间, 不是第一次登录，更新本次登录时间
            staffLoginStatus.setTheLoginTime(DateFormatHelper.getNowTimeStr());
            staffLoginStatus.setStatus("1");// 登录状态
            staffLoginStatusMapper.updateByPrimaryKeySelective(staffLoginStatus);
        }
        return ApiResult.right(mapSS);
    }

    @Override
    public ApiResult registerTwo_v2(Map<String, String> map) throws Exception {
        //更新医生信息
        ServiceStaff serviceStaff = new ServiceStaff();// 手机号码
        serviceStaff.setUuid(map.get("doctorUuid"));
        serviceStaff.setRealName(map.get("doctorName"));
        serviceStaff.setEmail(map.get("email"));
        int success = serviceStaffMapper.updateByPrimaryKeySelective(serviceStaff);
        if (success <= 0) {
            return ApiResult.error(ApiCode.SERVER_ERROR, "更新医生信息失败！");
        }
        //更新医生基础信息
        //查找是否有基础信息
        ServiceStaffInfo serviceStaffInfo = serviceStaffInfoMapper.selectByDoctorUuid(map.get("doctorUuid"));
        if (!ObjectUtils.isEmpty(serviceStaffInfo)) {
            serviceStaffInfo.setSex(map.get("sex"));
            serviceStaffInfo.setImage(map.get("icon"));
            serviceStaffInfoMapper.updateByPrimaryKeySelective(serviceStaffInfo);
            if (success <= 0) {
                return ApiResult.error(ApiCode.SERVER_ERROR, "更新医生基础信息失败！");
            }
        } else {
            serviceStaffInfo = new ServiceStaffInfo();
            serviceStaffInfo.setUuid(IdentityHelper.uuid2());
            serviceStaffInfo.setServiceStaffUuid(serviceStaff.getUuid());
            serviceStaffInfo.setOpeTime(DateFormatHelper.getNowTimeStr());
            serviceStaffInfo.setSex(map.get("sex"));
            serviceStaffInfo.setImage(map.get("icon"));
            success = serviceStaffInfoMapper.insertSelective(serviceStaffInfo);
            if (success <= 0) {
                return ApiResult.error(ApiCode.SERVER_ERROR, "创建医生基本信息失败！");
            }
        }
        //返回id
        map.remove("icon");
        map.remove("sex");
        map.remove("doctorName");
        map.remove("email");
        return ApiResult.right(map);
    }

    @Override
    public ApiResult registerThree_v2(HttpServletRequest request) throws Exception {
        String doctorUuid = request.getParameter("doctorUuid");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String area = request.getParameter("area");
        String infirmary = request.getParameter("infirmary");
        String departments = request.getParameter("departments");
        String speciality = request.getParameter("speciality");
        String professional = request.getParameter("professional");
        String synopsis = request.getParameter("synopsis");
        String telephone = request.getParameter("telephone");
        //查找医生信息，更新注册状态为1，表示注册成功
        ServiceStaff serviceStaff = serviceStaffMapper.selectByPrimaryKey(doctorUuid);
        int success;
        if (!ObjectUtils.isEmpty(serviceStaff)) {
            serviceStaff.setRegState("1");// 注册状态改为1：成功
            serviceStaff.setSate("0");
            serviceStaff.setHospital(infirmary);// 医院
            if ("1".equals(infirmary)) {
                serviceStaff.setStatus(request.getParameter("otherhospital"));
            }
            serviceStaff.setDepartment(departments);// 科室
            serviceStaff.setDepartmentLine(telephone);// 科室电话
            success = serviceStaffMapper.updateByPrimaryKeySelective(serviceStaff);
            if (success <= 0) {
                return ApiResult.error(ApiCode.SERVER_ERROR, "更新医生信息失败！");
            }
            //更新医生基础信息
            ServiceStaffInfo serviceStaffInfo = serviceStaffInfoMapper.selectByDoctorUuid(doctorUuid);
            if (!ObjectUtils.isEmpty(serviceStaffInfo)) {
                serviceStaffInfo.setProvince(province);// 省
                serviceStaffInfo.setCity(city);// 市
                serviceStaffInfo.setRegion(area);// 区县
                serviceStaffInfo.setProfessional(professional);
                serviceStaffInfo.setSynopsis(synopsis);
                serviceStaffInfo.setTerritory(speciality);// 特长
                success = serviceStaffInfoMapper.updateByPrimaryKeySelective(serviceStaffInfo);
                if (success <= 0) {
                    return ApiResult.error(ApiCode.SERVER_ERROR, "更新医生基础信息失败！");
                }
            }
            //更新用户积分
            VipclubIntegralLog vipclubIntegralLog = new VipclubIntegralLog();
            vipclubIntegralLog.setCustomerUuid(doctorUuid);
            vipclubIntegralLog.setUserType("2");
            vipclubIntegralLog.setIntergralType("4");

            vipclubIntegralLog = vipclubIntegralLogMapper.selectSelective(vipclubIntegralLog);
            if (ObjectUtils.isEmpty(vipclubIntegralLog) || ObjectUtils.isEmpty(vipclubIntegralLog.getUuid())) {
                vipclubIntegralLog = new VipclubIntegralLog();
                vipclubIntegralLog.setUuid(IdentityHelper.uuid2());
                vipclubIntegralLog.setCustomerUuid(doctorUuid);
                vipclubIntegralLog.setOpeTime(DateFormatHelper.getNowTimeStr());
                vipclubIntegralLog.setUserType("2");
                vipclubIntegralLog.setIntergralType("4");
                vipclubIntegralLog.setDescription("完善个人信息");// 保存描述
                vipclubIntegralLog.setIntergralCount(10);// 设置会员积分日志记录的积分数量
                vipclubIntegralLogMapper.insertSelective(vipclubIntegralLog);
            }
            //更新医生权限
            DoctorRight doctorRight = doctorRightMapper.selectByDoctorUuid(doctorUuid);

            if (ObjectUtils.isEmpty(doctorRight) || ObjectUtils.isEmpty(doctorRight.getDoctorUuid())) {
                doctorRight = new DoctorRight();
                doctorRight.setUuid(IdentityHelper.uuid2());
                doctorRight.setPlus("0");// 加号预约 0：未开通 1：开通 
                doctorRight.setDisturb("0");// 免打扰 0：未开通 1：开通
                doctorRight.setTeletext("1");// 图文咨询 默认开通
                doctorRight.setPhone("0");// 电话咨询 0：未开通 1：开通
                doctorRight.setTelDisturb("0");// 图文免打扰 0：未开通 1：开通
                doctorRight.setPersonal("0");// 私人医生 0：未开通 1：开通
                doctorRight.setExam("0");// 是否开通审核 0：未开通 1：开通
                doctorRight.setPersonalExam("0");// 私人医生托管审核 0：未开通 1：开通
                doctorRight.setOpeTime(DateFormatHelper.getNowTimeStr());
                doctorRight.setDoctorUuid(doctorUuid);
                doctorRightMapper.insertSelective(doctorRight);// 创建
            }
            //返回手机号与mobile
            Map<String, Object> result = new HashMap<>();
            result.put("mobile", serviceStaff.getMobile());
            result.put("doctorUuid", serviceStaff.getUuid());
            return ApiResult.right(result);
        } else {
            return ApiResult.error(ApiCode.BAD_REQUEST, "未能找到医生信息！");
        }
    }
}