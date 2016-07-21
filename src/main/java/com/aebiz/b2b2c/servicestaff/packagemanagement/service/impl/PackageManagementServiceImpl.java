package com.aebiz.b2b2c.servicestaff.packagemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.servicestaff.packagemanagement.dao.PackageManagementDAO;
import com.aebiz.b2b2c.servicestaff.packagemanagement.service.PackageManagementService;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementModel;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementQueryModel;

@Service
@Transactional
public class PackageManagementServiceImpl extends
		BaseServiceImpl<PackageManagementModel, PackageManagementQueryModel>
		implements PackageManagementService {
	private PackageManagementDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(PackageManagementDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	/**
	 * 添加套餐
	 */
	@Override
	public String create(PackageManagementModel m) {
		m.setUuid(us.getNextUuid("PackageManagement", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		m.setCreateTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	/**
	 * 更新套餐
	 */
	@Override
	public void update(PackageManagementModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 删除套餐
	 */
	@Override
	public void delete(PackageManagementModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据传入的套餐信息名判断套餐名是否已经存在
	 * 
	 * @param packageName
	 *            套餐名
	 * @return
	 */
	@Override
	public boolean checkPackageName(String packageName) {
		String flag = myDao.checkPackageName(packageName);
		if(flag.equals("1") ){
			return true;
		}
		return false;
	}
	
	/**
	 * 通过编号 获取套餐名
	 * @param uuid
	 * @return
	 */
	public String getPackageName(String uuid){
		return myDao.getPackageName(uuid);
	}
	/**
	 * 
	 * @Description: (根据医生的id获取医生的私人医生套餐)    
	 * @author XP  
	 * @param doctorUuid
	 * @return
	 * @date 2016-1-18 下午1:03:11
	 */
        @Override
        public List<PackageManagementModel> getPackageListByDoctorUuid(List<String> uuids) {
            return myDao.getPackageListByDoctorUuid(uuids);
        }

}