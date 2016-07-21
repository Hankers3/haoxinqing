package com.aebiz.b2b2c.customermgr.consultrecord.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordModel;
import com.aebiz.b2b2c.customermgr.consultrecord.vo.ConsultRecordQueryModel;
import com.wxcommon.util.ObjectUtils;

@Repository
public class ConsultRecordH4Impl extends BaseH4Impl<ConsultRecordModel, ConsultRecordQueryModel>
		implements ConsultRecordDAO {

	protected String getMultiModel(ConsultRecordQueryModel qm) {
	        if(!StringUtil.isEmpty(qm.getDoctorName())||!StringUtil.isEmpty(qm.getDoctorMobileq())){
	           qm.setDoctorUuid("1");
	            }
		if (StringUtil.isEmpty(qm.getDoctorUuid())) {
			return " , CustomerModel as c where o.customerUuid = c.uuid ";
		} else {
			return " , CustomerModel as c , ServicestaffModel as s where  o.customerUuid = c.uuid and o.doctorUuid = s.uuid  ";
		}
	}

	@Override
	protected String getAppendHql(ConsultRecordQueryModel qm) {
		// 连接条件
		StringBuffer hql = new StringBuffer("");
		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			hql.append(" and c.customerName like:customerName");
		}
		if (!StringUtil.isEmpty(qm.getCustomerMobileq())) {
			hql.append(" and c.mobile like:customerMobileq");
		}

		if (!StringUtil.isEmpty(qm.getDoctorName())) {
			hql.append(" and s.realName like:docoterName");
		}
		if (!StringUtil.isEmpty(qm.getDoctorMobileq())) {
			hql.append(" and s.mobile like:doctorMobileq");
		}

		if (qm != null) {
                    hql.append(" order by o.").append(qm.getSortName()).append(" ").append(qm.getSortType());
		} else {
                    hql.append(" order by o.createTime desc");
		}
		
		return hql.toString();
	}
	


	@Override
	protected void setAppendHqlValue(ConsultRecordQueryModel qm, Query q) {
		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			q.setString("customerName", "%" + qm.getCustomerName() + "%");
		}

		if (!StringUtil.isEmpty(qm.getCustomerMobileq())) {
			q.setString("customerMobileq", "%" + qm.getCustomerMobileq() + "%");
		}

		if (!StringUtil.isEmpty(qm.getDoctorName())) {
			q.setString("docoterName", "%" + qm.getDoctorName() + "%");
		}

		if (!StringUtil.isEmpty(qm.getDoctorMobileq())) {
			q.setString("doctorMobileq", "%" + qm.getDoctorMobileq() + "%");
		}

	}

	// 重写父类的方法自己拼写sql语句
	private List getByCondition(boolean onlyUuids, boolean needPage, ConsultRecordQueryModel qm, int start,
			int pageShow) {
		String hql = "select o " + getMultiSelect() + " from  ConsultRecordModel  o  " + getMultiModel(qm);

		hql = hql + prepareHql(qm);
		hql = hql + getAppendHql(qm);

		Query q = getH4Session().createQuery(hql);
		setValue(qm, q);
		setAppendHqlValue(qm, q);

		if (needPage) {
			q.setFirstResult(start);
			q.setMaxResults(pageShow);
		}

		if (onlyUuids) {
			return q.list();
		}

		if ((getMultiSelect() != null) && (getMultiSelect().trim().length() > 0)) {
			List tempList = q.list();
			return exeResultList(tempList);
		}
		List retList = q.list();
		return retList;
	}

	public int getCount(ConsultRecordQueryModel qm) {
		
		StringBuffer hqlBuff = new StringBuffer("");
//		StringBuffer hqlBuff = new StringBuffer("select count(t.uuid) from (");
		hqlBuff.append(" select count(o.uuid) as uuid from  ConsultRecordModel  o ");
		hqlBuff.append(getMultiModel(qm));
		hqlBuff.append(prepareHql(qm));
		hqlBuff.append(getAppendHql(qm));
//		hqlBuff.append(") t ");
		


		Query q = getH4Session().createQuery(hqlBuff.toString());
		setValue(qm, q);
		setAppendHqlValue(qm, q);

		Object obj = q.uniqueResult();
		if (obj != null) {
			return ((Number) obj).intValue();
		}

		return 0;
	}
	

	

	/**
	 * 获取未处理消息数量接口
	 * 
	 * @return
	 */
	@Override
	public int getMissionCenterCount(String typeOnline, String doctorUuid) {
		String hql = "select count(uuid) from ConsultRecordModel crm where crm.consultType=:type and crm.dealState=:dealState and crm.doctorUuid=:doctorUuid ";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("type", typeOnline);
		query.setString("dealState", "0"); // 查看未处理的
		query.setString("doctorUuid", doctorUuid);
		Number obj = (Number) query.uniqueResult();
		if (obj != null) {
			return obj.intValue();
		}
		return 0;
	}

	/**
	 * 返回未回复消息数量
	 * 
	 * @return
	 */
	@Override
	public int getReplyCount(String typeOnline, String doctorUuid) {
		StringBuffer hql = new StringBuffer( "select count(crm.uuid) from ConsultRecordModel crm where crm.consultType=:type  ");
		hql.append(" and crm.reply=:reply and crm.doctorUuid=:doctorUuid ");
		//hql.append(" group by crm.customerUuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("type", typeOnline);
		query.setString("reply", "0"); // 查看未回复的
		query.setString("doctorUuid", doctorUuid);
		Number obj = (Number) query.uniqueResult();
		if (obj != null) {
			return obj.intValue();
		}
		return 0;
	}

	/**
	 * 根据消息类别获取消息列表
	 * 
	 * @param messageType
	 */
	@Override
	public List<ConsultRecordModel> getInnerMessageListByMessageType(String messageType, String doctorUuid,String reply) {
		StringBuffer hql = new StringBuffer(
				"from ConsultRecordModel crm where crm.consultType=:type and crm.doctorUuid=:doctorUuid  ");
		
		//预约加号  未处理 已处理 已完成
		//已处理包括 审核通过 和审核不通过
		if(messageType.equals(ConsultRecordModel.TYPE_ORDER)){
			if(!StringUtil.isEmpty(reply)&&"1".equals(reply)){
				hql.append(" and ( crm.dealState=:reply or crm.dealState=:reply1 ) ");
			}else{
				hql.append(" and crm.dealState=:reply ");
			}
		}else{
			//在线咨询 回复 未回复
			if(!StringUtil.isEmpty(reply)){
				hql.append(" and crm.reply=:reply");
			}
		}
		
		hql.append(" group by crm.customerUuid ");
		hql.append(" order by crm.createTime desc ");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("type", messageType);
		query.setString("doctorUuid", doctorUuid);
		//加号已处理包括 审核通过 和审核不通过 
		if(!StringUtil.isEmpty(reply)&&"1".equals(reply)){
			if(messageType.equals(ConsultRecordModel.TYPE_ORDER)){
				query.setString("reply", "1");
				query.setString("reply1", "2");
			}else{
				query.setString("reply", reply);
			}
		}else{
			query.setString("reply", reply);
		}
		
		List<ConsultRecordModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	/**
	 * 获取患者在该医生下咨询Uuid
	 * @param customerUuid
	 * @param doctorUuid
	 * @param type
	 * @return
	 */
	public List<String> getConsultRecordUuids(String messageType,String doctorUuid,String reply){
		StringBuffer hql = new StringBuffer(
				"select distinct(crm.customerUuid) from ConsultRecordModel crm where crm.consultType=:type and crm.doctorUuid=:doctorUuid  ");
		//预约加号  未处理 已处理 已完成
		//已处理包括 审核通过 和审核不通过
		if(messageType.equals(ConsultRecordModel.TYPE_ORDER)){
			if(!StringUtil.isEmpty(reply)&&"1".equals(reply)){
				hql.append(" and ( crm.dealState=:reply or crm.dealState=:reply1 ) ");
			}else{
				hql.append(" and crm.dealState=:reply ");
			}
		}else{
			//在线咨询 回复 未回复
			if(!StringUtil.isEmpty(reply)){
				hql.append(" and crm.reply=:reply");
			}
		}
		hql.append(" order by crm.createTime desc ");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("type", messageType);
		query.setString("doctorUuid", doctorUuid);
		//加号已处理包括 审核通过 和审核不通过 
		if(!StringUtil.isEmpty(reply)&&"1".equals(reply)){
			if(messageType.equals(ConsultRecordModel.TYPE_ORDER)){
				query.setString("reply", "1");
				query.setString("reply1", "2");
			}else{
				query.setString("reply", reply);
			}
		}else{
			query.setString("reply", reply);
		}
		
		List<String> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
		
	}
	
	public ConsultRecordModel getConsultRecord(String messageType, String doctorUuid,String reply,String customerUuid){
		StringBuffer hql = new StringBuffer(
				" from ConsultRecordModel crm where crm.consultType=:type and crm.doctorUuid=:doctorUuid  ");
		//预约加号  未处理 已处理 已完成
		hql.append(" and crm.customerUuid=:customerUuid ");

		//已处理包括 审核通过 和审核不通过
		if(messageType.equals(ConsultRecordModel.TYPE_ORDER)){
			if(!StringUtil.isEmpty(reply)&&"1".equals(reply)){
				hql.append(" and ( crm.dealState=:reply or crm.dealState=:reply1 ) ");
			}else{
				hql.append(" and crm.dealState=:reply ");
			}
		}else{
			//在线咨询 回复 未回复
			if(!StringUtil.isEmpty(reply)){
				hql.append(" and crm.reply=:reply");
			}
		}
		hql.append(" order by crm.createTime desc ");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("type", messageType);
		query.setString("doctorUuid", doctorUuid);
		query.setString("customerUuid", customerUuid);

		//加号已处理包括 审核通过 和审核不通过 
		if(!StringUtil.isEmpty(reply)&&"1".equals(reply)){
			if(messageType.equals(ConsultRecordModel.TYPE_ORDER)){
				query.setString("reply", "1");
				query.setString("reply1", "2");
			}else{
				query.setString("reply", reply);
			}
		}else{
			query.setString("reply", reply);
		}
		
		List<ConsultRecordModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
		
	}


	/**
	 * 根据咨询类别获取用户编号
	 * 
	 * @param messageType
	 */
	@Override
	public List<String> getCustomerUuidByType(String type) {
		StringBuffer hql = new StringBuffer(
				" select crm.customerUuid  from ConsultRecordModel crm where crm.consultType=:type order by crm.createTime desc");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("type", type);
		List<String> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 通过患者id得到ConsultRecordModel的List
	 * 
	 * @param uuid
	 * @param type
	 *            咨询类型 0在线咨询即图文咨询 1电话咨询 2预约加号
	 * @return
	 */
	@Override
	public List<ConsultRecordModel> getByCustomerUuid(String uuid, String type) {
		StringBuffer hql = new StringBuffer(" select  crm from ConsultRecordModel crm , ServicestaffModel  as s where crm.doctorUuid = s.uuid and  crm.consultType=:type and crm.customerUuid=:customerUuid  " +
				" group by crm.doctorUuid order by crm.createTime desc");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("type", type);
		query.setString("customerUuid", uuid);
		List<ConsultRecordModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 获取医生的图文咨询总量
	 * 
	 * @param doctorId
	 * @return
	 */
	@Override
	public int getConsultNumBydoctorId(String doctorId) {
		StringBuffer hql = new StringBuffer(
				"select count(distinct o.uuid)from ConsultRecordModel o , ServicestaffModel  as s where  o.doctorUuid = s.uuid and  o.consultType=:type2  "
						+ " and o.doctorUuid =:doctorId ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("type2", "0");
		query.setString("doctorId", doctorId);
		return ((Number) query.uniqueResult()).intValue();
	}

	/**
	 * 获取医生的预约加号咨询总量
	 * 
	 * @param doctorId
	 * @return
	 */
	@Override
	public int getAllOrderNumByDoctorId(String doctorId) {
		StringBuffer hql = new StringBuffer(
				"select count(distinct o.uuid)from ConsultRecordModel o, ServicestaffModel  as s where  o.doctorUuid = s.uuid and  o.consultType=:type2  "
						+ " and o.doctorUuid =:doctorId ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("type2", "2");
		query.setString("doctorId", doctorId);
		return ((Number) query.uniqueResult()).intValue();
	}

	/**
	 * 得到已完成的电话咨询数量
	 * 
	 * @param uuid
	 * @return
	 */
	@Override
	public int getTeledCount(String uuid) {
		String hql = "select count(uuid) from ConsultRecordModel crm where crm.consultType=:type and crm.dealState=:dealState and crm.doctorUuid=:doctorUuid ";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("type", ConsultRecordModel.TYPE_TEL);
		query.setString("dealState", "1"); // 查看已完成的电话咨询
		query.setString("doctorUuid", uuid);
		Number obj = (Number) query.uniqueResult();
		if (obj != null) {
			return obj.intValue();
		}
		return 0;
	}

	/**
	 * 得到已完成的就诊（加号）数量
	 * 
	 * @param uuid
	 * @return
	 */
	@Override
	public int getSeeDocCount(String doctorUuid) {
		String hql = "select count(uuid) from ConsultRecordModel crm where crm.consultType=:consultType and crm.dealState=:dealState and crm.doctorUuid=:doctorUuid ";
		Query query = this.getH4Session().createQuery(hql);
		query.setString("consultType", ConsultRecordModel.TYPE_ORDER);
		query.setString("dealState", "1"); // 查看已完成的加号咨询
		query.setString("doctorUuid", doctorUuid);
		Number obj = (Number) query.uniqueResult();
		if (obj != null) {
			return obj.intValue();
		}
		return 0;
	}

	/**
	 * 根据医生的uuid和messageType获取所有咨询记录的uuids
	 * 
	 * @param messageType
	 * @param doctorUuid
	 * @return
	 */
	@Override
	public List<String> getAllConsultRecordUuids(String messageType, String doctorUuid) {
		StringBuffer hql = new StringBuffer(
				"select crm.uuid from ConsultRecordModel crm where crm.consultType=:type and crm.doctorUuid=:doctorUuid  ");
		//hql.append(" group by crm.customerUuid ");
		hql.append(" order by crm.createTime desc ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("type", messageType);
		query.setString("doctorUuid", doctorUuid);
		List<String> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 通过患者id得到ConsultRecordModel的List
	 * 
	 * @author szr
	 * @param uuid
	 * @param type
	 *            咨询类型 0在线咨询即图文咨询 1电话咨询 2预约加号
	 * @return
	 */
	@Override
	public List<String> getUuidsByCustomerUuid(String type, String customerUuid) {
		StringBuffer hql = new StringBuffer(
				"select crm.uuid from ConsultRecordModel crm , ServicestaffModel  as s where crm.doctorUuid = s.uuid  and  crm.consultType=:type " +
				"and crm.customerUuid=:customerId group by crm.doctorUuid order by crm.createTime desc ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("type", type);
		query.setString("customerId", customerUuid);
		List<String> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 获取是否回复
	 * 
	 * @param customerUuid
	 * @param doctorUuid
	 * @param type
	 * @return
	 */
	@Override
	public ConsultRecordModel getConsultRecordReply(String customerUuid, String doctorUuid, String type) {
		StringBuffer hql = new StringBuffer(
				" from ConsultRecordModel crm where crm.consultType=:type and crm.customerUuid=:customerId ");
		hql.append(" and crm.doctorUuid =:doctorUuid ");
		hql.append(" order by  crm.createTime desc  ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("type", type);
		query.setString("customerId", customerUuid);
		query.setString("doctorUuid", doctorUuid);
		List<ConsultRecordModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 获取是否回复列表
	 * 
	 * @param customerUuid
	 * @param doctorUuid
	 * @param type
	 * @return
	 */
	@Override
	public List<ConsultRecordModel> getConsultRecords(String customerUuid, String doctorUuid, String type) {
		StringBuffer hql = new StringBuffer(
				" from ConsultRecordModel crm where crm.consultType=:type and crm.customerUuid=:customerId ");
		hql.append(" and crm.doctorUuid =:doctorUuid ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("type", type);
		query.setString("customerId", customerUuid);
		query.setString("doctorUuid", doctorUuid);
		List<ConsultRecordModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@Override
	public List getByConditionq(ConsultRecordQueryModel qm, int iDisplayStart, int iDisplayLength) {
		return getByCondition(false, true, qm, iDisplayStart, iDisplayLength);
	}

	/**
	 * 
	 * @Description: (查询预约加号的人数)
	 * @author XP
	 * @param doctorUuid
	 * @param timeType
	 * @param seeDoctorTime
	 * @return
	 * @date 2016-1-24 下午3:11:36
	 */
	@Override
	public int getPatientNumByDoctorUuidAndCustomerUuidAndTimeType(String doctorUuid, String timeType,
			String seeDoctorTime) {
		StringBuffer hql = new StringBuffer( "select count(uuid) from ConsultRecordModel crm where 1=1  ");
		if(!StringUtil.isEmpty(timeType)){
			hql.append(" and crm.timeType=:timeType ");
		}
		hql.append(" and crm.seeDoctorTime=:seeDoctorTime ");
		hql.append(" and crm.doctorUuid=:doctorUuid ");
		hql.append(" and crm.consultType=:consultType ");
		
		Query query = this.getH4Session().createQuery(hql.toString());
		if(!StringUtil.isEmpty(timeType)){
			query.setString("timeType", timeType);
		}
		query.setString("seeDoctorTime", seeDoctorTime);
		query.setString("doctorUuid", doctorUuid);
		query.setString("consultType", ConsultRecordModel.TYPE_ORDER);

		Number obj = (Number) query.uniqueResult();
		if (obj != null) {
			return obj.intValue();
		}
		return 0;
	}
	
	/**
     * 根据医生id和医生的咨询的类型获取咨询列表 
     * @author XP  
     * @param doctorUuid
     * @param consultType
     * @return
     * @date 2016-1-29 下午5:13:31
     */
    @Override
    public List<String> getByDoctorUuid(String doctorUuid, String consultType) {
        StringBuffer hql = new StringBuffer(
                "select DISTINCT(crm.customerUuid) from ConsultRecordModel crm where crm.consultType=:type and crm.doctorUuid=:doctorUuid ");
        //加号预约取处理成功的
        if(ConsultRecordModel.TYPE_ORDER.equals(consultType)){
        	hql.append(" and crm.dealState =:dealState ");
        }
        Query query = this.getH4Session().createQuery(hql.toString());
        query.setString("type", consultType);
        //加号预约取处理成功的
        if(ConsultRecordModel.TYPE_ORDER.equals(consultType)){
        	query.setString("dealState", "1");
        }
        
        query.setString("doctorUuid", doctorUuid);
        List<String> list = query.list();
        if (list != null && list.size() > 0) {
        return list;
        }
        return null;
    }

	
}

