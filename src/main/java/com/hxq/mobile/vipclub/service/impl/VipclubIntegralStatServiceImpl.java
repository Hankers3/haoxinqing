package com.hxq.mobile.vipclub.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.hxq.mobile.entity.common.VipclubIntegralStat;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.hxq.mobile.vipclub.service.VipclubIntegralStatService;
import com.wxcommon.repository.AbstractEntity;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

/**
 * 会员积分统计表service
 */
@Service("com.hxq.mobile.vipclub.service.VipclubIntegralStatService")
public class VipclubIntegralStatServiceImpl extends SpringJdbcSimpleEntityService
	implements VipclubIntegralStatService {
	Logger log = LoggerFactory.getLogger(VipclubIntegralStatServiceImpl.class);

	private String TABLE_COLUMNS="a.uuid, a.delFlag, a.opeTime, a.oper, a.customerUuid,"
			+ " a.intergralCount, a.intergralType, a.orderUuid, a.userType, a.cmobile";

	/**
	 * 创建会员积分统计记录
	 *
	 * @param VipclubIntegralStat
	 * @return int
	 */
	@Override
	public int insert(AbstractEntity<?> bean) throws Exception {
		VipclubIntegralStat vis = (VipclubIntegralStat) bean;
		vis.setDelFlag("1");
		vis.setOper(LoginUserHelper.getLoginUserUuid());
		vis.setOpeTime(DateFormatHelper.getNowTimeStr());
		return super.insert(vis);
	}

	/**
	 * 更新会员积分统计记录
	 *
	 * @param VipclubIntegralStat
	 * @return int
	 */
	@Override
	public int update(AbstractEntity<?> bean) throws Exception {
		VipclubIntegralStat vis = (VipclubIntegralStat) bean;
		vis.setDelFlag("1");
		vis.setOper(LoginUserHelper.getLoginUserUuid());
		vis.setOpeTime(DateFormatHelper.getNowTimeStr());
		return super.update(vis);
	}

	/**
	 * 删除会员积分统计记录
	 *
	 * @param VipclubIntegralStat
	 * @return String
	 */
	@Override
	public int delete(AbstractEntity<?> bean) throws Exception {
		VipclubIntegralStat vis = (VipclubIntegralStat) bean;
		vis.setDelFlag("2");
		vis.setOper(LoginUserHelper.getLoginUserUuid());
		vis.setOpeTime(DateFormatHelper.getNowTimeStr());
		return super.update(vis);
	}

	/**
	 * 通过会员id和积分类型查询会员积分
	 *
	 * @param customerUuid
	 * @return integralStatModel
	 */
	public VipclubIntegralStat selectByCustomerUuidAndIntegralType(String customerUuid, String integralType) {
		if(ObjectUtils.isEmpty(customerUuid)|| ObjectUtils.isEmpty(integralType)) return null;
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select ").append(TABLE_COLUMNS)
		.append(" from vipclub_integral_stat as a where a.customerUuid=? and a.intergralType=? order by a.opeTime desc");
		List<Map<String, Object>> rows = dao.query(sbf.toString(),
				new Object[]{StringUtils.trimToEmpty(customerUuid), StringUtils.trimToEmpty(integralType)},
				null, getQueryCache());
		try {
			return ObjectUtils.isEmpty(rows) ? null
					: SimpleBean2DBHelper.map2Bean(rows.get(0), VipclubIntegralStat.class);
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
	protected String getQueryCacheName() {return "VipclubIntegralStatService";}
}