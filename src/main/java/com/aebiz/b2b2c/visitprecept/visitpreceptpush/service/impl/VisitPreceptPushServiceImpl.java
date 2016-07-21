package com.aebiz.b2b2c.visitprecept.visitpreceptpush.service.impl;

import java.util.List;

import com.wxcommon.util.IdentityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.basebusiness.utils.DateUtil;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.cms.innermessage.service.InnerMessageService;
import com.aebiz.b2b2c.cms.innermessage.vo.InnerMessageModel;
import com.aebiz.b2b2c.customermgr.mobile.web.push.JPushService;
import com.aebiz.b2b2c.visitprecept.visitpreceptpush.dao.VisitPreceptPushDAO;
import com.aebiz.b2b2c.visitprecept.visitpreceptpush.service.VisitPreceptPushService;
import com.aebiz.b2b2c.visitprecept.visitpreceptpush.vo.VisitPreceptPushModel;
import com.aebiz.b2b2c.visitprecept.visitpreceptpush.vo.VisitPreceptPushQueryModel;

@Service
@Transactional
public class VisitPreceptPushServiceImpl extends BaseServiceImpl<VisitPreceptPushModel, VisitPreceptPushQueryModel>
		implements VisitPreceptPushService {
	private VisitPreceptPushDAO myDao = null;
	@Autowired
	private UuidService us;
	
	/*消息service*/
	@Autowired
	private InnerMessageService inmessageService;
	
	@Autowired
	public void setMyDao(VisitPreceptPushDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(VisitPreceptPushModel m) {
		m.setUuid(IdentityHelper.uuid2());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(VisitPreceptPushModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(VisitPreceptPushModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 随访按周期给患者 进行消息推送
	 */
	@Override
	public void pushVisitMeaasge() {
		List<VisitPreceptPushModel> list = this.getAll();
		if(list !=null && list.size()>0){
			for(VisitPreceptPushModel vp:list){
				//上次推送时间
				String pushTime = vp.getPushTime();
				//周期
				String period = vp.getPeriod();
				if(!StringUtil.isEmpty(pushTime)&& !StringUtil.isEmpty(period)){
					//获取指定周期的时间
					int week= Integer.parseInt(period);
					String weekTime ="";
					if(week >0){
						 weekTime = DateUtil.getSpecifyWeek(pushTime, week);
					}else{
						weekTime =DateFormatHelper.getNowTimeStr().substring(0, 10);
					}
					String nowTime = DateFormatHelper.getNowTimeStr();
					String customerUuid= vp.getCustomerUuid();
					String doctorUuid= vp.getDoctorUuid();
					//获取是否当天时期
					boolean flag = nowTime.contains(weekTime);
					if(flag){
						//推送消息
						inmessageService.saveInnerMessageAndPush(doctorUuid, customerUuid, "请填写随访表单，祝您身体健康", InnerMessageModel.ACCOUNT_TYPE_CUSTOMER, InnerMessageModel.ACCOUNT_TYPE_STORE,
								JPushService.USER_CUSTOMER, "receptPushUuid", vp.getUuid(), "");
						//修改推送时间与推送次数
						vp.setPushTime(nowTime);
						vp.setPushTimes(vp.getPushTimes()+1);
						//hibernate 框架实现的,调用父类的update方法,用泛型实现
						this.update(vp);
					}
				}
			}
		}
	}

	/**
	 * 通过随访id得到对象
	 * 
	 * @param visitPreceptUuid
	 * @return
	 */
	@Override
	public VisitPreceptPushModel getByVisitPreceptUuid(String doctorUuid,String customerUuid) {
		return myDao.getByVisitPreceptUuid(doctorUuid,customerUuid);
	}
		
}