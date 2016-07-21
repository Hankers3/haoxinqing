package com.aebiz.b2b2c.cms.storeback.service.innermessage.impl;

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
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageQueryModel;
import com.aebiz.b2b2c.cms.storeback.dao.innermessaage.StoreInnerMessageDAO;
import com.aebiz.b2b2c.cms.storeback.service.innermessage.StoreInnerMessageService;
import com.aebiz.b2b2c.store.storecomplex.vo.StoreComplexModel;
import com.aebiz.b2b2c.store.sysback.service.StoreComplexInteractive;

@Service
@Transactional
public class StoreInnerMessageServiceImpl extends
		BaseServiceImpl<InnerMessageModel, InnerMessageQueryModel> implements
		StoreInnerMessageService {
	private StoreInnerMessageDAO innerMessageDAO = null;
	@Autowired
	private UuidService us;

	@Autowired
	private StoreComplexInteractive storeComplexInteractive;

	@Autowired
	public void setMyDao(StoreInnerMessageDAO dao) {
		this.innerMessageDAO = dao;
		super.setDao(dao);
	}

	@Override
	public String create(InnerMessageModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		// 设置站内信发送时间
		m.setSendTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(InnerMessageModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		// 更新为已读状态
		m.setReadStatus(InnerMessageModel.READ_STATUS_READ);

		super.update(m);
	}

	@Override
	public void delete(InnerMessageModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 发送站内信
	 */
	@Override
	public void saveInnerMessage(String receiveUser, String title,
			String content) {
		InnerMessageModel innerMessage = new InnerMessageModel();
		innerMessage.setReceiveUser(receiveUser);
		innerMessage.setTitle(title);
		innerMessage.setContent(content);

		String userUuid = LoginUserHelper.getStoreLoginUserUuid();
		StoreComplexModel storeComplex = this.storeComplexInteractive
				.getStoreComplexModel(userUuid);
		if (storeComplex != null) {
			innerMessage.setUserImage(storeComplex.getStoreCompanyInfo()
					.getLogoPath());
		}
		// 设置发信人为当前用户
		innerMessage.setSendUser(userUuid);
		// 设置消息为未读状态
		this.create(innerMessage);
	}

	/**
	 * 删除站内信
	 */
	@Override
	public void deleteInnerMessage(List<String> needDeleteUuids, String type) {
		if (needDeleteUuids != null && needDeleteUuids.size() > 0) {
			for (String deleteUuid : needDeleteUuids) {
				if (!StringUtil.isEmpty(deleteUuid)) {
					InnerMessageModel deleteMessage = super
							.getByUuid(deleteUuid);

					if (type.equals("sx")) {
						deleteMessage.setSjDelStatus("1");
					} else if (type.endsWith("fx")) {
						deleteMessage.setFjDelStatus("1");
					}
					super.update(deleteMessage);
				}
			}
		}
	}

	/**
	 * 根据消息类别获取我的消息列表
	 */
	@Override
	public List<InnerMessageModel> getInnerMessageListByType(String type) {

		return this.innerMessageDAO.getInnerMessageListByType(type);
	}

}