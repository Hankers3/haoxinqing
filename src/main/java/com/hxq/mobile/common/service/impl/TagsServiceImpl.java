package com.hxq.mobile.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hxq.mobile.common.service.TagsService;
import com.hxq.mobile.entity.common.Tags;
import com.hxq.mobile.util.repository.SpringJdbcSimpleEntityService;
import com.wxcommon.repository.SimpleBean2DBHelper;
import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.StringUtils;

/**
 * @author 作者 E-mail: liuyang
 * @date 创建时间：2016年5月28日 下午12:01:34
 * @version 2.0
 * @parameter
 * @since
 * @return
 */
@Service("com.hxq.mobile.common.service.imp.TagsService")
public class TagsServiceImpl extends SpringJdbcSimpleEntityService implements TagsService {

	@Override
	public Tags getTagsByUuid(String uuid) throws Exception {
		if (ObjectUtils.isEmpty(uuid)) {
			return null;
		}
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("SELECT UUID,tagName,oper,opeTime  FROM tags ");
		sbf.append(" WHERE delFlag=1 and uuid=?");
		List<Map<String, Object>> lst = dao.query(sbf.toString(), new Object[] { StringUtils.trimToEmpty(uuid) }, null,
				getQueryCache());
		return ObjectUtils.isEmpty(lst) ? null : SimpleBean2DBHelper.map2Bean(lst.get(0), Tags.class);
	}

	@Override
	public List<Tags> getTagsList(String uuidStr) throws Exception {

		List<Tags> tagList = new ArrayList<Tags>();
		if (!ObjectUtils.isEmpty(uuidStr)) {
			String[] tagUuids = uuidStr.split(";");
			if (tagUuids != null && tagUuids.length > 0) {
				for (int i = 0; i < tagUuids.length; i++) {
					Tags dt = null;
					String uuid = tagUuids[i];
					if (!ObjectUtils.isEmpty(uuid)) {
						dt = getTagsByUuid(uuid);

						tagList.add(dt);
					}

				}
			}
		}
		return tagList;

	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) throws Exception {
		return null;
	}

	@Override
	protected String getCacheName() {
		return "h1";
	}

	@Override
	protected String getQueryCacheName() {
		return null;
	}

}
