package com.hxq.mobile.entity.weixin;

import com.wxcommon.repository.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Digger on 2015/11/24.
 */

/**
 * @author Digger
 *
 */
public class CsZySubject extends AbstractEntity<String> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @Column(columnDefinition="INTEGER")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public String id;                // 记录ID，由应用维护，不允许值为0
    public String title;             //标题
    @Column(name="parent_id")
    public String parentId;          //所属主题，子栏目的上级栏目，顶层栏目值为0
    public String digest;            //摘要描述，普通文字描述
    public String description;       //答题说明，html文本
    public int integral;             //用户答完题后得到的积分
    public String cover;             //封面图片，从菜单进入的图片
    public String media;             //文章图片的请求路径
    public String thumb;             //缩略图的请求路径，当上传文章图片时，系统自动生成缩略图
    public String url;               //正式发布后系统自动填写该字段，供其他模块引用

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String self;
    public int sort;                 //同一层级的显示顺序，从低到高依次显示。当值小于0（-1）时视为逻辑删除
    public int total;
    @Column(name="create_date")
    public Date createDate;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
    
}
