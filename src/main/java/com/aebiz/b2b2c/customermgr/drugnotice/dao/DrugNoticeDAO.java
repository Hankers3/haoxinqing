package com.aebiz.b2b2c.customermgr.drugnotice.dao;

import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.customermgr.drugnotice.vo.DrugNoticeModel;
import com.aebiz.b2b2c.customermgr.drugnotice.vo.DrugNoticeQueryModel;

public interface DrugNoticeDAO extends BaseDAO<DrugNoticeModel,DrugNoticeQueryModel>{
    /**
     * 
     * @Description: (根据患者的id获取服药提醒的列表)    
     * @author XP  
     * @param customerUuid
     * @return
     * @date 2016-1-13 下午3:09:28
     */
   public List<DrugNoticeModel> getDrugNoticeListByCustomerUuid(String customerUuid);

}