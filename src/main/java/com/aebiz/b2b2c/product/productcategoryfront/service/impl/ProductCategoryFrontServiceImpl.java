package com.aebiz.b2b2c.product.productcategoryfront.service.impl;

import java.util.ArrayList;
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
import com.aebiz.b2b2c.product.frontplatformcategorylog.service.FrontPlatformCategoryLogService;
import com.aebiz.b2b2c.product.frontplatformcategorylog.vo.FrontPlatformCategoryLogModel;
import com.aebiz.b2b2c.product.frontproductcatetemprel.service.FrontProductCateTempRelService;
import com.aebiz.b2b2c.product.productcategoryfront.dao.ProductCategoryFrontDAO;
import com.aebiz.b2b2c.product.productcategoryfront.service.ProductCategoryFrontService;
import com.aebiz.b2b2c.product.productcategoryfront.vo.ProductCategoryFrontModel;
import com.aebiz.b2b2c.product.productcategoryfront.vo.ProductCategoryFrontQueryModel;
import com.aebiz.b2b2c.product.productcategoryfront.vo.SelectAttributeJson;
import com.aebiz.b2b2c.product.productcategoryrel.service.ProductCategoryRelService;
import com.aebiz.b2b2c.product.productcatetemprel.service.ProductCateTempRelService;
import com.aebiz.b2b2c.product.producttemplate.service.ProductTemplateService;
import com.aebiz.b2b2c.product.producttemplateattr.service.ProductTemplateAttrService;
import com.aebiz.b2b2c.product.producttemplateattr.vo.ProductTemplateAttrModel;
import com.aebiz.b2b2c.product.producttemplateattrrel.service.ProductTemplateAttrRelService;
import com.aebiz.b2b2c.product.sysback.web.productcategoryfront.vo.AttributeAndValueWebModel;
import com.aebiz.b2b2c.product.sysback.web.productcategoryfront.vo.AttributeValueWebModel;

