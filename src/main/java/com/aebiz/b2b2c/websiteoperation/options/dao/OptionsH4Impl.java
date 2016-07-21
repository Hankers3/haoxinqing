package com.aebiz.b2b2c.websiteoperation.options.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsModel;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsQueryModel;

@Repository
public class OptionsH4Impl extends BaseH4Impl<OptionsModel, OptionsQueryModel>
		implements OptionsDAO {

	/**
	 * 删除某个试题的所有选项
	 * 
	 * 在编辑和保存试题的时候，需要删除所有的选项，再根据页面提交的数据进行保存
	 * 
	 * @param questionUuid
	 */
	public void removePreOptions(String questionUuid) {
		StringBuffer hql = new StringBuffer(
				" delete from OptionsModel om where om.questionUuid=:questionUuid ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("questionUuid", questionUuid);

		query.executeUpdate();
	}

	/**
	 * 通过试题的UUID查询试题的所有选项
	 * 
	 * @param questionUuid
	 */
	public List<OptionsModel> getOptionsByQuestionUuid(String questionUuid) {
		StringBuffer hql = new StringBuffer(
				" from OptionsModel where questionUuid=:questionUuid order by createTime asc ");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("questionUuid", questionUuid);

		List<OptionsModel> omList = query.list();
		return omList;
	}

	/**
	 * 根据试题编号删除选项
	 */
	@Override
	public void deleteByQuestionUuid(String questionUuid) {
		StringBuffer hql = new StringBuffer(
				" delete from OptionsModel where questionUuid=:questionUuid ");
		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("questionUuid", questionUuid);

		query.executeUpdate();

	}
	/**
	 * 根据试题的id获取提问的uuids
	 */
    @Override
    public List<String> getAllOptionsModelUuids(String questionUuid) {
        StringBuffer hql = new StringBuffer(
                "select crm.uuid from OptionsModel crm where crm.questionUuid=:questionUuid order by crm.createTime asc ");
            Query query = this.getH4Session().createQuery(hql.toString());
            query.setString("questionUuid", questionUuid);
            List<String> list = query.list();
            if (list != null && list.size() > 0) {
                    return list;
            }
            return null;
    }
}
