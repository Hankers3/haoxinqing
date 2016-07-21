package com.hxq.mobile.patient.visit.service.impl;

import com.hxq.mobile.entity.weixin.ProfessionResult;
import com.hxq.mobile.patient.visit.service.ProfessionCalculateService;
import com.wxcommon.repository.SpringDao;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("com.hxq.mobile.patient.visit.service.ProfessionCalculateService")
public class ProfessionCalculateServiceImpl implements ProfessionCalculateService {

	@Resource(name="WXDBRepository")
	private SpringDao repository;

	private String table_param="cs_parameter";
	private String table_qw="cs_qw_subject";
	private String table_zy="cs_zy_subject";

	@Override
	public ProfessionResult calculate(String subjectId, Map<String, String> answer) throws Exception {
		if(ObjectUtils.isEmpty(subjectId) || ObjectUtils.isEmpty(answer)) return null;

		Map<String, String> params = this.selectParameters("zy", subjectId, null);
		if(ObjectUtils.isEmpty(params)) return null;

		String strClassName = params.get("calculate_class_name");
		String strMethodName = params.get("calculate_method_name");
		if(ObjectUtils.isEmpty(strClassName) || ObjectUtils.isEmpty(strMethodName)) return null;

		Integer score = (Integer) ObjectUtils.getObjectByClass(Class.forName(strClassName), strMethodName, new Object[]{answer, params}, new Class[]{answer.getClass(), params.getClass()});
		ProfessionResult result = new ProfessionResult();
		result.setSubjectId(subjectId);
		result.setScore(score);
		return result;
	}

	/**
	 * 根据主题ID选择计算参数。相同code的参数，优先级别从高到低：自己>父>祖父>...
	 * @param type
	 * @param subjectId 主题ID
	 * @param params 赋值null
	 * @return 参数的map集合<code、value>
	 * @throws Exception
	 */
	private Map<String, String> selectParameters(String type, String subjectId, Map<String, String> params) throws Exception {
		if(ObjectUtils.isEmpty(type)) return null;

		StringBuilder sbf = new StringBuilder(1000);
		List<String> columns = new ArrayList<>();

		if(ObjectUtils.isEmpty(subjectId) || subjectId.equalsIgnoreCase("0")) {
			sbf.append("select '' as parent_id, code, value from ").append(table_param)
			.append(" where subject_id=? and type=?");
			columns.add("0");//0表示公用参数
			columns.add(type);
		} else {
			sbf.append("select a.parent_id, b.code, b.value from ")
			.append(type.equalsIgnoreCase("zy") ? table_zy : table_qw)
			.append(" as a inner join ").append(table_param)
			.append(" as b on a.id=b.subject_id where a.id=? and b.type=?")
			.append(" union all select c.parent_id,'' as code,'' as value from ")
			.append(type.equalsIgnoreCase("zy") ? table_zy : table_qw)
			.append(" as c where c.id=?");
			columns.add(subjectId);
			columns.add(type);
			columns.add(subjectId);
		}

		List<Map<String, Object>> rows = repository.query(sbf.toString(), columns.toArray(), null);
		if(rows==null || rows.isEmpty()) return params;

		String strCode;
		String strParentId = null;
		for(Map<String, Object> row : rows) {
			if(ObjectUtils.isEmpty(strParentId)) strParentId = (String) row.get("parent_id");
			strCode = StringUtils.trimToEmpty(row.get("code"));
			if(!ObjectUtils.isEmpty(strCode)) {
				if(params==null) params = new HashMap<>();
				//相同code的参数，优先级别从高到低：自己>父>祖父>...
				if(!params.containsKey(strCode)) params.put(strCode, StringUtils.trimToEmpty(row.get("value")));
			}
		}

		//继续查询上级参数
		if(!ObjectUtils.isEmpty(strParentId)) selectParameters(type, strParentId, params);
		return params;
	}
}
