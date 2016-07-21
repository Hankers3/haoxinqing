package com.aebiz.b2b2c.cms.content.outerdata;

import java.util.List;

/**
 * Created by sunchao on 15/12/23 and { 外部数据源接口 }
 */

public interface OuterDataSource<T> {
    /**
     * 拉取外部资源
     */
    public List<T> pullOuterData();

    /**
     * getLastTime
     */
    public String getLastTimestamp();

    /**
     * saveLastTime
     */
    public void saveLastTimestamp(String timestamp);
}
