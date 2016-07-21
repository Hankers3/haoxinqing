package com.hxq.mobile.message.InnerMessage.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.hxq.mobile.entity.message.InnerMessageNotice;
import com.hxq.mobile.message.InnerMessage.service.InnerMessageNoticeService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.Constants;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.pagination.PaginationHelper;
import com.wxcommon.repository.AbstractEntity;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

@Service("com.hxq.mobile.message.InnerMessage.service.InnerMessageNoticeService")
public class InnerMessageNoticeServiceImpl extends SpringJdbcSimpleEntityService
	implements InnerMessageNoticeService {

	private String TABLE_COLUMNS="a.uuid, a.delFlag, a.opeTime, a.oper, a.messageUuid, a.readStatus, a.userId";

	@Override
	public int insert(AbstractEntity<?> bean) throws Exception {
		InnerMessageNotice imn = (InnerMessageNotice) bean;
		imn.setDelFlag("1");
		imn.setOper(LoginUserHelper.getLoginUserUuid());
		imn.setOpeTime(DateFormatHelper.getNowTimeStr());
		return super.insert(imn);
	}

	@Override
	public int delete(AbstractEntity<?> id) throws Exception {
		InnerMessageNotice imn = (InnerMessageNotice) id;
		imn.setDelFlag("2");
		imn.setOper(LoginUserHelper.getLoginUserUuid());
		imn.setOpeTime(DateFormatHelper.getNowTimeStr());
		return super.update(imn);
	}

	@Override
	public InnerMessageNotice selectInnerMessageNotice(String messageUuid, String userId) throws Exception {
		if(ObjectUtils.isEmpty(messageUuid) || ObjectUtils.isEmpty(userId)) return null;
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select ").append(TABLE_COLUMNS).append(" from inner_message_notice as a where a.messageUuid=? and a.userId=?");
		List<Map<String, Object>> lst = dao.query(sbf.toString(),
			new Object[]{StringUtils.trimToEmpty(messageUuid), StringUtils.trimToEmpty(userId)}, null, getQueryCache());
		return ObjectUtils.isEmpty(lst) ? null : SimpleBean2DBHelper.map2Bean(lst.get(0), InnerMessageNotice.class);
	}

	@Override
	public List<InnerMessageNotice> selectByUserId(String userId, int pageCount, int pageNo) throws Exception {
		Map<String, Object> form = new HashMap<String, Object>();
		form.put("userId", userId);
		form.put(Constants.PAGINATION_PAGE_SIZE, pageCount);
		form.put(Constants.PAGINATION_PAGE_NO, pageNo);
		return queryList(form, true);
	}

	@Override
	public List<InnerMessageNotice> selectInnerMessageListByUserId(String userId, String readStatus, int pageCount, int pageNo) throws Exception {
		Map<String, Object> form = new HashMap<String, Object>();
		form.put("userId", userId);
		form.put("readStatus", readStatus);
		form.put(Constants.PAGINATION_PAGE_SIZE, pageCount);
		form.put(Constants.PAGINATION_PAGE_NO, pageNo);
		return queryList(form, true);
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private List<InnerMessageNotice> queryList(Map<String, Object> form, Boolean isPagination) throws Exception {
		if(RequestUtil.isEmpty(form, "userId")) return null;

		StringBuffer sbf = new StringBuffer(1000);
		List<String> args = new ArrayList<String>();

		sbf.append(" from inner_message_notice as a");

		sbf.append(args.isEmpty()?" where":" and").append(" a.userId=?");
		args.add(RequestUtil.getFormValue(form, "userId"));

		if(!RequestUtil.isEmpty(form, "readStatus")) {
			sbf.append(args.isEmpty()?" where":" and").append(" a.readStatus=?");
			args.add(RequestUtil.getFormValue(form, "readStatus"));
		}
		String strWhere = sbf.toString();

		sbf.delete(0, sbf.length());
		String strTotal = sbf.append("select count(1)").append(strWhere).toString();

		sbf.delete(0, sbf.length());
		String strQuery = sbf.append("select ").append(TABLE_COLUMNS)
				.append(strWhere).append(" order by a.opeTime").toString();

		Object[] params = args.isEmpty() ? null : args.toArray();
		List<Map<String, Object>> rows = null;
		if(isPagination != null && isPagination.booleanValue()) {
			PaginationHelper page = new PaginationHelper(dao, getQueryCache(), form);
			rows = page.queryByPage(strTotal, strQuery, params, null);
		} else {
			rows = dao.query(strQuery, params, null, getQueryCache());
		}
		return ObjectUtils.isEmpty(rows) ? null : SimpleBean2DBHelper.mapList2BeanList(rows,InnerMessageNotice.class);
	}

	@Override
	protected String getCacheName() {return "m1";}
	@Override
	protected String getQueryCacheName() {return "InnerMessageNoticeService";}
}