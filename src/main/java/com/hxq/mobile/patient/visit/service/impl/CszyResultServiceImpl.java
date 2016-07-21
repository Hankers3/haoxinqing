package com.hxq.mobile.patient.visit.service.impl;

import com.hxq.mobile.weixin.repository.CszyResultDao;
import com.hxq.mobile.entity.weixin.ProfessionResult;
import com.hxq.mobile.patient.visit.service.CszyResultService;
import com.hxq.mobile.util.repository.AbstractService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("com.wxsupport.web.service.CszyResultService")
public class CszyResultServiceImpl extends AbstractService<ProfessionResult, String> implements CszyResultService {

	@Autowired
    private CszyResultDao cszyResultDao;

	@Autowired
	public void setBaseDao() {
       super.setBaseDao(cszyResultDao);
	}

	/*通过患者id得到自评的历史记录*/
	@Override
	public List<Map<String, Object>> selectHistory(String customerUuid) {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("select a.complete_date , a.score,b.result,b.analys,a.usrfrm")
		.append(" from cs_zy_result as a LEFT JOIN cs_zy_evaluation as b on a.subject_id=b.subject_id")
		.append(" and a.score >= b.min_value and a.score <= b.max_value")
		.append(" where openid=?  and usrfrm='1' order by complete_date desc");
		//List<Map<String,Object>> lstReturn = dao.query(sbf.toString(), new Object[]{customerUuid}, null);
		return null;
		
	}
}
