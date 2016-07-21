package com.aebiz.b2b2c.userfront.platimagecategory.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.userfront.platimagecategory.dao.PlatImageCategoryDAO;
import com.aebiz.b2b2c.userfront.platimagecategory.service.PlatImageCategoryService;
import com.aebiz.b2b2c.userfront.platimagecategory.vo.PlatImageCategoryModel;
import com.aebiz.b2b2c.userfront.platimagecategory.vo.PlatImageCategoryQueryModel;
import com.aebiz.b2b2c.userfront.platimagecategory.vo.TreeElement;

@Service
@Transactional
public class PlatImageCategoryServiceImpl extends BaseServiceImpl<PlatImageCategoryModel,PlatImageCategoryQueryModel> implements PlatImageCategoryService {
	private PlatImageCategoryDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(PlatImageCategoryDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(PlatImageCategoryModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	@Override
	public void update(PlatImageCategoryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	@Override
	public void delete(PlatImageCategoryModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 根据图片父分类uuid查询图片子分类
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<PlatImageCategoryModel> getSubPlatImageCategoryByParentUuid(String parentUuid) {
		return myDao.getSubPlatImageCategoryByParentUuid(parentUuid);
	}
	
	/**
	 * 得到下级分类列表根据父分类uuid
	 * @param parenUuid
	 * @return
	 */
	public List<TreeElement> getRootList(String parenUuid){
		List<TreeElement> nodes=new ArrayList<TreeElement>();
		List<PlatImageCategoryModel> list =this.getSubPlatImageCategoryByParentUuid(parenUuid);
		if(list==null || list.size()==0){
			return nodes;
		}
		for(int i=0;i<list.size();i++){
			PlatImageCategoryModel model=list.get(i);
			TreeElement ele=new TreeElement();
			ele.setKey(model.getUuid());
			ele.setTitle(model.getCategoryName());
			List<PlatImageCategoryModel> sublist =this.getSubPlatImageCategoryByParentUuid(model.getUuid());
			if(sublist!=null&&sublist.size()>0){
				ele.setIsLazy(true);
			}
			ele.setIsFolder(true);
			ele.setExpand(false);
			nodes.add(ele);
		}
		return nodes;
	}
	
	/**
	 * 获取父分类下的所有分类的uuid集合
	 * @param parentUuid
	 * @return 
	 * List<String>
	 */
	public List<String> getSubCategory(String parentUuid){
		return myDao.getSubCategory(parentUuid);
	}
}