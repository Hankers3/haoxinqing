package com.aebiz.b2b2c.servicestaff.departmentinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.order.accountkey.service.AccountKeyService;
import com.aebiz.b2b2c.order.accountkey.vo.AccountKeyModel;
import com.aebiz.b2b2c.servicestaff.departmentinfo.service.DepartmentInfoService;
import com.aebiz.b2b2c.servicestaff.departmentinfo.dao.DepartmentInfoDAO;
import com.aebiz.b2b2c.servicestaff.departmentinfo.vo.DepartmentInfoModel;
import com.aebiz.b2b2c.servicestaff.departmentinfo.vo.DepartmentInfoQueryModel;

@Service
@Transactional
public class DepartmentInfoServiceImpl extends
		BaseServiceImpl<DepartmentInfoModel, DepartmentInfoQueryModel>
		implements DepartmentInfoService {
	// 编号类型订单
	public static final String NOTYPE_ORDER = "department";

	private DepartmentInfoDAO myDao = null;

	@Autowired
	private UuidService us;

	@Autowired
	private AccountKeyService accountKeyService;

	@Autowired
	public void setMyDao(DepartmentInfoDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(DepartmentInfoModel m) {
		m.setUuid(us.getNextUuid("DepartmentInfo", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setDepartmentId(this.createDepartMentNo());
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(DepartmentInfoModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(DepartmentInfoModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 检查科室名是否存在
	 * 
	 * @param departmentName
	 * @return
	 */
	@Override
	public boolean checkDepartmentName(String departmentName, String uuid) {

		return myDao.checkDepartmentName(departmentName, uuid);
	}

	/**
	 * 获取科室名
	 * 
	 * @param uuid
	 * @return
	 */
	@Override
	public String getDepartmentNameByUuid(String uuid) {
		return myDao.getDepartmentNameByUuid(uuid);
	}

	/**
	 * 获取uuid通过科室名
	 * 
	 * @param departmentUuid
	 * @return
	 */
	@Override
	public String getUuidByDepartmentName(String departmentUuid) {
		return myDao.getUuidByDepartmentName(departmentUuid);
	}

	/**
	 * 导入科室的Excel文件
	 * 
	 * @param departmentUuid
	 * @return
	 */
	@Override
	public List<Object> updloadExcel(MultipartFile[] files) {
		return myDao.updloadExcel(files);
	}

	/**
	 * 创建科室流水号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createDepartMentNo() {
		String returnNo = "";
		synchronized (NOTYPE_ORDER) {
			// 日期
			String dateStr = DateFormatHelper.getNowTimeStr().substring(0, 10);
			String date = dateStr.replace("-", "");
			int maxcount = getMaxCount();

			if (maxcount == 1000000)
				maxcount = 1;

			// 保存订单流水号
			AccountKeyModel accountKey = accountKeyService
					.getAccountKeyModelById(NOTYPE_ORDER);

			if (accountKey == null) {
				accountKey = new AccountKeyModel();
				accountKey.setId(NOTYPE_ORDER);
				accountKey.setValue("1");
				accountKeyService.create(accountKey);
			} else {
				accountKey.setValue(maxcount + "");
				accountKeyService.update(accountKey);
			}

			// 生成订单号
			String orderno = getId(maxcount + "", 4);
			returnNo = date + orderno;

			return returnNo;
		}
	}

	/**
	 * 返回最大数
	 * 
	 */
	private int getMaxCount() {
		try {
			AccountKeyModel key = accountKeyService
					.getAccountKeyModelById(NOTYPE_ORDER);
			if (key != null) {
				return Integer.parseInt(key.getValue()) + 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 1;
	}

	/**
	 * 获取定长流水号
	 * 
	 * @param sno
	 * @param length
	 * @return
	 */
	public static String getId(String sno, int length) {
		int zeronum = length - ((sno + "").length());

		String returnSno = "";

		for (int i = 0; i < zeronum; i++) {
			returnSno = returnSno + "0";
		}

		returnSno = returnSno + sno;

		return returnSno;
	}
}