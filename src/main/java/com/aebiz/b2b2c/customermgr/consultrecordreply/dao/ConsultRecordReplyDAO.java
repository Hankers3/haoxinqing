package com.aebiz.b2b2c.customermgr.consultrecordreply.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.customermgr.consultrecordreply.vo.ConsultRecordReplyModel;
import com.aebiz.b2b2c.customermgr.consultrecordreply.vo.ConsultRecordReplyQueryModel;

public interface ConsultRecordReplyDAO extends BaseDAO<ConsultRecordReplyModel,ConsultRecordReplyQueryModel>{

  public  List<ConsultRecordReplyModel> getConsultRecordReplListByUuid(String uuid);

}