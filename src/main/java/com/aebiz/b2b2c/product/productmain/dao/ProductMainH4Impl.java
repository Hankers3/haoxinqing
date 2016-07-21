package com.aebiz.b2b2c.product.productmain.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainQueryModel;
import com.aebiz.b2b2c.product.utils.ExcelUtils;

/**
 * 
 * 商品主表业务数据操作实现
 * 
 * @author huangpinpin
 * 
 */
@Repository
public class ProductMainH4Impl extends
		BaseH4Impl<ProductMainModel, ProductMainQueryModel> implements
		ProductMainDAO {

	/**
	 * 设置商品上下架,根据所选productUuid集合
	 * 
	 * @param state
	 *            上下架状态
	 * @param productUuids
	 *            商品productUuid集合
	 * 
	 */
	public void updateState(String state, List<String> productUuids) {
		if (productUuids == null || productUuids.size() == 0) {
			return;
		}

		String hql = "update ProductMainModel o set o.state=:state where o.uuid in (:productUuids)";
		Map<String, Object> mapParams = new HashMap();
		mapParams.put("state", state);
		mapParams.put("productUuids", productUuids.toArray());

		exeUpdate(hql, mapParams);
	}

	/**
	 * 设置商品上下架,根据所选productUuid集合
	 * 
	 * @param state
	 *            上下架状态
	 * @param productUuids
	 *            商品productUuid集合
	 * 
	 */
	public void updateStateByProductUuid(String state, String productUuid) {
		if (StringUtil.isEmpty(productUuid)) {
			return;
		}

		String hql = "update ProductMainModel o set o.state=:state where o.uuid = :productUuid";
		Map<String, Object> mapParams = new HashMap();
		mapParams.put("state", state);
		mapParams.put("productUuid", productUuid);

		exeUpdate(hql, mapParams);
	}

	/**
	 * 根据商品uuid 查询商品主信息
	 * 
	 * @param productUuid
	 * @return
	 */
	public ProductMainModel getProductMainModelByProductUuid(String productUuid) {
		if (StringUtil.isEmpty(productUuid)) {
			return null;
		}
		StringBuffer hql = new StringBuffer(
				"select o from ProductMainModel o where o.uuid=:productUuid ");
		Query q = getH4Session().createQuery(hql.toString());
		q.setString("productUuid", productUuid);

		Object obj = q.uniqueResult();
		if (obj != null) {
			return (ProductMainModel) obj;
		}
		return null;
	}

	/**
	 * 根据商品uuid修改审核状态
	 * 
	 * @param uuid
	 */
	public void updateAuditStateByUuid(String uuid, String state) {
		if (StringUtil.isEmpty(uuid)) {
			return;
		}

		String hql = "update ProductMainModel o set o.auditState=:state where o.uuid =:uuid ";
		Map<String, Object> mapParams = new HashMap();
		mapParams.put("state", state);
		mapParams.put("uuid", uuid);

		exeUpdate(hql, mapParams);
	}

	/**
	 * 根据商品编号来判断是否存在
	 * 
	 * @param productNo
	 * @return
	 */
	public boolean checkProductNoExist(String productNo) {
		StringBuffer hql = new StringBuffer(
				"from ProductMainModel where productNo=:productNo");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("productNo", productNo);

		List<ProductMainModel> list = query.list();
		if (list != null && list.size() > 0) {
			return true;
		}

		return false;
	}

	/**
	 * 根据商品编号来获取商品名称
	 * 
	 * @param productUuid
	 * @return
	 */
	public String getProductNameByUuid(String productUuid) {
		StringBuffer hql = new StringBuffer(
				"select  pc.productName  from ProductMainModel pc where pc.uuid=:productUuid");

		Query query = this.getH4Session().createQuery(hql.toString());
		query.setString("productUuid", productUuid);

		List list = query.list();
		if (list.size() > 0) {
			return (String) list.get(0);
		} else {
			return "";
		}

	}

	/**
	 * @param myfiles
	 */
	@Override
	public List<Object> updloadExcel(MultipartFile[] myfiles) {
		if (myfiles != null && myfiles.length > 0) {
			for (MultipartFile file : myfiles) {
				if (file != null && !file.isEmpty()) {
					ProductMainModel model = new ProductMainModel();
					Object[] columns = { "productType", "productName",
							"productEnglishName", "commonremedy",
							"mechanismAction", "laboratorExamination",
							"direction", "dosage", "drugReaction",
							"mildDrugReaction", "dangerousDrugReaction",
							"forbidden", "attention", "renalFunctionDamage",
							"liverFunctionDamage", "cardiacDysfunction",
							"oldPeople", "youngsters", "conception", "suckle",
							"drugInteractio", "overDose", "targets", "longRun",
							"addiction", "stopMedicine", "pharmacokinetics" };
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

	@Override
	public List<ProductMainModel> getByProductName(String productName) {
		StringBuffer hql = new StringBuffer(
				" from ProductMainModel pc where 1=1 ");
		if (!StringUtil.isEmpty(productName)) {
			hql.append(" and pc.productName like :productName ");
		}
		Query query = this.getH4Session().createQuery(hql.toString());
		if (!StringUtil.isEmpty(productName)) {
			query.setString("productName", "%" + productName + "%");
		}
		query.setFirstResult(0);
		query.setMaxResults(10);
		List list = query.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}

}
