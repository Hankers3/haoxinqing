package com.aebiz.b2b2c.cms.storeback.service.messageremind.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.messageremind.vo.AccountType;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindModel;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindQueryModel;
import com.aebiz.b2b2c.cms.remindsub.dao.RemindSubDAO;
import com.aebiz.b2b2c.cms.remindsub.vo.RemindSubModel;
import com.aebiz.b2b2c.cms.storeback.dao.messageremind.StoreMessageRemindDAO;
import com.aebiz.b2b2c.cms.storeback.service.messageremind.StoreMessageRemindService;
import com.aebiz.b2b2c.cms.storeback.web.messageremind.vo.StoreMessageRemind;

@Service
@Transactional
public class StoreMessageRemindServiceImpl extends BaseServiceImpl<MessageRemindModel,MessageRemindQueryModel> implements StoreMessageRemindService {
	
	/*注入商户消息DAO*/
	private StoreMessageRemindDAO myDao = null;
	
	/*注入会员消息设置DAO*/
	@Autowired
	private RemindSubDAO remindSubDAO;
	
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(StoreMessageRemindDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(MessageRemindModel m) {
		m.setUuid(us.getNextUuid("MessageRemind",10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(MessageRemindModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(MessageRemindModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	/**
	 * 根据会员uuid的到商户消息内容和商户消息设置
	 * @param customerUuid
	 * @return
	 */
	public List<StoreMessageRemind> getStoreMessageWebModelList(String storeUuid,String messageType) {
		
		//新建商户消息集合
		List<StoreMessageRemind> storeMessageReminds=new ArrayList<StoreMessageRemind>();
		
		//根据消息类型（卖家提醒，促销提醒，退款提醒）查询商户消息集合
		List<MessageRemindModel> messageRemindModels=myDao.getMessageRemindListByAccountType(AccountType.STORE.getValue(), messageType);
		
		if(messageRemindModels!=null && messageRemindModels.size()>0){
			
			for (int i = 0; i < messageRemindModels.size(); i++) {
				MessageRemindModel model=messageRemindModels.get(i);
				StoreMessageRemind storeMessageRemind=new StoreMessageRemind();
				storeMessageRemind.setRemindName(model.getRemindName());
				storeMessageRemind.setMessageUuid(model.getUuid());
				String messageUuid=model.getUuid();
				
				//查询该商户对应消息的设置记录
				RemindSubModel subModel=remindSubDAO.getRemindSubModelByMessageUuidAndAccountUuid(storeUuid, messageUuid);
				if(subModel!=null){
					storeMessageRemind.setRemindSubUuid(subModel.getUuid());
					storeMessageRemind.setMobileSend(subModel.getMobileSend());
					storeMessageRemind.setEmailSend(subModel.getEmailSend());
					storeMessageRemind.setInnerSend(subModel.getInnerSend());
				}
				storeMessageReminds.add(storeMessageRemind);
			}
		}
		if(storeMessageReminds!=null && storeMessageReminds.size()>0){
			return storeMessageReminds;
		}
		return null;
	}
}