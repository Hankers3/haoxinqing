package com.aebiz.b2b2c.userfront.platadimagerel.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.userfront.plattimagelibrary.service.PlattImageLibraryService;

/**
 * 平台广告与平台图片的关系<br>
 * 
 * 广告关联图片,不用管图片的效果,只需关联上图片即可,图片的效果由广告组件来做
 *
 * @author tangyunkai
 *
 * @date 2015年1月6日 下午8:32:58
 *
 */
@Entity
@Table(name = "plat_ad_image_rel")
@Component
public class PlatAdImageRelModel extends BaseModel {
	
	@Transient
	private static PlattImageLibraryService plattImageLibraryService;
	@Autowired
	public void setMyService(PlattImageLibraryService bs) {
		this.plattImageLibraryService = bs;
	}

	/* 选择的图片的名称 */
	@Transient
	private String imageName;

	public String getImageName() {
		String imageName="";
		if(this.imageUuid != null){
			imageName = plattImageLibraryService.getByUuid(this.getImageUuid()).getImageName();
		}
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	/* 广告uuid */
	private String adUuid = "";

	/* 图片uuid */
	private String imageUuid = "";

	/* 图片的地址 */
	private String imageUrl = "";

	/* 图片位置 */
	private int position;

	/* 图片的链接地址 */
	private String url = "";
	
	/* 图片的描述 */
	private String imageNote = "";
	
	/* 图片的描述 */
	@Transient
	private String[] imageNotes;

	/* 选择的图片的uuid */
	@Transient
	private String[] uuids;

	/* 选择的图片填入的链接地址 */
	@Transient
	private String[] urls;
	
	
	
        public String getImageNote() {
        return imageNote;
        }
        public void setImageNote(String imageNote) {
        this.imageNote = imageNote;
        }
        public String[] getImageNotes() {
        return imageNotes;
        }
        public void setImageNotes(String[] imageNotes) {
        this.imageNotes = imageNotes;
        }
        public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setAdUuid(String obj) {
		this.adUuid = obj;
	}
	public String getAdUuid() {
		return this.adUuid;
	}

	public void setImageUuid(String obj) {
		this.imageUuid = obj;
	}
	public String getImageUuid() {
		return this.imageUuid;
	}

	public void setPosition(int obj) {
		this.position = obj;
	}
	public int getPosition() {
		return this.position;
	}

	public String[] getUuids() {
		return uuids;
	}
	public void setUuids(String[] uuids) {
		this.uuids = uuids;
	}
	public String[] getUrls() {
		return urls;
	}
	public void setUrls(String[] urls) {
		this.urls = urls;
	}
	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[adUuid=" + this.getAdUuid() + ",imageUuid="
				+ this.getImageUuid() + ",position=" + this.getPosition()
				+ ",]";
	}
}
