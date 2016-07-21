package com.hxq.mobile.support.dao;

import com.hxq.mobile.entity.common.AppPublish;
import com.hxq.mobile.util.repository.SimpleEntityDao;
import com.wxcommon.mybatis.MyBatisRepository;

import java.util.List;
import java.util.Map;

/**
 * Created by alice on 2016/3/1 0001
 */
@MyBatisRepository
public interface AppVersionDao extends SimpleEntityDao {
    public AppPublish selectVersion(String type);
    public List<AppPublish> selectLast(Map<String,Object> map);
}
