package com.hxq.mobile.activecode.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.activecode.service.ActiveCodeService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.pagination.PaginationHelper;
import com.wxcommon.util.DateUtils;
import com.wxcommon.util.MathUtils;

@Service("com.hxq.mobile.activecode.service.ActiveCodeService")
public class ActiveCodeServiceImpl extends SpringJdbcSimpleEntityService implements ActiveCodeService {

    @Override
    public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
        return null;
    }

    @Override
    protected String getCacheName() {
        return null;
    }

    @Override
    protected String getQueryCacheName() {
        return null;
    }

	@Override
	public Object[] selectActiveCodeInfo(Map<String, Object> map) {
		StringBuffer sbf = new StringBuffer(1000);
		List<Object> info = new ArrayList<Object>();

		sbf.append(" FROM (").append("SELECT a.activeCode, b.usr,1 as allnum,")
		.append(" CASE c.professional WHEN '主任医师' THEN 1 WHEN '副主任医师' THEN 1 WHEN '1' THEN 1 WHEN '2' THEN 1 ELSE 0 END AS A,")// 主任医师或副主任医师或1或2时，增加1
		.append(" CASE c.professional WHEN '主治医师' THEN 1 WHEN '住院医师' THEN 1 WHEN '3' THEN 1 WHEN '4' THEN 1 ELSE 0 END AS B,")// 主治医师或住院医师或3或4时，增加1
		.append(" CASE c.professional WHEN '助理医师' THEN 1 WHEN '实习医师' THEN 1 WHEN '5' THEN 1 WHEN '6' THEN 1 ELSE 0 END AS C,")// 助理医师或实习医师或5或6时，增加1
		.append(" CASE c.professional WHEN '护师（士）' THEN 1 WHEN '心理咨询师' THEN 1 WHEN '7' THEN 1 WHEN '8' THEN 1 ELSE 0 END AS D,")// 护师（士）或心理咨询师或7或8时，增加1
		.append(" CASE c.professional WHEN '社工师' THEN 1 WHEN '药剂师' THEN 1 WHEN '9' THEN 1 WHEN '10' THEN 1 ELSE 0 END AS E,")// 社工师或药剂师或9或10时，增加1
		.append(" CASE c.professional ")
		.append(" WHEN '主任医师' THEN 0 WHEN '副主任医师' THEN 0 WHEN '1' THEN 0 WHEN '2' THEN 0")
		.append(" WHEN '主治医师' THEN 0 WHEN '住院医师' THEN 0 WHEN '3' THEN 0 WHEN '4' THEN 0")
		.append(" WHEN '助理医师' THEN 0 WHEN '实习医师' THEN 0 WHEN '5' THEN 0 WHEN '6' THEN 0")
		.append(" WHEN '护师（士）' THEN 0 WHEN '心理咨询师' THEN 0 WHEN '7' THEN 0 WHEN '8' THEN 0")
		.append(" WHEN '社工师' THEN 0 WHEN '药剂师' THEN 0 WHEN '9' THEN 0 WHEN '10' THEN 0 ").append(" ELSE 1")
		.append(" END AS F ").append("FROM service_staff as a")
		.append(" LEFT JOIN active_code as b ON a.activeCode=b.code")
		.append(" LEFT JOIN service_staff_info as c ON a.uuid=c.serviceStaffUuid")
		.append(" WHERE a.activeCode > ''").append(" AND a.sate IN('0','1','3')");
		if (!RequestUtil.isEmpty(map, "beginTime")) {
			sbf.append(" AND a.createTime >= ?");
			info.add(DateUtils.parseDate(RequestUtil.getFormValue(map, "beginTime")));
		}
		if (!RequestUtil.isEmpty(map, "endTime")) {
			sbf.append(" AND  a.createTime < ?");
			info.add(DateUtils.parseDate(RequestUtil.getFormValue(map, "endTime")));
		}
		if (!RequestUtil.isEmpty(map, "activecode")) {
			sbf.append(" AND a.activeCode LIKE ?");
			info.add((new StringBuffer(RequestUtil.getFormValue(map, "activecode"))).append("%").toString());
		}
		if (!RequestUtil.isEmpty(map, "doctorName")) {
			sbf.append(" AND b.usr LIKE ?");
			info.add((new StringBuffer(RequestUtil.getFormValue(map, "doctorName"))).append("%").toString());
		}
		sbf.append(") AS TB_1 GROUP BY activeCode, usr");

		String strWhere = sbf.toString();

		sbf.delete(0, sbf.length());
		String strTotal = sbf.append("select count(1) from ( Select 1 ").append(strWhere).append(") as tab_a").toString();

		sbf.delete(0, sbf.length());
		sbf.append("SELECT activeCode, IFNULL(usr,'') as usr, SUM(allnum) as allnum, SUM(A) as aNumber,")
		.append(" SUM(B) as bNumber, SUM(C) as cNumber, SUM(D) as dNumber, SUM(E) as eNumber, SUM(F) as fNumber").append(strWhere);
		if (!RequestUtil.isEmpty(map, "SortName") && !RequestUtil.isEmpty(map, "SortType")) {
			sbf.append(" ORDER BY ").append(RequestUtil.getFormValue(map, "SortName"))
			.append(" ").append(RequestUtil.getFormValue(map, "SortType"));
		}

		String strQuery = sbf.toString();
		Object[] params = info.isEmpty() ? null :info.toArray();
		int pagesize = MathUtils.toInt(RequestUtil.getFormValue(map, "iDisplayLength"), 10);
		int pageno = (MathUtils.toInt(RequestUtil.getFormValue(map, "iDisplayStart"), 0) + pagesize) / pagesize;
		PaginationHelper page = new PaginationHelper(dao, getQueryCache(), pageno, pagesize);
		List<Map<String, Object>> lst = page.queryByPage(strTotal, strQuery, params, null);
		return new Object[] { lst, page.getPageNo(), page.getTotalPage(), page.getTotalRows() };
	}

    @Override
    public Object[] selectDetails(Map<String, Object> map) {
    	if(RequestUtil.isEmpty(map, "activeCode")) return null;

        StringBuffer sbf = new StringBuffer(1000);
		List<Object> args = new ArrayList<Object>();

		sbf.append(" FROM service_staff AS a")
        .append(" LEFT JOIN service_staff_info AS b ON a.uuid=b.serviceStaffUuid")
        .append(" LEFT JOIN active_code AS c ON a.activeCode=c.code")
        .append(" LEFT JOIN basicdata_area_province AS d ON b.province=d.uuid")
        .append(" LEFT JOIN basicdata_area_city AS e ON b.city=e.uuid")
        .append(" LEFT JOIN basicdata_area_region AS f ON b.region=f.uuid")
        .append(" LEFT JOIN hospital_info AS g ON a.hospital=g.uuid")
        .append(" LEFT JOIN department_info AS h ON a.department=h.uuid")
        .append(" WHERE a.activeCode=? AND a.sate IN('0','1','3')");
        args.add(RequestUtil.getFormValue(map, "activeCode"));

        if (!RequestUtil.isEmpty(map, "doctorName")) {
            sbf.append(" AND a.realName LIKE ?");
            args.add((new StringBuffer(RequestUtil.getFormValue(map,"doctorName"))).append("%").toString());
        }
        if (!RequestUtil.isEmpty(map, "beginTime")) {
            sbf.append(" AND a.createTime >= ?");
            args.add(DateUtils.parseDate(RequestUtil.getFormValue(map,"beginTime")));
        }
        if (!RequestUtil.isEmpty(map, "endTime")) {
            sbf.append(" AND  a.createTime < ?");
            args.add(DateUtils.parseDate(RequestUtil.getFormValue(map,"endTime")));
        }
		if (!RequestUtil.isEmpty(map, "tag")) {
			String tag = RequestUtil.getFormValue(map, "tag");
			if (tag.equals("A")){
				sbf.append(" AND b.professional IN ('主任医师','副主任医师','1','2')");
			}else if (tag.equals("B")){
				sbf.append(" AND b.professional IN ('主治医师','住院医师','3','4')");
			}else if (tag.equals("C")){
				sbf.append(" AND b.professional IN ('助理医师','实习医师','5','6')");
			}else {
				sbf.append(" AND ISNULL(b.professional)");
			}
		}
		String strWhere = sbf.toString();

		//查询总数
		sbf.delete(0, sbf.length());
		String strTotal = sbf.append("select count(1)").append(strWhere).toString();

		sbf.delete(0, sbf.length());
		sbf.append("SELECT IFNULL(a.realName,'') as realName,")
        .append(" IFNULL(b.professional,'') as professional,")
        .append(" IFNULL(a.departmentLine,'') as departmentLine,")
        .append(" IFNULL(a.createTime,'') as createTime,")
        .append(" IFNULL(d.provinceName, IFNULL(b.province,'')) as province,")
        .append(" IFNULL(e.cityName, IFNULL(b.city,'')) as city,")
        .append(" IFNULL(f.regionName, IFNULL(b.region,'')) as region,")
        .append(" IFNULL(g.hospitalName, IFNULL(a.hospital,'')) as hospital,")
        .append(" IFNULL(h.departmentName, IFNULL(a.department,'')) as department")
        .append(strWhere);
		String strQuery = sbf.toString();

		Object[] params = args.isEmpty() ? null : args.toArray();
		int pagesize = MathUtils.toInt(RequestUtil.getFormValue(map, "iDisplayLength"), 20);
		int pageno = (MathUtils.toInt(RequestUtil.getFormValue(map, "iDisplayStart"), 0) + pagesize) / pagesize;
		PaginationHelper page = new PaginationHelper(dao, getQueryCache(), pageno, pagesize);
		List<Map<String, Object>> lst = page.queryByPage(strTotal, strQuery, params, null);
		return new Object[] { lst, page.getPageNo(), page.getTotalPage(), page.getTotalRows() };
    }
}
