package com.hxq.mobile.vipclub.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.IntegralType;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.QueryTimeType;
import com.aebiz.b2b2c.vipclub.vipclubintegrallog.vo.VipclubIntegralLogQueryModel;
import com.aebiz.b2b2c.vipclub.vipclubintegralstat.vo.StatIntegralType;
import com.hxq.mobile.entity.common.VipclubIntegralLog;
import com.hxq.mobile.entity.common.VipclubIntegralStat;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.hxq.mobile.vipclub.service.VipclubIntegralLogService;
import com.hxq.mobile.vipclub.service.VipclubIntegralStatService;
import com.wxcommon.pagination.PaginationHelper;
import com.wxcommon.repository.AbstractEntity;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.IdentityHelper;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

/**
 * 会员积分日志service实现类
 */
@Service("com.hxq.mobile.vipclub.service.VipclubIntegralLogService")
public class VipclubIntegralLogServiceImpl extends SpringJdbcSimpleEntityService
	implements VipclubIntegralLogService {
	Logger log = LoggerFactory.getLogger(VipclubIntegralLogServiceImpl.class);

    @Resource(name = "com.hxq.mobile.vipclub.service.VipclubIntegralStatService")
    private VipclubIntegralStatService statService;

	private String TABLE_COLUMNS="a.uuid, a.delFlag, a.opeTime, a.oper, a.customerUuid, a.description,"
			+ " a.intergralCount, a.intergralType, a.nowIntegral, a.orderMainUuid, a.overdueTime,"
			+ " a.createTime, a.productUuid, a.status, a.note, a.userType, a.searchType, a.operType";

	/**
	 * 创建会员积分日志实现方法
	 *
	 * @param bean
	 *            会员积分日志对象
	 * @return ret
	 */
	@Override
	public int insert(AbstractEntity<?> bean) throws Exception {
		VipclubIntegralLog vil = (VipclubIntegralLog) bean;
		vil.setDelFlag("1");
		vil.setOper(LoginUserHelper.getLoginUserUuid());
		vil.setOpeTime(DateFormatHelper.getNowTimeStr());
		return super.insert(vil);
	}

	/**
	 * 更新会员积分日志实现方法
	 *
	 * @param bean
	 *            会员积分日志对象
	 * @return
	 * @throws Exception
	 */
	@Override
	public int update(AbstractEntity<?> bean) throws Exception {
		VipclubIntegralLog vil = (VipclubIntegralLog) bean;
		vil.setDelFlag("1");
		vil.setOper(LoginUserHelper.getLoginUserUuid());
		vil.setOpeTime(DateFormatHelper.getNowTimeStr());
		return super.update(vil);
	}

	/**
	 * 删除会员积分日志实现方法
	 *
	 * @param bean
	 *            会员积分日志对象
	 * @return
	 * @throws Exception
	 */
	@Override
	public int delete(AbstractEntity<?> bean) throws Exception {
		VipclubIntegralLog vil = (VipclubIntegralLog) bean;
		vil.setDelFlag("2");
		vil.setOper(LoginUserHelper.getLoginUserUuid());
		vil.setOpeTime(DateFormatHelper.getNowTimeStr());
		return super.update(vil);
	}

	/**
	 * 通过会员Uuid查询积分日志列表实现方法
	 * @param customerUuid 会员Uuid
	 * @return
	 * @throws Exception
	 */
	public List<VipclubIntegralLog> selectByCustomerUuid(String customerUuid) {
		if(ObjectUtils.isEmpty(customerUuid)) return null;// 判断会员Uuid是否为空
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select ").append(TABLE_COLUMNS)
		.append(" from vipclub_integral_log as a where a.customerUuid=? order by a.opeTime desc");
		List<Map<String, Object>> rows = dao.query(sbf.toString(),
				new Object[]{StringUtils.trimToEmpty(customerUuid)}, null, getQueryCache());
		return ObjectUtils.isEmpty(rows) ? null : SimpleBean2DBHelper.mapList2BeanList(rows,VipclubIntegralLog.class);
	}

	/**
	 * 通过会员id查询会员日志记录
	 *
	 * @param customerUuid
	 * @param pageCount
	 * @param pageNo
	 * @return tempList<VipclubIntegralLogModel>
	 */
	@Override
	public List<VipclubIntegralLog> selectByCustomerUuid(String customerUuid, int pageCount, int pageNo) {
		if(ObjectUtils.isEmpty(customerUuid)) return null;// 判断会员Uuid是否为空

		StringBuffer sbf = new StringBuffer(1000);
		List<String> args = new ArrayList<String>();

		sbf.append(" from vipclub_integral_log as a where a.customerUuid=?");
		args.add(StringUtils.trimToEmpty(customerUuid));

		String strWhere = sbf.toString();

		sbf.delete(0, sbf.length());
		String strTotal = sbf.append("select count(1)").append(strWhere).toString();

		sbf.delete(0, sbf.length());
		sbf.append("select ").append(TABLE_COLUMNS).append(strWhere).append(" order by a.createTime desc");
		String strQuery = sbf.toString();

		Object[] params = args.isEmpty() ? null : args.toArray();
		PaginationHelper page = new PaginationHelper(dao, getQueryCache(), pageNo, pageCount);
		List<Map<String, Object>> rows = page.queryByPage(strTotal, strQuery, params, null);
		return ObjectUtils.isEmpty(rows) ? null : SimpleBean2DBHelper.mapList2BeanList(rows,VipclubIntegralLog.class);
	}

	/**
	 * 保存一条会员积分日志记录,更新积分统计表，会员账户表积分记录
	 *
	 * @param customerUuid
	 * @param adaptType
	 * @param changgeIntegralNum
	 * @param description
	 * @return
	 * @throws Exception
	 */
	public int insertVipIntegralLog(String customerUuid, String adaptType, int changgeIntegralNum, String intergralType,
			String userType, String content, String description) throws Exception {

		VipclubIntegralLog vil = new VipclubIntegralLog();// 新建一条会员积分日志记录
		vil.setCustomerUuid(customerUuid);// 设置会员积分日志记录的会员Uuid
		vil.setUserType(userType);// 设置用户类型
		vil.setDescription(content);// 保存描述
		vil.setIntergralType(intergralType);// 保存积分获取类型
		vil.setSearchType(description);// 设置会员积分日志记录的搜索条件
		vil.setIntergralCount(changgeIntegralNum);// 设置会员积分日志记录的积分数量

		// 查询统计表总积分记录
		VipclubIntegralStat visTotal = statService.selectByCustomerUuidAndIntegralType(
				customerUuid, StatIntegralType.TOTAL.getValue());
		if (visTotal == null) {
			visTotal = new VipclubIntegralStat();
			visTotal.setIntergralType(StatIntegralType.TOTAL.getValue());
			visTotal.setCustomerUuid(customerUuid);
			visTotal.setUserType(userType);
			visTotal.setUuid(IdentityHelper.uuid2());
			visTotal.setDelFlag("2");
			visTotal.setOper(LoginUserHelper.getLoginUserUuid());
			visTotal.setOpeTime(DateFormatHelper.getNowTimeStr());
			statService.insert(visTotal);
		}

		// 查询统计表可用积分记录
		VipclubIntegralStat visUsable = statService.selectByCustomerUuidAndIntegralType(
				customerUuid, StatIntegralType.USABLE.getValue());
		if (visUsable == null) {
			visUsable = new VipclubIntegralStat();
			visUsable.setIntergralType(StatIntegralType.USABLE.getValue());
			visUsable.setCustomerUuid(customerUuid);
			visUsable.setUserType(userType);
			visUsable.setUuid(IdentityHelper.uuid2());
			visUsable.setDelFlag("2");
			visUsable.setOper(LoginUserHelper.getLoginUserUuid());
			visUsable.setOpeTime(DateFormatHelper.getNowTimeStr());
			statService.insert(visUsable);
		}

		// 添加积分,更新统计表,设置积分日志类型
		if (adaptType.equals("add")) {
			// 更新会员统计表的累计积分数
			visTotal.setIntergralCount(visTotal.getIntergralCount() + changgeIntegralNum);
			// 更新会员统计表的累计积分数
			visUsable.setIntergralCount(visUsable.getIntergralCount() + changgeIntegralNum);
		} else if (adaptType.equals("reduce")) {
			// 更新会员统计表的可用积分数
			visUsable.setIntergralCount(visUsable.getIntergralCount() - changgeIntegralNum);
		}
		// 更新积分统计表
		statService.update(visUsable);
		statService.update(visTotal);

		// 设置会员操作日志的当前可用积分
		vil.setNowIntegral(visUsable.getIntergralCount());
		// 设置会员操作日志记录创建时间
		vil.setCreateTime(DateFormatHelper.getNowTimeStr());

        // 设置会员日志记录的删除标志，操作员Uuid，操作时间
        return super.insert(vil);
	}

	/**
	 * 通过会员模糊查询会员日志记录数
	 *
	 * @param qm
	 * @return int
	 */
	@Override
	public int selectVipclubIntegralLogCountByConditon(VipclubIntegralLogQueryModel qm) {
		if(ObjectUtils.isEmpty(qm.getCustomerUuid())) return 0;

		StringBuffer sbf = new StringBuffer(1000);
		List<String> args = new ArrayList<String>();

		sbf.append("select COUNT(1) from vipclub_integral_log as a where a.customerUuid=?");
		args.add(StringUtils.trimToEmpty(qm.getCustomerUuid()));

		if (!ObjectUtils.isEmpty(qm.getMinTime())) {// 最小时间不为空
			sbf.append(" and a.createTime > ?");
			args.add(StringUtils.trimToEmpty(qm.getMinTime()));
		}

		if (!ObjectUtils.isEmpty(qm.getMaxTime())) {// 最大时间不为空
			sbf.append(" and a.createTime < ?");
			args.add(StringUtils.trimToEmpty(qm.getMaxTime()));
		}

		if ("add".equalsIgnoreCase(qm.getIntegrals())) {// 查询记录类型为增加的语句
			sbf.append(" and (a.intergralType=? or a.intergralType=? or a.intergralType=?)");
			args.add(IntegralType.GET_BY_ORDER.getValue());
			args.add(IntegralType.GET_BY_REGISTER.getValue());
			args.add(IntegralType.GET_BY_PLATEFORM.getValue());
		}

		if ("reduce".equalsIgnoreCase(qm.getIntegrals())) {// 查询记录类型为减少的语句
			sbf.append(" and (a.intergralType=? or a.intergralType=?)");
			args.add(IntegralType.REDUCE_BY_PAY.getValue());
			args.add(IntegralType.REDUCE_BY_PLATEFORM.getValue());
		}

		Object[] params = args.isEmpty() ? null : args.toArray();
		return dao.queryForObject(sbf.toString(), params, null, Integer.class).intValue();
	}

	/**
	 * 通过会员查询model，查询该类型会员积分总数
	 *
	 * @param qm
	 * @return int
	 */
	@Override
	public int selectTypeIntegralSumByConditon(VipclubIntegralLogQueryModel qm) {
		if(ObjectUtils.isEmpty(qm.getCustomerUuid())) return 0;

		StringBuffer sbf = new StringBuffer(1000);
		List<String> args = new ArrayList<String>();

		sbf.append("select SUM(a.intergralCount) from vipclub_integral_log as a where a.customerUuid=?");
		args.add(StringUtils.trimToEmpty(qm.getCustomerUuid()));

		if (!ObjectUtils.isEmpty(qm.getMinTime())) {// 最小时间不为空
			sbf.append(" and a.createTime > ?");
			args.add(StringUtils.trimToEmpty(qm.getMinTime()));
		}

		if (!ObjectUtils.isEmpty(qm.getMaxTime())) {// 最大时间不为空
			sbf.append(" and a.createTime < ?");
			args.add(StringUtils.trimToEmpty(qm.getMaxTime()));
		}

		// 查询记录时间的语句
		if (!ObjectUtils.isEmpty(qm.getQueryTime()) && !ObjectUtils.isEmpty(qm.getQueryTimeType())) {
			// 拼接查询三个月以内和一年以内的查询语句
			if (qm.getQueryTimeType().equals(QueryTimeType.IN_THREE_MONTH.getValue())
					|| qm.getQueryTimeType().equals(QueryTimeType.IN_ONE_YEAR.getValue())) {
				sbf.append(" and a.createTime > ?");
				args.add(StringUtils.trimToEmpty(qm.getQueryTime()));
			}
			// 拼接查询三个月以前的查询语句
			if (qm.getQueryTimeType().equals(QueryTimeType.OUT_THREE_MONTH.getValue())) {
				sbf.append(" and a.createTime < ?");
				args.add(StringUtils.trimToEmpty(qm.getQueryTime()));
			}
		}

		if ("add".equalsIgnoreCase(qm.getIntegrals())) {// 查询记录类型为增加的语句
			sbf.append(" and (a.intergralType=? or a.intergralType=? or a.intergralType=?)");
			args.add(IntegralType.GET_BY_ORDER.getValue());
			args.add(IntegralType.GET_BY_REGISTER.getValue());
			args.add(IntegralType.GET_BY_PLATEFORM.getValue());
		}

		if ("reduce".equalsIgnoreCase(qm.getIntegrals())) {// 查询记录类型为减少的语句
			sbf.append(" and (a.intergralType=? or a.intergralType=?)");
			args.add(IntegralType.REDUCE_BY_PAY.getValue());
			args.add(IntegralType.REDUCE_BY_PLATEFORM.getValue());
		}

		Object[] params = args.isEmpty() ? null : args.toArray();
		return dao.queryForObject(sbf.toString(), params, null, Integer.class).intValue();
	}

	/**
	 *
	 * @Description: (根据用户类型以及用户id和描述获取日志对象)
	 * @author XP
	 * @param doctorid
	 * @param userType
	 * @param description
	 * @return
	 * @date 2016-1-19 下午1:54:17
	 */
	@Override
	public VipclubIntegralLog selectVipclubIntegralLogByConditions(
			String doctorid, String userType, String intergralType, String description) {

		StringBuffer sbf = new StringBuffer(1000);
		List<String> args = new ArrayList<String>();

		sbf.append("select ").append(TABLE_COLUMNS).append(" from vipclub_integral_log as a");
		if (!ObjectUtils.isEmpty(doctorid)) {
			sbf.append(args.isEmpty()?" where":" and").append(" a.customerUuid=?");
			args.add(StringUtils.trimToEmpty(doctorid));
		}
		if (!ObjectUtils.isEmpty(userType)) {
			sbf.append(args.isEmpty()?" where":" and").append(" a.userType=?");
			args.add(StringUtils.trimToEmpty(userType));
		}
		if (!ObjectUtils.isEmpty(intergralType)) {
			sbf.append(args.isEmpty()?" where":" and").append(" a.intergralType=?");
			args.add(StringUtils.trimToEmpty(intergralType));
		}
		if (!ObjectUtils.isEmpty(description)) {
			sbf.append(args.isEmpty()?" where":" and").append(" a.searchType=?");
			args.add(StringUtils.trimToEmpty(description));//按照原全网逻辑，存疑义
		}
		// 类型为登陆增加积分时，判断时间
		if ("1".equals(userType) || "2".equals(userType)) {
			sbf.append(args.isEmpty()?" where":" and").append(" a.createTime like ?");
			args.add((new StringBuffer(DateFormatHelper.getNowTimeStr().substring(0,10))).append("%").toString());
		}

		Object[] params = args.isEmpty() ? null : args.toArray();
		List<Map<String, Object>> rows = dao.query(sbf.toString(), params, null, getQueryCache());
		try {
			return ObjectUtils.isEmpty(rows) ? null
					: SimpleBean2DBHelper.map2Bean(rows.get(0), VipclubIntegralLog.class);
		} catch (Exception e) {
			log.error(doctorid, e);
		}
		return null;
	}

	@Override
	public VipclubIntegralLog selectVipclubIntegralLogByQuizResultUuid(
			String customerUuid, String vipclubUsertypeCus,	String quizCategoryUuid) {
		if(ObjectUtils.isEmpty(customerUuid) || ObjectUtils.isEmpty(vipclubUsertypeCus)
				|| ObjectUtils.isEmpty(quizCategoryUuid)) return null;

		StringBuffer sbf = new StringBuffer(1000);
		List<String> args = new ArrayList<String>();

		sbf.append("select ").append(TABLE_COLUMNS).append(" from vipclub_integral_log as a");

		sbf.append(args.isEmpty()?" where":" and").append(" a.customerUuid=?");
		args.add(StringUtils.trimToEmpty(customerUuid));

		sbf.append(args.isEmpty()?" where":" and").append(" a.userType=?");
		args.add(StringUtils.trimToEmpty(vipclubUsertypeCus));

		sbf.append(args.isEmpty()?" where":" and").append(" a.searchType=?");
		args.add(StringUtils.trimToEmpty(quizCategoryUuid));

		sbf.append(args.isEmpty()?" where":" and").append(" a.createTime like ?");
		args.add((new StringBuffer(DateFormatHelper.getNowTimeStr().substring(0,9))).append("%").toString());

		Object[] params = args.isEmpty() ? null : args.toArray();
		List<Map<String, Object>> rows = dao.query(sbf.toString(), params, null, getQueryCache());
		try {
			return ObjectUtils.isEmpty(rows) ? null
					: SimpleBean2DBHelper.map2Bean(rows.get(0), VipclubIntegralLog.class);
		} catch (Exception e) {
			log.error(customerUuid, e);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {return "VipclubIntegralLogService";}
}