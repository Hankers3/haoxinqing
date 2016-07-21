package com.aebiz.b2b2c.product.productpub.vo;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.product.productmain.vo.ProductMainModel;
import com.aebiz.b2b2c.product.productmaindescription.vo.ProductMainDescriptionModel;

/**
 * @author huangpinpin
 *
 */
public class ProductModel extends BaseModel{
	
	/* 计费方式-平米 */
	public static final String CHARGETYPE_SQUARE= "1";

	/* 计费方式-件数 */
	public static final String CHARGETYPE_NUMBER = "0";
	
	/* 发布范围-APP*/
	public static final String SCOPE_APP = "01";

	/* 发布范围-WED */
	public static final String SCOPE_WEB = "02";
	
	/* 商品主信息*/
	private ProductMainModel productMain;
	/* 药品描述*/
	private ProductMainDescriptionModel productDescription =new ProductMainDescriptionModel();
	
        public ProductMainDescriptionModel getProductDescription() {
            return productDescription;
        }
    
        public void setProductDescription(ProductMainDescriptionModel productDescription) {
            this.productDescription = productDescription;
        }

        public ProductMainModel getProductMain() {
    		return productMain;
	}

	public void setProductMain(ProductMainModel productMain) {
		this.productMain = productMain;
	}

}

