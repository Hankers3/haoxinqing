package com.hxq.mobile.util.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxcommon.util.MathUtils;
import com.wxcommon.util.ObjectUtils;

public class QueryListHelper {

	/**
	 * 解析查询条件，供service进行查询用
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> prepareQueryParam(HttpServletRequest request) throws Exception {

		Map<String, Object> aoData = parseAoData(request);
		Map<String, Object> searchParam = parseSearchParam(request);

		searchParam.put("iDisplayStart", MathUtils.toInt(aoData.get("iDisplayStart"), 1));
		searchParam.put("iDisplayLength", MathUtils.toInt(aoData.get("iDisplayLength"), 20));

		int iSortCol_0 = MathUtils.toInt(aoData.get("iSortCol_0"), 0);
		String sortFieldName = (String) aoData.get("mDataProp_" + iSortCol_0);
		String sortTypeString = (String) aoData.get("sSortDir_0");
		Boolean needSort = (Boolean) aoData.get("bSortable_" + iSortCol_0);
		if (needSort != null && needSort.booleanValue()) {
			searchParam.put("SortName", sortFieldName);
			searchParam.put("SortType", sortTypeString);
		}

		return searchParam;
	}

	/**
	 * 包装返回结果
	 * @param sEcho 传入的sEcho参数
	 * @param showList 总记录数
	 * @param iTotalRecords 总记录数
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> prepareQueryResult(Object sEcho, List<Map<String,Object>> showList, int iTotalRecords) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("sEcho", sEcho);
		jsonMap.put("iTotalRecords", iTotalRecords);
		jsonMap.put("iTotalDisplayRecords", iTotalRecords);
		jsonMap.put("aaData", showList);
		return jsonMap;
	}

	/**
	 * 解析aoData条件
	 * @param request
	 * @return
	 */
	private static Map<String, Object> parseAoData(HttpServletRequest request) {
		JSONObject jo = null;
		DataTablesPageParam dtpp = null;
		Map<String, Object> pageParamMap = new HashMap<String, Object>();
		JSONArray jsonArr = JSONArray.parseArray(request.getParameter("aoData"));
		for (Object o : jsonArr) {
			jo = (JSONObject) o;
			dtpp = (DataTablesPageParam) JSON.parseObject(jo.toJSONString(), DataTablesPageParam.class);
			pageParamMap.put(dtpp.getName(), dtpp.getValue());
		}
		return pageParamMap;
	}

	/**
	 * 解析searchParam条件
	 * @param request
	 * @return Map&lt;参数名, {参数值,比较符}&gt;
	 */
	private static Map<String, Object> parseSearchParam(HttpServletRequest request) {

		JSONObject jo = null;
		DataTablesPageParam dtpp = null;
		Map<String, Object> searchParamMap = new HashMap<String, Object>();
		JSONArray searParamArr = JSONArray.parseArray(request.getParameter("searchParam"));
		for (Object o : searParamArr) {
			jo = (JSONObject) o;
			dtpp = (DataTablesPageParam) JSON.parseObject(jo.toJSONString(), DataTablesPageParam.class);
			searchParamMap.put(dtpp.getName(), dtpp.getValue());
		}

		String qname = null;
		String fieldname = null;
		Object fieldvalue = null;
		Map<String, Object> queryMap = new HashMap<String, Object>();
		for (String key : searchParamMap.keySet()) {
			fieldvalue = searchParamMap.get(key);
			if (!key.endsWith("_q") && !ObjectUtils.isEmpty(fieldvalue)) {
				fieldname = key.endsWith("_s") ? key.substring(0, key.length() - 2) : key;
				qname = (String) searchParamMap.get(fieldname + "_q");
				if(ObjectUtils.isEmpty(qname)) qname = "EQ";
				queryMap.put(fieldname, new Object[]{fieldvalue,
						Integer.valueOf(ConditionOpTypeEnum.valueOf(qname).getCode())});
			}
		}
		return queryMap;
	}
}
