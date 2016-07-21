package com.aebiz.b2b2c.websiteoperation.symptomstructure.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.product.utils.ExcelUtils;
import com.aebiz.b2b2c.websiteoperation.customerdisease.vo.CustomerDiseaseModel;
import com.aebiz.b2b2c.websiteoperation.symptomstructure.vo.SymptomStructureModel;
import com.aebiz.b2b2c.websiteoperation.symptomstructure.vo.SymptomStructureQueryModel;

@Repository
public class SymptomStructureH4Impl extends BaseH4Impl<SymptomStructureModel,SymptomStructureQueryModel> implements SymptomStructureDAO {
    /**
     * 
     * @Description: (导入症状数据)    
     * @author XP  
     * @param files
     * @return
     * @date 2016-1-8 下午3:40:52
     */
    @Override
    public List<Object> updloadExcel(MultipartFile[] files) {
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                    if (file != null && !file.isEmpty()) {
                        SymptomStructureModel model = new SymptomStructureModel();
                            Object[] columns = { "firstClassification", "secondaryClassification",
                                            "symptom","breakdownSymptom"
                                           };
                            try {
                                    List list = ExcelUtils
                                                    .importExcel(file, model, columns);
                                    if (list != null && list.size() > 0) {
                                            return list;
                                    }
                            } catch (Exception e) {
                                    e.printStackTrace();
                            }
                    }
            }
    }
    return null;
    }

}
