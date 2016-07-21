package com.aebiz.b2b2c.basicdata.operatelog.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.basicdata.operatelog.vo.OperateLogModel;
import com.aebiz.b2b2c.basicdata.operatelog.vo.OperateLogQueryModel;
@Repository
public class OperateLogH4Impl  extends BaseH4Impl<OperateLogModel, OperateLogQueryModel> implements OperateLogDAO  {

}
