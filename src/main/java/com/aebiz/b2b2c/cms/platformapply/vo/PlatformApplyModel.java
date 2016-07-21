package com.aebiz.b2b2c.cms.platformapply.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.cms.platforminfo.service.PlatFormInfoService;
import com.aebiz.b2b2c.cms.platforminfo.vo.PlatFormInfoModel;
/**
 * 视频报名表
 * @author szr
 *
 */
@Entity
@Table(name="platform_apply")
@Component
public class PlatformApplyModel extends BaseModel{
    /* 注入视频管理的service */
    @Transient
    private static PlatFormInfoService platFormInfoService;

    @Autowired
    public void setPlatFormInfoService(PlatFormInfoService platFormInfoService) {
            this.platFormInfoService = platFormInfoService;
    }
    
	/* 视频编号 */
	private String vidoUuid;
	/* 视频名称 */
	private String vidoName;
	/* 报名人编号 */
	private String userUuid;
	/* 报名人人名称 */
	private String userName;
	/* 类型   1表示医生 2表示患者*/ 
	private String userType;
	/* 报名时间 */
	private String createTime;
	/* 备注 */
	private String remark;
	/* 报名时间 */
	@Transient
    private String startTime;
	
	public String getStartTime() {
	    if(!StringUtil.isEmpty(vidoUuid)){
	        return platFormInfoService.getStartTimeByVidoUuid(vidoUuid);
	    }
           return startTime;
    }
	
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public void setVidoUuid(String obj){
		this.vidoUuid = obj;
	}
	public String getVidoUuid(){
		return this.vidoUuid;
	}
	
	public void setVidoName(String obj){
		this.vidoName = obj;
	}
	public String getVidoName(){
		return this.vidoName;
	}
	
	public void setUserUuid(String obj){
		this.userUuid = obj;
	}
	public String getUserUuid(){
		return this.userUuid;
	}
	
	public void setUserName(String obj){
		this.userName = obj;
	}
	public String getUserName(){
		return this.userName;
	}
	
	public void setUserType(String obj){
		this.userType = obj;
	}
	public String getUserType(){
		return this.userType;
	}
	
	public void setCreateTime(String obj){
		this.createTime = obj;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	
	public void setRemark(String obj){
		this.remark = obj;
	}
	public String getRemark(){
		return this.remark;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[vidoUuid=" + this.getVidoUuid() + ",vidoName=" + this.getVidoName() + ",userUuid=" + this.getUserUuid() + ",userName=" + this.getUserName() + ",userType=" + this.getUserType() + ",createTime=" + this.getCreateTime() + ",remark=" + this.getRemark() + ",]";
	}	
}
