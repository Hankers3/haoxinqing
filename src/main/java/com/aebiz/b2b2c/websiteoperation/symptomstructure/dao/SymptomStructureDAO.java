package com.aebiz.b2b2c.websiteoperation.symptomstructure.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.websiteoperation.symptomstructure.vo.SymptomStructureModel;
import com.aebiz.b2b2c.websiteoperation.symptomstructure.vo.SymptomStructureQueryModel;

public interface SymptomStructureDAO extends BaseDAO<SymptomStructureModel,SymptomStructureQueryModel>{
    /**
     * 
     * @Description: (导入症状数据)    
     * @author XP  
     * @param files
     * @return
     * @date 2016-1-8 下午3:10:33
     */
   public List<Object> updloadExcel(MultipartFile[] files);

}