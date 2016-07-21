package com.aebiz.b2b2c.product.productcategoryplatform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.product.productcategoryplatform.dao.ProductCategoryPlatformDAO;
import com.aebiz.b2b2c.product.productcategoryplatform.service.ProductCategoryPlatformService;
import com.aebiz.b2b2c.product.productcategoryplatform.vo.ProductCategoryPlatformModel;
import com.aebiz.b2b2c.product.productcategoryplatform.vo.ProductCategoryPlatformQueryModel;
/**
 * 平台分类业务接口
 * 1.平台分类用于发布商品时选择分类
 * 2.平台分类用于创建商户时,选择商户所属分类
 * 3.分类还得关联模板/品牌/标签分类
 * 
 * @author huangpinpin
 *
 */
@Service
@Transactional
public class ProductCategoryPlatformServiceImpl extends BaseServiceImpl<ProductCategoryPlatformModel,ProductCategoryPlatformQueryModel> implements ProductCategoryPlatformService {
	// fullpath长度
	public static final int FULLPATH_LENGTH = 33;
	
	private ProductCategoryPlatformDAO myDao = null;
	@Autowired
	private UuidService us;
	@Autowired
	public void setMyDao(ProductCategoryPlatformDAO dao){
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProductCategoryPlatformModel m) {
		m.setUuid(us.getNextUuid());
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		
		String ret = super.create(m);
		return ret;
	}
	
	@Override
	public void update(ProductCategoryPlatformModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	@Override
	public void delete(ProductCategoryPlatformModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
		List<ProductCategoryPlatformModel> subList=this.getSubProductCategoryByParentUuid(m.getUuid());
	}

	/**
	 * 删除分类包括其子所有分类
	 */
	@Override
	public void deletes(List<String> needDeleteUuids) {
		myDao.deletes(needDeleteUuids);
		for(int i=0;i<needDeleteUuids.size();i++){
			String uuid=needDeleteUuids.get(i);
			List<String> subUuids=this.getSubProductCategoryUuidByParentUuid(uuid);
			if(subUuids==null || subUuids.size()==0){//没有子分类
				return ;
			}
			deletes(subUuids);
		}
		
	}
	
	/**
	 * 根据平台父分类uuid查询子分类
	 * 该功能用于分类树展开时查询子分类
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<ProductCategoryPlatformModel> getSubProductCategoryByParentUuid(String parentUuid) {
		return myDao.getSubProductCategoryByParentUuid(parentUuid);
	}
	
	/**
	 * 根据平台父分类uuid查询子分类uuid
	 * @param parentUuid 父分类uuid
	 * @return
	 */
	public List<String> getSubProductCategoryUuidByParentUuid(String parentUuid) {
		return myDao.getSubProductCategoryUuidByParentUuid(parentUuid);
	}
	
	/**
	 * 根据fullpath查询分类集合
	 * @param fullPath
	 * @return
	 */
	public List<ProductCategoryPlatformModel> getProductCategoryPlatformModelByFullPath(String fullPath){
		return myDao.getProductCategoryPlatformModelByFullPath(fullPath);
	}
	
	/**
	 * 生成fullpath
	 * @param parentId
	 * @param sheetIdNo
	 * @return
	 */
	public String getSheetNo(String parentId,int sheetIdNo){
		if (sheetIdNo <= 0)
			sheetIdNo = 1;
		String sheetId = convertSheetIdNo(parentId, sheetIdNo);

		List list = myDao.getProductCategoryPlatformModelByFullPath(sheetId);
		if (list != null && list.size() > 0) {
			return getSheetNo( parentId, ++sheetIdNo);
		}

		return sheetId;
	}

	/**
	 * fullpath规则
	 * @param parentId
	 * @param sheetIdNo
	 * @return
	 */
	public String convertSheetIdNo(String parentId, int sheetIdNo) {

		String parentPex = "";
		int breakIndex = -1;
		if (!StringUtil.isEmpty(parentId)) {
			int length = FULLPATH_LENGTH / 3;
			for (int i = 0; i <= length - 1; i++) {
				int startIndex = i * 3;
				int endIndex = (i + 1) * 3;

				String value = parentId.substring(startIndex, endIndex);

				if (Integer.parseInt(value) == 0) {
					breakIndex = i - 1;

					break;
				}
			}
		}

		parentPex = parentId.substring(0, (breakIndex + 1) * 3);

		int zeronum = 3 - ((sheetIdNo + "").length());

		String currentId = "";

		for (int i = 0; i < zeronum; i++) {
			currentId = currentId + "0";
		}

		currentId = currentId + sheetIdNo;

		String returnStr = parentPex + currentId;
		// zeronum = FULLPATH_LENGTH - (breakIndex + 1) * 3;
		zeronum = FULLPATH_LENGTH - returnStr.length();

		for (int i = 0; i < zeronum; i++) {
			returnStr = returnStr + "0";
		}

		return returnStr;
	}
	
	
	/**
	 * 批量添加分类
	 * @param list
	 */
	public void addBatch(List<ProductCategoryPlatformModel> list){
		if(list==null || list.size()==0){
			return;
		}
		for(int i=0;i<list.size();i++){
			ProductCategoryPlatformModel m=list.get(i);
			this.create(m);
		}
	}
	
	/**
	 * 根据分类uuid获取分类名称
	 * @param uuid 分类uuid
	 * @return
	 */
	public String getNameByCategoryUuid(String uuid){
		return myDao.getNameByCategoryUuid(uuid);
	}
	
	
	/**
	 * 获取分类路径全名称 例：通用分类>电子>手机
	 * @param uuid 分类uuid
	 * @param parentName 
	 * @return
	 */
	public String getAllNameByUuid(String uuid,String parentName){
		ProductCategoryPlatformModel categoryModel=getByUuid(uuid);
		
		if(categoryModel!=null){
			
			if(StringUtil.isEmpty(categoryModel.getParentUuid())){
				parentName=categoryModel.getCategoryName()+">"+parentName;
				return parentName;
			}else{
				if(!StringUtil.isEmpty(parentName)){
					parentName=categoryModel.getCategoryName()+">"+parentName;
				}else{
					parentName=categoryModel.getCategoryName();
				}
				
				return getAllNameByUuid(categoryModel.getParentUuid(), parentName);
			}
		}
		
		return "";
	}
	
	/**
	 * 对外接口：根据集合uuids 查出 分类集合
	 * @param uuids
	 * @return
	 */
	public Map<String, ProductCategoryPlatformModel> getCategorysByUuids(List<String> uuids){
		Map<String, ProductCategoryPlatformModel> reMap=new HashMap<String, ProductCategoryPlatformModel>();
		
		List<ProductCategoryPlatformModel> list=myDao.getCategorysByUuids(uuids);
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				ProductCategoryPlatformModel m=list.get(i);
				reMap.put(m.getUuid(), m);
			}
		}
		return reMap;
	}
	
}