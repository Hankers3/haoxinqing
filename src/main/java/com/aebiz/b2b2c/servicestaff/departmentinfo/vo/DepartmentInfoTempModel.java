package com.aebiz.b2b2c.servicestaff.departmentinfo.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.service.WebsiteOperateInteractive;

/**
 * 科室临时表
 * @author xp
 *
 */
@Component
public class DepartmentInfoTempModel extends BaseModel {
	
	public static final String STOREUUID_DEFAULT = "4f41fb4edb4b485cac3b391112a9c2cf";
	
	
	private static WebsiteOperateInteractive websiteService;
	
	@Autowired
	public void WebsiteOperateInteractive(WebsiteOperateInteractive ws) {
		websiteService = ws;
	}
	
	/****************DepartmentInfoTempModel*********************/
	/*科室ID*/
        private String departmentId;
        /*科室名称*/
        private String departmentName;
        /*科室电话*/
        private String mobile;
        /*备注*/
        private String note;
        

        public String getDepartmentId() {
            return departmentId;
        }
        public void setDepartmentId(String departmentId) {
            this.departmentId = departmentId;
        }
        public String getDepartmentName() {
            return departmentName;
        }
        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }
        public String getMobile() {
            return mobile;
        }
        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
        public String getNote() {
            return note;
        }
        public void setNote(String note) {
            this.note = note;
        }

       
        

}
	
	