@Service
@Transactional
public class ProductCategoryFrontServiceImpl
		extends
		BaseServiceImpl<ProductCategoryFrontModel, ProductCategoryFrontQueryModel>
		implements ProductCategoryFrontService {
	// fullpath长度
	public static final int FULLPATH_LENGTH = 33;
	
	private ProductCategoryFrontDAO myDao = null;

	@Autowired
	private UuidService us;

	@Autowired
	private ProductCategoryRelService productCategoryRelService;

	@Autowired
	private ProductCateTempRelService tempRelService;

	@Autowired
	private ProductTemplateService tempalteService;

	@Autowired
	private ProductTemplateAttrService attributeService;

	@Autowired
	private ProductTemplateAttrRelService templateAndAttrRelService;


	@Autowired
	private FrontProductCateTempRelService frontCategoryAttributeRelService;
	
	@Autowired
	private FrontPlatformCategoryLogService categoryLogService;

	@Autowired
	public void setMyDao(ProductCategoryFrontDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(ProductCategoryFrontModel m) {
		m.setUuid(us.getNextUuid());
		m.setCategoryNo(us.getNextUuid("Pcfront"));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		// 保存分类价格属性
		List<String> categoryPrices = m.getCategoryPrice();
		/*if (categoryPrices != null && categoryPrices.size() > 0) {
			int n = 1;
			for (String price : categoryPrices) {
				FrontProductCategoryPriceModel priceModel = new FrontProductCategoryPriceModel();
				priceModel.setCategoryUuid(m.getUuid());
				priceModel.setPriceRange(price);
				priceModel.setPosition(n);
				this.frontProductCategoryPriceService.create(priceModel);
				++n;
			}
		}*/

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(ProductCategoryFrontModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		// 更新分类价格属性、需要先删除原有的关联关系
		/*this.frontProductCategoryPriceService.deleteByCateUuid(m.getUuid());
		List<String> categoryPrices = m.getCategoryPrice();
		if (categoryPrices != null && categoryPrices.size() > 0) {
			int n = 1;
			for (String price : categoryPrices) {
				FrontProductCategoryPriceModel priceModel = new FrontProductCategoryPriceModel();
				priceModel.setCategoryUuid(m.getUuid());
				priceModel.setPriceRange(price);
				priceModel.setPosition(n);
				this.frontProductCategoryPriceService.create(priceModel);
				++n;
			}
		}
*/
		super.update(m);
	}

	@Override
	public void delete(ProductCategoryFrontModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}
	
	/**
	 * 根据前台父分类uuid查询子分类 该功能用于分类树展开时查询子分类
	 * 
	 * @param parentUuid
	 *            父分类uuid
	 * @return
	 */
	public List<ProductCategoryFrontModel> getSubProductCategoryByParentUuid(
			String parentUuid) {
		return myDao.getSubProductCategoryByParentUuid(parentUuid);
	}

	/**
	 * 批量添加分类
	 * 
	 * @param list
	 */
	public void addBatch(List<ProductCategoryFrontModel> list) {
		if (list == null || list.size() == 0) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			ProductCategoryFrontModel m = list.get(i);
			this.create(m);
		}
	}

	/**
	 * 删除分类包括其子所有分类
	 */
	@Override
	public void deletes(List<String> needDeleteUuids) {
		myDao.deletes(needDeleteUuids);
		for (int i = 0; i < needDeleteUuids.size(); i++) {
			String uuid = needDeleteUuids.get(i);
			List<String> subUuids = this
					.getSubProductCategoryUuidByParentUuid(uuid);
			if (subUuids == null || subUuids.size() == 0) {// 没有子分类
				return;
			}
			deletes(subUuids);
			
		}
	}

	/**
	 * 根据前台父分类uuid查询子分类uuid
	 * 
	 * @param parentUuid
	 *            父分类uuid
	 * @return
	 */
	public List<String> getSubProductCategoryUuidByParentUuid(String parentUuid) {
		return myDao.getSubProductCategoryUuidByParentUuid(parentUuid);
	}

	/**
	 * 前台分类关联属性时，可选属性列表
	 * 
	 * @param frontCategoryUuid
	 * @param categoryPlatfromUuids
	 * @return
	 */
	public List<ProductTemplateAttrModel> canSelectAttributeList(
			String frontCategoryUuid, List<String> categoryPlatfromUuids) {
		List<String> templateUuids = this
				.getProductTempalteUuidsByFrontCategoryUuid(frontCategoryUuid,
						categoryPlatfromUuids);
		if (templateUuids != null && templateUuids.size() > 0) {
			//List<ProductTemplateAttrModel> attributeList = getProductTemplateAttrModel(templateUuids);
			
			List<ProductTemplateAttrModel> attributeList = new ArrayList<ProductTemplateAttrModel>();
			return attributeList;
		}
		return null;
	}

	/**
	 * 根据前台分类uuid 获取后台分类所关联的模板
	 * 
	 * @param frontCategoryUuid
	 * @return
	 */
	public List<String> getProductTempalteUuidsByFrontCategoryUuid(
			String frontCategoryUuid, List<String> categoryPlatfromUuids) {
		List<String> productTemplateUuids = new ArrayList<String>();
		if (categoryPlatfromUuids != null && categoryPlatfromUuids.size() > 0) {
			for (String categoryPlatfromUuid : categoryPlatfromUuids) {
				List<String> templateUuids = tempRelService
						.getTemplateUuidsByCategoryUuid(categoryPlatfromUuid);
				if (templateUuids == null || templateUuids.size() == 0) {
					continue;
				}
				productTemplateUuids.addAll(templateUuids);
			}
		}

		return productTemplateUuids;
	}

	
	/**
	 * 获取页面展示的属性和属性值，并把数据库中有的选中
	 * 
	 * @param attributes
	 *            数据库中有的属性
	 * @param frontCategoryUuid
	 *            前台分类uuid
	 * @return
	 */
	public List<AttributeAndValueWebModel> buildAttributeWebList(
			List<ProductTemplateAttrModel> attributes, String frontCategoryUuid) {
		List<SelectAttributeJson> selectAttributes = frontCategoryAttributeRelService
				.getAttrJsonByCategoryUuid(frontCategoryUuid);
		Map<String, AttributeAndValueWebModel> webMap = new HashMap<String, AttributeAndValueWebModel>();

		for (SelectAttributeJson selectAttributeJson : selectAttributes) {
			AttributeAndValueWebModel attributeAndValue = new AttributeAndValueWebModel();
			attributeAndValue.setAttributeEnName(selectAttributeJson
					.getAttributeEnName());
			attributeAndValue.setAttributeUuid(selectAttributeJson
					.getAttributeUuid());
			attributeAndValue.setAttributeName(selectAttributeJson
					.getAttributeName());
			List<AttributeValueWebModel> valueList = new ArrayList<AttributeValueWebModel>();
			attributeAndValue.setSelect("checked");
			attributeAndValue.setType(selectAttributeJson.getType());
			List<String> selectValueNames = selectAttributeJson
					.getSelectValue();
			if (selectValueNames != null && selectValueNames.size() > 0) {
				for (String selectValueName : selectValueNames) {
					AttributeValueWebModel valueWeb = new AttributeValueWebModel();
					valueWeb.setValueName(selectValueName);
					valueWeb.setSelect("checked");
					valueList.add(valueWeb);
				}
			}
			attributeAndValue.setValueList(valueList);
			attributeAndValue.setUnit(selectAttributeJson.getUnit());
			attributeAndValue.setPosition(selectAttributeJson.getPosition());
			webMap.put(attributeAndValue.getAttributeUuid(), attributeAndValue);
		}

		List<AttributeAndValueWebModel> attributeAndValues = new ArrayList<AttributeAndValueWebModel>();
		if (attributes != null && attributes.size() > 0) {
			for (ProductTemplateAttrModel productTemplateAttrModel : attributes) {
				AttributeAndValueWebModel attributeAndValue = webMap
						.get(productTemplateAttrModel.getUuid());
				if (attributeAndValue != null) {
					/*List<String> valueUuids = attributeAndValueRelService
							.getValueUuidsByUuid(productTemplateAttrModel
									.getUuid());
					List<String> valueNames = attrValueService
							.getValueNamesByUuids(valueUuids);*/
					
					List<String> valueUuids = new ArrayList<String>();
					List<String> valueNames = new ArrayList<String>();

					// 如果是区间值，则不需要基础数据库中关联的属性值
					if (attributeAndValue.YES.equals(attributeAndValue
							.getType())) {
						List<AttributeValueWebModel> valueList = convertValueWebModel(valueNames);
						attributeAndValue.setYesValueList(attributeAndValue
								.getValueList());
						attributeAndValue.setValueList(valueList);
					} else {
						List<AttributeValueWebModel> valueList = connectValue(
								valueNames, attributeAndValue.getValueList());
						attributeAndValue.setValueList(valueList);
					}
				} else {
					attributeAndValue = new AttributeAndValueWebModel();
					attributeAndValue
							.setAttributeEnName(productTemplateAttrModel
									.getEngName());
					attributeAndValue.setAttributeUuid(productTemplateAttrModel
							.getUuid());
					//attributeAndValue.setUnit(productTemplateAttrModel.getUnit());
					attributeAndValue.setAttributeName(productTemplateAttrModel
							.getAttributeName());

					/*List<String> valueUuids = attributeAndValueRelService
							.getValueUuidsByUuid(productTemplateAttrModel
									.getUuid());
					List<String> valueNames = attrValueService
							.getValueNamesByUuids(valueUuids);*/
					
					List<String> valueUuids = new ArrayList<String>();
					List<String> valueNames = new ArrayList<String>();

					if (valueNames != null && valueNames.size() > 0) {
						List<AttributeValueWebModel> valueList = new ArrayList<AttributeValueWebModel>();
						for (String valueName : valueNames) {
							AttributeValueWebModel valueWeb = new AttributeValueWebModel();
							valueWeb.setValueName(valueName);
							// TODO 是否选中

							valueList.add(valueWeb);
						}
						attributeAndValue.setValueList(valueList);
					}
				}

				attributeAndValues.add(attributeAndValue);
			}
		}

		return attributeAndValues;
	}

	/**
	 * 把属性值集合 封装到webmodel 里
	 * 
	 * @param tempList
	 * @return
	 */
	private List<AttributeValueWebModel> convertValueWebModel(
			List<String> tempList) {
		List<AttributeValueWebModel> valueWebList = new ArrayList<AttributeValueWebModel>();
		for (String valueName : tempList) {
			AttributeValueWebModel valueWeb = new AttributeValueWebModel();
			valueWeb.setValueName(valueName);
			valueWebList.add(valueWeb);
		}

		return valueWebList;
	}

	/**
	 * 合并相同属性里的属性值
	 * 
	 * @param oneValues
	 *            第一个属性里的属性值
	 * @param twoValues
	 *            第二个属性里的属性值
	 * @return
	 */
	private List<AttributeValueWebModel> connectValue(List<String> oneValues,
			List<AttributeValueWebModel> webValues) {
		List<AttributeValueWebModel> valueWebList = new ArrayList<AttributeValueWebModel>();
		if (oneValues == null || oneValues.size() == 0) {
			return webValues;
		}

		List<String> twoList = new ArrayList<String>();
		if (webValues != null && webValues.size() > 0) {
			for (AttributeValueWebModel web : webValues) {
				twoList.add(web.getValueName());
			}
		}

		for (String oneValue : oneValues) {
			AttributeValueWebModel web = new AttributeValueWebModel();
			web.setValueName(oneValue);
			if (twoList.contains(oneValue)) {
				web.setSelect("checked");
			}
			valueWebList.add(web);
		}

		for (AttributeValueWebModel webValue : webValues) {
			if (!oneValues.contains(webValue.getValueName())) {
				valueWebList.add(webValue);
			}
		}

		return valueWebList;
	}

	/**
	 * 根据前台父分类uuid查询子分类 该功能用于分类树展开时查询子分类
	 * 
	 * @param parentUuid
	 *            父分类uuid
	 * @return
	 */
	public List<ProductCategoryFrontModel> getFrontCategoryByUuids(
			List<String> uuids) {
		return myDao.getFrontCategoryByUuids(uuids);
	}
	
	/**
	 * 根据fullpath 获取其所有子分类uuid集合（包括本身）
	 * @param fullPath
	 * @return
	 */
	public List<String> getUuidsByFullPath(String fullPath){
		return myDao.getUuidsByFullPath(fullPath);
	}
	
	/**
	 * 根据fullpath查询分类集合
	 * @param fullPath
	 * @return
	 */
	public List<ProductCategoryFrontModel> getProductCategoryFrontModelByFullPath(String fullPath){
		return myDao.getProductCategoryFrontModelByFullPath(fullPath);
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

		List list = myDao.getProductCategoryFrontModelByFullPath(sheetId);
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
	 * 获取结束时的长路径
	 * 
	 * @param fullPath
	 * @return
	 */
	public String getEndFullPath(String fullPath) {
		if (!StringUtil.isEmpty(fullPath)) {
			int length = fullPath.length();
			int curPosition = 0;
			char[] charArray = fullPath.toCharArray();
			for (int i = charArray.length - 1; i >= 0; i--) {
				curPosition++;
				if (charArray[i] != '0') {
					break;
				}
			}

			String parentPex = fullPath.substring(0, length - curPosition + 1);
			for (int i = 0; i < curPosition - 1; i++) {
				parentPex = parentPex + "9";
			}
			return parentPex;
		}
		return "";
	}
	
	/**
	 * 更新前台分类
	 * @param m
	 * @param isAddLog
	 * 		true 表示添加log日志
	 */
	public void updateCategory(ProductCategoryFrontModel m,boolean isAddLog){
		update(m);
		if(isAddLog){// 修改了名称就要对分类添加日志，以便搜索时对该分类所关联的商品进行更新
			List<String> platfromCategoryUuids=productCategoryRelService.getPlatfromCategoryUuidsByFrontCategoryUuid(m.getUuid());
			categoryLogService.bathAddLog(m.getUuid(), platfromCategoryUuids, FrontPlatformCategoryLogModel.UPDATE);
		}
		
	}
}