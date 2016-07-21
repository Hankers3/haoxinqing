package com.aebiz.b2b2c.websiteoperation.symptomstructure.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.websiteoperation.symptomstructure.vo.SymptomStructureModel;
import com.aebiz.b2b2c.websiteoperation.symptomstructure.vo.SymptomStructureQueryModel;

public interface SymptomStructureService extends BaseService<SymptomStructureModel,SymptomStructureQueryModel>{
    /**
     * 
     * @Description: (导入症状的数据)    
     * @author XP  
     * @param files
     * @return
     * @date 2016-1-8 下午3:08:54
     */
   public List<Object> updloadExcel(MultipartFile[] files);

}
