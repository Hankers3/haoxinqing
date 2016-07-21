package com.aebiz.b2b2c.customermgr.storeback.service.customerstorelevel;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;
import com.aebiz.b2b2c.customermgr.customerstorelevel.vo.CustomerStoreLevelModel;
import com.aebiz.b2b2c.customermgr.customerstorelevel.vo.CustomerStoreLevelQueryModel;

public interface CustomerStoreLevelService extends
		BaseService<CustomerStoreLevelModel, CustomerStoreLevelQueryModel> {
	/**
	 * 校验商户会员等级名称是否存在
	 * 
	 * @param levelName
	 *            等级名称
	 * @return
	 */
	public boolean checkLevelNameExist(String levelName, String uuid);

	/**
	 * 根据商户编号获取商户会员等级的集合
	 * 
	 * @param storeUuid
	 *            商户编号
	 * @return
	 */
	public List<CustomerStoreLevelModel> getStoreLevelModelList(String storeUuid);

	/**
	 * 上传商户会员等级图标
	 * 
	 * @param customerStoreLevelModel
	 * @param files
	 * @return
	 */
	public CustomerStoreLevelModel uploadImage(
			CustomerStoreLevelModel customerStoreLevelModel,
			MultipartFile[] files);

}
