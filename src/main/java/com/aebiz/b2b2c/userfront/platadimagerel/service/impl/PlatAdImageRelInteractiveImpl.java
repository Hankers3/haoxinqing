package com.aebiz.b2b2c.userfront.platadimagerel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.userfront.platadimagerel.service.PlatAdImageRelInteractive;
import com.aebiz.b2b2c.userfront.platadimagerel.service.PlatAdImageRelService;
import com.aebiz.b2b2c.userfront.platadimagerel.dao.PlatAdImageRelDAO;
import com.aebiz.b2b2c.userfront.platadimagerel.vo.PlatAdImageRelModel;
import com.aebiz.b2b2c.userfront.platadimagerel.vo.PlatAdImageRelQueryModel;
import com.aebiz.b2b2c.userfront.plattimagelibrary.service.PlattImageLibraryService;
import com.aebiz.b2b2c.userfront.plattimagelibrary.vo.PlattImageLibraryModel;
/**
 * 添加广告与图片的关系时,需要把图片的显示位置保存，<br>
 * 
 * 存储图片的显示位置,为了在前台展示时可以调整顺序<br>
 * 
 * 编辑关联的图片或者调整图片位置时,需要把原有的关联关系删除,然后重新添加数据库数据
 *
 * @author tangyunkai
 *
 * @date 2015年1月7日 下午8:15:32 
 *
 */
@Service
@Transactional
public class PlatAdImageRelInteractiveImpl extends BaseServiceImpl<PlatAdImageRelModel,PlatAdImageRelQueryModel> implements PlatAdImageRelInteractive {
	private PlatAdImageRelDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(PlatAdImageRelDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	//注入图片的service
	@Autowired
	private PlattImageLibraryService imageLibraryService;
	/**
	 * 添加时,先清空已经关联的所有的关联关系,在进行添加
	 */
	@Override
	public String create(PlatAdImageRelModel m) {
		//获取该广告关联的所有的关联关系的uuid
		List relUuids = this.getPlatAdImageRelModelUuidsByAdUuid(m.getAdUuid());
		if(relUuids != null && relUuids.size() > 0){
			this.deletes(relUuids);
		}
		
		String[] imageUuids = m.getUuids();
		String[] urls = m.getUrls();
		if(imageUuids != null && imageUuids.length >0 && urls != null && urls.length > 0){
			for (int i = 0; i < imageUuids.length; i++) {
				
				PlatAdImageRelModel model = new PlatAdImageRelModel();
				model.setUuid(us.getNextUuid());
				model.setDelFlag(BaseModel.DEL_FLAG_VALID);
				model.setOper(LoginUserHelper.getLoginUserUuid());
				model.setOpeTime(DateFormatHelper.getNowTimeStr());
				
				model.setAdUuid(m.getAdUuid());
				//图片的uuif
				model.setImageUuid(imageUuids[i]);
				
				// 保存图片在数据库中的地址
				PlattImageLibraryModel imageLibraryModel = imageLibraryService.getByUuid(imageUuids[i]);
				if(imageLibraryModel != null){
					model.setImageUrl(imageLibraryModel.getImagePath());
				}
				//设置的图片的链接地址
				model.setUrl(urls[i]);
				//存储图片的显示位置,为了在前台展示时可以调整顺序
				model.setPosition(i);
				
				super.create(model);
			}
		}
		
		return "";
	}
	
	/**
	 * 根据广告的uuid获取该广告关联的所有关联关系的uuid
	 * @param adUuid
	 * @return 
	 * List<String>
	 */
	public List<String> getPlatAdImageRelModelUuidsByAdUuid(String adUuid){
		return myDao.getPlatAdImageRelModelUuidsByAdUuid(adUuid);
	}
	
	/**
	 * 根据广告的uuid获取该广告关联的所有的图片
	 * @param adUuid
	 * @return 
	 * List<PlatAdImageRelModel>
	 */
	public List<PlatAdImageRelModel> getPlatAdImageRelModelsByAdUuid(String adUuid){
		return myDao.getPlatAdImageRelModelsByAdUuid(adUuid);
	}


}