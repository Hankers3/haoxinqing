package com.aebiz.b2b2c.websiteoperation.customerdisease.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseDAO;
import com.aebiz.b2b2c.websiteoperation.customerdisease.vo.CustomerDiseaseModel;
import com.aebiz.b2b2c.websiteoperation.customerdisease.vo.CustomerDiseaseQueryModel;

public interface CustomerDiseaseDAO extends BaseDAO<CustomerDiseaseModel,CustomerDiseaseQueryModel>{
    /**
     * 
     * @Description: (上传疾病的模板)    
     * @author XP  
     * @param files
     * @return
     * @date 2016-1-8 上午11:50:09
     */
   public List<Object> updloadExcel(MultipartFile[] files);

}