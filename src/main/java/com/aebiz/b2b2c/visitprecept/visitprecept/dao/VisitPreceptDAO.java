package com.aebiz.b2b2c.visitprecept.visitprecept.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.visitprecept.visitprecept.vo.VisitPreceptModel;
import com.aebiz.b2b2c.visitprecept.visitprecept.vo.VisitPreceptQueryModel;

public interface VisitPreceptDAO extends BaseDAO<VisitPreceptModel, VisitPreceptQueryModel> {

	/**
	 * 根据医生的id获取所有的方案名称
	 * 
	 * @param doctorid
	 * @return
	 */
	public List<VisitPreceptModel> getAllVisitpreceptByDoctorid(String doctorid);

	/**
	 * 通过医生id获取医生的随访方案（非推荐）
	 *
	 * @param doctorid doctorid
	 * @return
	 */
	List<VisitPreceptModel> getRecommendVisitpreceptByDoctorid();

	/**
	 * 通过医生id获取医生的随访方案（非推荐）
	 *
	 * @param doctorid doctorid
	 * @return
	 */
	List<VisitPreceptModel> getMyVisitpreceptByDoctorid(String doctorid);

	/**
	 * 通过编号获取随访方案名称
	 * 
	 * @param preceptUuid
	 * @return 方案名称
	 */
	public String getPreceptNameByUUid(String preceptUuid);

	/**
	 * 根据医生的id获取所有的方案名称
	 * 
	 * @param doctorid
	 * @return
	 */
	public List<String> getAllVisitpreceptUuidsByDoctorid(String doctorid);

	/**
	 * 根据随访方案的id删除随访方案
	 * 
	 * @param visitUuid
	 */
	public void deleteVisitPreceptServiceByUuid(String visitUuid);

	/**
	 * 根据随访方案的id删除随访方案的数据
	 * 
	 * @param visitUuid
	 */
	public void deleteVisitPrecept(String visitUuid);

}