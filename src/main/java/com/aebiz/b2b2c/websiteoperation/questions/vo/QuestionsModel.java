package com.aebiz.b2b2c.websiteoperation.questions.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.filemgr.service.FileService;
import com.aebiz.b2b2c.baseframework.filemgr.vo.FileModel;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.websiteoperation.options.service.OptionsService;
import com.aebiz.b2b2c.websiteoperation.options.vo.OptionsModel;
import com.aebiz.b2b2c.websiteoperation.quizcategory.service.QuizCategoryService;

@Entity
@Table(name = "questions")
@Component
public class QuestionsModel extends BaseModel {

	/* 试题是否启用：1表示启用 */
	public static final String STARTUSING = "1";

	/* 试题是否启用：0表示未启用 */
	public static final String ENDUSING = "0";

	@Transient
	private static OptionsService optionsService;

	@Autowired
	public void setOptionsService(OptionsService optionsService) {
		this.optionsService = optionsService;
	}

	/*注入测试题分类*/
	@Transient
	private static QuizCategoryService quizCategoryService;
	
	@Autowired
	public void setQuizCategoryService(QuizCategoryService quizCategoryService) {
		this.quizCategoryService = quizCategoryService;
	}
	
	/*注解文件service*/
	@Transient
	private static FileService fileService;
	
	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	
	/* 测试题编号*/
	private String quizUuid = "";
	
	/* 试题题目*/
	private String title = "";

	/* 是否发布 */
	private String state = "";

	/* 测试分类 */
	private String quizCategoryUuid = "";
	
	/* 导航图 */
	private String image ;
	
	/* 发布时间 */
	private String createTime = "";
	
	/* 备注 */
	private String remark = "";
	
	/* （用不到）试题状态 */
	private String questionType = "";
	
	/* 测试分类名 */
	@Transient
	private String categoryName;
	/* 试题位置 */
	@Transient
	private int position = 0;

	/* 试题选项集合 */
	@Transient
	private List<OptionsModel> optionsModels = new ArrayList<OptionsModel>();
	
	/* 图片上传路径 */
	@Transient
	private String imageUrl;
	
	
	public String getImageUrl() {
		if (StringUtil.isEmpty(this.image)) {
			return "";
		}
		FileModel file = fileService.getOneFileModel(this.image);
		if (file != null) {
			return file.getRemotePaths();
		} else {
			return this.getImage();
		}
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategoryName() {
		if(!StringUtil.isEmpty(quizCategoryUuid)){
			return quizCategoryService.getCategoryName(quizCategoryUuid);
		}
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getQuizCategoryUuid() {
		return quizCategoryUuid;
	}

	public void setQuizCategoryUuid(String quizCategoryUuid) {
		this.quizCategoryUuid = quizCategoryUuid;
	}
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<OptionsModel> getOptionsModels() {
		return optionsService.getOptionsByQuestionUuid(this.getUuid());
	}

	public void setTitle(String obj) {
		this.title = obj;
	}

	public String getTitle() {
		return this.title;
	}

	public void setState(String obj) {
		this.state = obj;
	}

	public String getState() {
		return this.state;
	}

	public void setQuestionType(String obj) {
		this.questionType = obj;
	}

	public String getQuestionType() {
		return this.questionType;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String toString() {
		return super.toString() + " , Model" + this.getClass().getName()
				+ "[title=" + this.getTitle() + ",state=" + this.getState()
				+ ",questionType=" + this.getQuestionType() + ",]";
	}
}
