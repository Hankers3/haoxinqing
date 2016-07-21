package com.aebiz.b2b2c.websiteoperation.websiteoperate.vo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.websiteoperation.websiteoperate.service.WebsiteOperateService;

@Entity
@Table(name = "website_operate")
@Component
public class WebsiteOperateModel extends BaseModel {
	@Transient
	private static WebsiteOperateService websiteOperateService;

	/* 注入运营service */
	@Autowired
	public void setWebsiteOperateService(
			WebsiteOperateService websiteOperateService) {
		this.websiteOperateService = websiteOperateService;
	}

	/* 模块名称,比如商品系统、订单系统 */
	private String moduleName = "";

	/* 模块页面展示数据 */
	@Transient
	private String moduleShowName = "";

	/* 中文名称 */
	private String resourceName = "";

	/* 编号，必须唯一，不能重复,只能是英文或者数字或者. */
	private String resourceKey = "";

	/* 参数具体的值 */
	private String paramValue = "";

	/* 参数值展示数据 */
	@Transient
	private String paramValueShowName = "";

	/* 参数类型,需要在编辑时展示给客户的类型 */
	private String paramType = "";

	/* 参数类型展示数据 */
	@Transient
	private String paramTypeShowName = "";

	/* 参数内容 */
	private String paramContent = "";

	/* 参数备注 */
	private String note = "";

	/* 排序 */
	private int position;

	public void setModuleName(String obj) {
		this.moduleName = obj;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	/* 通过moduleName值得到展示值moduleShowName */
	public String getModuleShowName() {
		return ModuleEnum.getValue(this.getModuleName());
	}

	public void setModuleShowName(String moduleShowName) {
		this.moduleShowName = moduleShowName;
	}

	public void setResourceName(String obj) {
		this.resourceName = obj;
	}

	public String getResourceName() {
		return this.resourceName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	/* 获取参数值展示数据 */
	public String getParamValueShowName() {
		if (this.getParamType().equals(ParamTypeEnum.radio.getKey())) {
			List<DataTablesPageParam> showNameList = websiteOperateService
					.getParamValueShowNameByParamContent(paramContent);
			if (showNameList != null && showNameList.size() > 0) {
				for (DataTablesPageParam dtpp : showNameList) {
					if (String.valueOf(dtpp.getValue()).equals(
							this.getParamValue())) {
						return dtpp.getName();
					}
				}
			}
		}
		return this.getParamValue();
	}

	public void setParamValueShowName(String paramValueShowName) {
		this.paramValueShowName = paramValueShowName;
	}

	public void setParamContent(String obj) {
		this.paramContent = obj;
	}

	public String getParamContent() {
		return this.paramContent;
	}

	public void setNote(String obj) {
		this.note = obj;
	}

	public String getNote() {
		return this.note;
	}

	public void setParamType(String obj) {
		this.paramType = obj;
	}

	public String getParamType() {
		return this.paramType;
	}

	/* 通过paramType值得到paramTypeShowName */
	public String getParamTypeShowName() {
		return ParamTypeEnum.getValue(this.getParamType());
	}

	public void setParamTypeShowName(String paramTypeShowName) {
		this.paramTypeShowName = paramTypeShowName;
	}

	public String getResourceKey() {
		return resourceKey;
	}

	public void setResourceKey(String resourceKey) {
		this.resourceKey = resourceKey;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "WebsiteOperateModel [moduleName=" + moduleName
				+ ", moduleShowName=" + moduleShowName + ", resourceName="
				+ resourceName + ", resourceKey=" + resourceKey
				+ ", paramValue=" + paramValue + ", paramValueShowName="
				+ paramValueShowName + ", paramType=" + paramType
				+ ", paramTypeShowName=" + paramTypeShowName
				+ ", paramContent=" + paramContent + ", note=" + note
				+ ", position=" + position + "]";
	}

}
