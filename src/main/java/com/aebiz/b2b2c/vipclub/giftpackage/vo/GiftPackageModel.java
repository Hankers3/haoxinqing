package com.aebiz.b2b2c.vipclub.giftpackage.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.message.MessageHelper;

/**
 * 会员大礼包
 * 
 * @author cj
 *
 */
@Entity
@Table(name="gift_package")
@Component
public class GiftPackageModel extends BaseModel{
	@Transient
	private static FileService fileService;
	
	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	
	/* 奖品类型 1积分  2优惠券  可以复选 用分号隔开*/
	public static final String PRIZE_TYPE_INTEGRAL = "1";
	public static final String PRIZE_TYPE_COUPON = "2";
	
	/* 礼包名称 */
	private String packageName = "";
	
	/* 礼包数量 */
	private int packageCount = 0;
	
	/* 积分数量 */
	private int integralCount = 0;
	
	/* 礼包图片 */
	private String packageImage = "";
	
	/* 奖品类型 1积分  2优惠券  可以复选 用分号隔开*/
	private String prizeType = "";
	
	/* 备注 */
	private String note = "";
	
	/* 商户名称 */
	private String storeUuid = "";
	
	/* 礼包图片路径*/
	@Transient
	private String packageImageUrl = "";
	
	/* 奖品类型数据转换 */
	@Transient
	String[] prizeTypeS = null;
	
	/* 奖品类型名称 */
	@Transient
	String prizeTypeName = "";
	
	/* 奖品类型中是否有优惠券 */
	@Transient
	String prizeTypeCoupon = "";
	
	public void setPackageName(String obj){
		this.packageName = obj;
	}
	public String getPackageName(){
		return this.packageName;
	}
	
	public void setPackageCount(int obj){
		this.packageCount = obj;
	}
	public int getPackageCount(){
		return this.packageCount;
	}
	
	public void setIntegralCount(int obj){
		this.integralCount = obj;
	}
	public int getIntegralCount(){
		return this.integralCount;
	}
	
	public void setPackageImage(String obj){
		this.packageImage = obj;
	}
	public String getPackageImage(){
		return this.packageImage;
	}
	public String getPrizeType() {
		return prizeType;
	}
	public void setPrizeType(String prizeType) {
		this.prizeType = prizeType;
	}
	public void setNote(String obj){
		this.note = obj;
	}
	public String getNote(){
		return this.note;
	}
	
	public void setStoreUuid(String obj){
		this.storeUuid = obj;
	}
	public String getStoreUuid(){
		return this.storeUuid;
	}
	
	public String[] getPrizeTypeS() {
		String[] obj = this.prizeType.split(",");
		return obj;
	}
	public void setPrizeTypeS(String[] prizeTypeS) {
		this.prizeTypeS = prizeTypeS;
	}
	
	public String getPrizeTypeName() {
		String prizeTypeNameS = "";
		if(this.getPrizeTypeS() != null && this.getPrizeTypeS().length>0){
			if(this.getPrizeTypeS()[0].equals(PRIZE_TYPE_INTEGRAL)){
				prizeTypeNameS = MessageHelper.getMessage("giftpackage.m.prizeTypeIntegral");
			}else if(this.getPrizeTypeS()[0].equals(PRIZE_TYPE_COUPON)){
				prizeTypeNameS = MessageHelper.getMessage("giftpackage.m.prizeTypeCoupon");
			}
		}
		if(this.getPrizeTypeS() != null && this.getPrizeTypeS().length>1){
			if(this.getPrizeTypeS()[1].equals(PRIZE_TYPE_INTEGRAL)){
				prizeTypeNameS += "、" + MessageHelper.getMessage("giftpackage.m.prizeTypeIntegral");
			}else if(this.getPrizeTypeS()[1].equals(PRIZE_TYPE_COUPON)){
				prizeTypeNameS += "、" + MessageHelper.getMessage("giftpackage.m.prizeTypeCoupon");
			}
		}
		return prizeTypeNameS;
	}
	public void setPrizeTypeName(String prizeTypeName) {
		this.prizeTypeName = prizeTypeName;
	}
	public String getPackageImageUrl() {
		if(!StringUtils.isEmpty(this.packageImage)){
			packageImageUrl = this.fileService.getOneFileUrl(this.packageImage);
			return packageImageUrl;
		}
		return "";

	}
	public void setPackageImageUrl(String packageImageUrl) {
		this.packageImageUrl = packageImageUrl;
	}
	public String getPrizeTypeCoupon() {
		String prizeTypeCouponS = "";
		if(this.getPrizeTypeS() != null && this.getPrizeTypeS().length==1){
			if(this.getPrizeTypeS()[0].equals(PRIZE_TYPE_COUPON)){
				prizeTypeCouponS = "1";
			}else{
				prizeTypeCouponS = "0";
			}
		}
		if(this.getPrizeTypeS() != null && this.getPrizeTypeS().length==2){
			if(this.getPrizeTypeS()[1].equals(PRIZE_TYPE_COUPON)){
				prizeTypeCouponS = "1";
			}else{
				prizeTypeCouponS = "0";
			}
		}
		return prizeTypeCouponS;
	}
	public void setPrizeTypeCoupon(String prizeTypeCoupon) {
		this.prizeTypeCoupon = prizeTypeCoupon;
	}
	
}
