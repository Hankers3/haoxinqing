package com.aebiz.b2b2c.cms.content.service;

import com.aebiz.b2b2c.cms.content.outerdata.OuterDataSource;

/**
 * Created by sunchao on 15/12/23 and { 获取外部content数据公用接口 }
 */
public interface OuterDataService {

    /*
    * 添加外部输入来源
    * */
    public void addOuterSource(OuterDataSource source);

    /*
    * 更新外部数据
    * */
    public void updateData();
}
