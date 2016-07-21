package com.aebiz.b2b2c.visitprecept.visitpreceptextend.vo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.visitprecept.visitprecept.service.VisitPreceptService;

/**
 * 随访方案扩展字段
 * @author szr
 *
 */
@Entity
@Table(name = "visit_precept_extend")
@Component      //加上他才能加入	@Transient
public class VisitPreceptExtendModel extends BaseModel {
	
	/*随访方案service*/
	@Transient
	private static VisitPreceptService visitPreceptService;
	@Autowired
	public  void setVisitPreceptService(VisitPreceptService visitPreceptService) {
		this.visitPreceptService=visitPreceptService;
	}
	/* 关联随访方案字段*/
	private String preceptUuid;
	/* 类型 */
	private String type;
	/* 名称 */
	private String name;
	/* 结果 */
	private String result;
	/* 周期 */
	private String period;
	
	/* 方案名称*/
	@Transient      //  加上这个注解貌似不会为这个字段建立数据库中的列
	private String preceptName;

	public String getPreceptName() {
		if (!StringUtil.isEmpty(this.preceptUuid)) {
			return visitPreceptService.getPreceptNameByUUid(preceptUuid);
		}
		return preceptName;
	}

	public void setPreceptName(String preceptName) {
		this.preceptName = preceptName;
	}

	public void setPreceptUuid(String obj) {
		this.preceptUuid = obj;
	}

	public String getPreceptUuid() {
		return this.preceptUuid;
	}

	public void setType(String obj) {
		this.type = obj;
	}

	public String getType() {
		return this.type;
	}

	public void setName(String obj) {
		this.name = obj;
	}

	public String getName() {
		return this.name;
	}

	public void setResult(String obj) {
		this.result = obj;
	}

	public String getResult() {
		return this.result;
	}

	public void setPeriod(String obj) {
		this.period = obj;
	}

	public String getPeriod() {
		return this.period;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[preceptUuid=" + this.getPreceptUuid() + ",type="
				+ this.getType() + ",name=" + this.getName() + ",result="
				+ this.getResult() + ",period=" + this.getPeriod() + ",]";
	}
}
