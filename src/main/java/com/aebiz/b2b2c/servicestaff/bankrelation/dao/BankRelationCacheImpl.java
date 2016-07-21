package com.aebiz.b2b2c.servicestaff.bankrelation.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.servicestaff.bankrelation.vo.BankRelationModel;
import com.aebiz.b2b2c.servicestaff.bankrelation.vo.BankRelationQueryModel;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportModel;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightModel;
import com.aebiz.b2b2c.servicestaff.doctorright.vo.DoctorRightQueryModel;
import com.aebiz.b2b2c.servicestaff.doctortelecoun.vo.DoctorTeleCounModel;
import com.aebiz.b2b2c.servicestaff.doctortelecoun.vo.DoctorTeleCounQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生关联银行卡的缓存)    
 * @author XP  
 * @date 2015-12-29 下午1:36:40
 */
@Primary
@Repository
public class BankRelationCacheImpl extends
		BaseMemcachedCache<BankRelationModel, BankRelationQueryModel> implements
		BankRelationDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private BankRelationDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public BankRelationCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_BANKRELATION_KEY);
	}
    
        @Override
        public List<BankRelationModel> getAllBankByDoctorUuid(String doctorUuid) {
            return dao.getAllBankByDoctorUuid(doctorUuid);
        }
        /**
         * 
         * @Description: (医生删除银行绑定的银行卡)    
         * @author XP  
         * @param doctorUuid
         * @param bankCode
         * @date 2015-12-31 上午10:53:04
         */
        @Override
        public void deleteBankCardByDoctorUuidAndBankUuid(String doctorUuid, String bankCode) {
            dao.deleteBankCardByDoctorUuidAndBankUuid(doctorUuid, bankCode);
            mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_BANKRELATION_KEY+bankCode);
        }
        
        @Override
        public BankRelationModel getModelByDoctorUuidAndBankUuid(String bankUuid, String doctorUuid) {
            return dao.getModelByDoctorUuidAndBankUuid(bankUuid, doctorUuid);
        }
        /**
         * 
         * @Description: (根据医生的id获取uuids)    
         * @author XP  
         * @param doctorUuid
         * @return
         * @date 2015-12-31 上午10:55:03
         */
        @Override
        public List<String> getUuidsByDoctorUuid(String doctorUuid) {
            return dao.getUuidsByDoctorUuid(doctorUuid);
        }
        /**
         * 
         * @Description: (根据医生和银行获取医生的银行卡的model)    
         * @author XP  
         * @param bankUuid
         * @param doctorUuid
         * @return
         * @date 2015-12-31 上午10:56:06
         */
        @Override
        public String getBankRelationModelUuid(String bankUuid, String doctorUuid) {
            return dao.getBankRelationModelUuid(bankUuid, doctorUuid);
        }
        /**
         * 
         * @Description: (根据银行卡的)    
         * @author XP  
         * @param doctorUuid
         * @param bankCode
         * @return
         * @date 2015-12-31 上午10:57:02
         */
        @Override
        public String getBankRelationUuid(String doctorUuid, String bankCode) {
            return dao.getBankRelationUuid(doctorUuid, bankCode);
        }
        /**
         * 
         * @Description: (根据银行卡的卡号获取关联model的缓存)    
         * @author XP  
         * @param bankCode
         * @return
         * @date 2015-12-31 上午10:58:30
         */
        @Override
        public BankRelationModel getByBankCode(String bankCode) {
            return dao.getByBankCode(bankCode);
        }

	
}
