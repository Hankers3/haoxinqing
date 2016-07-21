package com.aebiz.b2b2c.websiteoperation.customerdisease.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.product.utils.ExcelUtils;
import com.aebiz.b2b2c.websiteoperation.customerdisease.vo.CustomerDiseaseModel;
import com.aebiz.b2b2c.websiteoperation.customerdisease.vo.CustomerDiseaseQueryModel;

@Repository
public class CustomerDiseaseH4Impl extends BaseH4Impl<CustomerDiseaseModel,CustomerDiseaseQueryModel> implements CustomerDiseaseDAO {

        @Override
        public List<Object> updloadExcel(MultipartFile[] files) {
            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                        if (file != null && !file.isEmpty()) {
                            CustomerDiseaseModel model = new CustomerDiseaseModel();
                                Object[] columns = { "diseaseCategory", "diseaseName",
                                                "diseaseDescription", "pathogeny",
                                                "clinicalFeature", "remedy",
                                                "morbidity", "precaution"
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
