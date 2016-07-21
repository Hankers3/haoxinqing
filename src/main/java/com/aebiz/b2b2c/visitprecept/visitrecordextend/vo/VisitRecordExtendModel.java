package com.aebiz.b2b2c.visitprecept.visitrecordextend.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
/**
 * 随访表单扩展表
 * @author wangbingning
 *
 */
@Entity
@Table(name="visit_record_extend")
public class VisitRecordExtendModel extends BaseModel{
	/*随访编号*/
	private String visitRecordUuid;
	/*类型1代表睡眠情况 2 代表进食情况 3 代表是其他情况 4 代表是其他的检查结果*/
	private String type;
	/*名称1代表睡眠情况 2 代表进食情况 3 代表是其他情况 4 代表是其他的检查结果*/
	private String name;
	/*描述*/
	private String result;
	/*周期*/
	private String period;
	/*状态描述 1代表 良好 2代表一般 3代表异常 4代表其他*/
	private String state;
	
	
	public String getState() {
        return state;
        }
        public void setState(String state) {
            this.state = state;
        }
        public void setVisitRecordUuid(String obj){
		this.visitRecordUuid = obj;
	}
	public String getVisitRecordUuid(){
		return this.visitRecordUuid;
	}
	
	public void setType(String obj){
		this.type = obj;
	}
	public String getType(){
		return this.type;
	}
	
	public void setName(String obj){
		this.name = obj;
	}
	public String getName(){
		return this.name;
	}
	
	public void setResult(String obj){
		this.result = obj;
	}
	public String getResult(){
		return this.result;
	}
	
	public void setPeriod(String obj){
		this.period = obj;
	}
	public String getPeriod(){
		return this.period;
	}
	
	
	
	public String toString(){
		return super.toString()+" , Model"+this.getClass().getName()+"[visitRecordUuid=" + this.getVisitRecordUuid() + ",type=" + this.getType() + ",name=" + this.getName() + ",result=" + this.getResult() + ",period=" + this.getPeriod() + ",]";
	}	
}
