package com.aebiz.b2b2c.vipclub.giftpackagecustomerrel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.vipclub.giftpackage.service.GiftPackageService;
import com.aebiz.b2b2c.vipclub.giftpackagecustomerrel.dao.GiftPackageCustomerRelDAO;
import com.aebiz.b2b2c.vipclub.giftpackagecustomerrel.service.GiftPackageCustomerRelService;
import com.aebiz.b2b2c.vipclub.giftpackagecustomerrel.vo.GiftPackageCustomerRelModel;
import com.aebiz.b2b2c.vipclub.giftpackagecustomerrel.vo.GiftPackageCustomerRelQueryModel;

@Service
@Transactional
public class GiftPackageCustomerRelServiceImpl
		extends
		BaseServiceImpl<GiftPackageCustomerRelModel, GiftPackageCustomerRelQueryModel>
		implements GiftPackageCustomerRelService {
	private GiftPackageCustomerRelDAO myDao = null;

	@Autowired
	private GiftPackageService giftPackageService;

	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(GiftPackageCustomerRelDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(GiftPackageCustomerRelModel m) {
		m.setUuid(us.getNextUuid("GiftPackageCustomerRel", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(GiftPackageCustomerRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(GiftPackageCustomerRelModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 给会员发送礼包
	 */
	@Override
	public void saveCustomerGiftPackageRel(String customerUuid,
			List<String> giftPackageUuidList) {
		for (String giftPackageUuid : giftPackageUuidList) {
			if (!StringUtil.isEmpty(giftPackageUuid)) {
				GiftPackageCustomerRelModel gpm = new GiftPackageCustomerRelModel();
				gpm.setCustomerUuid(customerUuid);
				gpm.setGiftPackageUuid(giftPackageUuid);
				this.create(gpm);
				// 给会员发送礼包成功之后需要将礼包数量减去1
				giftPackageService.reduceGiftPackageCount(giftPackageService
						.getByUuid(giftPackageUuid));
			}
			continue;
		}

	}
}