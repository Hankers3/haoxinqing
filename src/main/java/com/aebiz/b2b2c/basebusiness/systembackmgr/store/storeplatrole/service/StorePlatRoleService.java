package com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrole.service;

import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrole.vo.StorePlatRoleModel;
import com.aebiz.b2b2c.basebusiness.systembackmgr.store.storeplatrole.vo.StorePlatRoleQueryModel;
import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;

public interface StorePlatRoleService extends
		BaseService<StorePlatRoleModel, StorePlatRoleQueryModel> {

	/**
	 * 检查角色编号是否存在
	 * 
	 * 1.当添加时，传入的uuid为空，此时只需要检查数据库中是否存在 <br />
	 * 2.当编辑时，传入的uuid不为空，此事在检查数据库中时，需要排除当前本身
	 * 
	 * @param uuid
	 * @param roleId
	 * @return
	 */
	public boolean check(String uuid, String roleId);
}
