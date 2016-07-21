package com.hxq.mobile.doctor.content.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.doctor.content.service.UseTheManualService;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.pagination.PaginationHelper;
import com.wxcommon.util.MathUtils;
import com.wxcommon.util.ObjectUtils;

@Service("com.hxq.mobile.doctor.content.service.UseTheManualService")
public class UseTheManualServiceImpl extends SpringJdbcSimpleEntityService implements UseTheManualService {

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {return null;}
	@Override
	protected String getCacheName() {return "h1";}
	@Override
	protected String getQueryCacheName() {	return "ContentService";}
	
	public static String ProductMainSql = "uuid,delFlag,opeTime,oper,auditState,createTime,integral,productName,productNo,productType,state,leastIntegral,"
			+ "brandUuid,addiction,commonremedy,dangerousDrugReaction,drugReaction,forbidden,longRun,mechanismAction,mildDrugReaction,overDose,pharmacokinetics,"
			+ "productEnglishName,specialPopulations,stopMedicine,targets,position,dosage,direction,renalFunctionDamage,liverFunctionDamage,cardiacDysfunction,"
			+ "oldPeople,youngsters,conception,suckle,image,laboratorExamination,attention,drugInteractio ";
	
	@Override
	public List<Map<String, Object>> selectProductMainListByName(String medicineName, Integer pageCount, Integer pageNo) {
		StringBuffer sbf = new StringBuffer(1000);
		List<Object> info = new ArrayList<Object>();
		
		sbf.append(" from product_main ");

		if (!ObjectUtils.isEmpty(medicineName)) {
			sbf.append(" where productName like '%").append(medicineName).append("%'");
		}
		
		String strWhere = sbf.toString();
		sbf.delete(0, sbf.length());
		String strTotal = sbf.append("select count(1)").append(strWhere).toString();
		
		sbf.delete(0, sbf.length());
		String strQuery = sbf.append(" select ").append(ProductMainSql).append(strWhere).toString();
		Object[] params = info.isEmpty() ? null :info.toArray();
		
		PaginationHelper page = new PaginationHelper(dao, getQueryCache(), MathUtils.toInt(pageNo,1), MathUtils.toInt(pageCount,20));
		return page.queryByPage(strTotal, strQuery, params, null); 
	}
	@Override
	public Map<String, Object> selectProductMainDescriptionByid(String productUuid) {
		StringBuffer sqlBuff = new StringBuffer(1000);
		sqlBuff.append(" select uuid from product_main_description where productUuid = ? and delFlag= '1' ");
		List<String> staff = new ArrayList<String>();
		staff.add(productUuid);
		Object[] ProductMainInfo = staff.isEmpty() ? null : staff.toArray();
		List<Map<String, Object>> staffReturn = dao.query(sqlBuff.toString(), ProductMainInfo, null, getQueryCache());
		return (ObjectUtils.isEmpty(staffReturn) ? null : staffReturn.get(0));
	}
}
