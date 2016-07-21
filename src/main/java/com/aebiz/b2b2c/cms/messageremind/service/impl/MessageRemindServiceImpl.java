package com.aebiz.b2b2c.cms.messageremind.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;

import com.aebiz.b2b2c.cms.messageremind.service.MessageRemindService;
import com.aebiz.b2b2c.cms.messageremind.dao.MessageRemindDAO;
import com.aebiz.b2b2c.cms.messageremind.vo.AccountType;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindModel;
import com.aebiz.b2b2c.cms.messageremind.vo.MessageRemindQueryModel;
import com.aebiz.b2b2c.cms.remindsub.dao.RemindSubDAO;
import com.aebiz.b2b2c.cms.remindsub.vo.RemindSubModel;
import com.aebiz.b2b2c.cms.usercenter.web.messageremind.vo.CustomerMessageRemind;
import com.aebiz.b2b2c.cms.usercenter.web.messageremind.vo.MessageRemindWebModel;

@Service
@Transactional
public class MessageRemindServiceImpl extends BaseServiceImpl<MessageRemindModel,MessageRemindQueryModel> implements MessageRemindService {
	private MessageRemindDAO myDao = null;
	
	/*注入会员消息设置DAO*/
	@Autowired
	private RemindSubDAO remindSubDAO;
	
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(MessageRemindDAO dao){
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
	 * 根据会员uuid的到会员消息内容和会员消息设置
	 * @param customerUuid
	 * @return
	 */
	public List<CustomerMessageRemind> getCoustomerMessageWebModelList(String customerUuid) {
		
		//新建该会员消息集合
		List<CustomerMessageRemind> customerMessageReminds=new ArrayList<CustomerMessageRemind>();
		
		//查询所有会员消息集合
		List<MessageRemindModel> messageRemindModels=myDao.getMessageRemindListByAccountType(AccountType.CUSTOMER.getValue());
		
		if(messageRemindModels!=null && messageRemindModels.size()>0){
			for (int i = 0; i < messageRemindModels.size(); i++) {
				MessageRemindModel model=messageRemindModels.get(i);
				CustomerMessageRemind customerMessageRemind=new CustomerMessageRemind();
				customerMessageRemind.setRemindName(model.getRemindName());
				customerMessageRemind.setMessageUuid(model.getUuid());
				String messageUuid=model.getUuid();
				
				//通过会员uuid查询和消息Uuid查询设置记录
				RemindSubModel subModel=remindSubDAO.getRemindSubModelByMessageUuidAndAccountUuid(customerUuid, messageUuid);
				if(subModel!=null){
					customerMessageRemind.setRemindSubUuid(subModel.getUuid());
					customerMessageRemind.setMobileSend(subModel.getMobileSend());
					customerMessageRemind.setEmailSend(subModel.getEmailSend());
					customerMessageRemind.setInnerSend(subModel.getInnerSend());
				}
				customerMessageReminds.add(customerMessageRemind);
			}
		}
		if(customerMessageReminds!=null && customerMessageReminds.size()>0){
			return customerMessageReminds;
		}
		return null;
	}
}