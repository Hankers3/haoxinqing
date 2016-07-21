package com.aebiz.b2b2c.websiteoperation.customerdisease.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.websiteoperation.customerdisease.vo.CustomerDiseaseModel;
import com.aebiz.b2b2c.websiteoperation.customerdisease.vo.CustomerDiseaseQueryModel;

public interface CustomerDiseaseService extends BaseService<CustomerDiseaseModel,CustomerDiseaseQueryModel>{
    /**
     * 
     * @Description: (上传疾病模板)    
     * @author XP  
     * @param files
     * @return
     * @date 2016-1-8 上午11:48:51
     */
    public List<Object> updloadExcel(MultipartFile[] files);

}
