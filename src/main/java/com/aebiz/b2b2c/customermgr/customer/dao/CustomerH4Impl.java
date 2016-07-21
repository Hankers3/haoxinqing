package com.aebiz.b2b2c.customermgr.customer.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerQueryModel;

@Repository
public class CustomerH4Impl extends
		BaseH4Impl<CustomerModel, CustomerQueryModel> implements CustomerDAO {
	/**
	 * 会员冻结:<br/>
	 * 冻结状态: 1表示冻结，0表示未冻结<br/>
	 * 需要设置冻结状态，冻结类型，操作人，冻结状态设置为1<br/>
	 */
	public void frozen(String customerUuid, String frozenType, String oper) {
		StringBuffer hql = new StringBuffer(
				" update CustomerModel set frozenState=:frozenState");
		hql.append(", frozenType=:frozenType,oper=:oper where uuid=:customerUuid");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("frozenState",
				CustomerModel.CUSTOMER_FROZENSTATE_FROZEN);
		query.setString("frozenType", frozenType);
		query.setString("oper", oper);
		query.setString("customerUuid", customerUuid);

		query.executeUpdate();
	}

	/**
	 * 会员解冻:<br/>
	 * 冻结状态: 1表示冻结，0表示未冻结<br/>
	 * 需要记录操作人,将冻结状态设置为0，冻结类型设置为空<br/>
	 */
	public void unfrozen(String customerUuid, String oper) {
		StringBuffer hql = new StringBuffer(
				" update CustomerModel set frozenState=:frozenState");
		hql.append(",oper=:oper,frozenType=:frozenType where uuid=:customerUuid");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("frozenState",
				CustomerModel.CUSTOMER_FROZENSTATE_UNFROZEN);
		query.setString("frozenType", "");
		query.setString("oper", oper);
		query.setString("customerUuid", customerUuid);

		query.executeUpdate();
	}

	/**
	 * 根据传入的用户名判断用户名是否已经存在
	 */
	public boolean checkCustomerNameExist(String customerName) {
		StringBuffer hql = new StringBuffer(
				"from CustomerModel where customerName=:customerName");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerName", customerName);

		List<CustomerModel> list = query.list();
		if (list != null && list.size() > 0) {
			return true;
		}

		return false;
	}

	/**
	 * 根据传入的手机号判断手机号是否已经存在
	 */
	public boolean checkMobileExist(String mobile) {
		StringBuffer hql = new StringBuffer(
				" from CustomerModel where mobile=:mobile");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("mobile", mobile);

		List<CustomerModel> list = query.list();
		if (list != null && list.size() > 0) {
			return true;
		}

		return false;
	}

	/**
	 * 根据传入的邮箱判断邮箱是否已经存在
	 */
	public boolean checkEmailExist(String email) {
		StringBuffer hql = new StringBuffer(
				" from CustomerModel where email=:email");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("email", email);

		List<CustomerModel> list = query.list();
		if (list != null && list.size() > 0) {
			return true;
		}

		return false;
	}

	/**
	 * 通过会员编号查询出会员
	 * 
	 * @param customerUuid
	 * @return
	 */
	public CustomerModel getCustomerByUuid(String customerUuid) {
		StringBuffer hql = new StringBuffer(
				" from CustomerModel where uuid=:customerUuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);

		Object obj = query.uniqueResult();
		if (obj != null) {
			return (CustomerModel) obj;
		}

		return null;
	}

	/**
	 * 查询的Model
	 */
	@Override
	protected String getMultiModel() {
		return " ,CustomerInfoModel as cim ";
	}

	/**
	 * 追加查询条件
	 */
	@Override
	protected String getAppendHql(CustomerQueryModel qm) {
		StringBuffer hql = new StringBuffer(" ");
		hql.append(" and o.uuid = cim.customerUuid ");
		
		if (!StringUtil.isEmpty(qm.getRealNameQ())) {
			hql.append(" and cim.realName like:realName ");
		}
		
		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			hql.append(" and o.customerName like:customerName ");
		}
		if (!StringUtil.isEmpty(qm.getSexQ())) {
			hql.append(" and cim.sex =:sex ");
		}
		// 省
		if (!StringUtil.isEmpty(qm.getProvince())) {
			hql.append(" and cim.province =:province ");
		}

		// 市
		if (!StringUtil.isEmpty(qm.getCity())) {
			hql.append(" and cim.city =:city ");
		}
		// 查询开始年龄
		if (!StringUtil.isEmpty(qm.getBeginAge())
				&& Integer.parseInt(qm.getBeginAge()) < 120) {
			hql.append(" and cim.age >=:beginAge ");
		}
		// 查询结束年龄
		if (!StringUtil.isEmpty(qm.getEndAge())
				&& Integer.parseInt(qm.getEndAge()) < 120) {
			hql.append(" and cim.age <=:endAge ");
		}

     	if (qm != null && !"uuid".equals(qm.getSortName())) {
    		hql.append(" order by o.").append(qm.getSortName()).append(" ").append(qm.getSortType());
   		}else {
   			hql.append(" order by o.createTime desc ");
   		}
     	
		return hql.toString();
	}

	/**
	 * 赋值
	 */
	@Override
	protected void setAppendHqlValue(CustomerQueryModel qm, Query q) {

		// 从页面得到真实姓名赋值
		if (!StringUtil.isEmpty(qm.getRealNameQ())) {
			q.setString("realName", "%" + qm.getRealNameQ() + "%");
		}

		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			q.setString("customerName", "%" + qm.getCustomerName() + "%");
		}
		
		// 从页面得到性别赋值
		if (!StringUtil.isEmpty(qm.getSexQ())) {
			q.setString("sex", qm.getSexQ());
		}

		if (!StringUtil.isEmpty(qm.getProvince())) {
			q.setString("province", qm.getProvince());
		}
		if (!StringUtil.isEmpty(qm.getCity())) {
			q.setString("city", qm.getCity());
		}

		if (!StringUtil.isEmpty(qm.getBeginAge())
				&& Integer.parseInt(qm.getBeginAge()) < 120) {
			q.setString("beginAge", qm.getBeginAge());
		}

		if (!StringUtil.isEmpty(qm.getEndAge())
				&& Integer.parseInt(qm.getEndAge()) < 120) {
			q.setString("endAge", qm.getEndAge());
		}

	}

	/**
	 * 会员重置密码,需要将随机生成的密码更新到数据库
	 */
	@Override
	public void resetPwd(String customerUuid, String randomPwd) {
		StringBuffer hql = new StringBuffer(
				" update CustomerModel set password=:password where uuid=:uuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("password", randomPwd);
		query.setString("uuid", customerUuid);

		query.executeUpdate();
	}

	/**
	 * 通过会员编号获取会员名称
	 */
	@Override
	public String getCustomerNameByCustomerUuid(String customerUuid) {
		StringBuffer hql = new StringBuffer(
				"select customerName from CustomerModel where uuid=:uuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", customerUuid);

		Object obj = query.uniqueResult();
		if (obj != null) {
			return (String) obj;
		}
		return "";
	}

	/**
	 * 更新会员的实名认证状态
	 */
	@Override
	public void updateCustomerAuthState(String customerUuid, String state) {
		StringBuffer hql = new StringBuffer(
				" update CustomerModel set authState=:authState where uuid=:uuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", customerUuid);
		query.setString("authState", state);

		query.executeUpdate();

	}

	/**
	 * 通过会员名称获取会员编号
	 * 
	 * @param customerName
	 *            会员名称
	 * @return
	 */
	@Override
	public String getCustomerUuidByCustomerName(String customerName) {
		StringBuffer hql = new StringBuffer(
				"select uuid from CustomerModel where customerName=:customerName ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerName", customerName);

		Object obj = query.uniqueResult();
		if (obj != null) {
			return (String) obj;
		}
		return "";
	}

	/**
	 * ********************************对外接口*************************************
	 * 
	 * 通过会员编号或者手机号或者用户名获取会员信息
	 */
	@Override
	public CustomerModel getCustomerModelByCondition(String param) {
		StringBuffer hql = new StringBuffer(" from CustomerModel where ");
		hql.append(" uuid=:uuid or mobile=:mobile or customerName=:customerName ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", param);
		query.setString("mobile", "%" + param + "%");
		query.setString("customerName", "%" + param + "%");
		Object obj = query.uniqueResult();

		if (obj != null) {
			return (CustomerModel) obj;
		}
		return null;
	}

	/**
	 * 通过会员uuid，会员编号，会员名称获取会员列表
	 */
	@Override
	public List<CustomerModel> getCustomerModelListByCondition(
			CustomerQueryModel qm, int start, int pageShow) {
		StringBuffer hql = new StringBuffer("select c from CustomerModel c,VipclubIntegralStatModel v  where 1=1 and c.uuid=v.customerUuid ");
		if (!StringUtil.isEmpty(qm.getUuid())) {
			hql.append(" and c.uuid=:uuid ");
		}
		if (!StringUtil.isEmpty(qm.getCustomerId())) {
			hql.append(" and c.customerId like :customerId ");
		}
		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			hql.append(" and c.customerName like :customerName ");
		}
		if (!StringUtil.isEmpty(qm.getMobile())) {
			hql.append(" and c.mobile like :mobile ");
		}
		if(!StringUtil.isEmpty(qm.getSortName()) && !"uuid".equals(qm.getSortName())){
			hql.append(" order by  v.intergralCount  " + qm.getSortType());
		}

		Query query = this.getH4Session().createQuery(hql.toString());

		if (!StringUtil.isEmpty(qm.getUuid())) {
			query.setString("uuid", qm.getUuid());
		}
		if (!StringUtil.isEmpty(qm.getCustomerId())) {
			query.setString("customerId", "%" + qm.getCustomerId() + "%");
		}
		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			query.setString("customerName", "%" + qm.getCustomerName() + "%");
		}
		if (!StringUtil.isEmpty(qm.getMobile())) {
			query.setString("mobile", "%" + qm.getMobile() + "%");
		}
		if(!StringUtil.isEmpty(qm.getSortName()) && !"uuid".equals(qm.getSortName())){
			hql.append(" order by v.intergralCount " + qm.getSortType());
		}else{
			hql.append(" order by o.createTime desc");
		}
		query.setFirstResult(start);
		query.setMaxResults(pageShow);

		List<CustomerModel> customerList = query.list();

		return customerList;
	}

	/**
	 * 通过会员会员名称模糊查询会员数量
	 */
	@Override
	public int getCountByCondition(CustomerQueryModel qm) {
		StringBuffer hql = new StringBuffer(
				" select count(c) from CustomerModel c ,VipclubIntegralStatModel v  where 1=1 and c.uuid=v.customerUuid ");
		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			hql.append(" and c.customerName like :customerName ");
		}
		if (!StringUtil.isEmpty(qm.getMobile())) {
			hql.append(" and c.mobile like :mobile ");
		}

		Query query = this.getH4Session().createQuery(hql.toString());

		if (!StringUtil.isEmpty(qm.getCustomerName())) {
			query.setString("customerName", "%" + qm.getCustomerName() + "%");
		}
		if (!StringUtil.isEmpty(qm.getMobile())) {
			query.setString("mobile", "%" + qm.getMobile() + "%");
		}

		return ((Number) query.uniqueResult()).intValue();
	}

	/**
	 * 通过会员名编号来获取用户电话号码
	 * 
	 * @param customerUuid
	 *            会员编号
	 * @return
	 */
	@Override
	public String getCustomerMobileByUuid(String customerUuid) {
		StringBuffer hql = new StringBuffer(
				"select cm.mobile from CustomerModel as cm  where cm.uuid=:uuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", customerUuid);
		List list = query.list();
		if(list !=null && list.size()>0){
			return (String) list.get(0);
		}
		return "";
	}

	/**
	 * 通过会员手机号获取会员
	 * 
	 * @param mobile
	 *            会员手机号
	 * @return
	 */
	@Override
	public CustomerModel getCustomerModelByMobile(String mobile) {
		StringBuffer hql = new StringBuffer(" from CustomerModel where 1=1");
		hql.append(" and mobile=:mobile");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("mobile", mobile);
		List list = query.list();
		if(list !=null && list.size()>0){
			return (CustomerModel) list.get(0);
		}
		
		return null;
	}

	/**
	 * 根据会员名称获取，会员uuid集合
	 * 
	 * @param customerName
	 *            会员名称
	 * @return
	 */
	public List<String> getCustomerUuids(String customerName) {
		if (StringUtil.isEmpty(customerName)) {
			return null;
		}
		StringBuffer hql = new StringBuffer(
				"select cm.uuid from CustomerModel as cm where 1=1 ");
		if (!StringUtil.isEmpty(customerName)) {
			hql.append(" and cm.customerName like :customerName ");
		}

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerName", "%" + customerName + "%");

		List<String> customerUuids = query.list();
		return customerUuids;
	}

	/**
	 * 通过会员编号获取会员头像名称
	 */
	@Override
	public String getCustomerInfoImageByCustomerUuid(String customerUuid) {
		StringBuffer hql = new StringBuffer(
				" select image from CustomerInfoModel where customerUuid=:customerUuid");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerUuid", customerUuid);

		Object obj = query.uniqueResult();

		if (obj != null) {
			return (String) obj;
		}
		return "";
	}

	/**
	 * 通过账户或者邮箱或者手机号名称查询出该会员所有信息
	 */
	public CustomerModel getCustomerModelByLoginNameOrMobileOrEmail(
			String lonigNameOrMobileOrEmail) {
		StringBuffer hql = new StringBuffer(
				" from CustomerModel where customerName =:customerName ");
		hql.append(" or mobile =:mobile ");
		hql.append(" or email =:email ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("customerName", lonigNameOrMobileOrEmail);
		query.setString("mobile", lonigNameOrMobileOrEmail);
		query.setString("email", lonigNameOrMobileOrEmail);
		List list = query.list();
		if(list !=null && list.size()>0){
			return  (CustomerModel)list.get(0);
		}

		return null;
	}

	/**
	 * 根据订单状态获取对应的uuids
	 * 
	 * @param state
	 * @return
	 */
	@Override
	public List<String> getCustomerUuidsByState(String state) {
		StringBuffer hql = new StringBuffer(
				" select o.uuid from CustomerModel as o where ");
		hql.append(" and o.frozenState=:frozenState");
		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("frozenState", state);

		return (List<String>) q.list();
	}

	/**
	 * 跟新会员登录的错误次数和最后一次错误登录时间
	 */
	@Override
	public void updateLoginErrorTimes(CustomerModel model) {
		StringBuffer hql = new StringBuffer(
				" update CustomerModel set loginErrorTimes =:loginErrorTimes , lastWrongLoginTime =:lastWrongLoginTime where uuid=:customerUuid ");

		Query query = this.getH4Session().createQuery(hql.toString());

		query.setInteger("loginErrorTimes", model.getLoginErrorTimes());
		query.setString("lastWrongLoginTime", model.getLastWrongLoginTime());
		query.setString("customerUuid", model.getUuid());

		query.executeUpdate();

	}

	/**
	 * 通过手机号码来获得用户密码
	 * 
	 * @param mobile
	 * @return
	 */
	@Override
	public String getPassword(String mobile) {
		StringBuffer hql = new StringBuffer(
				" select o.password from  CustomerModel o where 1=1 ");
		hql.append(" and o.mobile =:mobile");

		Query q = this.getH4Session().createQuery(hql.toString());
		q.setString("mobile", mobile);

		List list = q.list();
		if (list != null && list.size() > 0) {
			return (String) list.get(0);
		}
		return "";
	}

	/**
	 * 通过患者分类id获取该患者分类下是否有患者
	 * 
	 * @param checkIds
	 * @return
	 */
	@Override
	public String checkCustomerCategory(List<String> checkIds) {

		for (int i = 0; i < checkIds.size(); i++) {
			StringBuffer hql = new StringBuffer(
					" select o.uuid  from CustomerModel  as o ");
			hql.append(" where o.categoryUuid =:categoryUuid ");
			Query q = this.getH4Session().createQuery(hql.toString());
			q.setString("categoryUuid", checkIds.get(i));
			List list = q.list();
			if (list != null && list.size() > 0) {
				return "1";
			}
		}
		return "0";
	}

	/**
	 * 患者审核通过，需要更新患者登录信息中的审核状态<br />
	 * 1表示通过，0表示未审核，2表示未通过
	 * 
	 * @return
	 * @throws IOException
	 */
	@Override
	public void state(String customerUuid, String state) {
		StringBuffer hql = new StringBuffer(
				" update CustomerModel set activeState=:activeState");
		hql.append(" where uuid=:customerUuid");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("activeState", state);
		query.setString("customerUuid", customerUuid);
		query.executeUpdate();

	}

	@Override
	public String getCustomerNoByCustomerUuid(String uuid) {
		StringBuffer hql = new StringBuffer(
				"select customerId from CustomerModel where uuid=:uuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", uuid);

		Object obj = query.uniqueResult();
		if (obj != null) {
			return (String) obj;
		}
		return "";
	}

	/**
	 * 根据用户的Uuids将用户的所有的信息全部查询出来
	 * 
	 * 
	 * @return
	 * 
	 */

	@Override
	public List<CustomerModel> getAllCustomerByUuids(List<String> uuids) {
		StringBuffer hql = new StringBuffer(
				" from CustomerModel as c where c.uuid in (:uuids) ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setParameterList("uuids", uuids.toArray());

		List<CustomerModel> list = query.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 获取患者编号
	 * 
	 * @param uuid
	 * @return
	 */
	public String getCustomerId(String uuid) {
		StringBuffer hql = new StringBuffer(
				" select c.customerId from CustomerModel as c where c.uuid =:uuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("uuid", uuid);

		List<String> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return "";

	}

	/**
	 * 根据用户Id获取用户的余额
	 * @param uuid
	 * @return
	 */
	@Override
	public double getAccountAmountByUuid(String customerUuid) {
	    StringBuffer hql = new StringBuffer(
	            " select c.accountAmount from CustomerModel c where c.uuid=:customerUuid ");
	
	        Query query = this.getH4Session().createQuery(hql.toString());
	        query.setString("customerUuid", customerUuid);
	        
	          return (double) query.uniqueResult();
	}

            
            

	/**
	 * 根据用户名和密码判断用户是否存在
	 */
	@Override
	public CustomerModel getCustomerInfoModelByLoginNameAndPassword(
			String loginName, String pwd) {
		StringBuffer hql = new StringBuffer(
				" from CustomerModel where password =:pwd ");
		hql.append(" or customerName =:loginName ");
		hql.append(" or mobile =:loginName ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("pwd", pwd);
		query.setString("loginName", loginName);

		List<CustomerModel> list = query.list();

		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public CustomerModel checkMobileAndPassword(String mobile, String password) {

		StringBuffer hql = new StringBuffer(
				" from CustomerModel where 1=1 and mobile=:mobile and password=:password");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("mobile", mobile);
		query.setString("password", password);
		List<CustomerModel> list = query.list();
		if (list != null && list.size() > 0) {
			CustomerModel customerModel = list.get(0);
			if (customerModel != null) {
				return customerModel;
			} else {
				return null;
			}

		} else {
			return null;
		}
	}

}
